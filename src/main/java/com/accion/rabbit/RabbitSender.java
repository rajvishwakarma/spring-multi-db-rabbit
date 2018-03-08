package com.accion.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.accion.RabbitConfiguration;
import com.accion.model.postgres.Person;

@Component
@EnableScheduling
public class RabbitSender {

	@Autowired
	private RabbitTemplate template;

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {

		Person person = new Person();
		person.setAge(30);
		person.setId(1);
		person.setName("Rabbit");

		template.convertAndSend(RabbitConfiguration.EXCHANGE_NAME, RabbitConfiguration.ROUTING_KEY, person);

		System.out.println(" Sent " + person);
	}
}
