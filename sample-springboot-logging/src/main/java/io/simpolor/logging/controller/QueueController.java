package io.simpolor.logging.controller;

import io.simpolor.logging.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/student")
public class QueueController {

	private static final Logger eventLog = LoggerFactory.getLogger("event-log");

	@PostMapping(value="/queue/{seq}")
	public Student studentModify(@PathVariable int seq,
								 @RequestBody Student student) {

		student.setSeq(seq);
		eventLog.debug("{}", student);

		return student;
	}
}
