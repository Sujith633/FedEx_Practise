package com.example.R01BatchProcessing;


import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
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
	
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
	
		return new DataSourceTransactionManager(dataSource);
	}
	@Bean
	public MultiResourceItemReader<Person> multiResourceItemReader() {
	    MultiResourceItemReader<Person> resourceItemReader = new MultiResourceItemReader<Person>();
	    resourceItemReader.setResources(new Resource[] { new ClassPathResource("inputData.csv"), new ClassPathResource("student-details.csv") });
	    resourceItemReader.setDelegate(reader());
	    return resourceItemReader;
	}
	
	
	@Bean
	public FlatFileItemReader<Person> reader() {
		System.out.print("Reader");
	  return new FlatFileItemReaderBuilder<Person>()
	    .name("personItemReader")
	    .delimited()
	    .names("ID","Name","Email","Phone","Address","Age","Salary")
	    .targetType(Person.class)
	    .build();
	}
	@Bean
	public PersonItemProcessor processor() {
		System.out.print("Processor");
	  return new PersonItemProcessor();
	}
	
	@Bean
	public FlatFileItemWriter<Person> writer() {
	    FlatFileItemWriter<Person> writer = new FlatFileItemWriter<Person>();
	    writer.setResource(new FileSystemResource("output2.csv"));
	    writer.setLineAggregator(new DelimitedLineAggregator<Person>() {
	        {
	            setDelimiter(",");
	            setFieldExtractor(new BeanWrapperFieldExtractor<Person>() {
	                {
	                    setNames(new String[] {"ID","Name","Email","Phone","Address","Age","Salary"});
	                }
	            });
	        }
	    });
	    return writer;
	}
	@Bean
	public Job importUserJob(JobRepository jobRepository,Step step1) {
		System.out.println("Job Repository");
	  return new JobBuilder("importUserJob", jobRepository)	   
	    .start(step1)
	    .build();
	}

	@Bean
	public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
	          FlatFileItemReader<Person> reader, PersonItemProcessor processor,FlatFileItemWriter<Person> writer) {
		System.out.println("step1");
	  return new StepBuilder("step1", jobRepository)
	    .<Person, Person> chunk(10, transactionManager)
	    .reader(multiResourceItemReader())
	    .processor(processor)
	    .writer(writer)
	    .build();
	}
}