package io.simpolor.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SampleNextConditionalJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepNextConditionalJob() {
        return jobBuilderFactory.get("stepNextConditionalJob")
                .start(conditionalStep1())
                    .on("FAILED") // FAILED 일 경우
                    .to(conditionalStep3()) // step3으로 이동
                    .on("*") // step3의 결과 관계 없이
                    .end() // step3으로 이동하면 Flow가 종료
                .from(conditionalStep1()) // step1로부터
                    .on("*") // FAILED 외에 모든 경우
                    .to(conditionalStep2()) // step2로 이동
                    .next(conditionalStep3()) // step2가 정상 종료되면 step3으로 이동
                    .on("*") // step3의 결과 관계 없이
                    .end() // step3으로 이동하면 Flow가 종료
                .end() // Job 종료
                .build();
    }

    @Bean
    public Step conditionalStep1() {
        return stepBuilderFactory.get("conditionalStep1")
                .tasklet((contribution, chunkContext) -> {
                    log.info("> conditionalStep1");
                    // ExitStatus를 FAILED로 지정한다. 해당 status를 보고 flow가 진행된다.
                    contribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step conditionalStep2() {
        return stepBuilderFactory.get("conditionalStep2")
                .tasklet((contribution, chunkContext) -> {
                    log.info("> conditionalStep2");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step conditionalStep3() {
        return stepBuilderFactory.get("conditionalStep3")
                .tasklet((contribution, chunkContext) -> {
                    log.info("> conditionalStep3");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
