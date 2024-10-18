package com.example.triodb.persons_history;

import com.example.triodb.persons.PersonRepository;
import com.example.triodb.persons.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class PersonsHistoryService {

    public final PersonsHistoryRepository personsHistoryRepository;

    @Autowired
    public PersonsHistoryService(PersonsHistoryRepository personsHistoryRepository) {
        this.personsHistoryRepository = personsHistoryRepository;
    }
    public List<PersonsHistory> getPersonsHistory() {
        return personsHistoryRepository.findAll();
    }

    public List<PersonsHistory> getPersonHistory(Long personsHistoryId) {
        boolean exists = personsHistoryRepository.existsById(personsHistoryId);
        if (!exists){
            throw new IllegalStateException("PersonHistory with id " + personsHistoryId + " doesn't exsist");
        }
        return personsHistoryRepository.findAllById(Collections.singleton(personsHistoryId));
    }

    public void addNewPersonsHistory(PersonsHistory personsHistory){
        personsHistoryRepository.save(personsHistory);
        System.out.println(personsHistory);
    }

    public void deletePersonsHistory(Long personsHistoryId){
        boolean exists = personsHistoryRepository.existsById(personsHistoryId);
        if (!exists){
            throw new IllegalStateException("PersonHistory with id " + personsHistoryId + " doesn't exsist");
        }
        personsHistoryRepository.deleteById(personsHistoryId);
        System.out.println(personsHistoryId);
    }

//    @Transactional
//    public void updatePersonsHistory(Long personhId, Long personId,
//                             String name, String passport, Date begDate, Date endDate) {
//        PersonsHistory personsHistory = personsHistoryRepository.findById(personhId).orElseThrow(
//                () -> new IllegalStateException("PersonHistory with id " + personhId +
//                        "doesn't exists")
//        );
//
//        if (personhId != null  && !Objects.equals(personsHistory.getPersonhId(), personhId)) {
//            personsHistory.setPersonhId(personhId);
//        }
//        if (personId != null  && !Objects.equals(personsHistory.getPersonId(), personId)) {
//            personsHistory.setPersonId(personId);
//        }
//        if (name != null  && !Objects.equals(personsHistory.getName(), name)) {
//            personsHistory.setName(name);
//        }
//        if (passport != null  && !Objects.equals(personsHistory.getPassport(), passport)) {
//            personsHistory.setPassport(passport);
//        }
//        if (begDate != null  && !Objects.equals(personsHistory.getBegDate(), begDate)) {
//            personsHistory.setBegDate(begDate);
//        }
//        if (endDate != null  && !Objects.equals(personsHistory.getEndDate(), endDate)) {
//            personsHistory.setEndDate(endDate);
//        }
//    }

}
