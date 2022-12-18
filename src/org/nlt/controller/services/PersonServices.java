package org.nlt.controller.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.nlt.listener.DatabaseConnection;
import org.nlt.model.Persons;
import org.springframework.stereotype.Service;

@Service
public class PersonServices {

	public PersonServices()
	{
		System.out.println("Person Services Object Created");
	}
	
	public boolean submitPersonService(String name,int age,long phone)
	{
		System.out.println("Submit Person Method");
		Persons person=new Persons();
		person.setName(name);
		person.setAge(age);
		person.setPhone(phone);
		person.setStatus(1);
		
		Session ses = DatabaseConnection.getDatabaseSession();
		ses.beginTransaction();
		ses.save(person);
		ses.getTransaction().commit();
		
		
		return true;
	}
	public List<Persons> getPersonList()    //It will return list of Persons
	{
		Session ses = DatabaseConnection.getDatabaseSession();
		ses.beginTransaction();
		Query query = ses.createQuery("from Persons where status=1");
		List list = query.list();
		ses.getTransaction().commit();
		return list;
	}
	
	public boolean updatePersonService(Persons person)
	{
		Session ses = DatabaseConnection.getDatabaseSession();
		ses.beginTransaction();
		ses.update(person);
		ses.getTransaction().commit();
		return true;
	}

	public Persons getPersonRecord(int id)  //It will get the personRecord
	{
		Session ses = DatabaseConnection.getDatabaseSession();
		ses.beginTransaction();
		
		Persons person =(Persons)ses.get(Persons.class, id);
		ses.beginTransaction().commit();
		return person;
	}
}
