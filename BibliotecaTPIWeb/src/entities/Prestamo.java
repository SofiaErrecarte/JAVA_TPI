package entities;

import java.util.ArrayList;
import java.util.Date;

public class Prestamo {
	private int idPrestamo;
	private Date fechaPrestamo;
	private int diasPrestamo;
	private Date fechaADevoler;
	private ArrayList<LineaPrestamo> lineasPrestamo;
	private int idPersona;
	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public ArrayList<LineaPrestamo> getLineasPrestamo() {
		return lineasPrestamo;
	}
	public void setLineasPrestamo(ArrayList<LineaPrestamo> lineasPrestamo) {
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
	public int getDiasPrestamo() {
		return diasPrestamo;
	}
	public void setDiasPrestamo(int diasPrestamo) {
		this.diasPrestamo = diasPrestamo;
	}
	public Date getFechaADevoler() {
		return fechaADevoler;
	}
	public void setFechaADevoler(Date fechaADevoler) {
		this.fechaADevoler = fechaADevoler;
	}

}
