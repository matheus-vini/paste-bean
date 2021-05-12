package br.com.inatel.icc.pastebean.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.inatel.icc.pastebean.model.User;
import br.com.inatel.icc.pastebean.repository.UserRepository;

public class UpdateUserForm {

	@NotNull @NotEmpty
	@Size(min = 3, max = 32)
	private String username;
	@NotNull @NotEmpty
	@Size(min = 6, max = 32)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User update(Long id, UserRepository userRepository) {
		User user = userRepository.getOne(id);
		user.setUsername(this.username);
		user.setPassword(this.password);
		
		return user;
	}

}
