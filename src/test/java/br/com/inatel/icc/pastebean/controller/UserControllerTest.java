package br.com.inatel.icc.pastebean.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.icc.pastebean.controller.form.UserForm;
import br.com.inatel.icc.pastebean.repository.UserRepository;

@WebMvcTest
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private UserForm formRight;
	private UserForm formWrong;
	private UserController controller;
	private UserRepository repo;
	
	private String userUri = "http://localhost:8080/users/";

	@BeforeEach
	public void setup() {
		repo = Mockito.mock(UserRepository.class);
		formRight.setUsername("mock_user");
		formRight.setPassword("mock_pass");
		formWrong.setUsername("");
		formWrong.setPassword("");
		this.controller = new UserController(repo);
	}
	
	@Test
	void When_PassingCorrectParameters_Expect_UserCreationSucess() {
		controller.createUser(formRight, UriComponentsBuilder.fromUriString(userUri));
	}
	
	@Test
	void When_MissingUsernameOrPassword_Expect_CreateUserToFail() {
		controller.createUser(null, null);
	}

}
