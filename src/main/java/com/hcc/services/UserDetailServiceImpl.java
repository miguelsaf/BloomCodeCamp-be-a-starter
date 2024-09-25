package com.hcc.services;

import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
//    @Autowired
//    CustomPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepo;

    @Autowired
    CustomPasswordEncoder customPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepo.findByUsername(username);
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.getPasswordEncoder().encode("asdfasdf"));
        return userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
    }

    public UserDetails save(User user){
        if(user != null){
            if(user.getUsername() != null && user.getPassword() != null){
                if(userRepo.findByUsername(user.getUsername()).isPresent()){
                    throw new RuntimeException("Username already exist in database");
                }
                user.setPassword(customPasswordEncoder.getPasswordEncoder().encode(user.getPassword()));

                return userRepo.save(user);
            }
        }
        return null;
    }

    public String getLogedUsername(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if(username == null){
            throw new RuntimeException("No user logged in");
        }
        return username;
    }

    public User getLoogedUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}