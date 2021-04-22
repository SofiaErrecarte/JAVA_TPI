package logic;

import java.util.LinkedList;

import data.DataPersona;
import entities.Libro;
import entities.MyResult;
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
	
	public LinkedList<Persona> getDisponibles(){
		return dp.getDisponibles();
	} 
	public Persona getByDNI ( Persona p) {
		return dp.getByDNI(p);
	}
	public MyResult createPersona(Persona p) {
		return dp.add(p);
	}
	
	public MyResult editPersona(Persona p) {
		return dp.editPersona(p);
	}
	
	public void setEstado(Persona p, String estado) {
		dp.setEstado(p, estado);
	}
	public LinkedList<Persona> getByApellidoA() {
		return dp.getByApellidoA();
	}
	public LinkedList<Persona> getByApellidoZ() {
		return dp.getByApellidoZ();
	}
	public LinkedList<Persona> getByNombreA() {
		return dp.getByNombreA();
	}
	public LinkedList<Persona> getByNombreZ(){
		return dp.getByNombreZ();
	}
}
