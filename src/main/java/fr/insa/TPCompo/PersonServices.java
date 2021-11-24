package fr.insa.TPCompo;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
public class PersonServices implements DictionnaryItf {

    private Map<Integer, Person> hm ;

    public PersonServices() {
        hm = new HashMap<Integer, Person>();
        hm.put(1, new Person(1, "Steven",
                "Helin"
                ,
                "0606060606", "Anzin"));
        hm.put(2, new Person(2, "Dylan", "Helin", "0707070707", "Anzin"));
        hm.put(3, new Person(3, "Pauline", "Polvent", "0781889125", "Maubeuge"));

    }


    @Override
    public Collection<Person> getAll() {
        return hm.values();
    }

    @Override
    public Person getFromId(int id) {
        return hm.get(id);
    }

    @Override
    public List<Person> getFromName(String name) {
        List<Person> listPerson= new ArrayList<Person>();
        for (Person p: hm.values()){
            if (p.getName().equals(name)){
                listPerson.add(p);
            }
        }
        return listPerson;
    }

    @Override
    public boolean deleteFromId(int id) {
        hm.remove(id);
        return true;
    }

    @Override
    public void addPerson(Person p) {
        hm.put(hm.size()+1,p);
    }
}
