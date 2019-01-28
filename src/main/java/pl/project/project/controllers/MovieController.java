package pl.project.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.project.project.exception.MovieNotFoundException;
import pl.project.project.models.Movie;
import pl.project.project.models.TypeMovie;
import pl.project.project.repositories.MovieRepository;
import pl.project.project.repositories.TypeMovieRepository;
import pl.project.project.services.MovieService;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SessionAttributes("searchCommand")
@Controller
@Log4j2
public class MovieController {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TypeMovieRepository typeMovieRepository;

    @Autowired
    private MovieService movieService;

    @ModelAttribute("typeMovieList")
    public List<TypeMovie> loadTypeMovie(){
        List<TypeMovie> typeMovies = typeMovieRepository.findAll();
        return typeMovies;
    }

    @ModelAttribute("searchCommand")
    public FilterController getSimpleSearch(){
        return new FilterController();
    }

    @GetMapping(value="/admin/movieList.html", params = {"all"})
    public String resetList(@ModelAttribute("searchCommand") FilterController search){
        search.clear();
        return "redirect:admin/movieList.html";
    }

    @RequestMapping(value = {"/admin/movieList", "/"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String showMovieList(Model model, @RequestParam("page")Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size,@Valid @ModelAttribute("searchCommand") FilterController search){

        model.addAttribute("movieList", movieRepository.findAll());
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(3);
        Page<Movie> moviePage = movieService.findPagined(PageRequest.of(currentPage,pageSize),search);
//        Page<Movie> moviePage = movieService.findPagined(PageRequest.of(currentPage,pageSize));
        model.addAttribute("moviePage",moviePage);

        //wytworzenie listy numerów stron
        int totalPages = moviePage.getTotalPages();
        if(totalPages > 0)
        {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/movieList";
    }
//    @RequestParam(name = "id", required = false, defaultValue = "-1")

    @RequestMapping(value = {"/admin/movieForm","/movieForm"}, method = RequestMethod.GET)
    public String showMovieForm(Model model, Optional<Integer> id){

            model.addAttribute("movie",id.isPresent()? movieService.getMovie(id.get()) : new Movie());

        return "admin/movieForm";
    }

    @RequestMapping(value = {"/admin/movieForm","/movieForm"}, method = RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("movie") Movie movie, BindingResult errors){
        if (errors.hasErrors()){
            return "admin/movieForm";
        }
        movieRepository.save(movie);
        return "redirect:movieList";
    }


    @RequestMapping(value = {"/admin/deleteMovie","/deleteMovie"}, method = RequestMethod.GET)
    public String deleteMovie(Integer id){
        if (movieRepository.existsById(id)){
            movieRepository.deleteById(id);
        }
        else{
            throw new MovieNotFoundException(id);
        }
        return "redirect:movieList";
    }

    @RequestMapping(value = {"/admin/movieDetails", "/movieDetails"}, method = RequestMethod.GET)
    public String showMovieDetails(Model model, Integer id)
    {

        Movie m = movieService.getMovie(id);
        Set<TypeMovie> set = m.getTypeMovie();
        List<TypeMovie> list = new ArrayList<>();
        for (TypeMovie t : set)
        {
            list.add(t);
        }

        model.addAttribute("typeMovie",list);
        model.addAttribute("movieListDetails",m);
        return "admin/movieDetails";
    }

}
