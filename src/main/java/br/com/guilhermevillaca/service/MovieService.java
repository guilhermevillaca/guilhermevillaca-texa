/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.guilhermevillaca.service;

import aj.org.objectweb.asm.TypeReference;
import br.com.guilhermevillaca.GuilhermevillacaApplication;
import br.com.guilhermevillaca.model.Movie;
import br.com.guilhermevillaca.repository.MovieRepository;
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
import org.springframework.stereotype.Service;

/**
 *
 * @author villaca
 */
@Service
public class MovieService {
    
    @Autowired
    MovieRepository filmeRepository;
    
    public void persisteCsv() throws IOException{
        
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
    }
    
}
