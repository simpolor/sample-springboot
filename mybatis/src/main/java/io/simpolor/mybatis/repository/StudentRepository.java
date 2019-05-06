package io.simpolor.mybatis.repository;

import io.simpolor.mybatis.domain.Student;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    @Autowired
    private SqlSession sqlSession;

    public int selectStudentTotalCount(){
        return sqlSession.selectOne("selectStudentTotalCount");
    }

    public Student selectStudent(int seq){
        return sqlSession.selectOne("selectStudent", seq);
    }

    public int insetStudent(Student student){
        return sqlSession.insert("insertStudent", student);
    }

    public int updateStudent(Student student){
        return sqlSession.update("updateStudent", student);
    }

    public int deleteStudent(int seq){
        return sqlSession.delete("deleteStudent", seq);
    }
}
