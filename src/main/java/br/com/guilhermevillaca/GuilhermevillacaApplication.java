package br.com.guilhermevillaca;

import aj.org.objectweb.asm.TypeReference;
import br.com.guilhermevillaca.model.Filme;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@RestController
public class GuilhermevillacaApplication {

    public static String TYPE = "text/csv";
    static String[] HEADERs = {"year", "title", "studios", "producers", "winner"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SpringApplication.run(GuilhermevillacaApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/filmes2")
    public String filmes() throws FileNotFoundException, IOException {
        List<List<String>> records = new ArrayList<>();
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");

        try ( BufferedReader br = new BufferedReader(new FileReader("/csv/movielist.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }
        String ret = "";
        int index = 0;
        for (List<String> record : records) {
            ret += record.get(index);
            index++;
        }
        return ret;
    }

    @GetMapping("filmes")
    public ResponseEntity<List<Filme>> outroFilme() throws IOException {

        List<Filme> filmes = new ArrayList<Filme>();

        InputStream is = TypeReference.class.getResourceAsStream("/csv/movielist.csv");
        //CSVFormat csvFormat = CSVFormat.DEFAULT;

        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.newFormat(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Filme filme = new Filme();
                Map<String, String> csvMap = csvRecord.toMap();
                filme.setYear(csvMap.get("year"));
                filme.setTitle(csvMap.get("title"));
                filme.setStudios(csvMap.get("studios"));
                filme.setProducer(csvMap.get("producers"));
                filme.setWinner(csvMap.get("winner"));
                filmes.add(filme);
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GuilhermevillacaApplication.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ResponseEntity.ok(filmes);

    }

    //@GetMapping("/multiple")
    //public ResponseEntity<List<Pojo>> multiple() {
    //return ResponseEntity.ok(Arrays.asList(new Pojo("one"), new Pojo("two")));
    //}
}
