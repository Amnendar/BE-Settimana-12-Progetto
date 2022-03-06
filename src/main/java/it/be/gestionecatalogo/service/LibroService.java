package it.be.gestionecatalogo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.gestionecatalogo.exception.AutoreException;
import it.be.gestionecatalogo.exception.CategoriaException;
import it.be.gestionecatalogo.exception.LibroException;
import it.be.gestionecatalogo.model.Autore;
import it.be.gestionecatalogo.model.Categoria;
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.repository.AutoreRepository;
import it.be.gestionecatalogo.repository.CategoriaRepository;
import it.be.gestionecatalogo.repository.LibroRepository;

@Service
public class LibroService {

	@Autowired
	LibroRepository librorepo;

	@Autowired
	CategoriaRepository categoriarepo;

	@Autowired
	AutoreRepository autorepo;

	@Autowired
	AutoreService autoservice;

	// get di tutti i libri tramite determinato id

	// get di tutti i libri
	public List<Libro> findAllLibri() {
		return librorepo.findAll();
	}

	// get di un singolo libro
	public Optional<Libro> findLibroById(Long id) {
		return librorepo.findById(id);
	}

	// save di un libro
	public Libro addLibro(Libro libro) {
		// bisogna fare controllo delle id di autori e categorie (bisogna passare le id
		// gia esistenti dal database)
		// prendi autori con find by id e li settiamo
//		Set<Autore> autori = new HashSet<>();
//		for (Autore autore : libro.getAutori()) {
//			Autore temp = autorepo.findById(autore.getId()).get();
//			autori.add(temp);
//		}
//		libro.setAutori(autori);
		List<Libro> tutti = librorepo.findAll();
		for (Libro libro2 : tutti) {
			if(libro2.getId().equals(libro.getId())) {
				throw new LibroException("ERRORE! Libro con questo id gia esistente!");
			}
		}
		return librorepo.save(libro);
	}

	// update di un libro
	public Libro updateLibro(Long id, Libro libro) {
		Optional<Libro> trovato = librorepo.findById(id);
		if (trovato.isPresent()) {
			Libro update = trovato.get();
			update.setTitolo(libro.getTitolo());
			update.setPrezzo(libro.getPrezzo());
			update.setAutori(libro.getAutori());
			update.setAnnoPubblicazione(libro.getAnnoPubblicazione());
			update.setCategorie(libro.getCategorie());
			librorepo.save(update);
			return update;
		} else {
			throw new LibroException("ERRORE! Libro non trovato!");
		}

	}

	// delete di un libro
	public void deleteLibroById(Long id) {
		librorepo.deleteById(id);
	}

	// delete di tutti i libri senza almeno un autore
	public void deleteLibroSenzaAutori() {
		List<Libro> tutti = librorepo.findAll();
		List<Libro> librinoautore = new ArrayList<>();
		for (Libro libro : tutti) {
			if (libro.getAutori().isEmpty()) {
				librinoautore.add(libro);
			}

		}
		librorepo.deleteAll(librinoautore);

	}

	// get di libri scritti da piu autori data una lista dei loro id
	public List<Libro> getLibriByAutori(Set<Long> valori) {

		List<Autore> autori = new ArrayList<>();
		List<Libro> trovati = new ArrayList<>(); // lista di appoggio
		List<Libro> tutti = librorepo.findAll();// lista di tutti i libri

		for (Long idautore : valori) {
			autori.add(autoservice.getAutoreById(idautore)); // per ogni id autore, inserisci l autore corrispondente
																// nella lista autori
		}
		// for esterno cicli gli autori
		// for interno cicli i libri
		// c-e un if che controlla se quel libro e stato scritto da quell autore
		for (Autore autore : autori) {
			for (Libro libro : tutti) {
				if (libro.getAutori().contains(autore)) {
					trovati.add(libro);
				}
			}
		}
		return trovati;

	}

	// get dei libri scritti da un singolo autore dato un id
	public List<Libro> getLibroByIdAutore(Long id) {
		List<Libro> tutti = librorepo.findAll();
		Autore cercare = autorepo.getById(id);
		if (cercare == null) {
			throw new AutoreException("ERRORE! Nessun autore corrispondente a questo id!");
		}
		List<Libro> libriTrovati = new ArrayList<>();
		for (Libro libro : tutti) {
			for (Autore autore : libro.getAutori()) {
				if (autore.equals(cercare)) {
					libriTrovati.add(libro);
				}
			}
		}
		return libriTrovati;
	}

	// get dei libri per una singola categoria
	public List<Libro> getLibriPerCategoria(Long id) {

		List<Libro> tutti = librorepo.findAll();
		Optional<Categoria> cercare = categoriarepo.findById(id);
		if (cercare.isEmpty()) {
			throw new CategoriaException("ERRORE! Categoria non presente!");
		}
		Categoria trovata = cercare.get();
		List<Libro> trovati = new ArrayList<>();
		for (Libro libro : tutti) {
			for (Categoria categoria : libro.getCategorie()) {
				if (categoria.equals(trovata)) {
					trovati.add(libro);
				}
			}
		}
		return trovati;

	}
	
	
	// get di libri per piu categorie
		public List<Libro> getLibriByCategorie(Set<Long> valori) {

			List<Categoria> categorie = new ArrayList<>();
			List<Libro> trovati = new ArrayList<>(); // lista di appoggio
			List<Libro> tutti = librorepo.findAll();// lista di tutti i libri

			for (Long idcategoria : valori) {
				categorie.add(categoriarepo.getById(idcategoria)); 													
			}
			for (Categoria categoria : categorie) {
				for (Libro libro : tutti) {
					if (libro.getCategorie().contains(categoria)) {
						trovati.add(libro);
					}
				}
			}
			return trovati;

		}
	

}
