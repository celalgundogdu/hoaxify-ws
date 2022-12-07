package com.hoaxify;

import com.hoaxify.dto.request.CreateUserRequest;
import com.hoaxify.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class HoaxifyApplication implements CommandLineRunner {

	private final UserService userService;

	public HoaxifyApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(HoaxifyApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		CreateUserRequest request = new CreateUserRequest();
		request.setUsername("user1");
		request.setDisplayName("display1");
		request.setPassword("Pass1234");
		userService.createUser(request);
	}
}
