package logic;

import java.util.LinkedList;

import data.DataPersona;
import entities.Persona;
import entities.Proveedor;

public class PersonaController {
	private DataPersona dp;
	public PersonaController() {
		dp=new DataPersona();
	}
	
	public Persona getByIdPersona (Persona p) {
		return dp.getById(p);
	}
		
	public Persona getByEmail ( Persona p) {
		return dp.getByEmail(p);
	}
	
	public LinkedList<Persona> getAllPersonas(){
		return dp.getAll();
	}
}
