package fr.insa.TPCompo;

import fr.insa.TPCompo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonServices implements DictionnaryItf {

    @Autowired
    PersonRepository personrepo;

    @Override
    public List<Person> getAll() {
        return personrepo.findAll();
    }

    @Override
    public Optional<Person> getFromId(long id) {
        return personrepo.findById(id);
    }

    @Override
    public List<Person> getFromName(String name) {
       return personrepo.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        personrepo.deleteById(id);
    }

    @Override
    public void addPerson(Person p) {
    personrepo.save(p);
    }

}
