package com.blog.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.custom_exception.ResourceNotFoundException;
import com.blog.dao.UserDao;
import com.blog.dto.AddressDto;
import com.blog.dto.ApiRespone;
import com.blog.entity.Address;
import com.blog.entity.User;

public class AddressServiceImpl implements AddressService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ApiRespone assignUserAddress(Long userId, AddressDto dto) {
		User user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid user id !!!!"));
		// user : PERSISTENT
		// 2. map dto -> adr entity
		Address addressEntity = modelMapper.map(dto, Address.class);
		// 3. establish uni dir asso User 1--->1 Address
		user.setUserAddress(addressEntity);// modifying state persistent entity : user
		// no need of explicitly saving the address : thanks to cascadetType.ALL
		return new ApiRespone("Assigned user address....");
	}

}
