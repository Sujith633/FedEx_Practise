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

	 private Set<Long> commonRecords = new HashSet<>();
	 private Set<Long> existingRecords = new HashSet<>();
	 
//	 	public Person afterPropertiesSet(Person item) throws Exception {
//	        // Loading existing records from the output file
//	        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
//	        reader.setResource(new FileSystemResource("outputdata.csv"));
//	        reader.setLineMapper(new DefaultLineMapper<Person>() {
//	            {
//	                setLineTokenizer(new DelimitedLineTokenizer() {
//	                    {
//	                        setNames(new String[]{"id", "name", "email","phonenum","address","age","salary"});
//	                    }
//	                });
//	                setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
//	                    {
//	                        setTargetType(Person.class);
//	                    }
//	                });
//	            }
//	        });
//	        
////	        System.out.println(existingRecords);
////	        if (existingRecords.contains(item)) {
////	        	return item;
////	        }
////	        else {
////	        	existingRecords.add(item.getId());
////	        	return null;
////	        }
//	        	
//	        reader.open(new ExecutionContext());
//	        while ((item = reader.read()) != null) {
//	            existingRecords.add(item.getId());
//	        }
//	        reader.close();
//			return null;
//			
//	    }
//	 	
//	 	 public  Person commonNames(Person item) throws Exception{
//	 		 if(commonRecords.contains(item.getId())) {
//	 			 System.out.println(item);
//	 			 return item;
//	 		 }
//	 		 commonRecords.add(item.getId());
////	 		 System.out.println(commonRecords);
//			return null;
//	 		 
//	 	 }
//
//	 	 
	    @Override
	    public Person process(Person item) throws Exception {
//	    	afterPropertiesSet(item);
	    	if(existingRecords.contains(item.getId())) {
	 			 System.out.println(item);
	 			 return item;
	 		 }
	    	else {
	    		existingRecords.add(item.getId());
				return null;
	    	}	
	    }
}
