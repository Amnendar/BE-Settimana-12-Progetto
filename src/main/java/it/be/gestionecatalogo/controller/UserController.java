package it.be.gestionecatalogo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.gestionecatalogo.model.User;
import it.be.gestionecatalogo.service.UserService;


@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//per accedere a Swagger: http://localhost:8080/swagger-ui.html
	
	@Operation(summary="Get User by Id", description = "Permette di cercare Utenti o Admin tramite chiave primaria.")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/user/{id}")
	public ResponseEntity<User> findById(@PathVariable(required = true) Long id) {
		Optional<User> find = userService.findById(id);
		
		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
