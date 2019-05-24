package io.simpolor.event.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class StudentEvent {

	private String name;
	private int age;
}
