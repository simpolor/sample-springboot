package io.simpolor.elasticsearch.controller;

import io.simpolor.elasticsearch.domain.Student;
import io.simpolor.elasticsearch.service.EsStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/es/student")
@RestController
public class EsStudentController {

	@Autowired
	private EsStudentService estudentService;

	@RequestMapping(value="/totalcount", method=RequestMethod.GET)
	public long studentTotalCount() {
		return estudentService.getStudentTotalCount();
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Student> studentList() {
		return estudentService.getStudentList();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Student studentView(@PathVariable String id) {
		return estudentService.getStudent(id);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public Student studentRegister(@RequestBody Student student) {
		return estudentService.registerStudent(student);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public Student studentModify(@PathVariable String id,
							 @RequestBody Student student) {
		student.setId(id);
		return estudentService.modifyStudent(student);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String studentDelete(@PathVariable String id) {
		return estudentService.deleteStudent(id);

	}


}
