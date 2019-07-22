package io.simpolor.batch.config;

import io.simpolor.batch.jobs.CustomQuartzJob;
import org.quartz.*;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class QuartzConfig {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobLocator jobLocator;

    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }

    @Bean
    public JobDetail sampleJobDetail() {
        //Set Job data map
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobName", "sampleJob");
        jobDataMap.put("jobLauncher", jobLauncher);
        jobDataMap.put("jobLocator", jobLocator);

        return JobBuilder.newJob(CustomQuartzJob.class)
                .withIdentity("sampleJob")
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    @Bean
    public JobDetail shareJobDetail() {
        //Set Job data map
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobName", "shareJob");
        jobDataMap.put("jobLauncher", jobLauncher);
        jobDataMap.put("jobLocator", jobLocator);

        return JobBuilder.newJob(CustomQuartzJob.class)
                .withIdentity("shareJob")
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    @Bean
    public JobDetail sampleFlowJobDetail() {
        //Set Job data map
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobName", "sampleFlowJob");
        jobDataMap.put("jobLauncher", jobLauncher);
        jobDataMap.put("jobLocator", jobLocator);

        return JobBuilder.newJob(CustomQuartzJob.class)
                .withIdentity("sampleFlowJob")
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    @Bean
    public JobDetail itemJobDetail() {
        //Set Job data map
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobName", "itemJob");
        jobDataMap.put("jobLauncher", jobLauncher);
        jobDataMap.put("jobLocator", jobLocator);

        return JobBuilder.newJob(CustomQuartzJob.class)
                .withIdentity("itemJob")
                .setJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInMinutes(1)
                .repeatForever();

        return TriggerBuilder
                .newTrigger()
                .forJob(sampleJobDetail())
                .withIdentity("sampleJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger sampleFlowJobTrigger() {
        CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("40 * * * * ?");

        return TriggerBuilder
                .newTrigger()
                .forJob(sampleFlowJobDetail())
                .withIdentity("sampleFlowJobTrigger")
                .withSchedule(cronSchedule)
                .build();
    }

    @Bean
    public Trigger shareJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(50)
                .repeatForever();

        return TriggerBuilder
                .newTrigger()
                .forJob(shareJobDetail())
                .withIdentity("shareJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger itemJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(40)
                .repeatForever();

        return TriggerBuilder
                .newTrigger()
                .forJob(itemJobDetail())
                .withIdentity("itemJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(sampleJobTrigger(), sampleFlowJobTrigger(), shareJobTrigger(), itemJobTrigger());
        scheduler.setJobDetails(sampleJobDetail(), sampleFlowJobDetail(), shareJobDetail(), itemJobDetail());
        scheduler.setQuartzProperties(quartzProperties());
        return scheduler;
    }

    /*@Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }*/

    @Bean
    public Properties quartzProperties() {
        Properties props = new Properties();
        props.put("org.quartz.scheduler.instanceName", "MyScheduler");
        props.put("org.quartz.threadPool.threadCount", "3");
        props.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
        return props;
    }


}
