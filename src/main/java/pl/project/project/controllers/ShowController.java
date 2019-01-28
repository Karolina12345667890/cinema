package pl.project.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.project.project.models.Hall;
import pl.project.project.models.Movie;
import pl.project.project.models.Show;
import pl.project.project.repositories.HallRepository;
import pl.project.project.repositories.MovieRepository;
import pl.project.project.repositories.ShowRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ShowController {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    HallRepository hallRepository;

    @ModelAttribute("movieList")
    public List<Movie> loadMovies(){
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

    @ModelAttribute("hallList")
    public List<Hall> loadHall(){
        List<Hall> halls = hallRepository.findAll();
        return halls;
    }

    @RequestMapping(value = "/admin/showList", method = RequestMethod.GET)
    public String showShowList(Model model){
        model.addAttribute("showList", showRepository.findAll());
        return "admin/showList";
    }

    @RequestMapping(value = "/admin/showForm", method = RequestMethod.GET)
    public String showShowForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Integer id){
        if (id > 0){
            model.addAttribute("show", showRepository.findById(id).get());
        }else{
            model.addAttribute("show", new Show());
        }
        return "admin/showForm";
    }

    @RequestMapping(value = "/admin/showForm", method = RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("show") Show show, BindingResult errors){
        if (errors.hasErrors()){
            return "admin/showForm";
        }
        showRepository.save(show);
        return "redirect:showList";
    }

    @RequestMapping(value = "/admin/deleteShow", method = RequestMethod.GET)
    public String deleteShow(@RequestParam(name = "id") Integer id){
        if (showRepository.existsById(id)){
            showRepository.deleteById(id);
        }
        return "redirect:showList";
    }
}
