package io.simpolor.locator.controller;

import io.simpolor.locator.domain.Student;
import io.simpolor.locator.locator.SvcLocator;
import io.simpolor.locator.service.StudentServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class StudentController {

	@Autowired
	private SvcLocator svcLocator;

	@RequestMapping(value="/student/totalcount", method=RequestMethod.GET)
	public int studentTotalCount(@RequestParam(defaultValue = "") String p) {

		StudentServcie studentServcie = svcLocator.getStudentService(p);

		return studentServcie.count();
	}

	@RequestMapping(value="/student/list", method=RequestMethod.GET)
	public List<Student> studentList(@RequestParam(defaultValue = "") String p) {

		StudentServcie studentServcie = svcLocator.getStudentService(p);

		return studentServcie.getAll();
	}

}
