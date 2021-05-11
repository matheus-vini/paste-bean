package br.com.inatel.icc.pastebean.controller.dto;

import br.com.inatel.icc.pastebean.model.User;

public class UserDto {

	private String username = "@nonymous";

	public UserDto (User user) {
		if(user != null)
			this.username = user.getUsername();
	}
	
	public String getUsername() {
		return username;
	}
}
