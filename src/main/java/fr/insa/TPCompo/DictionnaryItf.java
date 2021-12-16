package fr.insa.TPCompo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DictionnaryItf {
    public Collection<Person> getAll();
    public Optional<Person> getFromId(long id);
    public List<Person> getFromName(String name);
    public void deleteById(Long id);
    public void addPerson(Person p);

}
