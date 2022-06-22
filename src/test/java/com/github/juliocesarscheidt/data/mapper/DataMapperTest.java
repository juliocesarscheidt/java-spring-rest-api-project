package com.github.juliocesarscheidt.data.mapper;

import com.github.juliocesarscheidt.data.dto.CustomerDTO;
import com.github.juliocesarscheidt.data.entity.Customer;
import com.github.juliocesarscheidt.data.mapper.mock.CustomerMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DataMapperTest {
  CustomerMock inputObject;

  @Before
  public void setUp() {
    inputObject = new CustomerMock();
  }

  @Test
  public void parseEntityToDTOTest() {
    CustomerDTO output = DataMapper.parseObject(inputObject.mockEntity(), CustomerDTO.class);
    int index = 0;

    Assert.assertEquals(Long.valueOf(0L), output.getUniqueId());

    Assert.assertEquals("address_" + index, output.getAddress());
    Assert.assertEquals("firstName_" + index, output.getFirstName());
    Assert.assertEquals("lastName_" + index, output.getLastName());
    Assert.assertEquals("email_" + index, output.getEmail());

    Assert.assertEquals("Male", output.getGender());
  }

  @Test
  public void parseDTOToEntityTest() {
    Customer output = DataMapper.parseObject(inputObject.mockDTO(), Customer.class);
    int index = 0;

    Assert.assertEquals(Long.valueOf(0L), output.getId());

    Assert.assertEquals("address_" + index, output.getAddress());
    Assert.assertEquals("firstName_" + index, output.getFirstName());
    Assert.assertEquals("lastName_" + index, output.getLastName());
    Assert.assertEquals("email_" + index, output.getEmail());

    Assert.assertEquals("Male", output.getGender());
  }
}
