package com.hcc.controllers;

import com.hcc.dto.AuthCredentialsRequest;
import com.hcc.dto.TokenRequest;
import com.hcc.entities.User;
import com.hcc.services.AssignmentService;
import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @PostMapping(value ="/api/auth/login" )
    public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest credentialsRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentialsRequest.getUsername(),credentialsRequest.getPassword())
            );
        }catch (AuthenticationException e){
            throw new Exception("Invalid Username or Password");
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(credentialsRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping(value="/api/auth/validate")
    public ResponseEntity<?> validateToken(@RequestBody TokenRequest token){

        String username =  jwtUtil.getUsernameFromToken(token.getToken());
        if(username == null){
            return ResponseEntity.badRequest().body("Invalid token");
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(username);

        boolean isValidToken = jwtUtil.validateToken(token.getToken(), userDetails);

        if(isValidToken){
            return ResponseEntity.ok("Token is valid");
        }
        return ResponseEntity.status(401).body("Token is not valid");
    }

    @PostMapping(value="/api/auth/register")
    public ResponseEntity<?> register(@RequestBody User user){
        UserDetails savedUserDetails = userDetailService.save(user);
        if( savedUserDetails != null)
            return new ResponseEntity<>("User created",HttpStatus.CREATED);
        return ResponseEntity.badRequest().body("Invalid Username or Password");
    }
}
