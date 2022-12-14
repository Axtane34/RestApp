package ru.axtane.springcource.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.springcource.FirstRestApp.dto.PersonDTO;
import ru.axtane.springcource.FirstRestApp.models.Person;
import ru.axtane.springcource.FirstRestApp.repositories.PeopleRepository;
import ru.axtane.springcource.FirstRestApp.util.PersonNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void deleteById(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        if (foundPerson.isPresent()) {
            peopleRepository.deleteById(id);
        }else throw new PersonNotFoundException();
    }

    @Transactional
    public void update(int id, PersonDTO updatedPerson) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        if (foundPerson.isPresent()) {
            Person person = foundPerson.get();
            person.setName(updatedPerson.getName());
            person.setAge(updatedPerson.getAge());
            person.setEmail(updatedPerson.getEmail());
        }else throw new PersonNotFoundException();
    }

   /* private void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("ADMIN");
    }*/

    @Transactional
    public void save(Person person){
        /*enrichPerson(person);*/
        peopleRepository.save(person);
    }
}
