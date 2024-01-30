package com.example.R01BatchProcessing;
 
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
 
@Component
@EnableScheduling
public class MyScheduler {
	@Autowired
	private JobLauncher  jobLauncher;
	@Autowired
	private Job importUserJob;
	 @Scheduled(cron="*/100 * * * * *")
	 public void myScheduler()
	 {
		 JobParameters  jobParameters=new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();
				 try {
					 JobExecution  jobExecution=jobLauncher.run(importUserJob, jobParameters);
					 System.out.println("Job Status:::"+jobExecution.getStatus());
				 }
				 catch(JobExecutionAlreadyRunningException|JobRestartException| JobInstanceAlreadyCompleteException|JobParametersInvalidException e) {
					
					e.printStackTrace();
				 }
	 }
}