package com.example.triodb.persons;

import com.example.triodb.persons_history.PersonsHistory;
import com.example.triodb.persons_history.PersonsHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class PersonService {
    public final PersonRepository personRepository;
    public final PersonsHistoryRepository personsHistoryRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonsHistoryRepository personsHistoryRepository) {
        this.personRepository = personRepository;
        this.personsHistoryRepository = personsHistoryRepository;
    }
    public List<Persons> getPersons() {
        return personRepository.findAll();
    }

    public ResponseEntity<?> savePerson(Persons person) {
        if (personRepository.existsByLogin(person.getLogin())) {
            return ResponseEntity.ok("{\"line\":\"Login already in use!\"}");
        }
        Persons person2 = personRepository.findByLoginIgnoreCase(person.getLogin());
        PersonsHistory personsHistory = new PersonsHistory();
        personsHistory.setPersonRealId(person2.getPersonId());
        personsHistory.setName(person.getPersonRealId().get(0).getName());
        personsHistoryRepository.save(personsHistory);
        return  ResponseEntity.ok("{\"line\":\"You are registered! Persons ID is " + person2.getPersonId()
                + "\",\"status\":\"ok\"," +
                "\"person\":{\"personId\":" + person2.getPersonId() +
                ",\"gender\":" + person2.getGender() + ",\"birthday\":\"" + person2.getBirthdate().toString()
                + "\",\"name\":\"" + person2.getPersonRealId().get(0).getName().toString() + "\", \"role\":\"" +
                person2.getRole() + "\"}}");
    }
    public List<Persons> getPerson(Long personId) {
        boolean exists = personRepository.existsById(personId);
        if (!exists){
            throw new IllegalStateException("Person with id " + personId + " doesn't exsist");
        }
        return personRepository.findAllById(Collections.singleton(personId));
    }

    public void addNewPerson(Persons person){
        if (personRepository.existsByLogin(person.getLogin())) {
            System.out.println("{\"line\":\"Login already in use!\"}");
        }
        personRepository.save(person);
        System.out.println(person);
    }

    public void deletePerson(Long personId){
        boolean exists = personRepository.existsById(personId);
        if (!exists){
            throw new IllegalStateException("Person with id " + personId + " doesn't exist");
        }
        personRepository.deleteById(personId);
        System.out.println(personId);
    }

    public ResponseEntity<?> loginPerson(Persons person){
        if (!personRepository.existsByLogin(person.getLogin())) {
            return ResponseEntity.ok("{\"line\":\"Login not found!\"}");
        } else {
            Persons person2 = personRepository.findByLoginIgnoreCase(person.getLogin());
            if (person.getPassword().equals(person2.getPassword())) {
                return ResponseEntity.ok("{\"line\":\"You are logged in! Persons ID is " + person2.getPersonId()
                        + "\",\"status\":\"ok\"," +
                        "\"person\":{\"personId\":" + person2.getPersonId() +
                        ",\"gender\":" + person2.getGender() + ",\"birthday\":\"" + person2.getBirthdate().toString()
                        + "\",\"name\":\"" + person2.getPersonRealId().get(0).getName().toString() + "\", \"role\":\"" +
                        person2.getRole() + "\"}}");
            }
        }
        return ResponseEntity.ok("{\"line\":\"Password not valid!\"}");
    }

    @Transactional
    public void updatePerson(Long personId, Integer gender, Date birthdate,
                             String login, String password, String role, Boolean active, String name) {
        Persons persons = personRepository.findById(personId).orElseThrow(
                () -> new IllegalStateException("Person with id " + personId +
                        "doesn't exists")
        );

        if (!Objects.equals(persons.getPersonId(), personId)) {
            persons.setPersonId(personId);
        }
        if (gender != null  && !Objects.equals(persons.getGender(), gender)) {
            persons.setGender(gender);
        }
        if (birthdate != null  && !Objects.equals(persons.getBirthdate(), birthdate)) {
            persons.setBirthdate(birthdate);
        }
        if (login != null  && !Objects.equals(persons.getLogin(), login)) {
            persons.setLogin(login);
        }
        if (password != null  && !Objects.equals(persons.getPassword(), password)) {
            persons.setPassword(password);
        }
        if (role != null  && !Objects.equals(persons.getRole(), role)) {
            persons.setRole(role);
        }
        if (active != null  && !Objects.equals(persons.getActive(), active)) {
            persons.setActive(active);
        }
        if (name != null  && !Objects.equals(persons.getPersonRealId().get(0).getName(), name)) {
            persons.getPersonRealId().get(0).setName(name);
        }
    }

}
