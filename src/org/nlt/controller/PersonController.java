package org.nlt.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nlt.controller.services.PersonServices;
import org.nlt.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController 
{
	@Autowired
	private PersonServices personService;

	public PersonController() 
	{
		System.out.println("Person Controller Object Created");
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView home()
	{
		HashMap m=new HashMap();
		System.out.println("Home Page");
		List<Persons> personList = personService.getPersonList();
		m.put("PersonList", personList);
		m.put("action", "./submitPerson");
		m.put("button", "SUBMIT");
		
		return new ModelAndView("index",m);
	}
	
	@RequestMapping(value="/submitPerson",method=RequestMethod.POST)
	public ModelAndView submitPersonData(HttpServletRequest req,HttpServletResponse res)
	{
		HashMap m=new HashMap();
		System.out.println("Person Submit");
		
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		String phone=req.getParameter("phone");
		
		m.put("nameValue", name);
		m.put("ageValue", age);
		m.put("phoneValue", phone);
		
		String errorMessage="";
		
		if(name.isEmpty())
		{
			errorMessage="Name Can Not Be Empty";
			m.put("error", errorMessage);
		}
		else if(age.isEmpty())
		{
			errorMessage="Age Can Not Be Empty";
			m.put("error", errorMessage);
		}
		else if(phone.isEmpty())
		{
			errorMessage="Phone Can Not Be Empty";
			m.put("error", errorMessage);
		}
		else
		{
		boolean result=personService.submitPersonService(name,Integer.parseInt(age),Long.parseLong(phone));
			if(result)
			{
				m.put("success", "Record Submitted Succesfully");
				m.put("nameValue", "");
				m.put("ageValue", "");
				m.put("phoneValue", "");
			}
			else
			{
				m.put("error", "Can Not Submit Record");
			}
		}
		List<Persons> personList = personService.getPersonList();
		m.put("PersonList", personList);
		m.put("action", "./submitPerson");
		m.put("button", "SUBMIT");
		
		return new ModelAndView("index",m);
	}
	
	@RequestMapping(value="/updatePerson",method=RequestMethod.POST)
	public ModelAndView updatePersonData(HttpServletRequest req,HttpServletResponse res)
	{
		HashMap m=new HashMap();
		
		String id=req.getParameter("pid");
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		String phone=req.getParameter("phone");
		
		m.put("nameValue", name);
		m.put("ageValue", age);
		m.put("phoneValue", phone);
		
		String errorMessage="";
		
		if(name.isEmpty())
		{
			errorMessage="Name Can Not Be Empty";
			m.put("error", errorMessage);
		}
		else if(age.isEmpty())
		{
			errorMessage="Age Can Not Be Empty";
			m.put("error", errorMessage);
		}
		else if(phone.isEmpty())
		{
			errorMessage="Phone Can Not Be Empty";
			m.put("error", errorMessage);
		}
		else
		{
			Persons person=personService.getPersonRecord(Integer.parseInt(id));
			person.setName(name);
			person.setAge(Integer.parseInt(age));
			person.setPhone(Long.parseLong(phone));
			
			boolean result = personService.updatePersonService(person);
			if(result)
			{
				m.put("success", "Record Updated Successfully");
			}
			else
			{
				m.put("error", "Can Not Update Record");
			}
		}
		List<Persons> personList = personService.getPersonList();
		m.put("PersonList", personList);
		m.put("action", "./submitPerson");
		m.put("button", "SUBMIT");
		return new ModelAndView("index",m);
	}
	
	@RequestMapping(value="/getPersonUpdate",method=RequestMethod.GET)
	public ModelAndView getPersonData(HttpServletRequest req,HttpServletResponse res)
	{
		HashMap m=new HashMap();
		
		String id = req.getParameter("id");
		Persons person = personService.getPersonRecord(Integer.parseInt(id));
		m.put("nameValue", person.getName());
		m.put("ageValue", person.getAge());
		m.put("phoneValue", person.getPhone());
		m.put("idValue", person.getId());
		List<Persons> personList = personService.getPersonList();
		m.put("PersonList", personList);
		m.put("action", "./updatePerson");
		m.put("button", "UPDATE");
		return new ModelAndView("index",m);
	}
	
}
