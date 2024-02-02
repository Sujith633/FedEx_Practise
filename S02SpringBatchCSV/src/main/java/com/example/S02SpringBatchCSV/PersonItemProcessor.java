package com.example.S02SpringBatchCSV;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private Set<Long> commonRecords = new HashSet<>();
    
//    @Override
//    public Person process(Person item) throws Exception {
//    	
//    	 if(commonRecords.contains(item.getId())) {
// 			 System.out.println(item);
// 			 return item;
// 		 }
// 		 commonRecords.add(item.getId());
//		return null;
//	 		  
//	 	 }
    
    private Set<Long> existingRecords = new HashSet<>();
    
    
    public void afterPropertiesSet() throws Exception {
    	Person item;
        // Loading existing records from the output file
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("outputdata.csv"));
        reader.setLineMapper(new DefaultLineMapper<Person>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[]{"id", "name", "email","phonenum","address","age","salary"});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
                    {
                        setTargetType(Person.class);
                    }
                });
            }
        });
        
        reader.open(new ExecutionContext());
        while ((item = reader.read()) != null) {
            existingRecords.add(item.getId());
        }
//        System.out.println("Hello"+existingRecords);
        reader.close();
    }
 	
    
    @Override
    public Person process(Person item) throws Exception {
        if (commonRecords.contains(item.getId()) && !existingRecords.contains(item.getId())) {
//            System.out.println(existingRecords.contains(item.getId()));
            afterPropertiesSet();
//            existingRecords.add(item.getId());
            return item;
        } else {
        	
            commonRecords.add(item.getId());
            return null;
        }
}
}
