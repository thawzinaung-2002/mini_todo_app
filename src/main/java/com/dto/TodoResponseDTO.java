package com.dto;

import lombok.Getter;
import lombok.Setter;

public class TodoResponseDTO {
	@Getter @Setter private String title;
	@Getter @Setter private String target;
	@Getter @Setter private String status;
	@Getter @Setter private int id;

}
