package com.github.juliocesarscheidt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.juliocesarscheidt.data.dto.CustomerDTO;
import com.github.juliocesarscheidt.data.entity.Customer;
import com.github.juliocesarscheidt.data.mapper.DataMapper;
import com.github.juliocesarscheidt.exception.BadRequestException;
import com.github.juliocesarscheidt.exception.EntityNotFoundException;
import com.github.juliocesarscheidt.exception.ServerErrorException;
import com.github.juliocesarscheidt.repository.CustomerRepository;

@Service
public class CustomerService extends BaseService {
  @Autowired
  CustomerRepository repository;

  public CustomerDTO findOne(Long id) throws Exception {
    Customer entity = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Customer Not Found"));

    return DataMapper.parseObject(entity, CustomerDTO.class);
  }

  public List<CustomerDTO> find(Integer page, Integer size) throws Exception {
    try {
      Pageable pageable = PageRequest.of(page, size);
      List<Customer> customers = repository.findAll(pageable).getContent();

      return DataMapper.parseObjects(customers, CustomerDTO.class);

    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }

  public CustomerDTO create(CustomerDTO customer) throws Exception {
    if (!customer.validate()) {
      throw new BadRequestException("Invalid Attributes");
    }

    try {
      Customer entity = DataMapper.parseObject(customer, Customer.class);

      entity.setCreatedAt(this.getTimestamp());
      entity.setEnabled(true);

      System.out.println(entity.toString());

      return DataMapper.parseObject(repository.save(entity), CustomerDTO.class);

    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }

  public CustomerDTO update(Long id, CustomerDTO customer) throws Exception {
    if (!customer.validate()) {
      throw new BadRequestException("Invalid Attributes");
    }

    Customer entity = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Customer Not Found"));

    if (customer.getFirstName() != null) entity.setFirstName(customer.getFirstName());
    if (customer.getLastName() != null) entity.setLastName(customer.getLastName());
    if (customer.getEmail() != null) entity.setEmail(customer.getEmail());
    if (customer.getAddress() != null) entity.setAddress(customer.getAddress());
    if (customer.getGender() != null) entity.setGender(customer.getGender());

    entity.setUpdatedAt(this.getTimestamp());
    entity.setEnabled(true);

    System.out.println(entity.toString());

    try {
      return DataMapper.parseObject(repository.save(entity), CustomerDTO.class);

    } catch (Exception e) {
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

  @Transactional
  public void disable(Long id) {
    repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Customer Not Found"));

    try {
      repository.disableCustomer(id, this.getTimestamp());

    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }

  @Transactional
  public void enable(Long id) {
    repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Customer Not Found"));

    try {
      repository.enableCustomer(id, this.getTimestamp());

    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }
}
