package com.charles.hikari.dao;

import com.charles.hikari.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> findAll() {

        List<Student> result = jdbcTemplate.query(
                "SELECT id, name, email FROM student",
                (rs, rowNum) -> new Student(rs.getInt("id"),
                        rs.getString("name"), rs.getString("email"))
        );

        return result;

    }

    public void addStudent(String name, String email) {

        jdbcTemplate.update("INSERT INTO student(name, email) VALUES (?,?)",
                name, email);

    }
}
