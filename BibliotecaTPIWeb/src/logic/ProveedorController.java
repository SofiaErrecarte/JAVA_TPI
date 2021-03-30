package logic;

import data.*;
import entities.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ProveedorController {
	
	private DataProveedor dpr;
	
	public ProveedorController() {
		dpr=new DataProveedor();
	}
	
	public LinkedList<Proveedor> getAllProveedores(){
		return dpr.getAll();
	}
	
	public Proveedor getByCUIT(Proveedor prov) {
		return dpr.getByCUIT(prov);
	}
	
	public MyResult createProveedor(Proveedor prov) {
		return dpr.add(prov);
	}

	public MyResult editProveedor(Proveedor prov) {
		return dpr.editProveedor(prov);
	}

	public MyResult deleteProveedor(Proveedor prov) {
		return dpr.deleteProveedor(prov);
	}
	
	public Proveedor getById(Proveedor prov) {
		return dpr.getById(prov);
	}
	
	
}
