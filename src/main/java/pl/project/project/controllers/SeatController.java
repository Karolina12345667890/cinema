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
import pl.project.project.models.Seat;
import pl.project.project.repositories.SeatRepository;

import javax.validation.Valid;

@Controller
public class SeatController {

    @Autowired
    private SeatRepository seatRepository;


    @RequestMapping(value = "/reservedSeatList", method = RequestMethod.GET)
    public String showSeatList(Model model){
        model.addAttribute("reservedSeatList", seatRepository.findAll());
        return "/reservedSeatList";
    }

//    @RequestMapping(value = "/reservedSeatList", method = RequestMethod.GET)
//    public String showSeatForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Integer id){
//
//        Seat seat = new Seat();
//        seat.setId(id);
//        seat.setReserved(true);
//        seatRepository.save(seat);
////        if (id > 0){
////            model.addAttribute("reservedSeat", seatRepository.findById(id).get());
////
////        }else{
////            model.addAttribute("movie", new Movie());
////        }
//        return "/reservedSeatList";
//    }

//    @RequestMapping(value = "/admin/movieForm", method = RequestMethod.POST)
//    public String processForm(@Valid @ModelAttribute("movie") Movie movie, BindingResult errors){
//        if (errors.hasErrors()){
//            return "admin/movieForm";
//        }
//        movieRepository.save(movie);
//        return "redirect:movieList";
//    }

//
//    @RequestMapping(value = "/admin/deleteMovie", method = RequestMethod.GET)
//    public String deleteMovie(@RequestParam(name = "id") Integer id){
//        if (movieRepository.existsById(id)){
//            movieRepository.deleteById(id);
//        }
//        return "redirect:movieList";
//    }
}
