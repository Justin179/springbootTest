package com.justin.springboottest.service;

import com.justin.springboottest.models.CollegeStudent;
import com.justin.springboottest.models.HistoryGrade;
import com.justin.springboottest.models.MathGrade;
import com.justin.springboottest.models.ScienceGrade;
import com.justin.springboottest.repository.HistoryGradesDao;
import com.justin.springboottest.repository.MathGradesDao;
import com.justin.springboottest.repository.ScienceGradesDao;
import com.justin.springboottest.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    MathGrade mathGrade;

    @Autowired
    private MathGradesDao mathGradesDao;

    @Autowired
    ScienceGrade scienceGrade;

    @Autowired
    private ScienceGradesDao scienceGradesDao;

    @Autowired
    HistoryGrade historyGrade;

    @Autowired
    private HistoryGradesDao historyGradesDao;

    public void createStudent(String firstname, String lastname, String email){
        CollegeStudent student = new CollegeStudent(firstname,lastname,email);
//        student.setId(0);
        studentDao.save(student);
    }

    public boolean checkIfStudentExists(int id) {
        Optional<CollegeStudent> student = studentDao.findById(id);
        if (student.isPresent())
            return true;
        else
            return false;
    }

    public void deleteStudent(int id) {
        studentDao.deleteById(id);
    }

    public Iterable<CollegeStudent> getGradebook() {
        return studentDao.findAll();
    }

    public boolean createGrade(double grade, int studentId, String gradeType) {

        boolean studentExists = checkIfStudentExists(studentId);
        if (!studentExists){
            return false;
        }

        if (grade>=0 && grade<=100){
            if (gradeType.equals("math")){
                mathGrade.setId(0);
                mathGrade.setGrade(grade);
                mathGrade.setStudentId(studentId);
                mathGradesDao.save(mathGrade);
                return true;
            }
            if (gradeType.equals("science")){
                scienceGrade.setId(0);
                scienceGrade.setGrade(grade);
                scienceGrade.setStudentId(studentId);
                scienceGradesDao.save(scienceGrade);
                return true;
            }
            if (gradeType.equals("history")){
                historyGrade.setId(0);
                historyGrade.setGrade(grade);
                historyGrade.setStudentId(studentId);
                historyGradesDao.save(historyGrade);
                return true;
            }
        }

        return false;
    }
}












