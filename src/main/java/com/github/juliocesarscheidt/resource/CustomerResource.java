package com.github.juliocesarscheidt.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.github.juliocesarscheidt.data.dto.CustomerDTO;
import com.github.juliocesarscheidt.exception.ServerErrorException;
import com.github.juliocesarscheidt.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
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

@Api(
    value = "Customer Endpoint",
    tags = {"Customer"})
@RestController
@RequestMapping("/v1/customer")
public class CustomerResource extends BaseResource {

  @Autowired private CustomerService customerService;

  protected Logger logger = LoggerFactory.getLogger(CustomerResource.class);

  protected void addLinkToDto(RepresentationModel<?> dto, Long id) {
    try {
      dto.add(linkTo(methodOn(BookResource.class).findOne(id)).withSelfRel());
    } catch (Exception e) {
      logger.error("Error caught " + e.getMessage());
      throw new ServerErrorException("Internal Server Error");
    }
  }

  protected Consumer<CustomerDTO> addLinkConsumer =
      entity -> addLinkToDto(entity, entity.getUniqueId());

  @ApiOperation(value = "Find One")
  @GetMapping(
      value = "/{id}",
      produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.OK)
  public CustomerDTO findOne(@PathVariable("id") Long id) throws Exception {
    CustomerDTO dto = customerService.findOne(id);
    addLinkToDto(dto, id);

    return dto;
  }

  @ApiOperation(value = "Find All")
  @GetMapping(produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<Page<CustomerDTO>> find(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "50") Integer size)
      throws Exception {
    Pageable pageable = PageRequest.of(page, size);
    Page<CustomerDTO> customers = customerService.find(pageable);
    customers.stream().forEach(this.addLinkConsumer);

    return ResponseEntity.ok().body(customers);
  }

  @ApiOperation(value = "Find by Name")
  @GetMapping(
      value = "/findByName/{firstName}",
      produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<Page<CustomerDTO>> findByName(
      @PathVariable("firstName") String firstName,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "50") Integer size)
      throws Exception {
    Pageable pageable = PageRequest.of(page, size);
    Page<CustomerDTO> customers = customerService.findByName(firstName, pageable);
    customers.stream().forEach(this.addLinkConsumer);

    return ResponseEntity.ok().body(customers);
  }

  @ApiOperation(value = "Create")
  @PostMapping(produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.CREATED)
  public CustomerDTO create(@RequestBody CustomerDTO customer) throws Exception {
    CustomerDTO dto = customerService.create(customer);
    addLinkToDto(dto, dto.getUniqueId());

    return dto;
  }

  @ApiOperation(value = "Update")
  @PutMapping(
      value = "/{id}",
      produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.ACCEPTED)
  public CustomerDTO update(@PathVariable("id") Long id, @RequestBody CustomerDTO customer)
      throws Exception {
    CustomerDTO dto = customerService.update(id, customer);
    addLinkToDto(dto, id);

    return dto;
  }

  @ApiOperation(value = "Delete")
  @DeleteMapping(
      value = "/{id}",
      produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
    customerService.delete(id);

    return ResponseEntity.noContent().build();
  }

  @ApiOperation(value = "Disable")
  @PatchMapping(
      value = "/{id}/disable",
      produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public ResponseEntity<?> disable(@PathVariable("id") Long id) throws Exception {
    customerService.disable(id);

    return ResponseEntity.noContent().build();
  }

  @ApiOperation(value = "Enable")
  @PatchMapping(
      value = "/{id}/enable",
      produces = {"application/json"})
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public ResponseEntity<?> enable(@PathVariable("id") Long id) throws Exception {
    customerService.enable(id);

    return ResponseEntity.noContent().build();
  }
}
