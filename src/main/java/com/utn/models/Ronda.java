package com.utn.models;

import java.util.List;

public class Ronda {
	
	private int numero;
	private List<Partido> arrayDePartidos;
	public static final int PARTIDOS_POR_RONDA = 2;
	
	//Constructor
	public Ronda(int numero, List<Partido> arrayDePartidos) {
		this.numero = numero;
		this.arrayDePartidos = arrayDePartidos;
	}
	//Constructo default
	public Ronda() {}
	//toString
	public String toString() {
		return " Ronda numero: " +" ("+this.numero +") "+ this.arrayDePartidos + "\n";
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public List<Partido> getArrayDePartidos() {
		return arrayDePartidos;
	}
	public void setArrayDePartidos(List<Partido> arrayDePartidos) {
		this.arrayDePartidos = arrayDePartidos;
	}
	
}
