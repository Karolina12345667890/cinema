package pl.project.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.project.project.models.Movie;
import pl.project.project.models.TypeMovie;
import pl.project.project.repositories.MovieRepository;
import pl.project.project.repositories.TypeMovieRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TypeMovieRepository typeMovieRepository;

    @ModelAttribute("typeMovieList")
    public List<TypeMovie> loadTypeMovie(){
        List<TypeMovie> typeMovies = typeMovieRepository.findAll();
        return typeMovies;
    }

    @RequestMapping(value = "/admin/movieList", method = RequestMethod.GET)
    public String showMovieList(Model model){
        model.addAttribute("movieList", movieRepository.findAll());
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
}
