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
import it.be.gestionecatalogo.model.Categoria;
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.service.CategoriaService;
import it.be.gestionecatalogo.service.LibroService;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class CategoriaController {
	
	@Autowired
	private LibroService libroservice;
	
	@Autowired
	private CategoriaService categoriaservice;
	
	//per accedere a Swagger: http://localhost:8080/swagger-ui.html
	
	@Operation(summary="Get All Categorie", description="Restituisce tutte le categorie")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/categorie")
	public ResponseEntity<List<Categoria>> findAllAutori(){
		List<Categoria> find = categoriaservice.getAllCategorie();
		if(find.isEmpty()) {
			return new ResponseEntity<>(find, HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(find, HttpStatus.ACCEPTED);
			
		}
	}
	
	
	@Operation(summary="Get Categoria By Id", description="Permette di cercare una categoria tramite chiave primaria")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')") 
	@GetMapping("/getcategoria/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable(required=true)Long id){
		Optional<Categoria> find = categoriaservice.getCategoriaById(id);
		if(find.isEmpty()) {
			return new ResponseEntity<>(find.get(), HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(find.get(), HttpStatus.ACCEPTED);
		}
	}
	
	
	@Operation(summary="Aggiungi Categoria", description="Permette di aggiungere una categoria al sistema.")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/aggiungicategoria")
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria){
	
		Categoria salvato = categoriaservice.saveCategoria(categoria);
		return new ResponseEntity<>(salvato, HttpStatus.ACCEPTED);
	}
	
	
	
	@Operation(summary="Cancella Categoria", description="Permette di cancellare una categoria tramite id")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/cancellacategoria/{id}")
	public ResponseEntity<String> delete(@PathVariable(required=true) Long id){
		Optional<Categoria> trovato = categoriaservice.getCategoriaById(id);
		if (trovato.isEmpty()) {
			return new ResponseEntity<>("ERRORE! Nessuna categoria con questo ID", HttpStatus.NOT_FOUND);
		}
		else {
			Categoria cancellata = trovato.get();
			List<Libro> tuttiLibri = libroservice.findAllLibri();
			for (Libro libro : tuttiLibri) {
				libro.deleteCategoriaFromSet(cancellata);
			}
			categoriaservice.deleteCategoriaById(id);
			return new ResponseEntity<>("Categoria cancellata correttamente!", HttpStatus.ACCEPTED);
		
		}
	
	}
	
	
	@Operation(summary="Aggiorna Categoria", description="Permette di aggiornare i dati di una categoria")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/aggiornacategoria/{id}")
	public ResponseEntity<Categoria> update(@PathVariable(required=true) Long id,@RequestBody Categoria categoria){
		categoriaservice.updateCategoria(id, categoria);
		Optional<Categoria> aggiornata = categoriaservice.getCategoriaById(id);
		if(aggiornata.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(categoria, HttpStatus.ACCEPTED);
		}
	}
	
	
}
