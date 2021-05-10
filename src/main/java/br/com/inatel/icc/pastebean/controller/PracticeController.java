package br.com.inatel.icc.pastebean.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.icc.pastebean.model.Paste;
import br.com.inatel.icc.pastebean.model.PastePrivacy;
import br.com.inatel.icc.pastebean.model.User;

@RestController // Adds "@ResponseBody" to every method
public class PracticeController {
	
	@RequestMapping("/") // Maps endpoints (this to the main page)
	// @ResponseBody // Returns to browser instead of looking for a web page address
	public String hello() {
		
		String returnMessage = "Hello World!<br><br>";
		
		PastePrivacy paste1Privacy = PastePrivacy.PUBLIC;
		String paste1Title = "Bob Bobbington's Paste";
		String paste1Content = "Bobbington is a village and civil parish in the South Staffordshire district of "
				+ "Staffordshire, England, about 5 miles west of Wombourne.";
		PastePrivacy paste2Privacy = PastePrivacy.HIDDEN;
		String paste2Title = "Spell Cards";
		String paste2Content = "Beckon Sign \"Many Danmaku Guests\"<br>"
				+ "Beckon Sign \"Shoot Away Disaster, Beckon in Fortune\"";
		
		String user1Name = "Bob";
		String user1Pass = "BoB_808";
		String user2Name = "Mike";
		String user2Pass = "go-to-Kuji";
		
		User user1 = new User(user1Name, user1Pass);
		User user2 = new User(user2Name, user2Pass);
		Paste paste1 = new Paste(paste1Privacy, paste1Title, paste1Content);
		Paste paste2 = new Paste(paste2Privacy, paste2Title, paste2Content);
		
		returnMessage += ("Hello " + user1.getUsername() + "! Your ID is " + user1.getId() + ".<br>");
		returnMessage += ("Hello " + user2.getUsername() + "! Your ID is " + user2.getId() + ".<br>");
		
		returnMessage += "<br>";
		
		returnMessage += (paste1.getTitle() + "<br>");
		returnMessage += (paste1.getContent() + "<br>");
		
		returnMessage += "<br>";
		
		returnMessage += (paste2.getTitle() + "<br>");
		returnMessage += (paste2.getContent() + "<br>");
		
		return returnMessage;
	}

}
