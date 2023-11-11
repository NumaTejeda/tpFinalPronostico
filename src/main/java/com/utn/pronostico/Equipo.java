package com.utn.pronostico;

public class Equipo {
	private String nombre;
	private String descripcion;
	
	//constructor
	public Equipo(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	//constructo por defecto
	public Equipo() {};
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
