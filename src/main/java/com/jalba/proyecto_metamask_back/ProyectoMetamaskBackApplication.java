package com.jalba.proyecto_metamask_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class ProyectoMetamaskBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoMetamaskBackApplication.class, args);
	}

	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(User.withUsername("user")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build(),
                User.withUsername("dani")
                        .password(passwordEncoder().encode("123"))
                        .roles("USER")
                        .build());
    }
}
