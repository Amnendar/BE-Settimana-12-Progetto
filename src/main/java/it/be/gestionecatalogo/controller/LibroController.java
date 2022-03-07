package it.be.gestionecatalogo.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.service.AutoreService;
import it.be.gestionecatalogo.service.CategoriaService;
import it.be.gestionecatalogo.service.LibroService;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class LibroController {

	@Autowired
	private LibroService libroservice;
	
	@Autowired
	private AutoreService autoservice;
	
	@Autowired
	private CategoriaService categoriaservice;

	//per accedere a Swagger: http://localhost:8080/swagger-ui.html
	
	//metodo che restituisce tutti i libri
	
	@Operation(summary="Get All Libri", description = "Mostra tutti i libri presenti al momento nel catalogo")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/libri")
	public ResponseEntity<List<Libro>> findAllLibri(){
		List<Libro> find = libroservice.findAllLibri();
		if(find.isEmpty()) {
			return new ResponseEntity<>(find, HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(find, HttpStatus.ACCEPTED);
			
		}
	}
	
	//restituisce libro tramite chiave primaria
	
	@Operation(summary="Get Libro By Primary Key",description = "Restituisce il libro corrispondente all'id passato in input")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')") 
	@GetMapping("/getlibro/{id}")
	public ResponseEntity<Libro> getLibroById(@PathVariable(required=true)Long id){
		Optional<Libro> find = libroservice.findLibroById(id);
		if(find.isEmpty()) {
			return new ResponseEntity<>(find.get(), HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(find.get(), HttpStatus.ACCEPTED);
		}
	}
	
	
	@Operation(summary="Aggiungi Libro", description = "Permette di aggiungere un nuovo Libro al catalogo. NOTA: SE IL LIBRO HA AUTORI O CATEGORIE GIA PRESENTI NEL DATABASE, SCRIVERE GLI ID CORRISPONDENTI CON EVENTUALI NOMI IN QUANTO VERRANNO SOVRASCRITTI. SE NUOVI, CANCELLARE LA RIGA ID CHE VERRANNO SALVATI AUTOMATICAMENTE. SE SI SCRIVONO CATEGORIE O AUTORI GIA PRESENTI SENZA PASSARE ID VERRANNO NUOVAMENTE AGGIUNTI AL DB")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/aggiungilibro")
	public ResponseEntity<Libro> save(@RequestBody Libro libro){
		categoriaservice.saveAllFromSet(libro.getCategorie());
		autoservice.saveAllFromSet(libro.getAutori());
		
		Libro salvato = libroservice.addLibro(libro);
		return new ResponseEntity<>(salvato, HttpStatus.ACCEPTED);
	}
	
	
	@Operation(summary="Cancella Libro", description = "Permette di cancellare un libro tramite la sua chiave primaria")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/cancellalibro/{id}")
	public ResponseEntity<String> delete(@PathVariable(required=true) Long id){
		Optional<Libro> trovato = libroservice.findLibroById(id);
		if (trovato.isEmpty()) {
			return new ResponseEntity<>("ERRORE! Nessun libro con questo ID", HttpStatus.NOT_FOUND);
		}
		else {
			libroservice.deleteLibroById(id);
			return new ResponseEntity<>("Libro cancellato correttamente!", HttpStatus.ACCEPTED);
		}
	}
	
	
	@Operation(summary="Aggiorna Libro", description = "Permette di aggiornare un libro tramite la sua chiave primaria.NOTA: SE IL LIBRO HA AUTORI O CATEGORIE GIA PRESENTI NEL DATABASE, SCRIVERE GLI ID CORRISPONDENTI CON EVENTUALI NOMI IN QUANTO VERRANNO SOVRASCRITTI. SE NUOVI, SE NUOVI, CANCELLARE LA RIGA ID CHE VERRANNO SALVATI AUTOMATICAMENTE. SE SI SCRIVONO CATEGORIE O AUTORI GIA PRESENTI SENZA PASSARE ID VERRANNO NUOVAMENTE AGGIUNTI AL DB")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/aggiornalibro/{id}")
	public ResponseEntity<Libro> update(@PathVariable(required=true) Long id,@RequestBody Libro libro){
		categoriaservice.saveAllFromSet(libro.getCategorie());
		autoservice.saveAllFromSet(libro.getAutori());
		libroservice.updateLibro(id, libro);
		Optional<Libro> aggiornato = libroservice.findLibroById(id);
		if(aggiornato.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(libro, HttpStatus.ACCEPTED);
		}
	}
	
	
	@Operation(summary="Get Libri singolo Autore", description = "Restituisce tutti i libri scritti dall'autore con l'id passato in input. NOTA: VISTO IL CAMBIAMENTO DI UNA RIGA SU APPLICATION PROPERTIES, SENZA LA QUALE IL METODO NON FUNZIONA, I RISULTATI AVRANNO UNA RIGA: \"hibernateLazyInitializer\": {}" + "IN PIU" )
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/libripersingoloautore/{id}")
	public ResponseEntity<List<Libro>> findAllLibriByIdAutore(@PathVariable(required=true)Long id){
		Long idpassato = id;
		List<Libro> trovati = libroservice.getLibroByIdAutore(idpassato);
		if(trovati.isEmpty()) {
			return new ResponseEntity<>(trovati, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(trovati, HttpStatus.ACCEPTED);
	}
	
	@Operation(summary="Get Libri di piu Autori", description ="Restituisce tutti i libri scritti dagli autori corrispondenti alle id passate in input. NOTA: VISTO IL CAMBIAMENTO DI UNA RIGA SU APPLICATION PROPERTIES, SENZA LA QUALE IL METODO NON FUNZIONA, I RISULTATI AVRANNO UNA RIGA: \"hibernateLazyInitializer\": {}\" + \"IN PIU\"" )
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/libriperautore/{id}")
	public ResponseEntity<List<Libro>> findAllLibriByIdAutori(@PathVariable Set<Long> id){
		
		List<Libro> trovati = libroservice.getLibriByAutori(id);
		return new ResponseEntity<>(trovati, HttpStatus.ACCEPTED);
	}
	
	
	@Operation(summary="Get Libri singola Categoria", description = "Restituisce tutti i libri della categoria con id passata in input" )
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/libripersingolacategoria/{id}")
	public ResponseEntity<List<Libro>> findAllLibriByIdCategoria(@PathVariable(required=true)Long id){
		Long idpassato = id;
		List<Libro> trovati = libroservice.getLibriPerCategoria(idpassato);
		if(trovati.isEmpty()) {
			return new ResponseEntity<>(trovati, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(trovati, HttpStatus.ACCEPTED);
	}
	
	
	@Operation(summary="Get Libri di piu categorie", description ="Restituisce tutti i libri appartenenti a piu categorie NOTA: VISTO IL CAMBIAMENTO DI UNA RIGA SU APPLICATION PROPERTIES, SENZA LA QUALE IL METODO NON FUNZIONA, I RISULTATI AVRANNO UNA RIGA: \\\"hibernateLazyInitializer\\\": {}\" + \"IN PIU\"" )
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/libripercategorie/{id}")
	public ResponseEntity<List<Libro>> findAllLibriByIdCategorie(@PathVariable Set<Long> id){
		
		List<Libro> trovati = libroservice.getLibriByCategorie(id);
		return new ResponseEntity<>(trovati, HttpStatus.ACCEPTED);
	}
	
	
}
