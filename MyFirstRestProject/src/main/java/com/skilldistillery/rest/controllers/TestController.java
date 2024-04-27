package com.skilldistillery.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rest.data.User;

@RestController
public class TestController {

//	@RequestMapping(path = "ping", method = RequestMethod.GET)
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}

	@PostMapping("echo")
	public String echo(@RequestBody String input) {
		return input;
	}

	@GetMapping("api/users/1")
	public User getUser(){
	  return new User("Julia Cousins", "JCousins", "JC@gmail.com", "wombat1");
	}
	
	@PostMapping("api/users")
//	public String addUser(@RequestBody String userParam){
	public User addUser(@RequestBody User userParam){
	  System.out.println(userParam);
	  return userParam;
	}
}
