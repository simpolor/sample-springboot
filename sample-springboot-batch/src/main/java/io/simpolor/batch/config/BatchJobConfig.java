package io.simpolor.batch.config;

import io.simpolor.batch.tasks.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Slf4j
@EnableBatchProcessing
@Configuration
public class BatchJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Primary // jobLauncherController에서 Initialize 하기 위한 애노테이션
    @Bean(name = "sampleJob")
    public Job sampleJob(){
        return jobBuilderFactory.get("sampleJob")
                .start(sampleOneStep())
                .next(sampleTwoStep())
                .build();
    }

    @Bean(name = "sampleFlowJob")
    public Job sampleFlowJob(){
        return jobBuilderFactory.get("sampleFlowJob")
                .flow(sampleTaskletStep())
                .build()
                .build();
    }

    @Bean(name = "shareJob")
    public Job shareJob(){
        return jobBuilderFactory.get("shareJob")
                .start(shareOneStep())
                .next(shareTwoStep())
                .build();
    }

    @Bean
    public Step sampleOneStep(){
        return stepBuilderFactory.get("sampleOneStep")
                .tasklet(new SampleOneTasklet())
                .build();
    }

    @Bean
    public Step sampleTwoStep(){
        return stepBuilderFactory.get("sampleTwoStep")
                .tasklet(new SampleTwoTasklet())
                .build();
    }

    @Bean
    public Step sampleTaskletStep(){
        return stepBuilderFactory.get("sampleTaskletStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("SampleTaskletStep run");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step shareOneStep(){
        return stepBuilderFactory.get("shareOneStep")
                .tasklet(new ShareOneTasklet())
                .build();
    }

    @Bean
    public Step shareTwoStep(){
        return stepBuilderFactory.get("shareTwoStep")
                .tasklet(new ShareTwoTasklet())
                .build();
    }

}
