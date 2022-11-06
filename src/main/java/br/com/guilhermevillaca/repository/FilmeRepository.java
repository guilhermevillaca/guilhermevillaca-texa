package br.com.guilhermevillaca.repository;

import br.com.guilhermevillaca.model.Filme;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author guilhermevillaca
 */
public interface FilmeRepository extends CrudRepository<Filme, Long>{
    
}
