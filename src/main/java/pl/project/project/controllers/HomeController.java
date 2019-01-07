package pl.project.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

//    @RequestMapping(value = {"/", "/admin/movieList"}, method = RequestMethod.GET)
//    public String showIndex(){
//        return "/admin/movieList";
//    }

//    @RequestMapping(value = {"/history"}, method = RequestMethod.GET)
//    public String showId(){
//        return "history";
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(){
        return "login";
    }

    @RequestMapping(value = "/admin/panel", method = RequestMethod.GET)
    public String showAdminPanel(){
        return "admin/panel";
    }


}
