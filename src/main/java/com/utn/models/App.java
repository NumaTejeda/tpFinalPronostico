package com.utn.models;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    	List<Participante> participantes =  obtenerPuntaje(rondas, obtenerParticipante(pronosticos));
    	
    	for(Participante participante : participantes) {
    		System.out.println(participante);
    	}
    	
    }
    
    public static List<Partido> obtenerResultados(String rutaResutlados, List<Partido> resultadosPartidos){
    	
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
    			
    			if(linea.length == 5) {
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
				
    		} 
    		manejador.close();
    	} 
    	catch (NumberFormatException e) {
			e.printStackTrace();
		}
    	catch (Exception e) { 
    		e.printStackTrace(); 
    	}
    	System.out.println(resultadosPartidos);
		return resultadosPartidos;
	}
    
    public static List<Ronda> crearRondas(List<Partido> partidos) {
    	
    	// Crear una lista de rondas con 2 partidos por ronda
    	List<Ronda> rondas = new ArrayList<>();
    	//Creo una SUBLISTA de List<Partido> pasada por parametro cada 2 partidos y creo una RONDA y agrego a RONDAS.
        for(int i = 0; i < partidos.size(); i += Ronda.PARTIDOS_POR_RONDA) {
        	  List<Partido> subList = new ArrayList<>(partidos.subList(i, i + Ronda.PARTIDOS_POR_RONDA));
        	  Ronda ronda2 = new Ronda(subList.get(0).getNumeroRonda(), subList);
        	  rondas.add(ronda2);
        }
    	/*for(Partido partido : partidos) {
    		partido.ge
    	}*/
    	
    	/* pronosticosPorParticipante
                    .computeIfAbsent(nombreParticipante, k -> new ArrayList<>())
                    .add(pronostico);*/
    	
        System.out.println(rondas);
    	return rondas;
    }
    
    public static List<Pronostico> obtenerPronosticos(String rutaPronostico, List<Pronostico> resultadosPronosticos){
    	
    	try { 
    		// Creacion del objeto FileReader
    		// se le pasa path CSV como parametro 
    		FileReader resultados = new FileReader(rutaPronostico); 
    		
    		// Creacion de objeto csvReader
    		// fileReader como parametro
    		CSVReader manejador = new CSVReaderBuilder(resultados).withSkipLines(1).build();; 
    		String[] nextRecord;     		

    		// Leo el archivo linea por linea
    		while ((nextRecord = manejador.readNext()) != null) { 
    			//Guardo cada valor separado por comas
    			String nameParticipante = nextRecord[0];
    			Equipo equipo1 = new Equipo(nextRecord[1]);
    			String gana = nextRecord[2];
    			String empata = nextRecord[3];
    			String pierde = nextRecord[4];
    			//Creo un pronostico 
    			Pronostico pronostico = new Pronostico();
    			//Agrego equipo y nombre de participante
    			pronostico.setEquipo(equipo1);
    			pronostico.setNameParticipante(nameParticipante);
    			//Mapeo las x y guardo el valor segun corresponda
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
        
    public static Map<String, List<Pronostico>> obtenerParticipante(List<Pronostico> resultadoPronosticos){
    	//Creo un HashMap String <----(clave) y List<Pronostico> <---(valor) 
    	Map<String, List<Pronostico>> pronosticosPorParticipante = new HashMap<>();

        // Proceso los resultadoPronosticos y organizarlos por participante
    	//Utilizo el metodo computeIfAbsent que toma el nombre como clave o key, y agrega los pronosticos
    	//Si key (k) cambia, la funcion que se pasa como segundo parametro crea un nuevo arraylist para
    	// la nueva key (o sea nuevo participante) y crea un array y agrega los pronosticos de ese participante
        for (Pronostico pronostico : resultadoPronosticos) {
            String nombreParticipante = pronostico.getNameParticipante(); // tomo el nombre de participante
            pronosticosPorParticipante
                    .computeIfAbsent(nombreParticipante, k -> new ArrayList<>())
                    .add(pronostico);
        }
    	System.out.println(pronosticosPorParticipante + "Aqui");
    	return pronosticosPorParticipante;
    }
    
    public static List<Participante> obtenerPuntaje(List<Ronda> rondas, Map<String, List<Pronostico>> pronosticosPorParticipante) {

    	ArrayList<ResultadoEnum> resultados = new ArrayList<>();
    	List<Participante> participantes = new ArrayList<>(); 
    	
    	//Guardo los resutlados de los partidos de cada ronda en funcion del equipo1 en un Array type Enum
    	for(Ronda ronda : rondas) {
    		for(int i = 0; i < ronda.getArrayDePartidos().size(); i++) {
    			resultados.add(ronda.getArrayDePartidos().get(i).getResutladoEquipo1());
    		}
    	}
    	// itero por las key creando participantes con sus respectivos pronosticos
    	for (String participante : pronosticosPorParticipante.keySet()) {
            
            int puntajeObtenido = 0;
            // Obtener la lista de pronósticos para el participante actual
            List<Pronostico> pronosticos = pronosticosPorParticipante.get(participante);
            Participante nuevoParticipante = new Participante(participante, pronosticos);
            
            // Realizar acciones con la lista de pronósticos si es necesario
            for (int i = 0; i < pronosticos.size(); i++) {
            	if(pronosticos.get(i).getResultado() == resultados.get(i)) {
            	   puntajeObtenido++;
            	}
            }
            //Por el momento la cantidad de pronosticos es igual a la cantidad de puntaje obtenido
            nuevoParticipante.setPronosticosAcertados(puntajeObtenido);
            nuevoParticipante.setPuntaje(puntajeObtenido);
            participantes.add(nuevoParticipante);
        }
    	/*for(Participante participante : participantes) {
    		System.out.println(participante);
    	}*/
    	return participantes;
    }
}






