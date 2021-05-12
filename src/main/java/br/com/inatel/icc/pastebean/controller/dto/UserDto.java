package br.com.inatel.icc.pastebean.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

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

	public static List<UserDto> convert(List<User> users) {
		return users.stream().map(UserDto::new).collect(Collectors.toList());
	}
}
