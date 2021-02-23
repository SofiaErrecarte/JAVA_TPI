package logic;

import data.DataPersona;
import entities.Persona;

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
}
