package io.simpolor.upload.controller;

import io.simpolor.upload.domain.Student;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/student")
@RestController
public class StudentController {

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public Student studentProfileUpload(
			/*@RequestBody Student student,*/
			@RequestPart("profile") MultipartFile profile
	) {
		//System.out.println("student : "+student.toString());
		System.out.println("profile : "+profile.getOriginalFilename());
		System.out.println("profile : "+profile.getName());
		System.out.println("profile : "+profile);
		return new Student();
	}

}
