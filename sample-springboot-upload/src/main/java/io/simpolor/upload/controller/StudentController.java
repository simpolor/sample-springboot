package io.simpolor.upload.controller;

import io.simpolor.upload.component.FileUploader;
import io.simpolor.upload.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@RequestMapping("/student")
@RestController
public class StudentController {

	@Autowired
	private FileUploader fileUploader;

	@Value("${application.file.path}")
	String filePath;

	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public ModelAndView studentProfileUploadForm(ModelAndView mav){

		mav.setViewName("upload");

		return mav;
	}

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public Student studentProfileUpload2(
			// MultipartHttpServletRequest request
			@Valid Student student
	) {
		// System.out.println("getOriginalFilename : "+request.getFile("profile").getOriginalFilename());
		// System.out.println("getParameter : "+request.getParameter("name"));

		// System.out.println("getOriginalFilename : "+student.getProfile().getOriginalFilename());
		// System.out.println("getParameter : "+student.getName());

		System.out.println("student : "+student.toString());
		MultipartFile multipartFile = student.getProfile();
		if(multipartFile != null){
			try {
				File image = new File(filePath + File.separator + student.getProfile().getOriginalFilename());
				multipartFile.transferTo(image);
			}catch (IOException ioe){
				ioe.printStackTrace();
			}
		}

		// FileUploader.Files files = fileUploader.createFile(student.getProfile(), "room");
		/*if(files != null){
			student.setOrgFileName(files.getOrg_file_name());
			student.setSavedFileName(files.getSaved_file_name());
			student.setFileExt(files.getFile_ext());
			student.setFileSize(files.getFile_size());
		}*/

		return student;
	}


}
