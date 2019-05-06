package io.simpolor.mybatis.mapper;

import io.simpolor.mybatis.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

	int selectStudentTotalCount();

	Student selectStudent(int seq);

	int insertStudent(Student student);

	int updateStudent(Student student);

	int deleteStudent(int seq);
	
	@Select("SELECT COUNT(*) FROM student")
	int findByAllCount();
}
