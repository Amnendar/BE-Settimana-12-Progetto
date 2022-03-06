package it.be.gestionecatalogo.model;

import lombok.Data;

@Data
public class LoginRequest {
	
	private String userName;
	private String password;

}
