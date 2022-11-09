package br.com.guilhermevillaca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import br.com.guilhermevillaca.service.MovieService;

@SpringBootApplication
@RestController
public class GuilhermevillacaApplication {    
    
    @Autowired
    MovieService movieService;

    public static void main(String[] args) {
        SpringApplication.run(GuilhermevillacaApplication.class, args);
    }

    //adicionar filmes ao banco ao executar
    @Bean
    CommandLineRunner runner() {
        return args -> {            
            this.movieService.persisteCsv();
        };
    }   

}
