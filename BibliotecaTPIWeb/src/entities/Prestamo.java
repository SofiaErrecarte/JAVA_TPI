package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Prestamo {
	private int idPrestamo;
	private Date fechaPrestamo;
	private String estado;
	private Date fechaADevoler;
	private Date fechaDevolucion;
	

	private LinkedList<LineaPrestamo> lineasPrestamo = new LinkedList<LineaPrestamo>();
	private int idPersona;
	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public LinkedList<LineaPrestamo> getLineasPrestamo() {
		return lineasPrestamo;
	}
	public void setLineasPrestamo(LinkedList<LineaPrestamo> lineasPrestamo) {
		this.lineasPrestamo = lineasPrestamo;
	}
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public Date getFechaADevoler() {
		return fechaADevoler;
	}
	public void setFechaADevoler(Date fechaADevoler) {
		this.fechaADevoler = fechaADevoler;
	}
	
	public void addLp(LineaPrestamo lp) {
		lineasPrestamo.add(lp);
	}
}
