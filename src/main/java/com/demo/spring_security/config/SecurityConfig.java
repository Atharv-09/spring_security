package com.demo.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //diasbling the CSRF(coz if it will be enable then we can't do the put/post/delete operations on data
        httpSecurity.csrf(customizer -> customizer.disable());
        // to authorize the application , to achieve login restriction
        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        /*we are sending the username and password but no way we are using so we have to add
         formLogin - for browser*/
        // httpSecurity.formLogin(Customizer.withDefaults()); // comment if using below session coz everytime it will give the login form as theier were many sessions
        /* as using form login we are getting the html in postman as it is returning login form
         so we used httpBasic - for REST API access (postman); */
        httpSecurity.httpBasic(Customizer.withDefaults());
        /* as we are disalbing the csrf, we can make the http stateless means everytime we reload or we go to different page thier is new session id
         work in postman but will get login page every time in browser/loginform
            to get work in browser commment formLogin so that thier is http form it will login and refersh we see dif session ids */
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }
}
