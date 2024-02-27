package com.example.S01SpringBatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.core.io.ClassPathResource;

public class S01SpringBatchTest {

	
	@Test
	public void readertest() throws Exception {

		FlatFileItemReader<Person> reader = new FlatFileItemReaderBuilder<Person>()
			    .name("personItemReader")
			    .resource(new ClassPathResource("testcases.csv"))
			    .delimited()
			    .names("firstName", "lastName")
			    .targetType(Person.class)
			    .build();
		
		reader.open(new ExecutionContext());
		Person item = reader.read();
		
		assertNotNull(item);
		assertEquals("value", item.firstName());
	}
}
