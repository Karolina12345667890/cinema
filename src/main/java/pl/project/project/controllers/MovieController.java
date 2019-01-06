package pl.project.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.project.project.models.Movie;
import pl.project.project.models.TypeMovie;
import pl.project.project.repositories.MovieRepository;
import pl.project.project.repositories.TypeMovieRepository;
import pl.project.project.services.MovieService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SessionAttributes("searchCommand")
@Controller
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
    public String resetehicleList(@ModelAttribute("searchCommand") FilterController search){
        search.clear();
        return "redirect:admin/movieList.html";
    }

    @RequestMapping(value = "/admin/movieList", method = {RequestMethod.GET, RequestMethod.POST})
    public String showMovieList(Model model, @RequestParam("page")Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size,@Valid @ModelAttribute("searchCommand") FilterController search){

        model.addAttribute("movieList", movieRepository.findAll());
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(2);
        Page<Movie> moviePage = movieService.findPagined(PageRequest.of(currentPage,pageSize),search);
//        Page<Movie> moviePage = movieService.findPagined(PageRequest.of(currentPage,pageSize));
        model.addAttribute("moviePage",moviePage);

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

    @RequestMapping(value = "/admin/movieForm", method = RequestMethod.GET)
    public String showMovieForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Integer id){
        if (id > 0){
            model.addAttribute("movie", movieRepository.findById(id).get());
        }else{
            model.addAttribute("movie", new Movie());
        }
        return "admin/movieForm";
    }

    @RequestMapping(value = "/admin/movieForm", method = RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("movie") Movie movie, BindingResult errors){
        if (errors.hasErrors()){
            return "admin/movieForm";
        }
        movieRepository.save(movie);
        return "redirect:movieList";
    }


    @RequestMapping(value = "/admin/deleteMovie", method = RequestMethod.GET)
    public String deleteMovie(@RequestParam(name = "id") Integer id){
        if (movieRepository.existsById(id)){
            movieRepository.deleteById(id);
        }
        return "redirect:movieList";
    }

    @RequestMapping(value = "/admin/movieDetails", method = RequestMethod.GET)
    public String showMovieDetails(Model model, @RequestParam(name = "id" , required = false, defaultValue = "-1") Integer id)
    {
        model.addAttribute("typeMovie",typeMovieRepository.findAll());
        model.addAttribute("movieListDetails", movieRepository.findById(id).get());
        return "admin/movieDetails";
    }
}
