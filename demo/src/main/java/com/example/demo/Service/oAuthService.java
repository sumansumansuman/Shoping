package com.example.demo.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.example.demo.entity.profile;
import com.example.demo.entity.user;
import com.example.demo.dao.userRepository;

@Service
public class oAuthService extends OidcUserService {
	
	@Autowired
	userRepository rep;

 

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map attributes = oidcUser.getAttributes();
        System.out.println(attributes);
        if(rep.findByProfileEmail((String) attributes.get("email"))!=null);
        else {
        	user user=new user();
        	profile p=new profile();
        	p.setEmail((String) attributes.get("email"));
            p.setName((String) attributes.get("name"));
            user.setProfile(p);
        	rep.save(user);
        }
       System.out.println("name:"+attributes.get("name"));        
        return oidcUser;
    }
    

}