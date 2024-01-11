package com.example.S01SpringBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchScheduling {
	
	@Autowired
    JobLauncher jobLauncher;
	
    @Autowired
    Job importUserJob;
 
    @Scheduled(cron = "0 */1 * * * ?")
    public void perform() throws Exception {
    	System.out.println("TAsk Sceduled");
        JobParameters params = new JobParametersBuilder()
            .addString("JobID", String.valueOf(System.currentTimeMillis()))
            .toJobParameters();
        	JobExecution jb = jobLauncher.run(importUserJob, params);
        	if(jb.getStatus().isUnsuccessful()) {
        		System.out.println("Unsucess");
        	}
        	else
        		System.out.println("Successfull");
    }
}
