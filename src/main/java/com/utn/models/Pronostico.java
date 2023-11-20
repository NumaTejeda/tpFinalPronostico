package com.utn.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Pronostico {
	private Equipo equipo;
	private ResultadoEnum resultado;
	private String nameParticipante;
	
	public Pronostico(Equipo equipo, ResultadoEnum resultado, String nameParticipante) {
		this.equipo = equipo;
		this.resultado = resultado;
		this.setNameParticipante(nameParticipante);
	}
	public Pronostico() {}
	public String toString() {
		return this.equipo +": " + this.resultado;
	}
	
	//tal vez este metodo no vaya aca
	public int puntos() {
		if(resultado == ResultadoEnum.ganador) {
			return 1;
		}
		else if(resultado == ResultadoEnum.perdedor) {
			return -1;
		}
		return 0;
	}
	
	public Equipo getEquipo() {
		return equipo;
	}
	
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public ResultadoEnum getResultado() {
		return resultado;
	}
	
	public void setResultado(ResultadoEnum resultado) {
		this.resultado = resultado;
	}
	public String getNameParticipante() {
		return nameParticipante;
	}
	public void setNameParticipante(String nameParticipante) {
		this.nameParticipante = nameParticipante;
	}
}
