package com.utn.models;

public class Equipo {
	private String nombre;
	
	//constructor
	public Equipo(String nombre) {
		this.nombre = nombre;
	}
	//constructo por defecto
	public Equipo() {};
	
	public String toString() {
		return this.nombre;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
