package org.nlt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class TestController 
{
	//CommonsMultipartResolver
	public TestController() 
	{
		System.out.println("Test Controller Object Created");
	}
}
