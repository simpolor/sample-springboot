package io.simpolor.localthread.controller;

import io.simpolor.localthread.context.ThreadLocalContext;
import io.simpolor.localthread.model.StudentDto;
import io.simpolor.localthread.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping(value="/total-count")
	public Long totalCount() {

		return studentService.getTotalCount();
	}

	@GetMapping
	public List<StudentDto> list() {

		ThreadLocalContext.threadLocal.set("sample");

		List<StudentDto> studentDtos = studentService.getAll();

		// ThreadLocal을 사용한 후 직접 remove를 호출해야 함
		// ( 그렇지 않을 경우 재사용되는 Thread가 올바르지 않은 데이터를 참조할 수 있음 )
		ThreadLocalContext.threadLocal.remove();

		return studentDtos;
	}

	@GetMapping(value="/{seq}")
	public StudentDto detail(@PathVariable Long seq) {

		System.out.println("detail : "+ThreadLocalContext.threadLocal.get());

		return studentService.get(seq);
	}

	@PostMapping
	public void register(@RequestBody StudentDto studentDto) {

		studentService.create(studentDto);
	}

	@PutMapping(value="/{seq}")
	public void modify(@PathVariable Long seq,
					   @RequestBody StudentDto studentDto) {

		studentDto.setSeq(seq);
		studentService.update(studentDto);
	}

	@DeleteMapping(value="/{seq}")
	public void delete(@PathVariable Long seq) {

		studentService.delete(seq);
	}

}
