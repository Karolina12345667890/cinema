package pl.project.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.project.project.models.TypeMovie;
import pl.project.project.repositories.MovieRepository;
import pl.project.project.repositories.TypeMovieRepository;

import javax.validation.Valid;

@Controller
public class TypeMovieController {

    @Autowired
    private TypeMovieRepository typeMovieRepository;

    @RequestMapping(value = "/admin/typeMovieList", method = RequestMethod.GET)
    public String showTypeMovieList(Model model){
        model.addAttribute("typeMovieList", typeMovieRepository.findAll());
        return "admin/typeMovieList";
    }

    @RequestMapping(value = "/admin/typeMovieForm", method = RequestMethod.GET)
    public String showForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Integer id){
        if (id > 0){
            model.addAttribute("typeMovie", typeMovieRepository.findById(id).get());
        }else{
            model.addAttribute("typeMovie", new TypeMovie());
        }
        return "admin/typeMovieForm";
    }

    @RequestMapping(value = "/admin/typeMovieForm", method = RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("typeMovie") TypeMovie typeMovie, BindingResult errors){
        if (errors.hasErrors()){
            return "admin/typeMovieForm";
        }
        System.out.println(typeMovie.getId());
        typeMovieRepository.save(typeMovie);
        return "redirect:typeMovieList";
    }

}
