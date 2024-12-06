package com.demo.spring_security.service;

import com.demo.spring_security.model.UserPrincipal;
import com.demo.spring_security.model.Users;
import com.demo.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(username);
        if(user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found"); // to know our system
        }
        // if user found we have to return obj of userdetails (interface) we have to create out own class which implements this UserDetails

        return new UserPrincipal(user);
    }
}
