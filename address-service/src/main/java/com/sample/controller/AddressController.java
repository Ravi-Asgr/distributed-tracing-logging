package com.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.config.SystemConfig;
import com.sample.model.Address;
import com.sample.service.AddressService;

/*
 * Counter Metric. Single Metric with different tags for GET, POST and PUT.
 */
@RestController
@RequestMapping("/address")
public class AddressController {


	@Autowired
	private AddressService addressService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@GetMapping("/{name}")
    public Address findByName(@PathVariable String name) {

		logger.info("Got GET /address request");
		SystemConfig.sleep();
		Address address = addressService.getAddressByName(name);
		logger.info("Completed GET /address request");
		return address;
    }
	
	
}
