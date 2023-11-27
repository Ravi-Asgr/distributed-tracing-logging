package com.sample.service;

import org.springframework.stereotype.Service;

import com.sample.model.Address;

@Service
public class AddressService {

	public Address getAddressByName(String name) {
		Address address = new Address();
		address.setField(name);
		return address;
	}
}
