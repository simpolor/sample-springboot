package io.simpolor.logging.controller;

import io.simpolor.logging.domain.Student;
import io.simpolor.logging.service.StudentService;
import lombok.extern.slf4j.Slf4j;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

	// private static final Logger eventLog = LoggerFactory.getLogger("event-log");

	@Autowired
	private StudentService studentService;

	@GetMapping(value="/totalcount")
	public long studentTotalcount() {

		return studentService.getTotalcount();
	}

	@GetMapping(value="/list")
	public List<Student> studentList() {

		return studentService.getAll();
	}

	@GetMapping(value="/{seq}")
	public Student studentView(@PathVariable long seq) {

		log.debug("Student view : {}", seq);

		return studentService.get(seq);
	}

	@PostMapping(value="")
	public Student studentRegister(@RequestBody Student student) {

		return studentService.register(student);
	}

	@PutMapping(value="/{seq}")
	public Student studentModify(@PathVariable int seq,
								 @RequestBody Student student) {

		student.setSeq(seq);
		// eventLog.debug("put event : {}", student);

		return studentService.modify(student);
	}

	@DeleteMapping(value="/{seq}")
	public long studentDelete(@PathVariable long seq) {

		log.error("Student delete : {}", seq);

		return studentService.delete(seq);
	}


}
