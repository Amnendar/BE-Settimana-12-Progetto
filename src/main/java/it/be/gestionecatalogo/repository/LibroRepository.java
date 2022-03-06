package it.be.gestionecatalogo.repository;




import org.springframework.data.jpa.repository.JpaRepository;


import it.be.gestionecatalogo.model.Libro;


public interface LibroRepository extends JpaRepository<Libro, Long> {

		
	
}
