package com.jalba.proyecto_metamask_back;


 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jalba.proyecto_metamask_back.jwt.JwtRequestFilter;
import com.jalba.proyecto_metamask_back.jwt.JwtTokenUtil;
 
@Configuration
@EnableWebSecurity
public class WebConf {


    @Autowired
    JwtTokenUtil jwtUtils;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    

     @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
    .csrf().disable()
    .addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
    .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/authenticate").permitAll()
        .anyRequest().authenticated()
    );

return http.build();

}

}