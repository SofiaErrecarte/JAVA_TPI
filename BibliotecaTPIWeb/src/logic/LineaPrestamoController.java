package logic;

import data.*;
import entities.*;

import java.util.LinkedList;
public class LineaPrestamoController {
private DataLineaPrestamo dlp;

public LineaPrestamoController() {
	dlp = new DataLineaPrestamo();
}

public MyResult addLineaPrestamo (LineaPrestamo lp) {
	return dlp.add(lp);
	
}
public MyResult editLineaprestamo (LineaPrestamo lp) {
	return dlp.editLineaPrestamo(lp);
}
public LineaPrestamo deleteLineaPrestamo (LineaPrestamo lp) {
	return dlp.deleteLineaPrestamo(lp);
}

public LineaPrestamo getById (LineaPrestamo lp) {
	return dlp.getById(lp);
}

}
