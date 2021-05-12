package br.com.inatel.icc.pastebean.controller.dto.form;

import br.com.inatel.icc.pastebean.model.User;
import br.com.inatel.icc.pastebean.repository.UserApi;

public class UserFormDto {
	
	private UserApi userApi;
	
	private String username;
	private String password;
	private String auxUsername;
	private String auxPassword;

	public void setUsername(String username) {
		this.auxUsername = username;
	}

	public void setPassword(String password) {
		this.auxPassword = password;
	}

	public void validateCredentials() {
		br.com.inatel.icc.pasteuser.model.User user =
				userApi.createUser(auxUsername, auxPassword);
		this.username = user.getUsername();
		this.password = user.getPassword();
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public User convert() {
		validateCredentials();
		return new User(this.username, this.password);
	}
}
