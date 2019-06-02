package io.simpolor.upload.controller;

import io.simpolor.upload.domain.Student;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/student")
@RestController
public class StudentController {

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public Student studentProfileUpload(
			@RequestBody Student student,
			@RequestParam("profile_img") MultipartFile file
	) {
		return student;
	}

}
