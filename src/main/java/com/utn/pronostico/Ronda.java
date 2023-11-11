package com.utn.pronostico;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
	
	private int numero;
	private List<Partido> arrayDePartidos;
	
	
	public Ronda(int numero, ArrayList<Partido> arrayDePartidos) {
		this.numero = numero;
		this.arrayDePartidos = new ArrayList<Partido>();
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
