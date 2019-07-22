package io.simpolor.batch.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class ShareTwoTasklet implements Tasklet, StepExecutionListener {

    private String shareData;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        log.info("SampleTwoTasklet start..");

        log.info("> shareData : {}", shareData);

        log.info("SampleTwoTasklet done..");

        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        Object obj =
                stepExecution
                        .getJobExecution()
                        .getExecutionContext()
                        .get("share_data");

        shareData = (obj != null) ? (String)obj : "";
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
                .getJobExecution()
                .getExecutionContext().put("share_data", null);
        return ExitStatus.COMPLETED;
    }
}
