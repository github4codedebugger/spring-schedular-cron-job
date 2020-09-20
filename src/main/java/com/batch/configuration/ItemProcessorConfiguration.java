package com.batch.configuration;

import com.batch.entity.Contract;
import com.batch.entity.ContractHistory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@Slf4j
public class ItemProcessorConfiguration {

    private AtomicInteger count = new AtomicInteger();

    private ObjectMapper mapper = new ObjectMapper();

    @Bean
    public ItemProcessor<Contract, ContractHistory> itemProcessor() {
        return new ItemProcessor<Contract, ContractHistory>() {
            @Override
            public ContractHistory process(Contract contract) throws Exception {
                log.info("processing the data " + contract.getContractId() + " Record No :" + count.incrementAndGet());
                return mapper.convertValue(contract, ContractHistory.class);
            }
        };
    }
}
