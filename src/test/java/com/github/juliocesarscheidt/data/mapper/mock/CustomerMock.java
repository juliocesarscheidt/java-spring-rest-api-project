package com.github.juliocesarscheidt.data.mapper.mock;

import com.github.juliocesarscheidt.data.dto.CustomerDTO;
import com.github.juliocesarscheidt.data.entity.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerMock {
  public Customer mockEntity() {
    return mockEntity(0);
  }

  public CustomerDTO mockDTO() {
    return mockDTO(0);
  }

  public List<Customer> mockEntityList() {
    List<Customer> customers = new ArrayList<Customer>();
    for (int i = 0; i < 10; i++) {
      customers.add(mockEntity(i));
    }

    return customers;
  }

  public List<CustomerDTO> mockDTOList() {
    List<CustomerDTO> customers = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      customers.add(mockDTO(i));
    }

    return customers;
  }

  private Customer mockEntity(Integer number) {
    Customer customer = new Customer();
    // setting ID
    customer.setId(number.longValue());

    customer.setAddress("address_" + number);
    customer.setFirstName("firstName_" + number);
    customer.setLastName("lastName_" + number);
    customer.setEmail("email_" + number);

    customer.setGender(((number % 2) == 0) ? "Male" : "Female");

    return customer;
  }

  private CustomerDTO mockDTO(Integer number) {
    CustomerDTO customer = new CustomerDTO();
    // setting unique ID
    customer.setUniqueId(number.longValue());

    customer.setAddress("address_" + number);
    customer.setFirstName("firstName_" + number);
    customer.setLastName("lastName_" + number);
    customer.setEmail("email_" + number);

    customer.setGender(((number % 2) == 0) ? "Male" : "Female");

    return customer;
  }
}
