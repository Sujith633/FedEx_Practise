package com.example.R01BatchProcessing;
 
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
public class PersonItemProcessor implements ItemProcessor<Person, Person>{

	 private Set<Long> commonRecords = new HashSet<>();
	    @Override
	    public Person process(Person item) throws Exception {
	    	
	    	 if(commonRecords.contains(item.getID())) {
	 			 System.out.println(item);
	 			 return item;
	 		 }
	 		 commonRecords.add(item.getID());
			return null;
		 		  
		 	 }
	        
	    }
