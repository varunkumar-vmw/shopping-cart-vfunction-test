package com.tanzu.vfunction.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanzu.vfunction.dto.UserDTO;
import com.tanzu.vfunction.entity.User;
import com.tanzu.vfunction.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody final UserDTO user) {
		final User userCreated = userService.addUser(user);
		return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
	}
	
	@GetMapping("{userid}")
	public ResponseEntity<?> getUser(@PathVariable final int userid) {
		final Optional<User> user = userService.getUser(userid);
		
		if (user.isEmpty()) {
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("all")
	public ResponseEntity<?> getUsers() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}
	
	@DeleteMapping("{userid}")
	public ResponseEntity<?> deleteUser(@PathVariable final int userid) {
		userService.deleteUser(userid);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody final User user) {
		final boolean isUpdated = userService.updateUser(user);
		return new ResponseEntity<>(isUpdated ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("deleteall")
	public ResponseEntity<?> deleteAll() {
		userService.deleteAll();

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
