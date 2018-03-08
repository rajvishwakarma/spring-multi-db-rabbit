package com.accion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accion.model.mysql.Person;
import com.accion.repo.mysql.PersonRepoMS;
import com.accion.repo.postgres.PersonRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccionDemoApplicationTests {

	@Autowired
	private PersonRepoMS personRepo;

	@Autowired
	private PersonRepo personRepoPG;

	@Test
	public void whenCreatingPerson_thenCreatedPostGres() {
		com.accion.model.postgres.Person person = new com.accion.model.postgres.Person();
		person.setName("PostGres");
		person.setAge(30);
		person = personRepoPG.save(person);

		com.accion.model.postgres.Person personDB = personRepoPG.findOne(person.getId());

		assertNotNull(personDB);
		assertEquals(personDB.getId(), person.getId());
	}

	@Test
	public void whenCreatingPerson_thenCreatedMySql() {
		Person person = new Person();
		person.setName("MySQL");
		person.setAge(29);
		person = personRepo.save(person);

		Person personDB = personRepo.findOne(person.getId());

		assertNotNull(personDB);
		assertEquals(personDB.getId(), person.getId());
	}
}
