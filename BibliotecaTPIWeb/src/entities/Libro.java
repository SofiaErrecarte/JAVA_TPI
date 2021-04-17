
package entities;

import java.io.InputStream;
//import java.sql.Blob; esta por la imagen 
import java.util.*;

public class Libro {
private String titulo;
private  int idLibro ;
private int isbn;
private Date fechaEdicion; 
private int nroEdicion;
private int cantDiasMaxPrestamo;
private String genero;
private int idProveedor;
private ArrayList<Ejemplar> ejemplaresL;
private String razonSocialProv;
private String CUIT;
private byte[] imagen;
private InputStream imagen_carga;
private String autor; 

public String getCUIT() {
	return CUIT;
}
public void setCUIT(String cUIT) {
	CUIT = cUIT;
}
public String getRazonSocialProv() {
	return razonSocialProv;
}
public void setRazonSocialProv(String razonSocialProv) {
	this.razonSocialProv = razonSocialProv;
}
public ArrayList<Ejemplar> getEjemplaresL() {
	return ejemplaresL;
}
public void setEjemplaresL(ArrayList<Ejemplar> ejemplaresL) {
	this.ejemplaresL = ejemplaresL;
}
public int getIdProveedor() {
	return idProveedor;
}
public void setIdProveedor(int idProveedor) {
	this.idProveedor = idProveedor;
}

//private Blog imagen; ver esto
private ArrayList<Ejemplar> ejLib;
public ArrayList<Ejemplar> getEjLib() {
	return ejLib;
}
public void setEjLib(ArrayList<Ejemplar> ejLib) {
	this.ejLib = ejLib;
}

public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public int getIdLibro() {
	return idLibro;
}
public void setIdLibro(int idLibro) {
	this.idLibro = idLibro;
}
public int getIsbn() {
	return isbn;
}
public void setIsbn(int isbn) {
	this.isbn = isbn;
}
public Date getFechaEdicion() {
	return fechaEdicion;
}
public void setFechaEdicion(Date date) {
	this.fechaEdicion = date;
}
public int getNroEdicion() {
	return nroEdicion;
}
public void setNroEdicion(int nroEdicion) {
	this.nroEdicion = nroEdicion;
}
public int getCantDiasMaxPrestamo() {
	return cantDiasMaxPrestamo;
}
public void setCantDiasMaxPrestamo(int cantDiasMaxPrestamo) {
	this.cantDiasMaxPrestamo = cantDiasMaxPrestamo;
}
public String getGenero() {
	return genero;
}
public void setGenero(String genero) {
	this.genero = genero;
}

@Override
public String toString() {
	return "Libro [Id Libro=" + idLibro + ", Isbn=" + isbn + ", Título=" + titulo + ", Fecha Edición=" + fechaEdicion + ", N° Edición" + nroEdicion
			+ ", Cant. de días máx. de préstamo=" + cantDiasMaxPrestamo+
			", Género=" + genero+"]";
}
public byte[] getImagen() {
	return imagen;
}

public InputStream getImagen_carga() {
	return imagen_carga;
}
public void setImagen(byte[] imagen) {
	this.imagen = imagen;
}
public String getAutor() {
	return autor;}

public void setAutor(String autor) {
	this.autor = autor;
}
public void setImagen_carga(InputStream imagen_carga) {
	this.imagen_carga = imagen_carga;
}
}
