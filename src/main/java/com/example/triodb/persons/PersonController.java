package com.example.triodb.persons;

import com.example.triodb.chats.ChatService;
import com.example.triodb.chats.Chats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "persons")
public class PersonController {
    public final PersonService personService;
    public final ChatService chatService;
    @Autowired
    public PersonController(PersonService personService, ChatService chatService) {
        this.personService = personService;
        this.chatService = chatService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerPerson(@RequestBody Persons person) {
        return personService.savePerson(person);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginPerson(@RequestBody Persons person) {
        return personService.loginPerson(person);
    }
    @GetMapping
    public List<Persons> getPersons(){
        List<Persons> pers = personService.getPersons();
        for (int i = 0; i < pers.size(); i++){
            pers.get(i).setPassword("");
            pers.get(i).setLogin("");
            for (int j = 0; j < pers.size()-1; j++){
                if (pers.get(j).getPersonId() > pers.get(j+1).getPersonId()){
                    Persons temp = pers.get(j);
                    pers.set(j, pers.get(j+1));
                    pers.set(j+1, temp);
                }
            }
        }
        return pers;
    }

    @GetMapping(path ="{personId}")
    public List<Persons> getPerson(@PathVariable("personId") Long personId){
        List<Persons> pers = personService.getPerson(personId);
        for (int i = 0; i < pers.size(); i++){
            pers.get(i).setPassword("");
            pers.get(i).setLogin("");
            for (int j = 0; j < pers.size()-1; j++){
                if (pers.get(j).getPersonId() > pers.get(j+1).getPersonId()){
                    Persons temp = pers.get(j);
                    pers.set(j, pers.get(j+1));
                    pers.set(j+1, temp);
                }
            }
        }
        return pers;
    }
    @GetMapping(path ="/not-in-chats-with/{personId}")
    public List<Persons> getPersonsNotInChatsWith(@PathVariable("personId") Long personId){
        List<Persons> allPers = personService.getPersons();
        List<Persons> targetPers = new ArrayList<>();
        List<Chats> allChats = chatService.getChats();
        List<Long> chatsWithPers = new ArrayList<>();
        for (int i = 0; i < allChats.size(); i++){
            if (allChats.get(i).getPerson1Id().getPersonId() == personId ||
                    allChats.get(i).getPerson2Id().getPersonId() == personId){
                    chatsWithPers.add(allChats.get(i).getPerson2Id().getPersonId());
                    chatsWithPers.add(allChats.get(i).getPerson1Id().getPersonId());
            }
        }
        for (int i = 0; i < allPers.size(); i++){
            if (!chatsWithPers.contains(allPers.get(i).getPersonId())){
                targetPers.add(allPers.get(i));
            }
        }
        for (int i = 0; i < targetPers.size(); i++){
            targetPers.get(i).setPassword("");
            targetPers.get(i).setLogin("");
            for (int j = 0; j < targetPers.size()-1; j++){
                if (targetPers.get(j).getPersonId() > targetPers.get(j+1).getPersonId()){
                    Persons temp = targetPers.get(j);
                    targetPers.set(j, targetPers.get(j+1));
                    targetPers.set(j+1, temp);
                }
            }
        }
        return targetPers;
    }
    @PostMapping
    public ResponseEntity<String> registerNewPerson(@RequestBody Persons person){
        personService.addNewPerson(person);
        return ResponseEntity.ok("{\"line\":\"Person saved\"}");
    }
    @DeleteMapping(path = "{personId}")
    public void deletePerson(@PathVariable("personId") Long personId){
        personService.deletePerson(personId);
    }
    @PutMapping(path = "{personId}")
    public ResponseEntity<String> updatePerson(
            @PathVariable("personId") Long personId,
            @RequestParam(required = false) Integer gender,
            @RequestParam(required = false) Date birthdate,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String name
    )
    {
        personService.updatePerson(personId,
                gender, birthdate, login, password, role, active, name);
        return ResponseEntity.ok("{\"line\":\"Person updated\"}");
    }
}
