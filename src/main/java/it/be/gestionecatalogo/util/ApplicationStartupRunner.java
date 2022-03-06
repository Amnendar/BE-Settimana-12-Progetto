package it.be.gestionecatalogo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.be.gestionecatalogo.model.Autore;
import it.be.gestionecatalogo.model.Categoria;
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.repository.AutoreRepository;
import it.be.gestionecatalogo.repository.CategoriaRepository;
import it.be.gestionecatalogo.repository.LibroRepository;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

	@Autowired
	private LibroRepository librorepo;
	
	@Autowired
	private AutoreRepository autorerepo;
	
	@Autowired
	private CategoriaRepository categoriarepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		//popoliamo il db

		//creiamo le categorie
		
		Categoria distopico = new Categoria();
		distopico.setNome("Distopico");
		categoriarepo.save(distopico);
		
		Categoria biografia = new Categoria();
		biografia.setNome("Biografia");
		categoriarepo.save(biografia);
		
		Categoria romanzo = new Categoria();
		romanzo.setNome("Romanzo");
		categoriarepo.save(romanzo);
		
		Categoria fantasy = new Categoria();
		fantasy.setNome("Fantasy");
		categoriarepo.save(fantasy);
		
		Categoria horror = new Categoria();
		horror.setNome("Horror");
		categoriarepo.save(horror);
		
		Categoria storico = new Categoria();
		storico.setNome("Storico");
		categoriarepo.save(storico);
		
		Categoria fumetto = new Categoria();
		fumetto.setNome("Fumetto");
		categoriarepo.save(fumetto);
		
		
		//creiamo degli autori
		
		Autore autore1 = new Autore();
		autore1.setNome("Suzanne");
		autore1.setCognome("Collins");
		autorerepo.save(autore1);
		
		Autore autore2 = new Autore();
		autore2.setNome("Christopher");
		autore2.setCognome("Paolini");
		autorerepo.save(autore2);
		
		Autore autore3 = new Autore();
		autore3.setNome("Frank");
		autore3.setCognome("Miller");
		autorerepo.save(autore3);
		
		Autore autore4 = new Autore();
		autore4.setNome("Sun");
		autore4.setCognome("Tzu");
		autorerepo.save(autore4);
		
		Autore autore5 = new Autore();
		autore5.setNome("H.P.");
		autore5.setCognome("Lovecraft");
		autorerepo.save(autore5);
		
		Autore autore6 = new Autore();
		autore6.setNome("Lynn");
		autore6.setCognome("Varley");
		autorerepo.save(autore6);
		
		//creiamo i libri
		
		Libro libro1 = new Libro();
		libro1.setTitolo("Eragon");
		libro1.setAnnoPubblicazione(2004);
		libro1.setPrezzo(9.0);
		libro1.addAutore(autore2);
		libro1.addCategoria(fantasy);
		librorepo.save(libro1);
		
		Libro libro2 = new Libro();
		libro2.setTitolo("Eldest");
		libro2.setAnnoPubblicazione(2006);
		libro2.setPrezzo(10.0);
		libro2.addAutore(autore2);
		libro2.addCategoria(fantasy);
		librorepo.save(libro2);
		
		Libro libro3 = new Libro();
		libro3.setTitolo("Brisingr");
		libro3.setAnnoPubblicazione(2010);
		libro3.setPrezzo(14.0);
		libro3.addAutore(autore2);
		libro3.addCategoria(fantasy);
		librorepo.save(libro3);
		
		Libro libro4 = new Libro();
		libro4.setTitolo("Hunger Games");
		libro4.setAnnoPubblicazione(2010);
		libro4.setPrezzo(11.0);
		libro4.addAutore(autore1);
		libro4.addCategoria(distopico);
		librorepo.save(libro4);
		
		Libro libro5 = new Libro();
		libro5.setTitolo("Hunger Games: La Ragazza di Fuoco");
		libro5.setAnnoPubblicazione(2013);
		libro5.setPrezzo(14.0);
		libro5.addAutore(autore1);
		libro5.addCategoria(distopico);
		librorepo.save(libro5);
		
		Libro libro6 = new Libro();
		libro6.setTitolo("Hunger Games: Il Canto della Rivolta");
		libro6.setAnnoPubblicazione(2016);
		libro6.setPrezzo(15.0);
		libro6.addAutore(autore1);
		libro6.addCategoria(distopico);
		librorepo.save(libro6);

		Libro libro7 = new Libro();
		libro7.setTitolo("300");
		libro7.setAnnoPubblicazione(2015);
		libro7.setPrezzo(15.0);
		libro7.addAutore(autore3);
		libro7.addAutore(autore6);
		libro7.addCategoria(fumetto);
		libro7.addCategoria(storico);
		librorepo.save(libro7);
		
		Libro libro8 = new Libro();
		libro8.setTitolo("Watchmen");
		libro8.setAnnoPubblicazione(2001);
		libro8.setPrezzo(13.0);
		libro8.addAutore(autore3);
		libro8.addAutore(autore6);
		libro8.addCategoria(fumetto);
		libro8.addCategoria(distopico);
		librorepo.save(libro8);
		
		Libro libro9 = new Libro();
		libro9.setTitolo("L'Arte della Guerra");
		libro9.setAnnoPubblicazione(1450);
		libro9.setPrezzo(10.0);
		libro9.addAutore(autore4);
		libro9.addCategoria(storico);
		librorepo.save(libro9);
		
		Libro libro10 = new Libro();
		libro10.setTitolo("Cthlulhu: I Racconti del Mito");
		libro10.setAnnoPubblicazione(1975);
		libro10.setPrezzo(20.0);
		libro10.addAutore(autore5);
		libro10.addCategoria(horror);
		librorepo.save(libro10);
	
	}

}
