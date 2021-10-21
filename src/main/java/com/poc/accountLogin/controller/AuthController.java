package com.poc.accountLogin.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import com.poc.accountLogin.model.AuditLog;
import com.poc.accountLogin.model.AuthenticationRequest;
import com.poc.accountLogin.model.AuthenticationResponse;
import com.poc.accountLogin.util.JwtUtil;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping(path="/authenticate")
public class AuthController {
	
	private static Logger log = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	AuthenticationManager authenticationManager;
	
	

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtUtils;
	
	@Autowired
   private KafkaTemplate<String, AuditLog> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example_json";
	
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws  ClassNotFoundException {

		try
		{
		
		Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword()));

		User principal = (User) authentication.getPrincipal();
		
		// kafkaTemplate.send(TOPIC,  new AuditLog("AccountLogin", "authenticate", principal.getUsername(), new Date()));
		
		 log.info("Call createAuthenticationToken : "+authenticationRequest.getPassword());
		return ResponseEntity.ok(new AuthenticationResponse(jwtUtils.generateToken(principal.getUsername())));
		}catch(AuthenticationException e ){
			
			// kafkaTemplate.send(TOPIC,  new AuditLog("AccountLogin", "authenticateFail", authenticationRequest.getUsername(), new Date()));
			throw e;
		}
	}
	
	/*@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String getDetails() throws  ClassNotFoundException {

		/*Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword()));

		User principal = (User) authentication.getPrincipal();
		
		AppUserProfileDetails userDetails = userProfileService.findUserProfileDetails(principal.getUsername(),principal.getUsername()) ;
		return ResponseEntity.ok(new AuthenticationResponse(jwtUtils.generateToken(principal.getUsername()), userDetails.getData().get(0)));
		return "hello";
	}*/

}
