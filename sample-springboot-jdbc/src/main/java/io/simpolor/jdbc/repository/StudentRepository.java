package io.simpolor.jdbc.repository;

import io.simpolor.jdbc.domain.Student;
import io.simpolor.jdbc.repository.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.StringJoiner;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int selectStudentTotalCount(){
        String query = "SELECT COUNT(*) FROM student";
        int result = jdbcTemplate.queryForObject(query, Integer.class);
        return result;
    }

    public List<Student> selectStudentList(){
        String query = "SELECT * FROM student";
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        List<Student> result = jdbcTemplate.query(query, rowMapper);
        return result;
    }

    public Student selectStudent(long seq){
        String query = "SELECT * FROM student WHERE seq = ?";
        Student result = jdbcTemplate.queryForObject(query, new Object[]{seq}, new StudentMapper());
        // RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        // Student result = jdbcTemplate.queryForObject(query, rowMapper, seq);
        return result;
    }

    public long insertStudent(Student student){
        //String query = "INSERT INTO student ( name, grade, age, hobby ) values ( ?, ?, ?, ? )";
        //return jdbcTemplate.update(query, student.getName(), student.getGrade(), student.getAge(), this.stringJoiner(student.getHobby()));

        String query = "INSERT INTO student ( name, grade, age, hobby ) values ( ?, ?, ?, ? )";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps =
                        connection.prepareStatement(query,
                                Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, student.getName());
                ps.setInt(2, student.getGrade());
                ps.setInt(3, student.getAge());
                ps.setString(4, stringJoiner(student.getHobby()));

                return ps;
            }
        }, keyHolder);
        long seq = keyHolder.getKey().longValue();

        /*String query = "INSERT INTO student ( name, grade, age, hobby ) values ( ?, ?, ?, ? )";
        SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(student);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(query, fileParameters, keyHolder);
        return keyHolder.getKey().intValue();*/

        return seq;
    }

    public long insertStudent2(Student student){

        String query = "INSERT INTO student ( name, grade, age, hobby ) values ( ?, ?, ?, ? )";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", student.getName());
        parameters.addValue("grade", student.getGrade());
        parameters.addValue("age,", student.getAge());
        parameters.addValue("hobby",  stringJoiner(student.getHobby()));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        int nb = namedJdbcTemplate.update(query, parameters, keyHolder, new String[]{"seq"});
        long generatedId = keyHolder.getKey().longValue();
        System.out.println("nb : "+nb);
        System.out.println("generatedId : "+generatedId);

        return generatedId;
    }

    public int updateStudent(Student student){
        String query = "UPDATE student SET name=?, grade=?, age=?, hobby=? WHERE seq = ?";
        return jdbcTemplate.update("query", student.getName(), student.getGrade(), student.getAge(), this.stringJoiner(student.getHobby()));
    }

    public int deleteStudent(long seq){
        String query = "DELETE FROM student WHERE seq = ?";
        return jdbcTemplate.update(query, seq);
    }

    public String stringJoiner(List<String> list){
        StringJoiner joiner = new StringJoiner(",");
        for (String str : list){
            joiner.add(str);
        }
        return joiner.toString();

    }
}
