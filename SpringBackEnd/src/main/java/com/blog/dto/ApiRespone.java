package com.blog.dto;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO : Data Transfer Object (exchange data between RESt Client and REST Server)

@Getter
@Setter
@NoArgsConstructor
public class ApiRespone {

	private String message;
	
	private LocalDateTime timeStamp;
	
	public ApiRespone(String message) {
		super();
		this.message= message;
		this.timeStamp=timeStamp;
	}
}
