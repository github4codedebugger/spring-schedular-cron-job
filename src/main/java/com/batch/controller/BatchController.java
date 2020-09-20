package com.batch.controller;

import com.batch.entity.Contract;
import com.batch.repo.ContractRepo;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class BatchController {

    @Autowired
    private ContractRepo repo;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @GetMapping("/insert")
    public String saveDummyData() {
        List<Contract> contractList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Contract contract = new Contract();
            contract.setHolderName("name-" + i);
            contract.setDuration(new Random().nextInt());
            contract.setAmount(new Random().nextInt(500000));
            Date date = new Date();
            date.setDate(new Random().nextInt(30));
            date.setMonth(new Random().nextInt(12));
            date.setYear(new Random().nextInt(2020));
            contract.setCreationDate(date);
            contract.setStatus("InProgress");
            contractList.add(contract);
        }
        repo.saveAll(contractList);
        return "saved successfully";
    }

    @GetMapping("/start-batch")
    @SneakyThrows
    public String startBatch() {

        JobParameters jobParameter = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(job, jobParameter);

        return "batch started...";
    }

}
