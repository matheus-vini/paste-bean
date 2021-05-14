package br.com.inatel.icc.pastebean.controller;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
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
public class PasteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	String uri = "/pastes";
	
	@Test
	public void shouldShowPastesIdTitleAndContent() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get(uri))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("title")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("content")));
	}
	
	@Test
	public void shouldShowPasteOfId2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get(uri+"/2"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("title")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("content")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("Bob")));
	}
	
	@Test
	@Order(1)
	public void shouldCreatePaste() throws Exception {
		String json = "{\"title\":\"Drag on a Sphere\","
					 + "\"content\":\"The aerodynamic drag on an object depends on several factors, "
					 + "including the shape, size, inclination, and flow conditions. "
					 + "A sphere has symmetry around all three axes, and therefore its angle of attack never varies.\","
					 + "\"privacy\":\"PUBLIC\"}";
		
		mockMvc.perform(MockMvcRequestBuilders
						.post(uri)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string(containsString("sphere")));
	}
	
	@Test
	@Order(2)
	public void shouldChangeTitleAndContentOfNewlyCreatedPaste() throws Exception {
		String json = "{\"title\":\"Drag on a Banana\","
				 + "\"content\":\"There are no conclusive studies on air drag on a banana.\"}";
		
		String id = Query.highestPasteId().toString();
		
		mockMvc.perform(MockMvcRequestBuilders
						.put(uri+"/"+id)
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("banana")))
				.andExpect(MockMvcResultMatchers.content().string(containsString(id)));
	}
	
	@Test
	@Order(3)
	public void shouldDeleteNewlyCreatedUser() throws Exception {
		Long id = Query.highestPasteId();
		
		mockMvc.perform(MockMvcRequestBuilders
						.delete(uri+"/"+id))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
