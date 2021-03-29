package logic;

import java.util.LinkedList;

import data.DataPoliticaPrestamo;
import entities.MyResult;
import entities.PoliticaPrestamo;

public class PoliticaPrestamoController {
	private DataPoliticaPrestamo dpp;
	public PoliticaPrestamoController() {
		dpp=new DataPoliticaPrestamo();
	}
	public LinkedList<PoliticaPrestamo> ppGetAll(){
		return dpp.getAll();
	}
	public MyResult newPolitica(PoliticaPrestamo pp) {
		return dpp.add(pp);
	}
	
	public PoliticaPrestamo getByIdPolitica (PoliticaPrestamo pp) {
		return dpp.getById(pp);
	}
	public PoliticaPrestamo editPolitica (PoliticaPrestamo pp) {
		return dpp.editPolitica(pp);
	}
	public MyResult deletePoliticaPrestamo (PoliticaPrestamo pp) {
		return dpp.deletePolitica(pp);
	}
	
	public LinkedList<PoliticaPrestamo> ppGetByBusqueda(int nombuscar){
		return dpp.getbybusqueda(nombuscar);
	}
}
