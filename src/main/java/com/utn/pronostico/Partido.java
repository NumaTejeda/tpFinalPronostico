package com.utn.pronostico;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Partido {
	private Equipo equipo1;
	private Equipo equipo2;
	private int golesEquipo1;
	private int golesEquipo2;
	private ResultadoEnum resultadoEquipo1;
	
	public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2, ResultadoEnum resultadoEquipo1) {
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.golesEquipo1 = golesEquipo1;
		this.golesEquipo2 = golesEquipo2;
		this.resultadoEquipo1 = resultadoEquipo1;
	}
	public Partido() {};
	public String toString() {
		return this.equipo1.getNombre() + ": "+ this.getGolesEquipo1()
					+ " " +this.equipo2.getNombre() + ": " + this.getGolesEquipo2() + " \n"
					+ "Resutlado: " + this.equipo1 + " " + this.resultadoEquipo1;
	}
	public ResultadoEnum setResultado(Partido partido) {
		if(partido.getGolesEquipo1() > partido.getGolesEquipo2()) {
			return ResultadoEnum.ganador;
		}
		if(partido.getGolesEquipo1() < partido.getGolesEquipo2()) {
			return ResultadoEnum.perdedor;
		}
		return ResultadoEnum.empate;
	}
	
	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	public int getGolesEquipo2() {
		return golesEquipo2;
	}
	public ResultadoEnum getResutladoEquipo1(){
		return resultadoEquipo1;
	}
}
