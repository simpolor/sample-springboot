package io.simpolor.batch.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class ShareOneTasklet implements Tasklet, StepExecutionListener {

    private String shareData;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        log.info("ShareOneTasklet start..");


        log.info("> Set shareData");
        shareData = "This is shareDate";


        log.info("ShareOneTasklet done..");

        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) { }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
                .getJobExecution()
                .getExecutionContext().put("share_data", shareData);
        return ExitStatus.COMPLETED;
    }
}
