package entities;

public class Ejemplar {
	private int idEjemplar;
	private int idLibro;
	private boolean disponible;  
	
	

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

	
	
	
	@Override
	public String toString() {
		return "Ejemplar [Id Ejemplar=" + idEjemplar + ", Id Libro=" + idLibro  + ", estado="+ disponible +"]";
	}
}