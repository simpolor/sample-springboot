package io.simpolor.batch.config;

import io.simpolor.batch.tasks.MyTaskOne;
import io.simpolor.batch.tasks.MyTaskTwo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean(name = "demoJobOne")
    public Job demoJobOne(){
        return jobs.get("demoJobOne")
                .start(stepOne())
                .next(stepTwo())
                .build();
    }

    @Bean(name = "demoJobTwo")
    public Job demoJobTwo(){
        return jobs.get("demoJobTwo")
                .flow(stepOne())
                .build()
                .build();
    }

    @Bean
    public Step stepOne(){
        return steps.get("stepOne")
                .tasklet(new MyTaskOne())
                .build();
    }

    @Bean
    public Step stepTwo(){
        return steps.get("stepTwo")
                .tasklet(new MyTaskTwo())
                .build();
    }


}
