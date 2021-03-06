package entities;

import java.sql.Date;

public class LineaPrestamo {
	private int idLineaPrestamo;
	private int idEjemplar;
	private boolean devuelto; //se calcularķa
	private int idPrestamo;
	private Date fechaDevolucion;
	private String tituloEjemplar;
	public String getTituloEjemplar() {
		return tituloEjemplar;
	}
	public void setTituloEjemplar(String tituloEjemplar) {
		this.tituloEjemplar = tituloEjemplar;
	}
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public int getIdLineaPrestamo() {
		return idLineaPrestamo;
	}
	public void setIdLineaPrestamo(int idLineaPrestamo) {
		this.idLineaPrestamo = idLineaPrestamo;
	}
	public int getIdEjemplar() {
		return idEjemplar;
	}
	public void setIdEjemplar(int idEjemplar) {
		this.idEjemplar = idEjemplar;
	}
	public boolean isDevuelto() {
		return devuelto;
	}
	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	
	
	
}
