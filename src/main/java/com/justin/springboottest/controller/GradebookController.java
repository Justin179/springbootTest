package com.justin.springboottest.controller;

import com.justin.springboottest.models.CollegeStudent;
import com.justin.springboottest.models.Grade;
import com.justin.springboottest.models.Gradebook;
import com.justin.springboottest.models.GradebookCollegeStudent;
import com.justin.springboottest.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GradebookController {

	@Autowired
	private Gradebook gradebook;

	@Autowired
	private StudentAndGradeService studentService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStudents(Model m) {
		Iterable<CollegeStudent> collegeStudents = studentService.getGradebook();
		m.addAttribute("students",collegeStudents);
		return "index";
	}

	@PostMapping(value = "/")
	public String createStudent(@ModelAttribute("student") CollegeStudent student, Model m){

		studentService.createStudent(student.getFirstname(),student.getLastname(),student.getEmailAddress());
		Iterable<CollegeStudent> collegeStudents = studentService.getGradebook();
		m.addAttribute("students",collegeStudents);

		return "index";
	}

	@GetMapping("/delete/student/{id}")
	public String deleteStudent(@PathVariable int id, Model model){

		boolean studentExists = studentService.checkIfStudentExists(id);
		if (!studentExists)
			return "error";

		studentService.deleteStudent(id);
		Iterable<CollegeStudent> collegeStudents = studentService.getGradebook();
		model.addAttribute("students",collegeStudents);
		return "index";
	}

	@GetMapping("/studentInformation/{id}")
	public String studentInformation(@PathVariable int id, Model m) {

		boolean studentExists = studentService.checkIfStudentExists(id);
		if (!studentExists){
			return "error";
		}

		GradebookCollegeStudent studentInformation = studentService.getStudentInformation(id);
		m.addAttribute("student",studentInformation);

		List<Grade> mathGradeList = studentInformation.getStudentGrades().getMathGradeResults();
		if (mathGradeList.size()>0){
			m.addAttribute("mathAverage",
					studentInformation.getStudentGrades().findGradePointAverage(mathGradeList));
		} else {
			m.addAttribute("mathAverage","N/A");
		}

		List<Grade> scienceGradeList = studentInformation.getStudentGrades().getScienceGradeResults();
		if (scienceGradeList.size()>0){
			m.addAttribute("scienceAverage",
					studentInformation.getStudentGrades().findGradePointAverage(scienceGradeList));
		} else {
			m.addAttribute("scienceAverage","N/A");
		}

		List<Grade> historyGradeList = studentInformation.getStudentGrades().getHistoryGradeResults();
		if (historyGradeList.size()>0){
			m.addAttribute("historyAverage",
					studentInformation.getStudentGrades().findGradePointAverage(historyGradeList));
		} else {
			m.addAttribute("historyAverage","N/A");
		}


		return "studentInformation";
	}


	@PostMapping(value="/grades")
	public String createGrade(@RequestParam("grade") double grade,
							  @RequestParam("gradeType") String gradeType,
							  @RequestParam("studentId") int studentId,
							  Model m){
		boolean studentExists = studentService.checkIfStudentExists(studentId);
		if (!studentExists){
			return "error";
		}

		boolean gradeCreated = studentService.createGrade(grade, studentId, gradeType);
		if (!gradeCreated){
			return "error";
		}

		GradebookCollegeStudent studentInformation = studentService.getStudentInformation(studentId);
		m.addAttribute("student",studentInformation);

		List<Grade> mathGradeList = studentInformation.getStudentGrades().getMathGradeResults();
		if (mathGradeList.size()>0){
			m.addAttribute("mathAverage",
					studentInformation.getStudentGrades().findGradePointAverage(mathGradeList));
		} else {
			m.addAttribute("mathAverage","N/A");
		}

		List<Grade> scienceGradeList = studentInformation.getStudentGrades().getScienceGradeResults();
		if (scienceGradeList.size()>0){
			m.addAttribute("scienceAverage",
					studentInformation.getStudentGrades().findGradePointAverage(scienceGradeList));
		} else {
			m.addAttribute("scienceAverage","N/A");
		}

		List<Grade> historyGradeList = studentInformation.getStudentGrades().getHistoryGradeResults();
		if (historyGradeList.size()>0){
			m.addAttribute("historyAverage",
					studentInformation.getStudentGrades().findGradePointAverage(historyGradeList));
		} else {
			m.addAttribute("historyAverage","N/A");
		}


		return "studentInformation";
	}
}














