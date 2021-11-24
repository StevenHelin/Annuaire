package fr.insa.TPCompo.controller;

import fr.insa.TPCompo.Person;
import fr.insa.TPCompo.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

@GetMapping("/ajout")
public String ajouter(Model model) {
        return "ajout";
    }




    @GetMapping("/annuaire/recherche")
    public String recherche(Model model,@RequestParam (name="name", required=false) String name) {
         model.addAttribute("entries", services.getFromName(name));
        return "annuaire";
    }

    @PostMapping("/annuaire/ajouter")
    public String ajout(Model model,@RequestParam (name="name", required=false) String name,@RequestParam (name="surname", required=false) String surname,@RequestParam (name="phone", required=false) String phone,@RequestParam (name="city", required=false) String city){
        this.services.addPerson(new Person(name,surname,phone,city));
        return "redirect:/annuaire";
    }

    @GetMapping("/annuaire/ajouter")
    public String rechercheAjout(Model model) {
        model.addAttribute("entries", services.getAll());
        return "redirect:/annuaire";
    }

        @GetMapping("/annuaire/supprimer/{id}")
        public String suppprime(Model model, @PathVariable int id) {
            services.deleteFromId(id);
            model.addAttribute("entries", services.getAll());
            return "redirect:/annuaire";
        }
}