package com.example.demo.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.JwtAuthenticationResponse;
import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.LoginResponse;
import com.example.demo.payload.SignUpRequest;
import com.example.demo.util.JwtTokenUtil;
import com.example.demo.dao.userRepository;
import com.example.demo.entity.profile;
import com.example.demo.entity.user;


@RestController
@RequestMapping("/user/auth")
@CrossOrigin("http://localhost:3000")
public class userAuthController {
	


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    userRepository userRepository;
    
    @Autowired
    JwtTokenUtil tokenGenerator;
    
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req){

     System.out.println("request come"+req);
        
        Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
                   
        );
        

        SecurityContextHolder.getContext().setAuthentication(authentication);
        user u= userRepository.findByProfileEmail(req.getEmail());
        String jwt =tokenGenerator .generateToken(u);
        LoginResponse response=new LoginResponse(jwt,u.getProfile().getName(),u.getProfile().getEmail());
        return ResponseEntity.ok(response);

		
	}
	
	@PostMapping("/signUp")
	public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest req){
		if(userRepository.existsByProfileEmail(req.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
		if(userRepository.existsByProfileMobileNo(req.getMobile())) {
            return new ResponseEntity(new ApiResponse(false, "Mobile number already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
		
		profile p=new profile(req.getName(),req.getEmail(),req.getMobile(),req.getGender());
		System.out.println(p);
		user u=new user(p,req.getPassword());
		userRepository.save(u);
		System.out.println("saved sucessfully");
		return ResponseEntity.ok( "User registered successfully");
		
		
	}
	
	@GetMapping("/logoutSuccess")
	public ResponseEntity<?> logout(){
		return ResponseEntity.ok("successfuly logged out");
	}

}
