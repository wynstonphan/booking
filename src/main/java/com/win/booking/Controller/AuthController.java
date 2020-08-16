package com.win.booking.Controller;

import com.win.booking.Model.User;
import com.win.booking.Payload.Request.LoginRequest;
import com.win.booking.Payload.Response.JwtResponse;
import com.win.booking.Payload.Response.MessageResponse;
import com.win.booking.Payload.Request.RegisterRequest;
import com.win.booking.Repository.UserRepository;
import com.win.booking.Sercurity.Jwt.JwtUtils;
import com.win.booking.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    ResponseEntity login(@RequestBody LoginRequest loginRequest){
        User user = userRepository.findByUsername(loginRequest.getUsername());

        if(user == null ){
            return ResponseEntity.badRequest().body(new MessageResponse("Username or Password does not match"));
        }
        String password = userRepository.findByUsername(loginRequest.getUsername()).getPassword();
        Boolean matches = bCryptPasswordEncoder.matches(loginRequest.getPassword(), password);
        if(!matches){
            return ResponseEntity.badRequest().body(new MessageResponse("Username or Password does not match"));
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Long userId = userRepository.findByUsername(userDetails.getUsername()).getId();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),roles,userId));
    }

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        if(userRepository.findByUsername(registerRequest.getUsername()) != null){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken"));
        }
        if(!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: password and password confirm does not match"));
        }
        userService.saveUser(registerRequest);
        return ResponseEntity.ok(new MessageResponse("Successfully Register"));
    }

}
