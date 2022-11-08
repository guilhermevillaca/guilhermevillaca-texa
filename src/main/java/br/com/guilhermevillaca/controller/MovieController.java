package br.com.guilhermevillaca.controller;

import br.com.guilhermevillaca.utils.ProducerConverter;
import br.com.guilhermevillaca.utils.AwardInterval;
import br.com.guilhermevillaca.utils.AwardsResponse;
import br.com.guilhermevillaca.model.Movie;
import br.com.guilhermevillaca.service.ProducerService;
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
import br.com.guilhermevillaca.repository.MovieRepository;


/**
 *
 * @author guilhermevillaca
 */
@RestController
public class MovieController {
    
    @Autowired
    MovieRepository filmeRepository;
    
    @Autowired
    ProducerService producerService;
    
    @Autowired
    ProducerConverter producerConverter;
    
    @RequestMapping(value ="/filme", method = RequestMethod.GET )
    public ResponseEntity<List<Movie>> get(){
        List<Movie> filmes = new ArrayList<Movie>();
        filmes = (List<Movie>) filmeRepository.findAll();
        return ResponseEntity.ok(filmes);
    }
    
    @RequestMapping(value = "/filme/{id}", method = RequestMethod.GET)
    public ResponseEntity<Movie> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Movie> filme = filmeRepository.findById(id);
        if(filme.isPresent())
            return new ResponseEntity<Movie>(filme.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    //post
    @RequestMapping(value = "/filme", method =  RequestMethod.POST)
    public Movie Post(@RequestBody Movie filme)
    {
        return filmeRepository.save(filme);
    }

    @RequestMapping(value = "/filme/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Movie> Put(@PathVariable(value = "id") long id,  @RequestBody Movie newFilme)
    {
        Optional<Movie> oldFilme = filmeRepository.findById(id);
        if(oldFilme.isPresent()){
            Movie filme = oldFilme.get();  
            filme.setTitle(newFilme.getTitle());
            filmeRepository.save(filme);
            return new ResponseEntity<Movie>(filme, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/filme/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Movie> filme = filmeRepository.findById(id);
        if(filme.isPresent()){
            filmeRepository.delete(filme.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value ="/filme/maiorIntervalo", method = RequestMethod.GET )
    public ResponseEntity<AwardsResponse> maiorIntervalo() {
        AwardsResponse response = new AwardsResponse();
        AwardInterval awardIntervals = producerService.getIntervalAwards();
        producerConverter.updateMinAwardsRange(response.getMin(), awardIntervals);
        producerConverter.updateMaxAwardsRange(response.getMax(), awardIntervals);
        return ResponseEntity.ok(response);
    }
    
}
