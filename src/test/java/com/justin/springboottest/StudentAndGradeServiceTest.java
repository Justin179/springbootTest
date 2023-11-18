package com.justin.springboottest;

import com.justin.springboottest.models.CollegeStudent;
import com.justin.springboottest.models.GradebookCollegeStudent;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Collection;
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

    @Value("${sql.scripts.create.student}")
    private String sqlAddStudent;
    @Value("${sql.scripts.create.math.grade}")
    private String sqlAddMathGrade;
    @Value("${sql.scripts.create.science.grade}")
    private String sqlAddScienceGrade;
    @Value("${sql.scripts.create.history.grade}")
    private String sqlAddHistoryGrade;

    @Value("${sql.scripts.delete.student}")
    private String sqlDeleteStudent;
    @Value("${sql.scripts.delete.math.grade}")
    private String sqlDeleteMathGrade;
    @Value("${sql.scripts.delete.science.grade}")
    private String sqlDeleteScienceGrade;
    @Value("${sql.scripts.delete.history.grade}")
    private String sqlDeleteHistoryGrade;

    @BeforeEach
    public void setupDatabase(){
        jdbcTemplate.execute(sqlAddStudent);
        jdbcTemplate.execute(sqlAddMathGrade);
        jdbcTemplate.execute(sqlAddScienceGrade);
        jdbcTemplate.execute(sqlAddHistoryGrade);
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
        Optional<MathGrade> mathGrade = mathGradesDao.findById(1);
        Optional<ScienceGrade> scienceGrade = scienceGradesDao.findById(1);
        Optional<HistoryGrade> historyGrade = historyGradesDao.findById(1);
        assertTrue(student.isPresent(), "return true");
        assertTrue(mathGrade.isPresent());
        assertTrue(scienceGrade.isPresent());
        assertTrue(historyGrade.isPresent());

        studentService.deleteStudent(1);

        student = studentDao.findById(1);
        mathGrade = mathGradesDao.findById(1);
        scienceGrade = scienceGradesDao.findById(1);
        historyGrade = historyGradesDao.findById(1);
        assertFalse(student.isPresent(), "return false");
        assertFalse(mathGrade.isPresent());
        assertFalse(scienceGrade.isPresent());
        assertFalse(historyGrade.isPresent());
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

        assertTrue(((Collection<MathGrade>)mathGrades).size()==1,"there is one math grade");
    }

    // edge cases
    @Test
    public void createGradeServiceReturnFalse(){
        assertFalse(studentService.createGrade(105,1,"math"));
        assertFalse(studentService.createGrade(-5,1,"math"));
        assertFalse(studentService.createGrade(80.50,2,"math"));
        assertFalse(studentService.createGrade(80.50,1,"literature"));
    }

    @Test
    public void deleteGradeService(){
        assertEquals(1,studentService.deleteGrade(1,"math"), "returns student id after delete the math grade");
        assertEquals(1,studentService.deleteGrade(1,"science"), "returns student id after delete the science grade");
        assertEquals(1,studentService.deleteGrade(1,"history"), "returns student id after delete the history grade");
    }

    // verify the code can handle those cases
    @Test
    public void deleteGradeServiceReturnStudentIdOfZero(){
        assertEquals(0,studentService.deleteGrade(0,"science"),"no student should have 0 grade id");
        assertEquals(0,studentService.deleteGrade(1,"literature"),"no student should have a literature class");
    }

    @Test
    public void studentInformation(){
        GradebookCollegeStudent gradebookCollegeStudent = studentService.getStudentInformation(1);
        assertNotNull(gradebookCollegeStudent);
        assertEquals(1,gradebookCollegeStudent.getId());
        assertEquals("Eric",gradebookCollegeStudent.getFirstname());
        assertEquals("Roby",gradebookCollegeStudent.getLastname());
        assertEquals("eric.roby@gmail.com",gradebookCollegeStudent.getEmailAddress());
        assertTrue(gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size()==1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getScienceGradeResults().size()==1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getHistoryGradeResults().size()==1);
    }

    @Test
    public void studentInformationServiceReturnNull(){
        GradebookCollegeStudent studentInformation = studentService.getStudentInformation(0);
        assertNull(studentInformation);
    }

    @AfterEach
    public void setupAfterTransaction(){
        jdbcTemplate.execute(sqlDeleteStudent);
        jdbcTemplate.execute(sqlDeleteMathGrade);
        jdbcTemplate.execute(sqlDeleteScienceGrade);
        jdbcTemplate.execute(sqlDeleteHistoryGrade);
    }
}
