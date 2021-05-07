package com.github.juliocesarscheidt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.juliocesarscheidt.data.dto.CustomerDTO;
import com.github.juliocesarscheidt.data.entity.Customer;
import com.github.juliocesarscheidt.data.mapper.DataMapper;
import com.github.juliocesarscheidt.exception.BadRequestException;
import com.github.juliocesarscheidt.exception.EntityNotFoundException;
import com.github.juliocesarscheidt.exception.ServerErrorException;
import com.github.juliocesarscheidt.repository.CustomerRepository;

@Service
public class CustomerService {
  @Autowired
  CustomerRepository repository;
  
  private Logger logger = LoggerFactory.getLogger(CustomerService.class);

  public CustomerDTO findOne(Long id) throws Exception {
    Customer entity = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Customer Not Found"));
    
    return DataMapper.parseObject(entity, CustomerDTO.class);
  }

  public List<CustomerDTO> find() throws Exception {
    try {
      return DataMapper.parseObjects(repository.findAll(), CustomerDTO.class);

    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }

  public CustomerDTO create(CustomerDTO customer) throws Exception {
    if (customer.getGender() != null
      && (!customer.getGender().equals("Male")
      && !customer.getGender().equals("Female"))
    ) {
      throw new BadRequestException("Invalid Customer Gender");
    }

    try {
      Customer entity = DataMapper.parseObject(customer, Customer.class);
      return DataMapper.parseObject(repository.save(entity), CustomerDTO.class);

    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }

  public CustomerDTO update(Long id, CustomerDTO customer) throws Exception {
    if (customer.getGender() != null
      && (!customer.getGender().equals("Male")
      && !customer.getGender().equals("Female"))
    ) {
      throw new BadRequestException("Invalid Customer Gender");
    }

    Customer entity = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Customer Not Found"));

    if (customer.getFirstName() != null) entity.setFirstName(customer.getFirstName());
    if (customer.getLastName() != null) entity.setLastName(customer.getLastName());
    if (customer.getEmail() != null) entity.setEmail(customer.getEmail());
    if (customer.getAddress() != null) entity.setAddress(customer.getAddress());
    if (customer.getGender() != null) entity.setGender(customer.getGender());

    try {
      return DataMapper.parseObject(repository.save(entity), CustomerDTO.class);

    }  catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }

  public void delete(Long id) throws Exception {
    Customer entity = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Customer Not Found"));

    try {
      repository.delete(entity);

    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }
}
