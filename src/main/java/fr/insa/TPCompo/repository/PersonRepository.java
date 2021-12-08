package fr.insa.TPCompo.repository;

import fr.insa.TPCompo.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    public List<Person> findAll();
    public Optional<Person> findById(long id);
    public List<Person> findByName(String name);
    public void deleteById(Long id);

}
