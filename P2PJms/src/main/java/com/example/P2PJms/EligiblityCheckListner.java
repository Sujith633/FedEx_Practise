package com.example.P2PJms;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.JMSProducer;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Queue;


public class EligiblityCheckListner implements MessageListener {

	@Override
	public void onMessage(Message message) {

		ObjectMessage objectMessage = (ObjectMessage) message;
	
//		Patient patient = ((Patient) objectmessage).getObject();
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jc = cf.createContext()){
			
			InitialContext initialContext = new InitialContext();
			Queue replyQueue = (Queue) initialContext.lookup("queue,replyQueue");
			MapMessage replyMessage = jc.createMapMessage();
			Patient patient = (Patient)  objectMessage.getObject();
			
			String insuranceProvider = patient.getInsuranceProvider();
			
			System.out.println("Insurance Provider"+ insuranceProvider);
			if (insuranceProvider=="Blue Cross") {
				replyMessage.setBoolean("Eligible", true);
			}
			else {
				replyMessage.setBoolean("Eligible", false);
			}
			
			JMSProducer producer = jc.createProducer();
			producer.send(replyQueue, replyMessage);
			
		} catch (NamingException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
