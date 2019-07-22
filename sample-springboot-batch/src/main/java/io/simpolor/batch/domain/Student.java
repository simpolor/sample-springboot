package io.simpolor.batch.domain;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@Data
public class Student {

	private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private long seq;
	private String name;
	private int grade;
	private int age;
	private String regDate;

	public Student(long seq, String name, int grade, int age, LocalDateTime regDate) {
		this.seq = seq;
		this.name = name;
		this.grade = grade;
		this.age = age;
		this.regDate = regDate.format(pattern);
	}
}
