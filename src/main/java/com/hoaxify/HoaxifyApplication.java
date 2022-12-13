package com.hoaxify;

import com.hoaxify.hoax.Hoax;
import com.hoaxify.hoax.HoaxRepository;
import com.hoaxify.user.User;
import com.hoaxify.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Date;

@SpringBootApplication
public class HoaxifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoaxifyApplication.class, args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner createInitialUsers(UserRepository userRepository, HoaxRepository hoaxRepository) {
        return args -> {
            for (int i = 1; i <= 5; i++) {
                User user = new User();
                user.setUsername("user" + i);
                user.setDisplayName("display" + i);
                user.setPassword("Pass1234");
                userRepository.save(user);
                for (int j = 1; j <=2; j++) {
                    Hoax hoax = new Hoax();
                    hoax.setContent("hoax - " + j + " from user" + i);
                    hoax.setCreatedAt(new Date());
                    hoax.setUser(user);
                    hoaxRepository.save(hoax);
                }
            }
        };
    }
}
