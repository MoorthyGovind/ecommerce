package com.hcl.ecommerce.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	User user = new User();

	@Before
	public void init() {
		user.setId(1L);
		user.setUserId("moorthy127@gmail.com");
		user.setPassword("start@123");
	}

	@Test
	public void testLogin() {
		UserDto userDto = new UserDto();
		userDto.setUserId("moorthy127@gmail.com");
		userDto.setPassword("start@123");

		when(userRepository.findByUserIdAndPassword("moorthy127@gmail.com", "start@123")).thenReturn(user);
		User userDetail = userServiceImpl.login(userDto);
		assertEquals(userDetail.getUserId(), user.getUserId());
		assertEquals(userDetail.getPassword(), user.getPassword());
	}
}
