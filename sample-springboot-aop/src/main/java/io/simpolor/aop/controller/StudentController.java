package io.simpolor.aop.controller;

import io.simpolor.aop.domain.Student;
import io.simpolor.aop.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/student")
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value="/totalcount", method=RequestMethod.GET)
	public int studentTotalCount() {
		return studentService.getStudentTotalCount();
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Student> studentList() {
		return studentService.getStudentList();
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public Student studentView(@PathVariable long seq) {
		return studentService.getStudent(seq);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public Student studentRegister(@RequestBody Student student) {
		log.info("Controller student.toString() : ", student.toString());
		return studentService.registerStudent(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public Student studentModify(@PathVariable int seq,
							 @RequestBody Student student) {
		student.setSeq(seq);
		return studentService.modifyStudent(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.DELETE)
	public long studentDelete(@PathVariable long seq) {
		return studentService.deleteStudent(seq);

	}

	@RequestMapping(value="/not", method=RequestMethod.GET)
	public String studentNot() {
		return "Is not a studnet";

	}


}
