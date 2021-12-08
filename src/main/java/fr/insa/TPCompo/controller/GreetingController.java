package fr.insa.TPCompo.controller;

import fr.insa.TPCompo.Person;
import fr.insa.TPCompo.PersonServices;
import fr.insa.TPCompo.repository.PersonRepository;
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
    PersonRepository personRepository;


@GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

@GetMapping("/annuaire")
    public String recherche(Model model) {
        model.addAttribute("entries", personRepository.findAll());
        return "annuaire";
    }

@GetMapping("/ajout")
public String ajouter(Model model) {
        return "ajout";
    }

@GetMapping("/modification/{id}")
public String modification(Model model,@PathVariable int id){
    model.addAttribute("entry",personRepository.findById(id));
    return "Modification";
    }




    @GetMapping("/annuaire/recherche")
    public String recherche(Model model,@RequestParam (name="name", required=false) String name) {
         model.addAttribute("entries", personRepository.findByName(name));
        return "annuaire";
    }

    @PostMapping("/annuaire/ajouter")
    public String ajout(Model model,@RequestParam (name="name", required=false) String name,@RequestParam (name="surname", required=false) String surname,@RequestParam (name="phone", required=false) String phone,@RequestParam (name="city", required=false) String city){
        this.personRepository.save(new Person(name,surname,phone,city));
        return "redirect:/annuaire";
    }

    @GetMapping("/annuaire/ajouter")
    public String rechercheAjout(Model model) {
        model.addAttribute("entries", personRepository.findAll());
        return "redirect:/annuaire";
    }

        @GetMapping("/annuaire/supprimer/{id}")
        public String suppprime(Model model, @PathVariable long id) {
            personRepository.deleteById(id);
            model.addAttribute("entries", personRepository.findAll());
            return "redirect:/annuaire";
        }

    @PostMapping("/annuaire/modifier")
    public String modifier(Model model,@RequestParam (name="id", required=true) int id,@RequestParam (name="name", required=false) String name,@RequestParam (name="surname", required=false) String surname,@RequestParam (name="phone", required=false) String phone,@RequestParam (name="city", required=false) String city){

        this.personRepository.save(new Person(id,name,surname,phone,city));
        this.personRepository.deleteById(id);
        return "redirect:/annuaire";
    }
}