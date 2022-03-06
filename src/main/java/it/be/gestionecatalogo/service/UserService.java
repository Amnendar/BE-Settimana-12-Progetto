package it.be.gestionecatalogo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.gestionecatalogo.model.User;
import it.be.gestionecatalogo.repository.UserRepository;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

}
