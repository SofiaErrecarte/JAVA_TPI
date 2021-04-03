package logic;

import data.*;
import entities.*;

import java.util.LinkedList;
import java.util.Scanner;

public class LibroController {
	
	private DataLibro dl;
	
	public LibroController() {
		dl=new DataLibro();
	}
	
	public LinkedList<Libro> getAllLibros(){
		return dl.getAll();
	}
	
	public LinkedList<LibroProv> getAllLibrosProv(){
		return dl.getLibrosPorProv();
	}
	public Libro getByIdLibro(Libro lib) {
		return dl.getById(lib);
	}
	
	public MyResult createLibro(Libro lib) {
		return dl.add(lib);
	}

	public MyResult editLibro(Libro lib) {
		return dl.editLibro(lib);
	}

	public MyResult deleteLibro(Libro lib) {
		return dl.deleteLibro(lib);
	}
	
	public LinkedList<Ejemplar> getEjByIdLibro (Libro lib) {
		return dl.getEjByIdLibro(lib);
	}
	
	
	public MyResult deleteEjemplar(Ejemplar ej) {
		return dl.deleteEjemplar(ej);		
	}
	
	public LinkedList<Ejemplar> getAllEjemplares(){
		return dl.getAllEjemplares();
	}
	
	public MyResult deleteEjemplarPorIdLibro (Ejemplar ej, Libro lib) {
		return dl.deleteEjemplar(ej);		
	}

	public Libro getByIsbnLibro(Libro lib) {
		return dl.getByISBN(lib);
	}
	
	public Ejemplar addEjemplar(Libro l) {
		return dl.addEjemplar(l);
	}
	
	public LinkedList<Libro> getByDesc(String nombuscar){
		return dl.getByDesc(nombuscar);
	}
	

	public Ejemplar getByIdEjemplar(Ejemplar ej) {
		return dl.getByIdEjemplar(ej);
	}
	
	
}
