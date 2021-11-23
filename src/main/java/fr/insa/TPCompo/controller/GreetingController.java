package fr.insa.TPCompo.controller;

import fr.insa.TPCompo.Person;
import fr.insa.TPCompo.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

@Autowired
private PersonServices services =new PersonServices();

@GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

@GetMapping("/annuaire")
    public String recherche(Model model) {
        model.addAttribute("entries", services.getAll());
        return "annuaire";
    }

    @GetMapping("/annuaire/recherche")
    public String recherche(Model model,@RequestParam (name="name", required=false) String name) {
         model.addAttribute("entries", services.getFromName(name));
        return "annuaire";
    }

        @GetMapping("/annuaire/supprimer/{id}")
        public String suppprime(Model model, @PathVariable int id) {
            services.deleteFromId(id);
            model.addAttribute("entries", services.getAll());
            return "redirect:/annuaire/recherche";
        }
}