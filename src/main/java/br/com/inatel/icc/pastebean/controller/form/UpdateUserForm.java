package br.com.inatel.icc.pastebean.controller.form;

import br.com.inatel.icc.pastebean.model.User;
import br.com.inatel.icc.pastebean.repository.UserApi;
import br.com.inatel.icc.pastebean.repository.UserRepository;

public class UpdateUserForm {

	private UserApi userApi = new UserApi();
	
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

	public User update(Long id, UserRepository userRepository) {
		User user = userRepository.getOne(id);
		validateCredentials();
		user.setUsername(this.username);
		user.setPassword(this.password);
		
		return user;
	}

}
