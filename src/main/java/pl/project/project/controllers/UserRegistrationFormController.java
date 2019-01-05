package pl.project.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.project.project.models.User;
import pl.project.project.services.UserService;

import javax.validation.Valid;

@Controller
public class UserRegistrationFormController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registrationFrom", method = RequestMethod.GET)
    public String showRegistrationForm(){
        return "registrationFrom";
    }

    @RequestMapping(value = "/registrationFrom", method = RequestMethod.POST)
    public String processRegistrationForm(Model model, @Valid User user, BindingResult errors){
        User userExists = userService.findByUsername(user.getUsername());

        if (userExists != null){
            errors.reject("username");
        }

        if (errors.hasErrors()){
            return "registrationFrom";
        }

        userService.save(user);
        return "registrationSuccess";
    }



//    @GetMapping("/registrationFrom")
//    public String registration(Model model) {
//        model.addAttribute("userCommand", new User());
//        return "registrationFrom";
//    }
//
//    @PostMapping("/registrationFrom")
//    public String registration(@Valid @ModelAttribute("userCommand") User userForm, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "registrationFrom";
//        }
//        userService.save(userForm);
//        return "registrationSuccess";
//    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        //aby użytkownik nie mógł sobie wstrzyknąć aktywacji konta oraz ról (np., ADMIN)
//        //roles są na wszelki wypadek, bo warstwa serwisów i tak ustawia ROLE_USER dla nowego usera
//        binder.setDisallowedFields("enabled", "roles");
//    }
}
