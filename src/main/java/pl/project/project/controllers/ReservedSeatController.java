package pl.project.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.project.project.models.*;
import pl.project.project.repositories.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class ReservedSeatController {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    HallRepository hallRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservationSeatRepository reservationSeatRepository;
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    TypeTicketRepository typeTicketRepository;

    @ModelAttribute("typePriceList")
    public List<TypeTicket> loadTypeMovie(){
        List<TypeTicket> typeTickets = typeTicketRepository.findAll();
        return typeTickets;
    }

    @RequestMapping(value = "/reservedSeatForm", method = RequestMethod.GET)
    public String showReservationForm(Model model, @RequestParam(name = "id") Integer showId){

        Show show = showRepository.findById(showId).get();
        Hall hall = hallRepository.findById(show.getHall().getId()).get();


        List<Seat> hallSeats = new ArrayList<>(seatRepository.findSeatByHall(show.getHall()));

        List<String> listOfReservedSeats = new ArrayList<>();

        for (ReservationSeat reservedSeat : show.getReservationSeats()){
            listOfReservedSeats.add(reservedSeat.getSeat().getRow() + "-" + reservedSeat.getSeat().getSeat());
        }

        show.setListOfReservedSeats(listOfReservedSeats);

        // klucz-> rząd; wartość-> lista miejsc w rzędzie
        Map<Integer, List<Seat>> seats = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            List<Seat> tmp = new ArrayList<>();
            for (Seat seat : hallSeats){
                if (seat.getRow() == i+1){
                    tmp.add(seat);
                }
            }
            seats.put(i+1, tmp);
        }

        model.addAttribute("price", priceRepository.findAll());
        model.addAttribute("listOfResSeats", listOfReservedSeats);
        model.addAttribute("show", show);
        model.addAttribute("seats", seats);
        model.addAttribute("reservation", new Reservation());
        return "reservedSeatForm";
    }

    @RequestMapping(value = "/reservedSeatForm", method = RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("show") Show show, BindingResult errors){
        if (errors.hasErrors()){
            return "admin/reservedSeatForm";
        }

        List<ReservationSeat> hallSeats = new ArrayList<>(reservationSeatRepository.findReservedSeatByScreening(show));
        Price price = show.getPrice();
        List<String> listOfSeats = new ArrayList<>();
        for (ReservationSeat rs : hallSeats){
            listOfSeats.add(rs.getSeat().getRow() + "-" + rs.getSeat().getSeat());
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {

            username = ((UserDetails)principal).getUsername();

        } else {

            username = principal.toString();

        }

        User user = userRepository.findByUsername(username);

        Reservation reservation = new Reservation(new HashSet<>(), user);
        reservationRepository.save(reservation);


        for (String s : show.getListOfReservedSeats()){
            if (!listOfSeats.contains(s)) {
                String[] parts = s.split("-");
                Seat seat = seatRepository.findSeatByHallAndSeat(show.getHall(), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

                reservationSeatRepository.save(
                        new ReservationSeat( show,seat,reservation,price));
            }
        }

        return "redirect:repertuar";
    }
}
