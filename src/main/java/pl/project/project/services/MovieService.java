package pl.project.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.project.project.controllers.FilterController;
import pl.project.project.exception.MovieNotFoundException;
import pl.project.project.models.Movie;
import pl.project.project.repositories.MovieRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements MovieServices{

    @Autowired
    private MovieRepository movieRepository;

    public Page<Movie> findPagined(Pageable pageable, FilterController search)
    {
        int pageSize = pageable.getPageSize();
        int  currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        System.out.println("ala ma kota");

        List<Movie> movie;
        if(search.isEmpty())
        {
            movie = movieRepository.findAll();
        }
        else
        {
            movie = movieRepository.findAllMovieUsingFilter(search.getPhraseLIKE(),search.getDirectorLIKE(),search.getCountryLIKE());
        }

        List<Movie> list;
        if(movie.size() < startItem )
        {
            list = Collections.emptyList();
        }
        else {
            int toIndex = Math.min(startItem + pageSize, movie.size());
            list = movie.subList(startItem, toIndex);
        }
        Page<Movie> moviePage = new PageImpl<Movie>(list, PageRequest.of(currentPage, pageSize), movie.size());

        return moviePage;
    }

    @Transactional
    @Override
    public Movie getMovie(Integer id) {
        Optional<Movie> optional = movieRepository.findById(id);
        if( !optional.isPresent() )optional.orElseThrow(()->new MovieNotFoundException(id));
        return optional.get();
    }
}
