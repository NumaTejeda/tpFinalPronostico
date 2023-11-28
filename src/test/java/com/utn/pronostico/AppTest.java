package com.utn.pronostico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.utn.models.App;
import com.utn.models.Participante;
import com.utn.models.Partido;
import com.utn.models.Pronostico;
import com.utn.models.Ronda;


class AppTest{
	
	
	String rutaResutlados = "src\\test\\resources\\resultadosTest.csv";
	List<Partido> resultadosPartidos = new ArrayList<Partido>();
	List<Partido> listaPartidos = App.obtenerResultados(rutaResutlados, resultadosPartidos);
	
	String rutaPronostico = "src\\test\\resources\\pronosticosTest.csv";
	List<Pronostico> resultadosPronosticos = new ArrayList<Pronostico>();
	List<Pronostico> pronosticos =  App.obtenerPronosticos(rutaPronostico, resultadosPronosticos);
	
	List<Ronda> rondas =  App.crearRondas(listaPartidos);
	List<Participante> participantePrueba= App.obtenerPuntaje(rondas, App.obtenerParticipante(pronosticos));
	
	@Test
    public void puntajeParticipante2RondasConsecutivas() {
    	//Putos esperados
		assertEquals(4, participantePrueba.get(0).getPuntaje());
    	    	
    }
	@Test
	public void cantidadDePartidosPorRondaEsperado() {
		//Cantidad de partidos esperados por ronda
		assertEquals(2, rondas.get(0).getArrayDePartidos().size());
	}

    
   
}
