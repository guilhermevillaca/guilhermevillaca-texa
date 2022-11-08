package br.com.guilhermevillaca.service;

import br.com.guilhermevillaca.utils.AwardInterval;
import br.com.guilhermevillaca.model.Movie;
import br.com.guilhermevillaca.utils.ProducerWin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import br.com.guilhermevillaca.repository.MovieRepository;

@Service
public class ProducerService {    

    @Autowired
    private  MovieRepository filmeRepository;

    public AwardInterval getIntervalAwards() {
        List<Movie> winningMovies = filmeRepository.premioIntervalo();
        Map<String, List<Movie>> moviesByProducer = winningMovies.stream().collect(Collectors.groupingBy(Movie::getProducers));        
        List<ProducerWin> wins = new ArrayList<>();

        moviesByProducer.forEach((producer, movies) -> {            
            movies.sort(Comparator.comparing(Movie::getYear));
            if (movies.size() >= 2) {
                movies.forEach(movie -> {
                    Movie nextMovie = getNextMovie(movies, movie);
                    if (nextMovie != null) {
                        ProducerWin p = new ProducerWin();
                        p.setProducer(producer);
                        p.setPreviousWin(movie.getYear());
                        p.setFollowingWin(nextMovie.getYear());
                        p.setInterval(p.getFollowingWin() - p.getPreviousWin());
                        wins.add(p);
                    }
                });
            }
        });

        List<ProducerWin> minInterval = wins.stream().filter(
                producerInterval -> producerInterval.getInterval().equals(
                        wins.stream()
                                .min(Comparator.comparing(ProducerWin::getInterval))
                                .orElseThrow(NoSuchElementException::new).getInterval()
                )
        ).collect(Collectors.toList());

        List<ProducerWin> maxInterval = wins.stream().filter(
                producerInterval -> producerInterval.getInterval().equals(
                        wins.stream()
                                .max(Comparator.comparing(ProducerWin::getInterval))
                                .orElseThrow(NoSuchElementException::new).getInterval()
                )
        ).collect(Collectors.toList());
        return new AwardInterval(minInterval, maxInterval);
    }

    private Movie getNextMovie(List<Movie> winningMovies, Movie currentMovie) {
        int index = winningMovies.indexOf(currentMovie);
        if (index < 0 || index + 1 == winningMovies.size()) {
            return null;
        }
        return winningMovies.get(index + 1);
    }

}
