package com.justin.springboottest.repository;

import com.justin.springboottest.models.MathGrade;
import org.springframework.data.repository.CrudRepository;

public interface MathGradesDao extends CrudRepository<MathGrade,Integer> {

    public Iterable<MathGrade> findGradeByStudentId(int id);

    void deleteGradeByStudentId(int id);
}
