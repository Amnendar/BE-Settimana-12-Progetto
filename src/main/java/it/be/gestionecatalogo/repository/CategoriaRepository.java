package it.be.gestionecatalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.be.gestionecatalogo.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	
	
}
