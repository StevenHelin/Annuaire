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

import java.util.ArrayList;
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
    public ResponseEntity<?> ajout(@RequestBody Person p){
        List <Person> personList = ps.getAll();
        if(personList.contains(p)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else{
            this.ps.addPerson(p);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }


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
    public ResponseEntity<?> modifier(@RequestBody Person p){
        Optional<Person> p2;
        p2=ps.getFromId(p.getId());
        if (p2.isPresent()){
            p2.get().setCity(p.getCity());
            p2.get().setName(p.getName());
            p2.get().setSurname(p.getSurname());
            p2.get().setPhone(p.getPhone());
            ps.addPerson(p2.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}