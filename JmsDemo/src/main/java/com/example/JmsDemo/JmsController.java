package com.example.JmsDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmsController {
	
	@Autowired
	private JmsProducer jmsProducer;

	@GetMapping("/send")
	public void sendMessage() {
	  jmsProducer.sendMessage("Hello, World!");
	}
}
