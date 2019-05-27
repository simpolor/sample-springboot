package io.simpolor.elasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.List;

@Data
@Document(indexName = "student", type = "doc")
public class Student {


	// @JsonInclude(JsonInclude.Include.NON_NULL)
	// @JsonIgnore
	// @Field(type = FieldType.Keyword, store = true)

	@Id
	private String id;

	private String name;

	private int grade;

	private int age;

	private List<String> hobby;

	public Student(){ }

	public Student(String id, String name, int grade, int age, List<String> hobby) {
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.age = age;
		this.hobby = hobby;
	}

}
