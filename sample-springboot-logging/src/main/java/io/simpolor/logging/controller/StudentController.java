package io.simpolor.logging.controller;

import io.simpolor.logging.domain.Student;
import io.simpolor.logging.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping(value="/log")
	public String log() {

		log.trace("print : trace");
		log.debug("print : debug");
		log.info("print : info");
		log.warn("print : warn");
		log.error("print : error");

		return "log";
	}

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

		log.info("Register student : {}", student);

		return studentService.register(student);
	}

	@PutMapping(value="/{seq}")
	public Student studentModify(@PathVariable int seq,
								 @RequestBody Student student) {

		log.info("Modify student : {}, {}", seq, student);

		student.setSeq(seq);

		return studentService.modify(student);
	}

	@DeleteMapping(value="/{seq}")
	public long studentDelete(@PathVariable long seq) {

		log.info("Delete student : {}", seq);

		return studentService.delete(seq);
	}


}
