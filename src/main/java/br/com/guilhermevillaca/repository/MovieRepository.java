package br.com.guilhermevillaca.repository;

import br.com.guilhermevillaca.model.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author guilhermevillaca
 */
public interface MovieRepository extends CrudRepository<Movie, Long>{
    
    String strQry = "select * from movie where winner = 'yes'";
    @Query(value = strQry, nativeQuery = true)
    List<Movie> premioIntervalo();	
    
    String strQry2 = "select * from movie where winner = 'yes' and producers = ?1";
    @Query(value = strQry, nativeQuery = true)
    List<Movie> premioIntervaloPorProdutor(String producer);
}
