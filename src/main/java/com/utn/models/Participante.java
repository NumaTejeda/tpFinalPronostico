package com.utn.models;

import java.util.List;

public class Participante {
	private String name;
	private List<Pronostico> pronosticos;
	//Constructor
	public Participante(String name, List<Pronostico> pronosticos) {
		this.name = name;
		this.pronosticos = pronosticos;
	}
	//toString
	public String toString() {
		return "El participante: " + this.getName() + " apuesta que: " + this.pronosticos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Pronostico> getPronostico() {
		return pronosticos;
	}
	public void setPronostico(List<Pronostico> pronosticos) {
		this.pronosticos = pronosticos;
	}
}
