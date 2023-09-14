package org.jhay.services.impl;

import lombok.RequiredArgsConstructor;
import org.jhay.application.dto.request.PersonRequest;
import org.jhay.application.dto.response.PersonResponse;
import org.jhay.common.exception.exceptions.PersonAlreadyExistException;
import org.jhay.common.exception.exceptions.PersonNotFoundException;
import org.jhay.domain.model.Person;
import org.jhay.domain.repository.PersonRepository;
import org.jhay.services.PersonService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public PersonResponse addPerson(PersonRequest personRequest){
        if(personRepository.existsByName(personRequest.getName())){
            throw new PersonAlreadyExistException
                    ("Person with name "+personRequest.getName()+" already exist"+"\n Please choose a different name...");
        }
        Person person = personRepository.save(Person.builder().name(personRequest.getName()).build());
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .build();
    }

    @Override
    public PersonResponse getPerson(Long personId){
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person with id "+personId+" does not exist"));
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .build();
    }

    @Override
    public PersonResponse getPersonByName(String name){
        Person person = personRepository.findByName(name)
                .orElseThrow(() -> new PersonNotFoundException("Person with name: "+name+" does not exist"));
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .build();
    }

    @Override
    public PersonResponse modifyPerson(Long personId, PersonRequest personRequest){
        if(personRepository.existsByName(personRequest.getName())){
            throw new PersonAlreadyExistException
                    ("Person with name "+personRequest.getName()+" already exist"+"\n Please choose a different name...");
        }
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person with id "+personId+" does not exist"));
        person.setName(personRequest.getName());
        Person modifiedPerson = personRepository.save(person);
        return PersonResponse.builder()
                .id(modifiedPerson.getId())
                .name(modifiedPerson.getName())
                .build();
    }

    @Override
    public String deletePerson(Long personId){
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person with id "+personId+" does not exist"));
        personRepository.delete(person);
        return "Person with id "+personId+" Deleted Successfully";
    }

    @Override
    public String deletePersonByName(String name){
        Person person = personRepository.findByName(name)
                .orElseThrow(() -> new PersonNotFoundException("Person with name: "+name+" does not exist"));
        personRepository.delete(person);
        return "Person with name: "+name+" Deleted Successfully";
    }
}
