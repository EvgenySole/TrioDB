package com.example.triodb.persons_history;

import com.example.triodb.persons.PersonService;
import com.example.triodb.persons.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "persons_history")
public class PersonsHistoryController {
    public final PersonsHistoryService personsHistoryService;
    public final PersonsHistoryRepository personsHistoryRepository;
    @Autowired
    public PersonsHistoryController(PersonsHistoryService personsHistoryService,
                                    PersonsHistoryRepository personsHistoryRepository) {
        this.personsHistoryService = personsHistoryService;
        this.personsHistoryRepository = personsHistoryRepository;
    }

    @GetMapping
    public List<PersonsHistory> getPersonsHistory(){
        return personsHistoryService.getPersonsHistory();
    }

    @GetMapping(path ="{persons_historyId}")
    public PersonsHistory getPersonHistory(@PathVariable("persons_historyId") Long personHistoryId){
        List<PersonsHistory> employees = personsHistoryRepository.findAllByPersonRealId(personHistoryId);

        employees.forEach((employee) -> {
            System.out.println(employee.getPersonRealId());
            System.out.println(employee.getName());
            System.out.println(employee.getPassport());
        });
        List<PersonsHistory> employees2 = null;
        employees2.add(employees.get(employees.size()-1));
        //employees2.add(employees.get(0));
        return personsHistoryService.getPersonHistory(personHistoryId).get(2);
        //return employees;
    }
    @PostMapping
    public void registerNewPersonsHistory(@RequestBody PersonsHistory personsHistory){
        personsHistoryService.addNewPersonsHistory(personsHistory);
    }
    @DeleteMapping(path = "{persons_historyId}")
    public void deletePersonsHistory(@PathVariable("persons_historyId") Long personId){
        personsHistoryService.deletePersonsHistory(personId);
    }
    @PutMapping(path = "{persons_historyId}")
    public void updatePersonsHistory(
            @PathVariable("persons_historyId") Long personhId,
            @RequestParam(required = false) Long personId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String passport,
            @RequestParam(required = false) Date begDate,
            @RequestParam(required = false) Date endDate
    )
    {
//        personsHistoryService.updatePersonsHistory(personhId,
//                personId, name, passport, begDate, endDate);
    }



}
