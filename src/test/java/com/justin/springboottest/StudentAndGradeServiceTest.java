package com.justin.springboottest;

import com.justin.springboottest.models.CollegeStudent;
import com.justin.springboottest.models.HistoryGrade;
import com.justin.springboottest.models.MathGrade;
import com.justin.springboottest.models.ScienceGrade;
import com.justin.springboottest.repository.HistoryGradesDao;
import com.justin.springboottest.repository.MathGradesDao;
import com.justin.springboottest.repository.ScienceGradesDao;
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

    @Autowired
    MathGradesDao mathGradesDao;

    @Autowired
    ScienceGradesDao scienceGradesDao;

    @Autowired
    HistoryGradesDao historyGradesDao;

    @BeforeEach
    public void setupDatabase(){
        jdbcTemplate.execute("insert into student(id,firstname, lastname, email_address) " +
                "values (1,'Eric','Roby','eric.roby@gmail.com')");

        jdbcTemplate.execute("insert into math_grade(id,student_id,grade) values (1,1,100.00)");
        jdbcTemplate.execute("insert into science_grade(id,student_id,grade) values (1,1,100.00)");
        jdbcTemplate.execute("insert into history_grade(id,student_id,grade) values (1,1,100.00)");
    }

    @Test
    public void createStudentService(){
        jdbcTemplate.execute("DELETE FROM student");

        studentService.createStudent("Chad", "Darby", "chad.darby@gmail.com");
        CollegeStudent student = studentDao.findByEmailAddress("chad.darby@gmail.com");
        assertEquals("chad.darby@gmail.com",student.getEmailAddress(),"find by email");
    }

    @Test
    public void isStudentNullCheck(){
        assertTrue(studentService.checkIfStudentExists(1));
        assertFalse(studentService.checkIfStudentExists(0));
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

    @Test
    public void createGradeService(){
        jdbcTemplate.execute("DELETE FROM math_grade");
        jdbcTemplate.execute("DELETE FROM science_grade");
        jdbcTemplate.execute("DELETE FROM history_grade");

        // create the grade
        assertTrue(studentService.createGrade(80.50,1,"math"));
        assertTrue(studentService.createGrade(80.50,1,"science"));
        assertTrue(studentService.createGrade(80.50,1,"history"));

        // get all grades with studentId
        Iterable<MathGrade> mathGrades = mathGradesDao.findGradeByStudentId(1);
        Iterable<ScienceGrade> scienceGrades = scienceGradesDao.findGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrades = historyGradesDao.findGradeByStudentId(1);

        // verify there is grades
        assertTrue(mathGrades.iterator().hasNext(),"student has math grades");
        assertTrue(scienceGrades.iterator().hasNext(),"student has science grades");
        assertTrue(historyGrades.iterator().hasNext(),"student has history grades");
    }

    // edge cases
    @Test
    public void createGradeServiceReturnFalse(){
        assertFalse(studentService.createGrade(105,1,"math"));
        assertFalse(studentService.createGrade(-5,1,"math"));
        assertFalse(studentService.createGrade(80.50,2,"math"));
        assertFalse(studentService.createGrade(80.50,1,"literature"));
    }

    @AfterEach
    public void setupAfterTransaction(){
        jdbcTemplate.execute("DELETE FROM student");
        jdbcTemplate.execute("DELETE FROM math_grade");
        jdbcTemplate.execute("DELETE FROM science_grade");
        jdbcTemplate.execute("DELETE FROM history_grade");
    }
}
