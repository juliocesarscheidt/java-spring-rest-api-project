package com.github.juliocesarscheidt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.juliocesarscheidt.entity.Customer;
import com.github.juliocesarscheidt.exception.BadRequestException;
import com.github.juliocesarscheidt.exception.EntityNotFoundException;
import com.github.juliocesarscheidt.exception.ServerErrorException;
import com.github.juliocesarscheidt.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository repository;
	
	public Customer findOne(Long id) throws Exception {
		Customer customer = repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Customer Not Found"));
		
		return customer;
	}
	
	public List<Customer> find() throws Exception {
		try {
			List<Customer> customers = repository.findAll();
			return customers;
			
		} catch (Exception e) {
			throw new ServerErrorException("Internal Server Error");
		}
	}
	
	public Customer create(Customer customer) throws Exception {
		if (customer.getGender() != null
			&& (!customer.getGender().equals("Male")
			&& !customer.getGender().equals("Female"))
		) {
			throw new BadRequestException("Invalid Customer Gender");
		}
		
		try {
			return repository.save(customer);
			
		} catch (Exception e) {
			throw new ServerErrorException("Internal Server Error");
		}
	}
	
	public Customer update(Long id, Customer customer) throws Exception {
		if (customer.getGender() != null
			&& (!customer.getGender().equals("Male")
			&& !customer.getGender().equals("Female"))
		) {
			throw new BadRequestException("Invalid Customer Gender");
		}
		
		Customer entity = repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Customer Not Found"));
		
		entity.setFirstName(customer.getFirstName());
		entity.setLastName(customer.getLastName());
		entity.setEmail(customer.getEmail());
		entity.setAddress(customer.getAddress());
		entity.setGender(customer.getGender());

		try {
			return repository.save(entity);
			
		} catch (Exception e) {
			throw new ServerErrorException("Internal Server Error");
		}
	}
	
	public void delete(Long id) throws Exception {
		Customer entity = repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Customer Not Found"));
		
		try {
			repository.delete(entity);
			
		} catch (Exception e) {
			throw new ServerErrorException("Internal Server Error");
		}
	}
}
