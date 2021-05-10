package br.com.inatel.icc.pastebean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PracticeController {
	
	@RequestMapping("/") // Main page
	@ResponseBody // Returns to browser instead of looking for a web page
	public String hello() {
		return "Hello World!";
	}

}
