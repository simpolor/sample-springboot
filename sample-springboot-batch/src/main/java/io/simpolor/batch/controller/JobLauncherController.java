package io.simpolor.batch.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class JobLauncherController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @GetMapping("/job/launcher")
    public String handle(@RequestParam("name") String name) throws Exception {

        try {
            // @Primary로 선언된 잡을 아래 파라미터로 실행하기 위한 런처
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("input.file.name", name)
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return "Done";
    }

}
