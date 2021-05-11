package br.com.inatel.icc.pastebean.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.icc.pastebean.controller.dto.PasteDto;
import br.com.inatel.icc.pastebean.model.Paste;
import br.com.inatel.icc.pastebean.model.PastePrivacy;
import br.com.inatel.icc.pastebean.model.User;

@RestController
public class PasteController {
	
	@RequestMapping("pastes")
	public List<PasteDto> showPastes() {
		
		PastePrivacy paste1Privacy = PastePrivacy.PUBLIC;
		String paste1Title = "Bob Bobbington's Paste";
		String paste1Content = "Bobbington is a village and civil parish in the South Staffordshire district of "
				+ "Staffordshire, England, about 5 miles west of Wombourne.";
		PastePrivacy paste2Privacy = PastePrivacy.HIDDEN;
		String paste2Title = "Spell Cards";
		String paste2Content = "Beckon Sign: Many Danmaku Guests | "
				+ "Beckon Sign: Shoot Away Disaster, Beckon in Fortune";
		
		String user1Name = "Bob";
		String user1Pass = "BoB_808";
		String user2Name = "Mike";
		String user2Pass = "go-to-Kuji";
		
		User user1 = new User(user1Name, user1Pass);
		User user2 = new User(user2Name, user2Pass);
		Paste paste1 = new Paste(paste1Privacy, paste1Title, paste1Content, user1);
		Paste paste2 = new Paste(paste2Privacy, paste2Title, paste2Content, user2);
		
		return PasteDto.convert(Arrays.asList(paste1, paste2));
	}

}
