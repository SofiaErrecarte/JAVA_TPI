package logic;
import data.*;
import entities.*;

import java.util.LinkedList;
public class PrestamoController {
private DataPrestamo dp;

public PrestamoController() {
	dp=new DataPrestamo();
}

public LinkedList<Prestamo> getAllPrestamos(){
	return dp.getAll();
}
public MyResult addPrestamo(Prestamo p ) {
	return dp.add(p);
	
}
public MyResult editPrestamo(Prestamo p ) {
	return dp.editPrestamo(p);
}
public Prestamo deletePrestamo(Prestamo p ) {
	return dp.deletePrestamo(p);
}

public LinkedList<LineaPrestamo> getLPByPrestamo(Prestamo p ){
	return dp.getLPByPrestamo(p);
			}

public Prestamo getByIdPrestamo(Prestamo p) {
	return dp.getById(p);
}

public void setEstado(Prestamo p, String e) {
	 dp.setEstado(p, e);
	}

public LinkedList<Prestamo> getByIDMinimo() {
	return dp.getByIDMinimo();
}
public LinkedList<Prestamo> getByIDMayor() {
	return dp.getByIDMayor();
}
public LinkedList<Prestamo> getByFechaMinimo() {
	return dp.getByFechaMinimo();
}
public LinkedList<Prestamo> getByFechaMayor() {
	return dp.getByFechaMayor();
}
public void cerrarPrestamo(Prestamo p) {
	dp.cerrarPrestamo(p);
}

public void actualizarLP (Prestamo p) {
	dp.actualizarLP(p);
}
	/*
	 * public int getCantLP(Prestamo p) { return dp.getCantLP(p); }
	 */


}
