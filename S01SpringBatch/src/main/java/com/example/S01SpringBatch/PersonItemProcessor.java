package com.example.S01SpringBatch;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person>{

	 private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);
	 
	  private Set<Person> existingRecords = new HashSet<>();
	  @Override
	  public Person process(final Person person) {
		  
		  try {
			  	final String firstName = person.firstName().toUpperCase();
			    final String lastName = person.lastName().toUpperCase();

			    final Person transformedPerson = new Person(firstName, lastName);

			    log.info("Converting (" + person + ") into (" + transformedPerson + ")");
			    
			    if(existingRecords.contains(transformedPerson)) {
			    	return null;
			    }
			    else {
			    	existingRecords.add(transformedPerson);
			    	return transformedPerson;
			    }
		  } catch(Exception e) {
			  System.out.println("Error");
			  log.error("Error processing the record :"+person,e);
			  return null;
		  }
	  }
}
