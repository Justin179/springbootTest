package com.justin.springboottest.service;

import com.justin.springboottest.models.CollegeStudent;
import com.justin.springboottest.models.Grade;
import com.justin.springboottest.models.GradebookCollegeStudent;
import com.justin.springboottest.models.HistoryGrade;
import com.justin.springboottest.models.MathGrade;
import com.justin.springboottest.models.ScienceGrade;
import com.justin.springboottest.models.StudentGrades;
import com.justin.springboottest.repository.HistoryGradesDao;
import com.justin.springboottest.repository.MathGradesDao;
import com.justin.springboottest.repository.ScienceGradesDao;
import com.justin.springboottest.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private StudentGrades studentGrades;

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
        boolean studentExists = checkIfStudentExists(id);
        if (studentExists){
            studentDao.deleteById(id);
            mathGradesDao.deleteGradeByStudentId(id);
            historyGradesDao.deleteGradeByStudentId(id);
            scienceGradesDao.deleteGradeByStudentId(id);
        }
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

    public int deleteGrade(int gradeId, String gradeType) {
        int studentId = 0;

        if (gradeType.equals("math")){
            Optional<MathGrade> mathGrade = mathGradesDao.findById(gradeId);
            if(!mathGrade.isPresent()){
                return studentId;
            }
            studentId = mathGrade.get().getStudentId();
            mathGradesDao.deleteById(gradeId);
            return studentId;
        } else if (gradeType.equals("science")){
            Optional<ScienceGrade> scienceGrade = scienceGradesDao.findById(gradeId);
            if(!scienceGrade.isPresent()){
                return studentId;
            }
            studentId = scienceGrade.get().getStudentId();
            scienceGradesDao.deleteById(gradeId);
            return studentId;
        } else if (gradeType.equals("history")){
            Optional<HistoryGrade> historyGrade = historyGradesDao.findById(gradeId);
            if(!historyGrade.isPresent()){
                return studentId;
            }
            studentId = historyGrade.get().getStudentId();
            historyGradesDao.deleteById(gradeId);
            return studentId;
        }

        return studentId;
    }

    public GradebookCollegeStudent getStudentInformation(int studentId) {

        boolean studentExists = checkIfStudentExists(studentId);
        if (!studentExists)
            return null;

        Optional<CollegeStudent> student = studentDao.findById(studentId);
        Iterable<MathGrade> mathGrades = mathGradesDao.findGradeByStudentId(studentId);
        Iterable<ScienceGrade> scienceGrades = scienceGradesDao.findGradeByStudentId(studentId);
        Iterable<HistoryGrade> historyGrades = historyGradesDao.findGradeByStudentId(studentId);

        List<Grade> mathGradeList = new ArrayList<>();
        mathGrades.forEach(mathGradeList::add);
        List<Grade> scienceGradeList = new ArrayList<>();
        scienceGrades.forEach(scienceGradeList::add);
        List<Grade> historyGradeList = new ArrayList<>();
        historyGrades.forEach(historyGradeList::add);

        studentGrades.setMathGradeResults(mathGradeList);
        studentGrades.setScienceGradeResults(scienceGradeList);
        studentGrades.setHistoryGradeResults(historyGradeList);

        CollegeStudent collegeStudent = student.get();
        GradebookCollegeStudent gradebookCollegeStudent = new GradebookCollegeStudent(collegeStudent.getId()
                , collegeStudent.getFirstname()
                , collegeStudent.getLastname()
                , collegeStudent.getEmailAddress()
                , studentGrades);

        return gradebookCollegeStudent;
    }
}












