package pl.project.project.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.project.project.exception.MovieNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Log4j2
public class GlobalControlerAdvice {
    @ExceptionHandler(MovieNotFoundException.class)
    public String handleVehocleNotFoundError(Model model, HttpServletRequest req, Exception ex) {
        log.error("Request: " + req.getRequestURL() + " raised " + ex);

        model.addAttribute("exception", ex);
        model.addAttribute("url", req.getRequestURL());

        return "errors/error404MovieNotFound";
    }
}
