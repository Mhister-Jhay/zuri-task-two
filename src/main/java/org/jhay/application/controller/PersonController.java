package org.jhay.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jhay.application.dto.request.PersonRequest;
import org.jhay.application.dto.response.PersonResponse;
import org.jhay.common.validations.ValidName;
import org.jhay.common.validations.ValidNumber;
import org.jhay.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponse> addPerson(@Valid @RequestBody PersonRequest personRequest){
        return new ResponseEntity<>(personService.addPerson(personRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonResponse> getPerson(@ValidNumber @PathVariable Long personId){
        return new ResponseEntity<>(personService.getPerson(personId), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PersonResponse> getPersonByName(@ValidName @RequestParam("name") String name){
        return new ResponseEntity<>(personService.getPersonByName(name), HttpStatus.OK);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonResponse> modifyPerson(@ValidNumber @PathVariable Long personId,@Valid  @RequestBody PersonRequest personRequest){
        return new ResponseEntity<>(personService.modifyPerson(personId, personRequest), HttpStatus.OK);
    }
    @DeleteMapping("/{personId}")
    public ResponseEntity<String> deletePerson(@Valid @PathVariable Long personId){
        return new ResponseEntity<>(personService.deletePerson(personId), HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String> deletePersonByName(@ValidName @RequestParam("name") String name){
        return new ResponseEntity<>(personService.deletePersonByName(name), HttpStatus.OK);
    }
}
