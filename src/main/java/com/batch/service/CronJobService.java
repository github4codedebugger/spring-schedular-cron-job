package com.batch.service;

import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CronJobService {


    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;


    @SneakyThrows
    @Scheduled(fixedRate = 15000)
    @Async
    public void startBatch() {

        JobParameters jobParameter = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(job, jobParameter);

        System.out.println("batch started...");
    }
}


