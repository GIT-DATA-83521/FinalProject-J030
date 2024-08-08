package com.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

	@Column(name = "adr_line1",length = 50)
	private String adrLine1;
	
	@Column(name = "adr_line2",length = 50)
	private String adrLine2;
	
	@Column(name = "city",length = 50)
	private String city;
	
	@Column(name = "state",length = 50)
	private String state;
	
	@Column(name = "pin_code",length = 6)
	private String pinCode;
	
	@Column(name = "country",length = 50)
	private String country;
	
	@Column(name = "phone_no",length = 20)
	private String phoneNo;
}
