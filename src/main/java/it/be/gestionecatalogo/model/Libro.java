package it.be.gestionecatalogo.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titolo;
	@Column(nullable = false)
	private Integer annoPubblicazione;
	@Column(nullable = false)
	private Double prezzo;
	
	@ManyToMany
	@JoinTable(name = "libri_autori",
    joinColumns = @JoinColumn(name = "libri_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "autore_id", referencedColumnName = "id"))
	private Set<Autore> autori = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "libri_categorie",
    joinColumns = @JoinColumn(name = "libri_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id"))
	private Set<Categoria> categorie = new HashSet<>();
	
	
	
	
	public void addAutore(Autore a) {
		this.autori.add(a);
	}
	
	public void addCategoria(Categoria c) {
		this.categorie.add(c);
	}
	
	
	//rimuove un autore dal libro(se presente)
	public void deleteAutoreFromSet(Autore a) {
		if(this.autori.contains(a))
		this.autori.remove(a);
	}
	
	//rimuove una categoria dal libro(se presente)
	public void deleteCategoriaFromSet(Categoria c) {
		if(this.categorie.contains(c)) {
			this.categorie.remove(c);
		}
	}
	

}
