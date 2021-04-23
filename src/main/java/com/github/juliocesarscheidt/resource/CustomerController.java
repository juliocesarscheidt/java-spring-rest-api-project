package com.github.juliocesarscheidt.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.juliocesarscheidt.entity.Customer;
import com.github.juliocesarscheidt.exception.BadRequestException;
import com.github.juliocesarscheidt.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	// GET /api/customer
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Customer> find() throws Exception {
		List<Customer> customers = customerService.find();
		return customers;
	}
		
	// GET /api/customer/:id
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Customer findOne(@PathVariable("id") Long id) throws Exception {
		if (! id.toString().matches("^[0-9]+$")) {
			throw new BadRequestException("Invalid Customer ID");
		}
		return customerService.findOne(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Customer create(@RequestBody Customer customer) throws Exception {
		return customerService.create(customer);
	}
	
	// PUT /api/customer/:id {}
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Customer update(@PathVariable("id") Long id, @RequestBody Customer customer) throws Exception {
		if (! id.toString().matches("^[0-9]+$")) {
			throw new BadRequestException("Invalid Customer ID");
		}
		return customerService.update(id, customer);
	}
	
	// DELETE /api/customer/:id
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) throws Exception {
		if (! id.toString().matches("^[0-9]+$")) {
			throw new BadRequestException("Invalid Customer ID");
		}
		customerService.delete(id);
	}
}
