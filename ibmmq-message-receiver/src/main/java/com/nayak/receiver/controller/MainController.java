package com.nayak.receiver.controller;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsOperations;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class MainController {
	@Autowired
	JmsOperations jmsOperations;

	@JmsListener(destination = "sample.queue")
	public void receiveMessage(final Message<String> message) throws JMSException {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		MessageHeaders headers = message.getHeaders();
		System.out.println("Application : headers received : " + headers);

		String response = message.getPayload();
		System.out.println("Application : response received : " + response);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
