package io.simpolor.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Slf4j
// @Configuration
public class SampleParameterJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job parameterJob() {
        return jobBuilderFactory.get("parameterJob")
                .start(parameterStep1(null))
                .next(parameterStep2("20190305"))
                .build();
    }

    @Bean
    @JobScope
    public Step parameterStep1(@Value("#{jobParameters[date]}") String requestDate) {
        return stepBuilderFactory.get("parameterStep1")
                .tasklet((contribution, chunkContext) -> {
                    log.info("> parameterStep1");
                    throw new IllegalArgumentException("Failed step1");
                })
                .build();
    }

    @Bean
    @JobScope
    public Step parameterStep2(@Value("#{jobParameters[date]}") String request) {
        return stepBuilderFactory.get("parameterStep2")
                .tasklet((contribution, chunkContext) -> {
                    log.info("> parameterStep2");
                    log.info("> Request date : {}", request);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
