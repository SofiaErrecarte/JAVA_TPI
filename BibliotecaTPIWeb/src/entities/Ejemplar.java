package entities;

public class Ejemplar {
	private int idEjemplar;
	private int idLibro;
	private boolean disponible;  
	private String titulo;
	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public int getIdEjemplar() {
		return idEjemplar;
	}

	public void setIdEjemplar(int idEjemplar) {
		this.idEjemplar = idEjemplar;
	}
	
	
	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	
	
	
	public String toString(Ejemplar e) {
		return "Id Ejemplar=" + e.idEjemplar + ", Libro=" + e.titulo ;
	}
}