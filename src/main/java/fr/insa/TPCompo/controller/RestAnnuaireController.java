package fr.insa.TPCompo.controller;

import fr.insa.TPCompo.Person;
import fr.insa.TPCompo.PersonServices;
import fr.insa.TPCompo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestAnnuaireController {

    @Autowired
    PersonServices ps;

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/entree")
    public ResponseEntity<?>recherche(Model model) {
        return ResponseEntity.status(HttpStatus.OK).body(ps.getAll());
    }


    @GetMapping("/entree/{id}")
    public ResponseEntity<?> modification(Model model, @PathVariable int id){
        Optional<Person> p;
        p=ps.getFromId(id);
        if(p.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(ps.getFromId(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ps.getFromId(id));
        }

    }


    @PostMapping("/entree")
    public void ajout(@RequestBody Person p){
        this.personRepository.save(p);
    }

    @DeleteMapping("/entree/{id}")
    public ResponseEntity<?> suppprime(Model model, @PathVariable Long id) {
        Optional<Person> p;
        p=ps.getFromId(id);
        if(p.isPresent()){
            ps.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/entree")
    public String modifier(Model model,@RequestParam (name="id", required=true) Long id,@RequestParam (name="name", required=false) String name,@RequestParam (name="surname", required=false) String surname,@RequestParam (name="phone", required=false) String phone,@RequestParam (name="city", required=false) String city){
        Optional<Person> p=this.ps.getFromId(id);
        if (p.isPresent()){
            p.get().setCity(city);
            p.get().setName(name);
            p.get().setSurname(surname);
            p.get().setPhone(phone);
            personRepository.save(p.get());
        }
        return "redirect:/annuaire";
    }
}