package org.jhay.domain.repository;

import org.jhay.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByName(String name);

    Optional<Person> findByName(String name);
}
