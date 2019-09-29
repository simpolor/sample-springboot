package io.simpolor.mongo.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "student")
public class Student {

	@Id
	private String id;

	private String name;

	private int grade;

	private int age;

	private List<String> hobby;

}
