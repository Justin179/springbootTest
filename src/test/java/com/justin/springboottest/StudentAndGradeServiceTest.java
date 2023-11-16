package com.justin.springboottest;

import com.justin.springboottest.models.CollegeStudent;
import com.justin.springboottest.repository.StudentDao;
import com.justin.springboottest.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    StudentAndGradeService studentService;

    @Autowired
    StudentDao studentDao;

    @BeforeEach
    public void setupDatabase(){
        jdbcTemplate.execute("insert into student(id, firstname, lastname, email_address) values (1,'Eric','Roby','eric.roby@gmail.com')");
    }

    @Test
    public void createStudentService(){
        studentService.createStudent("Chad", "Darby", "chad.darby@gmail.com");
        CollegeStudent student = studentDao.findByEmailAddress("chad.darby@gmail.com");
        assertEquals("chad.darby@gmail.com",student.getEmailAddress(),"find by email");
    }

    @Test
    public void isStudentNullCheck(){
        assertTrue(studentService.checkIfStudentIsNull(1));
        assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @Test
    public void deleteStudentService(){
        Optional<CollegeStudent> student = studentDao.findById(1);
        assertTrue(student.isPresent(), "return true");
        studentService.deleteStudent(1);
        student = studentDao.findById(1);
        assertFalse(student.isPresent(), "return false");
    }

    @Sql("/insertData.sql")
    @Test
    public void getGradebookService(){
        Iterable<CollegeStudent> studentIterable = studentService.getGradebook();
        List<CollegeStudent> studentList = new ArrayList<>();
        for (CollegeStudent collegeStudent: studentIterable){
            studentList.add(collegeStudent);
        }
        assertEquals(5,studentList.size());
    }

    @AfterEach
    public void setupAfterTransaction(){
        jdbcTemplate.execute("DELETE FROM student");
    }
}
