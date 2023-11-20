package com.utn.models;

import java.util.List;

public class Participante {
	private String name;
	private List<Pronostico> pronosticos;
	private int puntaje;
	private int pronosticosAcertados;
	//Constructor
	public Participante(String name, List<Pronostico> pronosticos) {
		this.name = name;
		this.pronosticos = pronosticos;
		this.getPuntaje();
	}
	//toString
	public String toString() {
		return "Participante: " + this.getName() + "\napuesta: " + this.pronosticos + "\n"
				+ "Puntaje obtenido: " + this.puntaje + "\n"+
				"Pronosticos Acertados: " + this.pronosticosAcertados+"\n";
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
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public void setPronosticosAcertados(int pronosticosAcertados) {
		this.pronosticosAcertados = pronosticosAcertados;
	}
	public int getPronosticosAcertados() {
		return pronosticosAcertados;
	}
}
