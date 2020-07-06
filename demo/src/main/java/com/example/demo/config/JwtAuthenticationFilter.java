package com.example.demo.config;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.util.JwtTokenUtil;
import com.example.demo.common.Constants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
     // System.out.println(req.getParameter(Constants.TOKEN_PARAM));
    	String token = req.getParameter(Constants.TOKEN_PARAM);
        String username = null;
        if (token != null) {
        	System.out.println("token is not null");
            try {
                username = jwtTokenUtil.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
               // logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
               // logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
               // logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
           // logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        	System.out.println("2222222222222222");
            if (jwtTokenUtil.validateToken(token, userDetails)) {
            	System.out.println("333333333");
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }
}
