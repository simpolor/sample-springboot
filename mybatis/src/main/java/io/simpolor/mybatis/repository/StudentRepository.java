package io.simpolor.mybatis.repository;

import io.simpolor.mybatis.domain.Student;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    @Autowired
    private SqlSession sqlSession;

    public int selectStudentCount(){
        return sqlSession.selectOne("selectStudentCount");
    }

    public Student selectStudent(int seq){
        return sqlSession.selectOne("selectStudent", seq);
    }

    public int insetStudent(Student student){
        System.out.println("student.getName : "+student.getName());
        System.out.println("student.getGrade : "+student.getGrade());
        System.out.println("student.getAge : "+student.getAge());
        System.out.println("student.getHobby : "+student.getHobby());
        return sqlSession.insert("insertStudent", student);
    }

    public int updateStudent(Student student){
        return sqlSession.update("updateStudent", student);
    }

    public int deleteStudent(int seq){
        return sqlSession.delete("deleteStudent", seq);
    }
}
