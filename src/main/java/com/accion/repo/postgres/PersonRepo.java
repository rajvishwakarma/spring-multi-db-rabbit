package com.accion.repo.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accion.model.postgres.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
}
