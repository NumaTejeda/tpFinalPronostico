package com.utn.models;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


public class App 
{
    public static void main( String[] args ) {
    	
    	String rutaResutlados = "src\\main\\resources\\resultados.csv";
    	List<Partido> resultadosPartidos = new ArrayList<Partido>();
    	List<Partido> listaPartidos = obtenerResultados(rutaResutlados, resultadosPartidos);
    	
    	String rutaPronostico = "src\\main\\resources\\pronosticos.csv";
    	List<Pronostico> resultadosPronosticos = new ArrayList<Pronostico>();
    	List<Pronostico> pronosticos = obtenerPronosticos(rutaPronostico, resultadosPronosticos);
    	
    	List<Ronda> rondas =  crearRondas(listaPartidos);
    	obtenerPuntaje(rondas, pronosticos);
    	
    }
    
    private static List<Partido> obtenerResultados(String rutaResutlados, List<Partido> resultadosPartidos){
    	
    	try { 
    		// Creacion del objeto FileReader
    		// se le pasa path CSV como parametro 
    		FileReader resultados = new FileReader(rutaResutlados); 
    		
    		// Creacion de objeto csvReader
    		// resutlados como parametro
    		CSVReader manejador = new CSVReaderBuilder(resultados).withSkipLines(1).build();; 
    		String[] linea; 
    		
    		//Creo array de partidos 
    		
    		// Leemos el archivo linea por linea
    		while ((linea = manejador.readNext()) != null) { 
    			//Obtengo numero ronda para cada partido
    			int numeroRonda = Integer.parseInt(linea[0]);
    			//Creo los equipos por linea
    			Equipo equipo1 = new Equipo(linea[1]);
				Equipo equipo2 = new Equipo(linea[4]);
				// Agrego los goles por equipo 
				int goles1 = Integer.parseInt(linea[2]);
				int goles2 = Integer.parseInt(linea[3]);
				//Creo un partido con los resultado obtenidos
				if(goles1 > goles2) {
					Partido partido = new Partido(numeroRonda,equipo1, equipo2, goles1, goles2, ResultadoEnum.ganador);
					resultadosPartidos.add(partido);
				}else if(goles1 < goles2){
					Partido partido = new Partido(numeroRonda,equipo1, equipo2, goles1, goles2, ResultadoEnum.perdedor);
					resultadosPartidos.add(partido);
				}else {
					Partido partido = new Partido(numeroRonda,equipo1,equipo2, goles1, goles2, ResultadoEnum.empate);
					resultadosPartidos.add(partido);
				}
				
    		} 
    		manejador.close();
    	} 
    	catch (Exception e) { 
    		e.printStackTrace(); 
    	}
    	System.out.println(resultadosPartidos);
		return resultadosPartidos;
	}
    
    private static List<Ronda> crearRondas(List<Partido> partidos) {
    	
    	// Crear una lista de rondas con 2 partidos por ronda
    	List<Ronda> rondas = new ArrayList<>();
        for(int i = 0; i < partidos.size(); i += Ronda.PARTIDOS_POR_RONDA) {
        	  List<Partido> subList = new ArrayList<>(partidos.subList(i, i + Ronda.PARTIDOS_POR_RONDA));
        	  //System.out.println("Sub Lista: "+subList);
        	  Ronda ronda2 = new Ronda(subList.get(0).getNumeroRonda(), subList);
        	  //System.out.println(ronda2);
        	  rondas.add(ronda2);
        }
    	
        System.out.println(rondas);
    	return rondas;
    }
    
    private static List<Pronostico> obtenerPronosticos(String rutaPronostico, List<Pronostico> resultadosPronosticos){
    	
    	try { 
    		// Creacion del objeto FileReader
    		// se le pasa path CSV como parametro 
    		FileReader resultados = new FileReader(rutaPronostico); 
    		
    		// Creacion de objeto csvReader
    		// fileReader como parametro
    		CSVReader manejador = new CSVReaderBuilder(resultados).withSkipLines(1).build();; 
    		String[] nextRecord; 
    		//Creo lista de pronostico vacia
    		
    		
    		// Leemos el archivo linea por linea
    		while ((nextRecord = manejador.readNext()) != null) { 
    			Equipo equipo1 = new Equipo(nextRecord[1]);
    			String gana = nextRecord[2];
    			String empata = nextRecord[3];
    			String pierde = nextRecord[4];
    			Pronostico pronostico = new Pronostico();
    			pronostico.setEquipo(equipo1);
    			if(gana.equals("x")) {
    				pronostico.setResultado(ResultadoEnum.ganador);
    				resultadosPronosticos.add(pronostico);
    			}
    			else if(empata.equals("x")) {
    				pronostico.setResultado(ResultadoEnum.empate);
    				resultadosPronosticos.add(pronostico);
    			}
    			else if(pierde.equals("x")){
    				pronostico.setResultado(ResultadoEnum.perdedor);
    				resultadosPronosticos.add(pronostico);
    			}
    			
    		
    		} 
    		manejador.close();
    	} 
    	catch (Exception e) { 
    		e.printStackTrace(); 
    	}
    	return resultadosPronosticos;
    }
    
    private static void obtenerPuntaje(List<Ronda> rondas, List<Pronostico> pronosticos) {
    	ArrayList<ResultadoEnum> resultadosEquipo1Enum = new ArrayList<>();
    	ArrayList<ResultadoEnum> resultadoPronosticoEnum = new ArrayList<>();
    	
    	//Guardo los resutlados de los partidos de cada ronda en funcion del equipo1 en un Array type Enum
    	for(Ronda ronda : rondas) {
    		for(int i = 0; i < ronda.getArrayDePartidos().size(); i++) {
    			resultadosEquipo1Enum.add(ronda.getArrayDePartidos().get(i).getResutladoEquipo1());
    		}
    	}
    	//Guardo los resultados obtenidos de los pronosticos en un Array type Enum
    	for(Pronostico pronostico : pronosticos) {
    		resultadoPronosticoEnum.add(pronostico.getResultado());
    	}
    	//Comparo los resultados
    	int puntaje = 0;
    	for(int i = 0; i < resultadoPronosticoEnum.size(); i++) {
    		if(resultadoPronosticoEnum.get(i) == resultadosEquipo1Enum.get(i)) {
    			puntaje += 1;
    		}else if(resultadoPronosticoEnum.get(i) != resultadosEquipo1Enum.get(i)){
    			puntaje -= 1;
    		}else {
    			System.out.println("0");
    		}
    	}
    	System.out.println("El puntaje obtenido es: " + puntaje);

    }
}





