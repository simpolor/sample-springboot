package io.simpolor.redis.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Data
@RedisHash("Student")
public class Student implements Serializable {

	@Id
	private String key;

	private String name;

	private int grade;

	private int age;

	private List<String> hobby;

}
