package com.github.juliocesarscheidt.resource;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.juliocesarscheidt.data.dto.CustomerDTO;
import com.github.juliocesarscheidt.exception.ServerErrorException;
import com.github.juliocesarscheidt.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerResource {

  @Autowired
  private CustomerService customerService;
  
  private Logger logger = LoggerFactory.getLogger(CustomerService.class);

  private void addLinkTo(CustomerDTO dto, Long id) {
	  	try {
	  		dto.add(linkTo(methodOn(CustomerResource.class).findOne(id)).withSelfRel());
			
		} catch (Exception e) {
			logger.error("Error caught " + e.getMessage());
			throw new ServerErrorException("Internal Server Error");
		}
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<CustomerDTO> find() throws Exception { 
    List<CustomerDTO> customers = customerService.find();
    customers.stream()
    	.forEach(cust -> addLinkTo(cust, cust.getUniqueId()));
    
    return customers;
  }
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public CustomerDTO create(@RequestBody CustomerDTO customer) throws Exception {
	CustomerDTO dto = customerService.create(customer);
	addLinkTo(dto, dto.getUniqueId());
 	
    return dto;
  }

  @GetMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public CustomerDTO findOne(@PathVariable("id") Long id) throws Exception {
	CustomerDTO dto = customerService.findOne(id);
	addLinkTo(dto, id);
	
    return dto;
  }

  @PutMapping("/{id}")
  @ResponseStatus(code = HttpStatus.ACCEPTED)
  public CustomerDTO update(@PathVariable("id") Long id, @RequestBody CustomerDTO customer) throws Exception {
	CustomerDTO dto = customerService.update(id, customer);
	addLinkTo(dto, id);
	
	return dto;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
    customerService.delete(id);
    
    return ResponseEntity.noContent().build();
  }
}
