package io.simpolor.mybatis.controller;

import io.simpolor.mybatis.domain.Student;
import io.simpolor.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
