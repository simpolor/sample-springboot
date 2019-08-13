package io.simpolor.aop.domain;

import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private long seq;
	private String name;
	private int grade;
	private int age;
	private List<String> hobby;

}
