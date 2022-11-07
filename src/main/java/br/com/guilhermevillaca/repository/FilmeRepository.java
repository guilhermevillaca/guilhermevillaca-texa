package br.com.guilhermevillaca.repository;

import br.com.guilhermevillaca.model.Filme;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author guilhermevillaca
 */
public interface FilmeRepository extends CrudRepository<Filme, Long>{
    
    String strQry = "select * from filme where winner = 'yes'";
    @Query(value = strQry, nativeQuery = true)
    List<Filme> premioIntervalo();	
    
    String strQry2 = "select * from filme where winner = 'yes' and producers = ?1";
    @Query(value = strQry, nativeQuery = true)
    List<Filme> premioIntervaloPorProdutor(String producer);
}
