package com.hcl.ecommerce.service;

import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.entity.User;

@FunctionalInterface
public interface UserService {

	User login(UserDto userDto);
}
