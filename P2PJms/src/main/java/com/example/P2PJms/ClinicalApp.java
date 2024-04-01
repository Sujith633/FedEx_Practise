package com.example.P2PJms;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.JMSProducer;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Queue;


public class ClinicalApp {
	
	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue,requestQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jc = cf.createContext()){
			
			JMSProducer producer = jc.createProducer();		
			
			ObjectMessage createObjectMessage = jc.createObjectMessage();
			Patient patient  = new Patient();
			
			patient.setId("01F");
			patient.setInsuranceProvider("Blue Cross ");
			patient.setName("Rashu");
			patient.setCopay(40d);
			patient.setAmounttobepaid(100d);
			
			createObjectMessage.setObject(patient);
			producer.send(requestQueue, createObjectMessage);
			
		}
	}

}
