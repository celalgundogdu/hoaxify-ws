package com.hoaxify;

import com.hoaxify.dto.request.CreateUserRequest;
import com.hoaxify.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class HoaxifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoaxifyApplication.class, args);
	}

	@Bean
	@Profile("dev")
	CommandLineRunner createInitialUsers(UserService userService) {
		return args -> {
			for(int i = 1; i < 25; i++) {
				CreateUserRequest request = new CreateUserRequest();
				request.setUsername("user" + i);
				request.setDisplayName("display" + i);
				request.setPassword("Pass1234");
				userService.createUser(request);
			}
		};
	}
}
