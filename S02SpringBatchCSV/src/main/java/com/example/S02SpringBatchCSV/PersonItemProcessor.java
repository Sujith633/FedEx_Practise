package com.example.S02SpringBatchCSV;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

public class PersonItemProcessor implements ItemProcessor<Person, Person>{

	 private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

	 private Set<String> existingRecords = new HashSet<>();
	 
	 	public void afterPropertiesSet() throws Exception {
	        // Load existing records from the output file
	        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
	        reader.setResource(new FileSystemResource("outputdata.csv"));
	        reader.setLineMapper(new DefaultLineMapper<Person>() {
	            {
	                setLineTokenizer(new DelimitedLineTokenizer() {
	                    {
	                        setNames(new String[]{ "firstName", "lastName"}); 
	                    }
	                });
	                setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
	                    {
	                        setTargetType(Person.class);
	                    }
	                });
	            }
	        });

	        Person item;
	        reader.open(new ExecutionContext());
	        while ((item = reader.read()) != null) {
	            existingRecords.add(item.getFirstName());
	        }
//	        System.out.println("Hello"+existingRecords);
	        reader.close();
	    }

	    @Override
	    public Person process(Person item) throws Exception {
	    	afterPropertiesSet();
	        if (!existingRecords.contains(item.getFirstName())) {
	            existingRecords.add(item.getFirstName());
	            return item;
	        } else {
	            return null; 
	        }
	    }
}
