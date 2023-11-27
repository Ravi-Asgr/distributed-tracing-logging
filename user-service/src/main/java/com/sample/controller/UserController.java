package com.sample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sample.config.SystemConfig;
import com.sample.model.Address;
import com.sample.model.User;
import com.sample.service.UserService;

/*
 * Counter Metric. Single Metric with different tags for GET, POST and PUT.
 */
@RestController
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@GetMapping("/{name}")
    public User findByName(@PathVariable String name) {

		logger.info("Got GET /user request");
		logger.info("Calling address-service as GET /user request");
		SystemConfig.sleep();
		ResponseEntity<Address> response = restTemplate.getForEntity("http://localhost:7001/address/"+name, Address.class);
		logger.info("Got response from address-service for GET /user request");
		User user = userService.getUserByName(name);
		user.setAddress(response.getBody());
		logger.info("Completed GET /user request");
		return user;
		
    }
	
	/*@PostMapping("/{name}")
    public List<String> insertByName(@PathVariable String name) {
		simpleMeterRegistry.counter("user-oper-counter", "user-operation", "post.user").increment();
		
		Timer timer = simpleMeterRegistry.timer("crud.user", "user-operation", "create");
		timer.record(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println("Post - " + timer.count());
		
		userList.add(name);
        return List.of("user1", "user2");
    }
	
	@PutMapping("/{name}")
    public List<String> updateByName(@PathVariable String name) {
		simpleMeterRegistry.counter("user-oper-counter", "user-operation", "put.user").increment();
		
		long start = System.currentTimeMillis();
		Timer timer = simpleMeterRegistry.timer("crud.user", "user-operation", "put");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.record(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
		
		return List.of("user1", "user2");
    }*/
	
}
