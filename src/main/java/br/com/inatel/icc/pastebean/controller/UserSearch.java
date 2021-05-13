package br.com.inatel.icc.pastebean.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.icc.pastebean.model.User;
import br.com.inatel.icc.pastebean.repository.UserRepository;

@RestController
public class UserSearch {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("users/{id}")
	public User findUser(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent())
			return user.get();
		return null;
	}
}
