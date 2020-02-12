package io.simpolor.event.listener.event;

import lombok.Value;

@Value(staticConstructor = "of")
public class StudentEvent {

	private String name;
	private int age;
}
