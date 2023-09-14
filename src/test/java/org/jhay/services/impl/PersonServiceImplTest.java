package org.jhay.services.impl;

import org.jhay.application.dto.request.PersonRequest;
import org.jhay.application.dto.response.PersonResponse;
import org.jhay.common.exception.exceptions.PersonAlreadyExistException;
import org.jhay.common.exception.exceptions.PersonNotFoundException;
import org.jhay.domain.model.Person;
import org.jhay.domain.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void addPerson_success() {
        // Arrange
        PersonRequest request = PersonRequest.builder()
                .name("John")
                .build();
        Person person = Person.builder()
                .id(1L)
                .name("John")
                .build();
        when(personRepository.existsByName("John")).thenReturn(false);
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);

        // Act
        PersonResponse response = personService.addPerson(request);

        // Assert
        assertEquals(1L, response.getId());
        assertEquals("John", response.getName());
    }

    @Test
    void addPerson_alreadyExists() {
        // Arrange
        PersonRequest request = new PersonRequest("John");
        when(personRepository.existsByName("John")).thenReturn(true);

        // Act and Assert
        PersonAlreadyExistException thrown =
                assertThrows(PersonAlreadyExistException.class, () -> {
                    personService.addPerson(request);
                });
    }

    @Test
    void getPerson_success() {
        // Arrange
        Person person = new Person(1L, "John");
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        // Act
        PersonResponse response = personService.getPerson(1L);

        // Assert
        assertEquals(1L, response.getId());
        assertEquals("John", response.getName());
    }

    @Test
    void getPerson_notFound() {
        // Arrange
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        PersonNotFoundException thrown =
                assertThrows(PersonNotFoundException.class, () -> {
                    personService.getPerson(1L);
                });
    }

    @Test
    void getPersonByName_success() {
        // Arrange
        Person person = new Person(1L, "John");
        when(personRepository.findByName("John")).thenReturn(Optional.of(person));

        // Act
        PersonResponse response = personService.getPersonByName("John");

        // Assert
        assertEquals(1L, response.getId());
        assertEquals("John", response.getName());
    }

    @Test
    void getPersonByName_notFound() {
        // Arrange
        when(personRepository.findByName("John")).thenReturn(Optional.empty());

        // Act and Assert
        PersonNotFoundException thrown =
                assertThrows(PersonNotFoundException.class, () -> {
                    personService.getPersonByName("John");
                });
    }
    // Tests for modifyPerson

    @Test
    void modifyPerson_success() {

        // Arrange
        Person existingPerson = new Person(1L, "John");
        Person modifiedPerson = new Person(1L, "Jane");
        PersonRequest request = new PersonRequest("Jane");

        when(personRepository.findById(1L)).thenReturn(Optional.of(existingPerson));
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(modifiedPerson);

        // Act
        PersonResponse response = personService.modifyPerson(1L,request);

        // Assert
        assertEquals(modifiedPerson.getId(), response.getId());
        assertEquals(modifiedPerson.getName(), response.getName());

    }

    @Test
    void modifyPerson_notFound() {

        // Arrange
        PersonRequest request = new PersonRequest( "Jane");
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        PersonNotFoundException thrown = assertThrows(PersonNotFoundException.class, () -> {
            personService.modifyPerson(1L, request);
        });

    }


// Tests for deletePerson

    @Test
    void deletePerson_success() {

        // Arrange
        Person existingPerson = new Person(1L, "John");
        when(personRepository.findById(1L)).thenReturn(Optional.of(existingPerson));
        String expectedOutput = "Person with id "+existingPerson.getId()+" Deleted Successfully";

        // Act
        String actualOutput = personService.deletePerson(1L);

        assertEquals(expectedOutput,actualOutput);

    }

    @Test
    void deletePerson_notFound() {

        // Arrange
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        PersonNotFoundException thrown = assertThrows(PersonNotFoundException.class, () -> {
            personService.deletePerson(1L);
        });

    }

    @Test
    void deletePersonByName_success() {

        // Arrange
        Person existingPerson = new Person(1L, "John");
        when(personRepository.findByName("John")).thenReturn(Optional.of(existingPerson));
        String expectedOutput = "Person with name: "+existingPerson.getName()+" Deleted Successfully";

        // Act
        String actualOutput = personService.deletePersonByName("John");

        assertEquals(expectedOutput,actualOutput);

    }

    @Test
    void deletePersonByName_notFound() {

        // Arrange
        when(personRepository.findByName("John")).thenReturn(Optional.empty());

        // Act and Assert
        PersonNotFoundException thrown = assertThrows(PersonNotFoundException.class, () -> {
            personService.deletePersonByName("John");
        });

    }
}
