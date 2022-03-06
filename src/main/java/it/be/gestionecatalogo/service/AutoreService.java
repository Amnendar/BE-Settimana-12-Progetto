package it.be.gestionecatalogo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.gestionecatalogo.exception.AutoreException;
import it.be.gestionecatalogo.model.Autore;

import it.be.gestionecatalogo.repository.AutoreRepository;

@Service
public class AutoreService {

	@Autowired
	AutoreRepository autorerepo;
	
	
	public Optional<Autore> findAutoreById(Long id) {
			return autorerepo.findById(id);
	}
	
	public void saveAllFromSet(Set<Autore> list) {
		for (Autore autore : list) {
			autorerepo.save(autore);
		}
	}
	
	public List<Autore> getAllAutore(){
		return autorerepo.findAll();
	}
	
	public Autore getAutoreById(Long id) {
		return autorerepo.getById(id);
	}
	
	public Autore saveAutore(Autore a) {
		List<Autore> tutti = autorerepo.findAll();
		for (Autore autore : tutti) {
			if(autore.getId().equals(a.getId())) {
				throw new AutoreException("ERRORE! Autore con questo id gia presente!");
			}
		}
		return autorerepo.save(a);
	}
	
	public void deleteAutoreById(Long id) {
		autorerepo.deleteById(id);
	}
	
	public Autore updateAutore(Long id,Autore a) {
		Optional<Autore> trovato = autorerepo.findById(id);
		if(trovato.isPresent()) {
			Autore update = trovato.get();
			update.setNome(a.getNome());
			update.setCognome(a.getCognome());
			autorerepo.save(update);
			return update;
		}
		else {
			throw new AutoreException("ERRORE! Autore non trovato!");
		}
		
		
	}
	
	
	
	
	
}
