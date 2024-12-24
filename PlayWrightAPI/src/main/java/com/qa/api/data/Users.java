package com.qa.api.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Users {
	
	private String id;
	private String name;
	private String email;
	private String status;
	private String gender;

}
