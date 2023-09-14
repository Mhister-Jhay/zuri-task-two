package org.jhay.services;

import org.jhay.application.dto.request.PersonRequest;
import org.jhay.application.dto.response.PersonResponse;
import org.springframework.stereotype.Service;

@Service
public interface PersonService {
    PersonResponse addPerson(PersonRequest personRequest);

    PersonResponse getPerson(Long personId);

    PersonResponse getPersonByName(String name);

    PersonResponse modifyPerson(Long personId, PersonRequest personRequest);

    String deletePerson(Long personId);

    String deletePersonByName(String name);
}
