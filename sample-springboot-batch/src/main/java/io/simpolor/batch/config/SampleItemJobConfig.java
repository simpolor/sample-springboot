package io.simpolor.batch.config;

import io.simpolor.batch.domain.Student;
import io.simpolor.batch.item.QueueItemReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.util.*;

@Slf4j
@Configuration
public class SampleItemJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Autowired
    private DataSource dataSource; // Spring-batch는 JPA를 지원하지 않음

    public static final int CHUNK_SIZE = 10;

    @Bean
    public Job itemJob() {
        return jobBuilderFactory.get("itemJob")
                .start(itemStep())
                .build();
    }

    @Bean
    public Step itemStep() {
        return stepBuilderFactory.get("itemStep")
                .<Student, Student>chunk(CHUNK_SIZE)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    /*@Bean
    public JdbcCursorItemReader<Student> jdbcCursorItemReader() {
        return new JdbcCursorItemReaderBuilder<Student>()
                .fetchSize(chunkSize)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Student.class))
                .sql("SELECT seq, name, grade, age FROM student")
                .name("jdbcCursorItemReader")
                .build();
    }*/

    @Bean
    public ItemReader<Student> itemReader() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "단순색", 1, 17));
        students.add(new Student(2L, "김영희", 2, 18));
        log.info("> itemReader students size : {}", students.size());
        return new QueueItemReader<>(students);
    }

    @Bean
    public ItemProcessor<Student, Student> itemProcessor(){
        return student -> {
            student.setAge(27);
            log.info("> itemProcess student : {}", student.toString());
            return student;
        };
    }

    private ItemWriter<Student> itemWriter() {
        return list -> {
            for (Student student : list) {
                log.info("> itemWriter student : {}", student.toString());
            }
        };
    }

}
