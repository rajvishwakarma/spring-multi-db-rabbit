package com.accion.repo.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accion.model.mysql.Person;

@Repository
public interface PersonRepoMS extends JpaRepository<Person, Integer>{
}
