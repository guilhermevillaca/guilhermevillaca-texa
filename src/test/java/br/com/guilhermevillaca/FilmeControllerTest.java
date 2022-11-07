/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.guilhermevillaca;

/**
 *
 * @author villaca
 */
import br.com.guilhermevillaca.controller.FilmeController;
import br.com.guilhermevillaca.model.Filme;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest

public class FilmeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private FilmeController filmeController;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(filmeController).build();
    }

    @Test
    public void testGETFilmeController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/filme")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void create() throws Exception {

        JSONObject json = new JSONObject();
        json.put("year", "2024");
        json.put("producers", "Villaca Donin");
        json.put("title", "Poderoso Chefão 5");
        json.put("studios", "Villaca");
        json.put("winner", "yes");        
        this.mockMvc.perform(MockMvcRequestBuilders.post("/filme")
                .contentType("application/json")
                .content(json.toString())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void update() {

    }

    @Test
    void delete() {

    }

}
