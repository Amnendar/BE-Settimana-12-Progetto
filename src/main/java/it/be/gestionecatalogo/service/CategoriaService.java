package it.be.gestionecatalogo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.gestionecatalogo.exception.CategoriaException;
import it.be.gestionecatalogo.model.Categoria;
import it.be.gestionecatalogo.repository.CategoriaRepository;
import it.be.gestionecatalogo.repository.LibroRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriarepo;
	
	@Autowired
	LibroRepository librorepo;
	
	//salva tutte le categoria presenti in un set
	public void saveAllFromSet(Set<Categoria> list) {
		for (Categoria categoria : list) {
			categoriarepo.save(categoria);
		}
	}
	//get di tutte le categoria
	public List<Categoria> getAllCategorie() {
		return categoriarepo.findAll();
	}
	
	//get di una categoria tramite id
	public Optional<Categoria> getCategoriaById(Long id) {
		return categoriarepo.findById(id);
	}
	
	//save di una categoria
	public Categoria saveCategoria(Categoria c) {
		List<Categoria> all = categoriarepo.findAll();
		for (Categoria categoria : all) {
			if(categoria.getId().equals(c.getId())) {
				throw new CategoriaException("ERRORE! ID gia presente!");
			}
		}
		
		return categoriarepo.save(c);
	}
	
	//update di una categoria
	public Categoria updateCategoria(Long id,Categoria c) {
		Optional<Categoria> trovata = categoriarepo.findById(id);
		if(trovata.isPresent()) {
			Categoria update = trovata.get();
			update.setNome(c.getNome());
			categoriarepo.save(update);
			return update;
		}
		else {
			throw new CategoriaException("ERRORE! Categoria non trovato!");
		}
	}
	
	//delete di una categoria
	public void deleteCategoriaById(Long id) {
		categoriarepo.deleteById(id);
	}
	
	
	
	
}
	
	
	
