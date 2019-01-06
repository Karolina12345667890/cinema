package pl.project.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.project.project.controllers.FilterController;
import pl.project.project.models.Movie;
import pl.project.project.repositories.MovieRepository;

import java.util.Collections;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Page<Movie> findPagined(Pageable pageable, FilterController search)
    {
        int pageSize = pageable.getPageSize();
        int  currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

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

}
