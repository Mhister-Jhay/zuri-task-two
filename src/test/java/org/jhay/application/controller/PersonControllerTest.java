package org.jhay.application.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jhay.application.dto.request.PersonRequest;
import org.jhay.application.dto.response.PersonResponse;
import org.jhay.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void addPerson_shouldReturnCreatedStatus() throws Exception {
        // Arrange
        PersonRequest personRequest = PersonRequest.builder()
                .name("John Wick")
                .build();
        PersonResponse expectedResponse = PersonResponse.builder()
                .id(1L)
                .name("John Wick")
                .build();
        when(personService.addPerson(personRequest)).thenReturn(expectedResponse);
        // Act & Assert
        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(expectedResponse.getName()));
    }
    @Test
    void addPerson_missingFields() throws Exception{
        PersonRequest request = new PersonRequest();

        mockMvc.perform(post("/api")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name must not be empty"));
    }

    @Test
    void addPerson_numericFields() throws Exception{
        PersonRequest request = new PersonRequest("John 123");

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name must contain only letters and spaces"));
    }

    @Test
    void addPerson_minimumFields() throws Exception{
        PersonRequest request = new PersonRequest("Jo");

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name must be 3 characters minimum"));
    }

    @Test
    void getPerson_shouldReturnOkStatus() throws Exception {
        // Arrange
        Long personId = 1L;
        PersonResponse expectedResponse = PersonResponse.builder()
                .id(1L)
                .name("John Wick")
                .build();
        when(personService.getPerson(personId)).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(get("/api/{personId}", personId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(expectedResponse.getName()));
        verify(personService).getPerson(personId);
        assertEquals(personId, expectedResponse.getId());
    }

    @Test
    void getPerson_missingPath() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getPerson_invalidPath() throws Exception{
        mockMvc.perform(get("/api/{personId}","Abel"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getPersonBYName_shouldReturnOkStatus() throws Exception {
        // Arrange
        String name = "John Wick";
        PersonResponse expectedResponse = PersonResponse.builder()
                .id(1L)
                .name("John Wick")
                .build();
        when(personService.getPersonByName(name)).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(get("/api").param("name",name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(expectedResponse.getName()));
        verify(personService).getPersonByName(name);
        assertEquals(name, expectedResponse.getName());
    }

    @Test
    void modifyPerson_shouldReturnOkStatus() throws Exception {
        // Arrange
        Long personId = 1L;
        PersonRequest personRequest = PersonRequest.builder()
                .name("John Wick")
                .build();
        PersonResponse expectedResponse = PersonResponse.builder()
                .id(1L)
                .name("John Wick")
                .build();
        when(personService.modifyPerson(personId, personRequest)).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(put("/api/{personId}", personId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(expectedResponse.getName()));
    }

    @Test
    void deletePerson_shouldReturnOkStatus() throws Exception {
        // Arrange
        Long personId = 1L;
        String expectedResponse = "Person deleted";
        when(personService.deletePerson(personId)).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(delete("/api/{personId}", personId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void deletePersonByName_shouldReturnOkStatus() throws Exception {
        // Arrange
        String name = "John Wick";
        String expectedResponse = "Person deleted";
        when(personService.deletePersonByName(name)).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(delete("/api").param("name",name))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}