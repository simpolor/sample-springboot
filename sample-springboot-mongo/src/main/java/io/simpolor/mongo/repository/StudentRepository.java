package io.simpolor.mongo.repository;

import io.simpolor.mongo.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

}
