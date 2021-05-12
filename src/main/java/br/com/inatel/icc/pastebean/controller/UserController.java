package br.com.inatel.icc.pastebean.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.icc.pastebean.controller.dto.UserDto;
import br.com.inatel.icc.pastebean.controller.form.UpdateUserForm;
import br.com.inatel.icc.pastebean.controller.form.UserForm;
import br.com.inatel.icc.pastebean.model.User;
import br.com.inatel.icc.pastebean.repository.UserRepository;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder){
		User user = userForm.convert();
		userRepository.save(user);
		
		URI uri = uriBuilder.path("users/{id}").buildAndExpand(user.getId()).toUri();		
		return ResponseEntity.created(uri).body(new UserDto(user));
	}
	
	@GetMapping
	public List<UserDto> readUsers(){
		List<User> users = userRepository.findAll();
		return UserDto.convert(users);
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<UserDto> updateUsers(@PathVariable Long id, @RequestBody @Valid UpdateUserForm userForm){
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			User user = userForm.update(id, userRepository);
			return ResponseEntity.ok(new UserDto(user));
		}
		
		return ResponseEntity.notFound().build();
	}
}
