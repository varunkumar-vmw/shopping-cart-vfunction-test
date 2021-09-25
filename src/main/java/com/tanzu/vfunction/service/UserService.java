package com.tanzu.vfunction.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanzu.vfunction.dto.UserDTO;
import com.tanzu.vfunction.entity.User;
import com.tanzu.vfunction.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User addUser(final UserDTO userDTO) {
		final User user = new User();
		user.setAddress(userDTO.getAddress());
		user.setName(userDTO.getName());
		return userRepository.save(user);
	}

	public User addUser(final User user) {
		return userRepository.save(user);
	}

	public void deleteUser(final int userId) {
		final Optional<User> user = getUser(userId);
		if (user.isPresent()) {
			userRepository.delete(user.get());
		}
	}

	public Optional<User> getUser(final int userId) {
		return userRepository.findById(userId);
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public boolean updateUser(final User iUser) {
		final Optional<User> user = getUser(iUser.getUserId());
		if (user.isPresent()) {
			addUser(iUser);
			return true;
		}

		return false;
	}
	
	public boolean isUserExists(final int userId) {
		return userRepository.findById(userId).isPresent();
	}
	
	public void deleteAll() {
		userRepository.deleteAll();
	}
}
