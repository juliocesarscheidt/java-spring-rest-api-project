package com.github.juliocesarscheidt.data.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.juliocesarscheidt.data.dto.CustomerDTO;
import com.github.juliocesarscheidt.data.entity.Customer;

public class DataMapper {
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <S, D> D parseObject(S origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	public static <S, D> List<D> parseObjects(List<S> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		for (Object o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		
		return destinationObjects;
	}
	
	public static CustomerDTO customerToDTO(Customer customer) {
		CustomerDTO dto = new CustomerDTO();
		
		dto.setUniqueId(customer.getId());
		dto.setFirstName(customer.getFirstName());
		dto.setLastName(customer.getLastName());
		dto.setEmail(customer.getEmail());
		dto.setAddress(customer.getAddress());
		dto.setGender(customer.getGender());
		
		return dto;
	}
	
	public static Customer DTOToCustomer(CustomerDTO dto) {
		Customer customer = new Customer();
		
		customer.setId(dto.getUniqueId());
		customer.setFirstName(dto.getFirstName());
		customer.setLastName(dto.getLastName());
		customer.setEmail(dto.getEmail());
		customer.setAddress(dto.getAddress());
		customer.setGender(dto.getGender());
		
		return customer;
	}
}
