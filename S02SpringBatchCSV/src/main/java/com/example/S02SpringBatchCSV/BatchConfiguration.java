package com.example.S02SpringBatchCSV;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration

public class BatchConfiguration {
	
//	 private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);
	 
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
	
	
	@Bean
	public MultiResourceItemReader<Person> multiResourceItemReader() {
	    MultiResourceItemReader<Person> resourceItemReader = new MultiResourceItemReader<Person>();
	    resourceItemReader.setResources(new Resource[] { new ClassPathResource("sample-data.csv"), new ClassPathResource("student-data.csv") });
	    resourceItemReader.setDelegate(reader());
	    return resourceItemReader;
	}
	
	
	@Bean
	public FlatFileItemReader<Person> reader() {
		System.out.println("Reader");
	  return new FlatFileItemReaderBuilder<Person>()
	    .name("MultiResourceItemReader")
	    .delimited()
	    .names("id", "name", "email","phonenum","address","age","salary")
	    .targetType(Person.class)
	    .build();
	}

	@Bean
	public FlatFileItemWriter<Person> writer(List<Person> items) {

	    FlatFileItemWriter<Person> writer = new FlatFileItemWriter<Person>();
//	    writer.open(new ExecutionContext());
	    writer.setResource(new FileSystemResource("outputdata.csv"));
	    writer.setLineAggregator(new DelimitedLineAggregator<Person>() {
	        {
	            setDelimiter(",");
	            setFieldExtractor(new BeanWrapperFieldExtractor<Person>() {
	                {
	                    setNames(new String[] {"id", "name", "email","phonenum","address","age","salary"});
	                }
	            });
	        }
	    });
	    System.out.println("Output csv");
	    return writer;
	}

	@Bean
	public Job importUserJob(JobRepository jobRepository,Step step1) {
		System.out.println("Job Repository");
	  return new JobBuilder("importUserJob", jobRepository)
		.incrementer(new RunIdIncrementer())
//	    .listener(listener)
	    .start(step1)
	    .build();
	}

	@Bean
	public Step step1(FlatFileItemReader<Person> reader,PersonItemProcessor processor,FlatFileItemWriter<Person> writer,JobRepository jobRepository, DataSourceTransactionManager transactionManager) 
	{
		System.out.println("step1");
	  return new StepBuilder("step1", jobRepository)
	    .<Person, Person> chunk(30, transactionManager)
	    .reader(multiResourceItemReader())
	    .processor(processor)
	    .writer(writer)
	    .build();
	}
	
	@Bean
	public PersonItemProcessor processor() {
		System.out.println("Processor");
	  return new PersonItemProcessor();
	}
}
