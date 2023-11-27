package com.sample.service;

import org.springframework.stereotype.Service;

import com.sample.model.User;

@Service
public class UserService {

	public User getUserByName(String name) {
		User user = new User();
		user.setName(name);
		return user;
	}
}
