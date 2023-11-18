package com.justin.springboottest;

import com.justin.springboottest.models.CollegeStudent;
import com.justin.springboottest.models.GradebookCollegeStudent;
import com.justin.springboottest.repository.StudentDao;
import com.justin.springboottest.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class GradebookControllerTest {

    private static MockHttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentAndGradeService studentCreateServiceMock;

    @Autowired
    StudentDao studentDao;

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

    @BeforeAll
    public static void setup(){
        request = new MockHttpServletRequest();
        request.setParameter("firstname","Chad");
        request.setParameter("lastname","Darby");
        request.setParameter("emailAddress","chad.darby@gmail.com");
    }

    @BeforeEach
    public void beforeEach(){
//        jdbcTemplate.execute("DELETE FROM student");
//        jdbcTemplate.execute("insert into student(firstname, lastname, email_address) values ('Eric','Roby','eric.roby@gmail.com')");

        jdbcTemplate.execute(sqlAddStudent);
        jdbcTemplate.execute(sqlAddMathGrade);
        jdbcTemplate.execute(sqlAddScienceGrade);
        jdbcTemplate.execute(sqlAddHistoryGrade);
    }

    @Test
    public void getStudentsHttpRequest() throws Exception{
        CollegeStudent student1 = new GradebookCollegeStudent("Eric","Roby","eric.roby@gmail.com");
        CollegeStudent student2 = new GradebookCollegeStudent("Chad","Darby","chad.darby@gmail.com");
        List<CollegeStudent> collegeStudents = new ArrayList<>(Arrays.asList(student1, student2));
        when(studentCreateServiceMock.getGradebook()).thenReturn(collegeStudents);
        assertIterableEquals(collegeStudents, studentCreateServiceMock.getGradebook());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav,"index");
    }

    @Test
    public void createStudentHttpRequest() throws Exception{
        jdbcTemplate.execute(sqlDeleteStudent);
        jdbcTemplate.execute(sqlDeleteMathGrade);
        jdbcTemplate.execute(sqlDeleteScienceGrade);
        jdbcTemplate.execute(sqlDeleteHistoryGrade);

        CollegeStudent student1 = new CollegeStudent("Eric", "Roby", "eric.roby@gmail.com");

        List<CollegeStudent> collegeStudents = new ArrayList<>(Arrays.asList(student1));

        when(studentCreateServiceMock.getGradebook()).thenReturn(collegeStudents);

        assertEquals(collegeStudents,studentCreateServiceMock.getGradebook());

        MvcResult mvcResult = this.mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                        .param("firstname", request.getParameterValues("firstname"))
                        .param("lastname", request.getParameterValues("lastname"))
                        .param("emailAddress", request.getParameterValues("emailAddress")))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView,"index");

        CollegeStudent verifyStudent = studentDao.findByEmailAddress("chad.darby@gmail.com");
        assertNotNull(verifyStudent,"student should be found");
    }

    @Test
    public void deleteStudentHttpRequest() throws Exception{
        assertTrue(studentDao.findById(1).isPresent());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/delete/student/{id}", 1))
                .andExpect(status().isOk()).andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView,"index");

        assertFalse(studentDao.findById(1).isPresent());
    }

    @Test
    public void deleteStudentHttpRequestErrorPage() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/delete/student/{id}", 0))
                .andExpect(status().isOk()).andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView,"error");
    }

    @AfterEach
    public void setupAfterTransaction(){
//        jdbcTemplate.execute("DELETE FROM student");

        jdbcTemplate.execute(sqlDeleteStudent);
        jdbcTemplate.execute(sqlDeleteMathGrade);
        jdbcTemplate.execute(sqlDeleteScienceGrade);
        jdbcTemplate.execute(sqlDeleteHistoryGrade);
    }
}
