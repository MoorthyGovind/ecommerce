package com.hcl.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.UserRepository;
import com.hcl.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * login check with userId and password
	 */
	@Override
	public User login(UserDto userDto) {
		return userRepository.findByUserIdAndPassword(userDto.getUserId(), userDto.getPassword());
	}
}
