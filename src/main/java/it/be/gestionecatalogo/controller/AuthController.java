package it.be.gestionecatalogo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import it.be.gestionecatalogo.exception.RuoloException;
import it.be.gestionecatalogo.model.LoginRequest;
import it.be.gestionecatalogo.model.LoginResponse;
import it.be.gestionecatalogo.model.RequestRegisterUser;
import it.be.gestionecatalogo.model.Role;
import it.be.gestionecatalogo.model.Roles;
import it.be.gestionecatalogo.model.User;
import it.be.gestionecatalogo.repository.RoleRepository;
import it.be.gestionecatalogo.repository.UserRepository;
import it.be.gestionecatalogo.service.UserDetailsImpl;
import it.be.gestionecatalogo.util.JwtUtils;



@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtUtils jwtUtils;
	
	//per accedere a Swagger: http://localhost:8080/swagger-ui.html
	
	@Operation(summary = "Login", description = "Pagina di Login")
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		LoginResponse loginResponse = new LoginResponse();
		
		loginResponse.setToken(token);
		loginResponse.setRoles(roles);
		
		return ResponseEntity.ok(loginResponse);
	}
	
	@Operation(summary = "Registration", description = "Pagina di Registrazione di un nuovo User")
	@PostMapping("/signup")
    public ResponseEntity<?> registraUser(@RequestBody RequestRegisterUser registraUser) {

        if (userRepository.existsByEmail(registraUser.getMail())) {
            return new ResponseEntity<String>("email già in uso!", HttpStatus.BAD_REQUEST);
        } else if (userRepository.existsByUserName(registraUser.getUserName())) {
            return new ResponseEntity<String>("username già in uso!", HttpStatus.BAD_REQUEST);
        }

        User userRegistrato = new User();
        userRegistrato.setUserName(registraUser.getUserName());
        userRegistrato.setEmail(registraUser.getMail());
        userRegistrato.setPassword(encoder.encode(registraUser.getPassword()));
        if (registraUser.getRoles().isEmpty()) {
            Optional<Role> ruolo = roleRepo.findByRoleName(Roles.ROLE_USER);
            Set<Role> ruoli = new HashSet<>();
            ruoli.add(ruolo.get());
            userRegistrato.setRoles(ruoli);
        } else {
            Set<Role> ruoli = new HashSet<>();
            for (String set : registraUser.getRoles()) {
                switch (set.toUpperCase()) {
                case "ADMIN":
                    Optional<Role> ruolo1 = roleRepo.findByRoleName(Roles.ROLE_ADMIN);
                    ruoli.add(ruolo1.get());
                    break;
                case "USER":
                    Optional<Role> ruolo2 = roleRepo.findByRoleName(Roles.ROLE_USER);
                    ruoli.add(ruolo2.get());
                    break;
                default:
                    throw new RuoloException("Ruolo non trovato!");

                }

            }
            userRegistrato.setRoles(ruoli);
            userRegistrato.setActive(true);

        }
        userRepository.save(userRegistrato);
        return new ResponseEntity<>("Utente inserito con successo: " + userRegistrato.toString(), HttpStatus.CREATED);

    }
	
	

}
