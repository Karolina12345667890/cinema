package pl.project.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.project.project.models.Price;
import pl.project.project.models.Reservation;
import pl.project.project.models.TypeTicket;
import pl.project.project.models.User;
import pl.project.project.repositories.*;

import java.util.List;

@Controller
public class HistoryController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationSeatRepository reservationSeatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TypeTicketRepository typeTicketRepository;
    @Autowired
    PriceRepository priceRepository;

    @ModelAttribute("typeTicketList")
    public List<TypeTicket> loadTypeMovie(){
        List<TypeTicket> typeTickets = typeTicketRepository.findAll();
        return typeTickets;
    }
    @ModelAttribute("typePriceList")
    public List<Price> loadPrice(){
        List<Price> typeTickets = priceRepository.findAll();
        return typeTickets;
    }


    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String showHistory(Model model)
    {
        //obiekt aktualnie uwirzytelnionego użytkownika
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        //jeśli principal jest instancją klasy UserDeatils
        if (principal instanceof UserDetails) {

            username = ((UserDetails)principal).getUsername();

        } else {

            username = principal.toString();

        }
        User user = userRepository.findByUsername(username);
        System.out.println("aaaaaaaaaaaaaaaaaaaa = " +username);
        System.out.println("cccccccccccccccccccc = " +user);

        model.addAttribute("user",reservationRepository.findReservationByUser(username));
        //model.addAttribute("list", reservationRepository.findAllById(Iterable<String>  ));
        model.addAttribute("seatList",reservationSeatRepository.findAll());
        return "history";
    }
}
