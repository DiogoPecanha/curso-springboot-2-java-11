package com.springbootstudy.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springbootstudy.course.entities.User;
import com.springbootstudy.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		
		List<User> users = service.findAll();		
		return ResponseEntity.ok(users);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User user = service.findById(id);
		
		if (user == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(user);
		}
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user) {
		User userCreated = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")
											 .buildAndExpand(userCreated.getId())
											 .toUri();
		return ResponseEntity.created(uri).body(userCreated);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
