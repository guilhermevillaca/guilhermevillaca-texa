package br.com.guilhermevillaca;

import aj.org.objectweb.asm.TypeReference;
import br.com.guilhermevillaca.model.Movie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.guilhermevillaca.repository.MovieRepository;

@SpringBootApplication
@RestController
public class GuilhermevillacaApplication {

    @Autowired
    MovieRepository filmeRepository;

    public static void main(String[] args) {
        SpringApplication.run(GuilhermevillacaApplication.class, args);
    }

    //adicionar filmes ao banco ao executar
    @Bean
    CommandLineRunner runner() {
        return args -> {
            List<Movie> filmes = new ArrayList<Movie>();

            InputStream is = TypeReference.class.getResourceAsStream("/csv/movielist.csv");
            //CSVFormat csvFormat = CSVFormat.DEFAULT;

            BufferedReader fileReader;
            try {
                fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.newFormat(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

                Iterable<CSVRecord> csvRecords = csvParser.getRecords();

                for (CSVRecord csvRecord : csvRecords) {
                    Movie filme = new Movie();
                    Map<String, String> csvMap = csvRecord.toMap();
                    filme.setYear(Long.valueOf(csvMap.get("year")));
                    filme.setTitle(csvMap.get("title"));
                    filme.setStudios(csvMap.get("studios"));
                    filme.setProducers(csvMap.get("producers"));
                    filme.setWinner(csvMap.get("winner"));
                    filmes.add(filme);
                    filmeRepository.save(filme);
                }

            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(GuilhermevillacaApplication.class.getName()).log(Level.SEVERE, null, ex);
            }

        };
    }

    //retorna lista de filmes
    @GetMapping("filmes")
    public ResponseEntity<List<Movie>> outroFilme() throws IOException {

        List<Movie> filmes = new ArrayList<Movie>();
        filmes = (List<Movie>) filmeRepository.findAll();
        return ResponseEntity.ok(filmes);

    }

}
