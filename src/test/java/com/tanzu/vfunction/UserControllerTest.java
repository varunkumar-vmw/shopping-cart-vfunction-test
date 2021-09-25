package com.tanzu.vfunction;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tanzu.vfunction.controller.UserController;
import com.tanzu.vfunction.dto.UserDTO;
import com.tanzu.vfunction.entity.User;

public class UserControllerTest extends ShoppingCartDemoApplicationTests {

	@Autowired
	private UserController userController;

	@AfterEach
	public void afterEach() {
		userController.deleteAll();
	}

	@Test
	public void contextTest() {
		assertThat(userController).isNotNull();
	}

	@Test
	public void userCreationAndGetUser() {
		final UserDTO user1 = new UserDTO();
		user1.setName("Test");
		user1.setAddress("Karnataka");
		final ResponseEntity<User> createdUser = userController.createUser(user1);
		final ResponseEntity<?> getUserByID = userController.getUser(createdUser.getBody().getUserId());

		assertThat(createdUser.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
		assertThat(getUserByID.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		@SuppressWarnings("unchecked")
		final User response = ((Optional<User>) getUserByID.getBody()).get();
		assertThat(response.getName()).isEqualTo("Test");
		assertThat(response.getAddress()).isEqualTo("Karnataka");
	}

	@Test
	public void getAllUser() {
		final UserDTO user1 = new UserDTO();
		final UserDTO user2 = new UserDTO();

		user1.setName("Test");
		user1.setAddress("Karnataka");

		user2.setName("Test 2");
		user2.setAddress("Uttar Pradesh");

		userController.createUser(user1);
		userController.createUser(user2);

		final ResponseEntity<?> users = userController.getUsers();

		assertThat(users.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

		@SuppressWarnings("unchecked")
		final List<User> response = ((List<User>) users.getBody());
		assertThat(response.size()).isEqualTo(2);
	}

	@Test
	public void updateUser() {
		final UserDTO user1 = new UserDTO();

		user1.setName("Test");
		user1.setAddress("Karnataka");

		final User createdUser = userController.createUser(user1).getBody();
		createdUser.setName("Test2");
		final ResponseEntity<?> updateUser = userController.updateUser(createdUser);

		assertThat(updateUser.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

		final ResponseEntity<?> getUserByID = userController.getUser(createdUser.getUserId());

		assertThat(getUserByID.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		@SuppressWarnings("unchecked")
		final User response = ((Optional<User>) getUserByID.getBody()).get();
		assertThat(response.getName()).isEqualTo("Test2");
	}

	@Test
	public void deleteUser() {
		final UserDTO user1 = new UserDTO();

		user1.setName("Test");
		user1.setAddress("Karnataka");

		final User createdUser = userController.createUser(user1).getBody();

		final ResponseEntity<?> users = userController.getUsers();

		assertThat(users.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

		@SuppressWarnings("unchecked")
		final List<User> response = ((List<User>) users.getBody());
		assertThat(response.size()).isEqualTo(1);
		
		/* Call Delete User */
		
		userController.deleteUser(createdUser.getUserId());
		final ResponseEntity<?> getUserByID = userController.getUser(createdUser.getUserId());
		assertThat(getUserByID.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
	}
}
