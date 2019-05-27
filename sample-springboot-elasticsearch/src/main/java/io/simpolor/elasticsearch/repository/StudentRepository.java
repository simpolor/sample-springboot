package io.simpolor.elasticsearch.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.simpolor.elasticsearch.domain.Student;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.ElasticsearchException;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Repository
public class StudentRepository {

    @Autowired
    private Client client;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private ObjectMapper objectMapper;

    Function<Student,String> toJson = new Function<Student, String>() {
        @Override
        public String apply(Student student) {
            String toJsonSource;
            try {
                toJsonSource = objectMapper.writeValueAsString(student);
            } catch (JsonProcessingException e) {
                throw new ElasticsearchException(e.getMessage());
            }
            return toJsonSource;
        }
    };

    Function<String, Student> toStudent = new Function<String, Student>() {
        @Override
        public Student apply(String json) {
            Student student = null;
            try {
                student = objectMapper.readValue(json, Student.class);
            } catch (IOException e) {
                throw new ElasticsearchException(e.getMessage());
            }
            return student;
        }
    };

    public long selectStudentTotalCount(){

        SearchResponse response
                = client.prepareSearch("student")
                    .setTypes("doc")
                    .get();

        return response.getHits().totalHits;
    }

    public List<Student> selectStudentList(){
        return null;
    }

    public Student selectStudent(String id){
        Client client = elasticsearchOperations.getClient();
        GetResponse response = client.prepareGet("student", "doc", id).get();

        if(response.isExists()){
            return toStudent.apply(response.getSourceAsString());
        }
        return new Student();
    }

    public int insertStudent(Student student){
        return 0;
    }

    public int updateStudent(Student student){
        return 0;
    }

    public int deleteStudent(String id){
        return 0;
    }
}
