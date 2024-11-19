package com.example.Kanban.controller;

import com.example.Kanban.model.UserModel;
import com.example.Kanban.Configuration.JWTRequest;
import com.example.Kanban.TaskRepository.UserRepo;
import com.example.Kanban.Configuration.JwtResponse;
import com.example.Kanban.Configuration.JwtTokenUtil;
import com.example.Kanban.Configuration.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetails userDetailsService;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserModel userModel) {
        if (userRepository.findByUsername(userModel.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        UserModel savedUser = userRepository.save(userModel);

        // Carregue o UserDetails usando o nome de usuário
        UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getUsername());

        // Gere o token com o UserDetails
        String token = jwtTokenUtil.generateToken(userDetails);

        // Retorne o token JWT
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
