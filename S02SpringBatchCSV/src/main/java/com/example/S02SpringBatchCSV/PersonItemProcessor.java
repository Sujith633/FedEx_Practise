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

//	 private Set<String> existingRecords = new HashSet<>();
	 private Set<Long> commonRecords = new HashSet<>();
	 
//	 	public void afterPropertiesSet() throws Exception {
//	 		int i = 0;
//	        // Load existing records from the output file
//	        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
//	        reader.setResource(new FileSystemResource("outputdata.csv"));
//	        reader.setLineMapper(new DefaultLineMapper<Person>() {
//	            {
//	                setLineTokenizer(new DelimitedLineTokenizer() {
//	                    {
//	                        setNames(new String[]{ "firstName", "lastName"});
//	                    }
//	                });
//	                setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
//	                    {
////	                    	i++;
//	                        setTargetType(Person.class);
//	                        log.info("i"+Person.class);
//	                    }
//	                });
//	            }
//	        });
//	        
//	       
//
//	        Person item;
//	        reader.open(new ExecutionContext());
//	        while ((item = reader.read()) != null) {
//	            existingRecords.add(item.getFirstName());
//	        }
////	        System.out.println("Hello"+existingRecords);
//	        reader.close();
//	    }
	 	
	 	 public  Person commonNames(Person item) throws Exception{
	 		 
	 		 if(commonRecords.contains(item.getId())) {
	 			 System.out.println(item);
	 			 return item;
	 		 }
	 		 commonRecords.add(item.getId());
			return null;
	 		 
	 	 }

	    @Override
	    public Person process(Person item) throws Exception {
	    	return commonNames(item);	
	    }
}
