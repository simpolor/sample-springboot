package io.simpolor.mybatis.controller;

import io.simpolor.mybatis.domain.Student;
import io.simpolor.mybatis.mapper.StudentMapper;
import io.simpolor.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mapper/student")
@RestController
public class StudentMapperController {

	@Autowired
	private StudentMapper studentMapper;

	@RequestMapping(value="/findByAllCount", method=RequestMethod.GET)
	public int studentFindByAllCount() {
		return studentMapper.selectStudentTotalCount();
	}

	@RequestMapping(value="/totalcount", method=RequestMethod.GET)
	public int studentTotalcount() {
		return studentMapper.selectStudentTotalCount();
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public Student studentView(@PathVariable int seq) {
		return studentMapper.selectStudent(seq);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public int studentRegister(@RequestBody Student student) {
		return studentMapper.insertStudent(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public int studentModify(@PathVariable int seq,
							 @RequestBody Student student) {
		student.setSeq(seq);
		return studentMapper.updateStudent(student);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.DELETE)
	public int studentDelete(@PathVariable int seq) {
		return studentMapper.deleteStudent(seq);
	}


}
