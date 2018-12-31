package pl.project.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.project.project.models.TypeTicket;
import pl.project.project.repositories.TypeTicketRepository;

import javax.validation.Valid;

@Controller
public class TypeTicketController {

    @Autowired
    private TypeTicketRepository typeTicketRepository;

    @RequestMapping(value = "/admin/typeTicketList", method = RequestMethod.GET)
    public String showTypeTicketList(Model model){
        model.addAttribute("typeTicketList", typeTicketRepository.findAll());
        return "admin/typeTicketList";
    }

    @RequestMapping(value = "/admin/typeTicketForm", method = RequestMethod.GET)
    public String showForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Integer id){
        if (id > 0){
            model.addAttribute("typeTicket", typeTicketRepository.findById(id).get());
        }else{
            model.addAttribute("typeTicket", new TypeTicket());
        }
        return "admin/typeTicketForm";
    }

    @RequestMapping(value = "/admin/typeTicketForm", method = RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("typeTicket") TypeTicket typeTicket, BindingResult errors){
        if (errors.hasErrors()){
            return "admin/typeTicketForm";
        }
        System.out.println(typeTicket.getId());
        typeTicketRepository.save(typeTicket);
        return "redirect:typeTicketList";
    }

    @RequestMapping(value = "/admin/deleteTypeTicket", method = RequestMethod.GET)
    public String deleteTicket(@RequestParam(name = "id") Integer id)
    {
        if(typeTicketRepository.existsById(id) == true)
        {
            typeTicketRepository.deleteById(id);
        }

        return "redirect:typeTicketList";
    }
}
