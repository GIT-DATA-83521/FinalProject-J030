package com.blog.service;

import com.blog.dto.AddressDto;
import com.blog.dto.ApiRespone;

public interface AddressService {
	
	ApiRespone assignUserAddress(Long userId, AddressDto dto);
}

