package com.example.demo.Service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.user;
import com.example.demo.dao.userRepository;
import com.example.demo.util.JwtTokenUtil;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	userRepository rep;
	


    @Autowired
    private JwtTokenUtil jwtTokenUtil;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user user=rep.findByProfileEmail(username);
		if(user==null) throw new UsernameNotFoundException("user not exist");
		List <GrantedAuthority> list=new ArrayList<>();
		
		 return new org.springframework.security.core.userdetails.User(user.getProfile().getEmail(),
                 user.getPassword(), list);
	}

}
