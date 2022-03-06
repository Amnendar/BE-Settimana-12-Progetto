package it.be.gestionecatalogo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.gestionecatalogo.model.Autore;
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.service.AutoreService;
import it.be.gestionecatalogo.service.LibroService;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class AutoreController {

	@Autowired
	private LibroService libroservice;
	
	@Autowired
	private AutoreService autoservice;
	
	
	//per accedere a Swagger: http://localhost:8080/swagger-ui.html
	
	@Operation(summary="Get All Autori", description = "Restituisce una lista di tutti gli autori presenti in catalogo")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/autori")
	public ResponseEntity<List<Autore>> findAllAutori(){
		List<Autore> find = autoservice.getAllAutore();
		if(find.isEmpty()) {
			return new ResponseEntity<>(find, HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(find, HttpStatus.ACCEPTED);
			
		}
	}
	
	
	@Operation(summary="Get Autore By Primary Key", description = "Restituisce un Autore cercandolo per chiave primaria")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')") 
	@GetMapping("/getautore/{id}")
	public ResponseEntity<Autore> getAutoreById(@PathVariable(required=true)Long id){
		Optional<Autore> find = autoservice.findAutoreById(id);
		if(find.isEmpty()) {
			return new ResponseEntity<>(find.get(), HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(find.get(), HttpStatus.ACCEPTED);
		}
	}
	
	
	@Operation(summary="Aggiungi Autore", description="Permette di aggiungere un autore al database.")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/aggiungiautore")
	public ResponseEntity<Autore> save(@RequestBody Autore autore){
	
		Autore salvato = autoservice.saveAutore(autore);
		return new ResponseEntity<>(salvato, HttpStatus.ACCEPTED);
	}
	
	
	@Operation(summary="Cancella Autore", description = "Permette di cancellare un autore dal database")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/cancellaautore/{id}")
	public ResponseEntity<String> delete(@PathVariable(required=true) Long id){
		Optional<Autore> trovato = autoservice.findAutoreById(id);
		if (trovato.isEmpty()) {
			return new ResponseEntity<>("ERRORE! Nessun autore con questo ID", HttpStatus.NOT_FOUND);
		}
		else {
			Autore cancellato = trovato.get();
			List<Libro> tuttiLibri = libroservice.findAllLibri();
			for (Libro libro : tuttiLibri) {
				libro.deleteAutoreFromSet(cancellato);
			}
			libroservice.deleteLibroSenzaAutori();
			autoservice.deleteAutoreById(id);
			return new ResponseEntity<>("Autore cancellato correttamente!", HttpStatus.ACCEPTED);
		}
	}
	
	
	@Operation(summary="Aggiorna Autore", description = "Permette di aggiornare i dati di un autore")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/aggiornaautore/{id}")
	public ResponseEntity<Autore> update(@PathVariable(required=true) Long id,@RequestBody Autore autore){
		autoservice.updateAutore(id, autore);
		Optional<Autore> aggiornato = autoservice.findAutoreById(id);
		if(aggiornato.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(autore, HttpStatus.ACCEPTED);
		}
	}
	
	
	
	
	
}
