package pl.project.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.project.project.models.Movie;
import pl.project.project.models.Show;
import pl.project.project.repositories.MovieRepository;
import pl.project.project.repositories.ShowRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class RepertoireController {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(value = "/repertuar", method = RequestMethod.GET)
    public String showRepertoire(Model model, @RequestParam(name = "date", required = false, defaultValue = "-1") String date){
        model.addAttribute("moviePoster",movieRepository.findAll());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now;

        if(!date.equals("-1")){
            now = LocalDate.parse(date, dtf);
        }else{
            now = LocalDate.now();
        }

        List<Date> dateOfWeek = new ArrayList<>();
        for (long i = 0; i < 7; i++) {
            dateOfWeek.add(Date.from(LocalDate.now().atTime(0,0).plusDays(i).atZone(ZoneId.systemDefault()).toInstant()));
        }

        model.addAttribute("nextWeekDates", dateOfWeek);

        /**
         * from -
         * now - aktualna data pobrana z  systemu w aktualnej strefie czasowej
         * atTime - tworzy z aktualnej daty datę i godzinę
         * atZone - tworzy strefę czasową dla date-hours
         * ZoneID - identyfikator strefy czasowej tj. Europa/Paryż
         * systemDefault - zwraca id strefy czasowej
         * toInstance - Obliczenie łączy lokalną datę i godzinę oraz przesunięcie.
         */

        Date timeShow = Date.from(now.atTime(0,0).atZone(ZoneId.systemDefault()).toInstant());
        Date nextDay = Date.from(now.plusDays(1).atTime(0,0).atZone(ZoneId.systemDefault()).toInstant());




        List<Movie> movieList = movieRepository.findByShowDate(timeShow, nextDay);

        Map<Movie, List<Show>> map = new HashMap<>();

        for (Movie m : movieList){
            map.put(m,showRepository.findByDateAndTitle(timeShow, nextDay, m.getId()));
        }

        model.addAttribute("movieShowMap", map);
        return "repertoire";
    }
}
