package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

@RestController

@RequestMapping(value="/ChatApplication")
public class Controller {
	
	@Autowired
	UserRepository userRepoistory;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping( value="/subs",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> createUser(@RequestBody AuthenticationRequest authenticationRequest)
	{
		UserModel ExistsUsername=userRepoistory.findByUsername(authenticationRequest.getUsername());
		if(ExistsUsername==null)
		{
			String Username=authenticationRequest.getUsername();
			String Password=authenticationRequest.getPassword();
			UserModel userModel= new UserModel();
			userModel.setUsername(Username);
			userModel.setPassword(Password);
		try {
			userRepoistory.save(userModel);
		} catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse(Username,"User Subscription Failed!!" ));
		}
			
			
			return ResponseEntity.ok(new AuthenticationResponse( Username,"User Succesfully Subscribed" ));

		}
		else if(ExistsUsername.getUsername().equals(authenticationRequest.getUsername()))
				{
					return ResponseEntity.ok(new AuthenticationResponse(authenticationRequest.getUsername(),"UserName Already Exists!!" ));
				}
		else {
		String Username=authenticationRequest.getUsername();
		String Password=authenticationRequest.getPassword();
		UserModel userModel= new UserModel();
		userModel.setUsername(Username);
		userModel.setPassword(Password);
	try {
		userRepoistory.save(userModel);
	} catch (Exception e) {
		return ResponseEntity.ok(new AuthenticationResponse(Username,"User Subscription Failed!!" ));
	}
		return ResponseEntity.ok(new AuthenticationResponse( Username,"User Succesfully Subscribed" ));
	}
	}
	
	@PostMapping(value="/auth",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> AuthorizeUser(@RequestBody AuthenticationRequest authenticationRequest)
	{
		String Username=authenticationRequest.getUsername();
		String Password=authenticationRequest.getPassword();
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(Username, Password));	
		} catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse(Username,"User Authentication Failed!!" ));
		}
		
		
		return ResponseEntity.ok(new AuthenticationResponse(Username,"User Authenticated" ));
	}
	
	@GetMapping(value="/getAllUSers")
	public List<UserModel> getallusers()
	{
		return userRepoistory.findAll();
	}
	
	@DeleteMapping(value="/deleteAll")
	public String deleteallUsers()
	{
		userRepoistory.deleteAll();
		return "Users Deleted";
	}

}
