package com.accion.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.accion.RabbitConfiguration;
import com.accion.model.postgres.Person;

@Component
@RabbitListener(queues = RabbitConfiguration.QUEUE_NAME)
public class RabbitReceiver {

	@RabbitHandler
	public void receive(final Person person) {
		System.out.println(" Received " + person);
	}
}
