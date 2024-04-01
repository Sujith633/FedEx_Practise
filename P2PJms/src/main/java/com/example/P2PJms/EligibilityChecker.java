package com.example.P2PJms;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Queue;


public class EligibilityChecker {
	
public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue,requestQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jc = cf.createContext()){
			
			JMSConsumer consumer = jc.createConsumer(requestQueue);
			
			consumer.setMessageListener(new EligiblityCheckListner());
			
		}
	}

}
