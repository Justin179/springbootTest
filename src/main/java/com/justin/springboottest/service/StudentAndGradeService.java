package com.justin.springboottest.service;

import com.justin.springboottest.models.CollegeStudent;
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

    public void createStudent(String firstname, String lastname, String email){
        CollegeStudent student = new CollegeStudent(firstname,lastname,email);
        student.setId(0);
        studentDao.save(student);
    }

    public boolean checkIfStudentIsNull(int id) {
        Optional<CollegeStudent> student = studentDao.findById(id);
        if (student.isPresent())
            return true;
        else
            return false;
    }

    public void deleteStudent(int id) {
        studentDao.deleteById(id);
    }
}
