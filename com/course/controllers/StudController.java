package com.course.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.course.beans.Student;
import com.course.dao.StudDao;
@Controller
public class StudController {
    @Autowired
	StudDao dao;
    

	@RequestMapping("/studform")
	public ModelAndView showform(){
		return new ModelAndView("studform","command",new Student());
	}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("stud") Student emp){
		dao.save(emp);
		return new ModelAndView("redirect:/viewstud");//will redirect to viewemp request mapping
	}

	@RequestMapping("/viewstud")
	public ModelAndView viewstud(){
		List<Student> list=dao.getStudents();
		return new ModelAndView("viewstud","list",list);
	}
	@RequestMapping(value="/editstud/{id}")
	public ModelAndView edit(@PathVariable int id){
		Student stud=dao.getStudById(id);
		return new ModelAndView("empeditform","command",stud);
	}

	@RequestMapping(value="/editsave",method = RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("stud") Student stud){
		dao.update(stud);
		return new ModelAndView("redirect:/viewstud");
	}
	
	@RequestMapping(value="/registersave",method = RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("stud") Student stud){
		dao.update(stud);
		return new ModelAndView("redirect:/viewstud");
	}	

	@RequestMapping(value="/deletestud/{id}",method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id){
		dao.delete(id);
		return new ModelAndView("redirect:/viewstud");
	}

}
