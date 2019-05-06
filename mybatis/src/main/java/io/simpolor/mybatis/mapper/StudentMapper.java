package io.simpolor.mybatis.mapper;

import io.simpolor.mybatis.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

	int selectStudentCount();
	
	Student selectStudent(int seq);
	
	@Select("SELECT COUNT(*) FROM student")
	int findAllCount();
}
