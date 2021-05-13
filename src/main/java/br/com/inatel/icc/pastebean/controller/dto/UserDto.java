package br.com.inatel.icc.pastebean.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.inatel.icc.pastebean.model.User;

public class UserDto {

	private Long id = (long)0;
	private String username = "@nonymous";

	public UserDto (User user) {
		if(user != null) {
			this.username = user.getUsername();
			this.id = user.getId();
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public Long getId() {
		return id;
	}

	public static List<UserDto> convert(List<User> users) {
		return users.stream().map(UserDto::new).collect(Collectors.toList());
	}
}
