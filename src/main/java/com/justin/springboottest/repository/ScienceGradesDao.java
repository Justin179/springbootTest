package com.justin.springboottest.repository;

import com.justin.springboottest.models.ScienceGrade;
import org.springframework.data.repository.CrudRepository;

public interface ScienceGradesDao extends CrudRepository<ScienceGrade,Integer> {

    public Iterable<ScienceGrade> findGradeByStudentId(int id);
}
