package io.simpolor.mybatis.controller;

import io.simpolor.mybatis.domain.Student;
import io.simpolor.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/student")
@RestController
public class StudentController {

	// @Autowired
	// StudentMapper demoMapper;

	@Autowired
	private StudentService studentService;

	@RequestMapping(value="/totalcount", method=RequestMethod.GET)
	public int studentTotalcount() {
		return studentService.getStudentCount();
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public Student studentView(@PathVariable int seq) {
		return studentService.getStudent(seq);
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public int studentRegister(@RequestBody Student student) {
		return studentService.registerStudent(student);
	}

	@RequestMapping(value="/modify/{seq}", method=RequestMethod.PUT)
	public int studentModify(@PathVariable int seq,
							 @RequestBody Student student) {
		student.setSeq(seq);
		return studentService.modifyStudent(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.DELETE)
	public int studentDelete(@PathVariable int seq) {
		return studentService.deleteStudent(seq);
	}


}
