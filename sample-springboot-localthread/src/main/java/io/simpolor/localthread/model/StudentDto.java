package io.simpolor.localthread.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	private long seq;
	private String name;
	private Integer grade;
	private Integer age;

}
