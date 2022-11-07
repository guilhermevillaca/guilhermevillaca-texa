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
import br.com.guilhermevillaca.repository.FilmeRepository;
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

    @Autowired
    private FilmeRepository filmeRepository;

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
    void update() throws Exception {
        Filme filme = new Filme();
        filme.setYear("2024");
        filme.setProducers("Villaca Donin");
        filme.setTitle("Poderoso Chefão 5");
        filme.setStudios("Villaca");
        filme.setWinner("yes");
        filme = this.filmeRepository.save(filme);

        JSONObject json = new JSONObject();
        json.put("year", "2034");
        json.put("producers", "Villaca Donin Sidnei");
        json.put("title", "Poderoso Chefão 6");
        json.put("studios", "Villaca");
        json.put("winner", "yes");
        this.mockMvc.perform(MockMvcRequestBuilders.put("/filme/" + filme.getId())
                .contentType("application/json")
                .content(json.toString())
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void delete() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/filme/" + 1)
                .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
