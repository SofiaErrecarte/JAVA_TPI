package logic;

import data.*;
import entities.*;

import java.util.LinkedList;
public class LineaPrestamoController {
private DataLineaPrestamo dlp;

public LineaPrestamoController() {
	dlp = new DataLineaPrestamo();
}

public LineaPrestamo addLineaPrestamo (LineaPrestamo lp) {
	return dlp.add(lp);
	
}
public LineaPrestamo editLineaprestamo (LineaPrestamo lp) {
	return dlp.editLineaPrestamo(lp);
}
public LineaPrestamo deleteLineaPrestamo (LineaPrestamo lp) {
	return dlp.deleteLineaPrestamo(lp);
}


}
