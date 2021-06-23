package io.simpolor.localthread.service;

import io.simpolor.localthread.context.ThreadLocalContext;
import io.simpolor.localthread.model.StudentDto;
import io.simpolor.localthread.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Long getTotalCount() {
        return studentRepository.count();
    }

    public List<StudentDto> getAll() {

        System.out.println("getAll : "+ThreadLocalContext.threadLocal.get());

        return studentRepository.findAll();
    }

    public StudentDto get(Long seq) {

        System.out.println("get : "+ThreadLocalContext.threadLocal.get());

        Optional<StudentDto> optionalStudentDto = studentRepository.findById(seq);
        if(!optionalStudentDto.isPresent()){
            throw new IllegalArgumentException("seq : "+seq);
        }

        return optionalStudentDto.get();
    }

    public StudentDto create(StudentDto Studentdto) {

        return studentRepository.save(Studentdto);
    }

    public StudentDto update(StudentDto studentDto) {

        Optional<StudentDto> optionalStudentDto = studentRepository.findById(studentDto.getSeq());
        if(!optionalStudentDto.isPresent()){
            throw new IllegalArgumentException("seq : "+studentDto.getSeq());
        }

        return studentRepository.save(studentDto);
    }

    public long delete(long seq) {

        studentRepository.deleteById(seq);

        return seq;
    }

}
