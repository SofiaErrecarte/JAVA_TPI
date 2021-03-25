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
public Prestamo addPrestamo(Prestamo p ) {
	return dp.add(p);
	
}
public Prestamo editPrestamo(Prestamo p ) {
	return dp.editPrestamo(p);
}
public Prestamo deletePrestamo(Prestamo p ) {
	return dp.deletePrestamo(p);
}

public LinkedList<LineaPrestamo> getLPByPrestamo(Prestamo p ){
	return dp.getLPByPrestamo(p);
			}


}
