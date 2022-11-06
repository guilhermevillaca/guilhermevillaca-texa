
package br.com.guilhermevillaca.controller;

import br.com.guilhermevillaca.model.Filme;
import br.com.guilhermevillaca.repository.FilmeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author guilhermevillaca
 */
@RestController
public class FilmeController {
    
    @Autowired
    FilmeRepository filmeRepository;
    
    @RequestMapping(value ="/filme", method = RequestMethod.GET )
    public ResponseEntity<List<Filme>> get(){
        List<Filme> filmes = new ArrayList<Filme>();
        filmes = (List<Filme>) filmeRepository.findAll();
        return ResponseEntity.ok(filmes);
    }
    
    @RequestMapping(value = "/filme/{id}", method = RequestMethod.GET)
    public ResponseEntity<Filme> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Filme> filme = filmeRepository.findById(id);
        if(filme.isPresent())
            return new ResponseEntity<Filme>(filme.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    //post
    @RequestMapping(value = "/filme", method =  RequestMethod.POST)
    public Filme Post(@RequestBody Filme filme)
    {
        return filmeRepository.save(filme);
    }

    @RequestMapping(value = "/filme/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Filme> Put(@PathVariable(value = "id") long id,  @RequestBody Filme newFilme)
    {
        Optional<Filme> oldFilme = filmeRepository.findById(id);
        if(oldFilme.isPresent()){
            Filme filme = oldFilme.get();  
            filme.setTitle(newFilme.getTitle());
            filmeRepository.save(filme);
            return new ResponseEntity<Filme>(filme, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/filme/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Filme> filme = filmeRepository.findById(id);
        if(filme.isPresent()){
            filmeRepository.delete(filme.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
}
