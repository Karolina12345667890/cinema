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
import pl.project.project.models.Price;
import pl.project.project.models.TypeMovie;
import pl.project.project.models.TypeTicket;
import pl.project.project.repositories.PriceRepository;
import pl.project.project.repositories.TypeTicketRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PriceController {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private TypeTicketRepository typeTicketRepository;

    @ModelAttribute("typeTicketList")
    public List<TypeTicket> loadTypeTicket(){
        List<TypeTicket> typeTicket = typeTicketRepository.findAll();
        return typeTicket;
    }

    @RequestMapping(value = "/admin/priceList", method = RequestMethod.GET)
    public String showPriceList(Model model){
        model.addAttribute("priceList", priceRepository.findAll());
        model.addAttribute("typeTicketList",typeTicketRepository.findAll());
        return "admin/priceList";
    }

    @RequestMapping(value = "/admin/priceForm", method = RequestMethod.GET)
    public String showPriceForm(Model model, @RequestParam(name = "id", required = false, defaultValue = "-1") Integer id){
        if (id > 0){
            model.addAttribute("price", priceRepository.findById(id).get());
        }else{
            model.addAttribute("price", new Price());
        }
        return "admin/priceForm";
    }

    @RequestMapping(value = "/admin/priceForm", method = RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("price") Price price, BindingResult errors){
        if (errors.hasErrors()){
            return "admin/priceForm";
        }
        priceRepository.save(price);
        return "redirect:priceList";
    }


    @RequestMapping(value = "/admin/deletePrice", method = RequestMethod.GET)
    public String deletePrice(@RequestParam(name = "id") Integer id){
        if (priceRepository.existsById(id)){
            priceRepository.deleteById(id);
        }
        return "redirect:priceList";
    }

}
