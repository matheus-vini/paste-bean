package br.com.inatel.icc.pastebean.controller;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.inatel.icc.pastebean.config.db.Query;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tests")
@TestMethodOrder(OrderAnnotation.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	String uri = "/users";
	
	@Test
	public void shouldShowUsersNameAndId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get(uri))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("username")));
	}
	
	@Test
	@Order(1)
	public void shouldCreateUser() throws Exception {
		String json = "{\"username\":\"Katamari\","
					 + "\"password\":\"Damacy\"}";
		
		mockMvc.perform(MockMvcRequestBuilders
						.post(uri)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Katamari")));
	}
	
	@Test
	@Order(2)
	public void shouldChangeUsernameAndPasswordOfNewlyCreatedUser() throws Exception {
		String json = "{\"username\":\"Megaman\","
				 + "\"password\":\"Protoman\"}";
		
		String id = Query.highestUserId().toString();
		
		mockMvc.perform(MockMvcRequestBuilders
						.put(uri+"/"+id)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("Megaman")))
				.andExpect(MockMvcResultMatchers.content().string(containsString(id)));
	}
	
	@Test
	@Order(3)
	public void shouldDeleteNewlyCreatedUser() throws Exception {
		Long id = Query.highestUserId();
		
		mockMvc.perform(MockMvcRequestBuilders
						.delete(uri+"/"+id))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
/*	
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
*/
}
