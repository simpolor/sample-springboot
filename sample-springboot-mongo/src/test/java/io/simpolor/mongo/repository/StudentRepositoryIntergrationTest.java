package io.simpolor.mongo.repository;

import io.simpolor.mongo.MongoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoApplication.class)
public class StudentRepositoryIntergrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testDemoCount() {

        // when
        long result = studentRepository.count();


        // then
        Assert.assertNotNull(result);
        System.out.println("result : "+result);
    }
}
