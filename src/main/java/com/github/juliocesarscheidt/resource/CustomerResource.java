package com.github.juliocesarscheidt.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.juliocesarscheidt.data.dto.CustomerDTO;
import com.github.juliocesarscheidt.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Customer Endpoint", tags = {"Customer"})
@RestController
@RequestMapping("/v1/customer")
public class CustomerResource extends BaseResource {

  @Autowired
  private CustomerService customerService;

  @ApiOperation(value = "Find All")
  @GetMapping(produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.OK)
  public List<CustomerDTO> find(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "50") Integer size) throws Exception {
    List<CustomerDTO> customers = customerService.find(page, size);
    customers.stream()
      .forEach(cust -> addLinkTo(cust, cust.getUniqueId()));

    return customers;
  }

  @ApiOperation(value = "Create")
  @PostMapping(produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.CREATED)
  public CustomerDTO create(@RequestBody CustomerDTO customer) throws Exception {
    CustomerDTO dto = customerService.create(customer);
    addLinkTo(dto, dto.getUniqueId());

    return dto;
  }

  @ApiOperation(value = "Find One")
  @GetMapping(value = "/{id}", produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.OK)
  public CustomerDTO findOne(@PathVariable("id") Long id) throws Exception {
    CustomerDTO dto = customerService.findOne(id);
    addLinkTo(dto, id);

    return dto;
  }

  @ApiOperation(value = "Update")
  @PutMapping(value = "/{id}", produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.ACCEPTED)
  public CustomerDTO update(@PathVariable("id") Long id, @RequestBody CustomerDTO customer) throws Exception {
    CustomerDTO dto = customerService.update(id, customer);
    addLinkTo(dto, id);

    return dto;
  }

  @ApiOperation(value = "Delete")
  @DeleteMapping(value = "/{id}", produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
    customerService.delete(id);

    return ResponseEntity.noContent().build();
  }
  
  @ApiOperation(value = "Disable")
  @PatchMapping(value = "/{id}/disable", produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public ResponseEntity<?> disable(@PathVariable("id") Long id) throws Exception {
    customerService.disable(id);

    return ResponseEntity.noContent().build();
  }
  
  @ApiOperation(value = "Enable")
  @PatchMapping(value = "/{id}/enable", produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public ResponseEntity<?> enable(@PathVariable("id") Long id) throws Exception {
    customerService.enable(id);

    return ResponseEntity.noContent().build();
  }
}
