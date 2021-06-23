package io.simpolor.localthread.repository;

import io.simpolor.localthread.context.ThreadLocalContext;
import io.simpolor.localthread.model.StudentDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

    private static long index = 1L;
    private final Map<Long, StudentDto> studentDtoMap = new HashMap<>();

    public long count(){

        return Long.valueOf(studentDtoMap.size());
    }

    public List<StudentDto> findAll(){

        System.out.println("findAll : "+ ThreadLocalContext.threadLocal.get());

        List<StudentDto> studentDtos = new ArrayList<>();
        for(Long seq : studentDtoMap.keySet()){
            studentDtos.add(studentDtoMap.get(seq));
        }

        return studentDtos;
    }

    public Optional<StudentDto> findById(long seq){

        return Optional.ofNullable(studentDtoMap.get(seq));
    }

    public StudentDto save(StudentDto studentDto){

        if(studentDto.getSeq() > 0){
            studentDtoMap.put(studentDto.getSeq(), studentDto);

        }else{
            studentDto.setSeq(index);
            studentDtoMap.put(index, studentDto);
            index++;
        }

        return studentDto;
    }

    public void deleteById(long seq){

        studentDtoMap.remove(seq);
    }



}
