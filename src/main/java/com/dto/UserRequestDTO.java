package com.dto;

import lombok.Getter;
import lombok.Setter;

public class UserRequestDTO {
	
	@Getter @Setter private String name;
	@Getter @Setter private String email;
	@Getter @Setter private String password;

}
