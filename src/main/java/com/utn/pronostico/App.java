package com.utn.pronostico;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class App 
{
    public static void main( String[] args ) {
    	
    	
    	String rutaResutlados = "src\\main\\java\\com\\utn\\pronostico\\resultados.csv";
    	List<Partido> resultadosPartidos = new ArrayList<Partido>();
    	List<Partido> listaPartidos = obtenerResultados(rutaResutlados, resultadosPartidos);
    	
    	String rutaPronostico = "src\\main\\java\\com\\utn\\pronostico\\pronosticos.csv";
    	List<Pronostico> resultadosPronosticos = new ArrayList<Pronostico>();
    	List<Pronostico> pronosticos = leerArchivoPronostico(rutaPronostico, resultadosPronosticos);
    	
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
    			//Creo los equipos por linea
    			Equipo equipo1 = new Equipo(linea[0]);
				Equipo equipo2 = new Equipo(linea[3]);
				// Agrego los goles por equipo 
				int goles1 = Integer.parseInt(linea[1]);
				int goles2 = Integer.parseInt(linea[2]);
				//Creo un partido con los resultado obtenidos
				if(goles1 > goles2) {
					Partido partido = new Partido(equipo1, equipo2, goles1, goles2, ResultadoEnum.ganador);
					resultadosPartidos.add(partido);
				}else if(goles1 < goles2){
					Partido partido = new Partido(equipo1, equipo2, goles1, goles2, ResultadoEnum.perdedor);
					resultadosPartidos.add(partido);
				}else {
					Partido partido = new Partido(equipo1, equipo2, goles1, goles2, ResultadoEnum.empate);
					resultadosPartidos.add(partido);
				}
				
				/*if(partidos.size() <= 2) {
					Ronda agregarRonda = new Ronda();
					agregarRonda.setArrayDePartidos(partidos);
					agregarRonda.setNumero(numeroRonda);
					listaDeRondas.add(agregarRonda);
					//System.out.println(agregarRonda);
					numeroRonda++;
					//partidos.clear();
				}*/
				//System.out.println(listaDeRondas);
    		} 
    		manejador.close();
    	} 
    	catch (Exception e) { 
    		e.printStackTrace(); 
    	}
    
		return resultadosPartidos;
	}
    
    private static List<Ronda> crearRondas(List<Partido> partidos) {
    	
    	// Crear una lista de rondas con 2 partidos por ronda
        //Ronda ronda = new Ronda();
    	List<Ronda> rondas = new ArrayList<>();
        int numeroDeRonda = 0;
        for(int i = 0; i < partidos.size(); i +=2) {
        	  List<Partido> subList = new ArrayList<>(partidos.subList(i, i + 2));
        	  //System.out.println("Sub Lista: "+subList);
        	  Ronda ronda2 = new Ronda(numeroDeRonda, subList);
        	  //System.out.println(ronda2);
        	  rondas.add(ronda2);
        	  
        	  numeroDeRonda++;
        }
        System.out.println("Rondas obtenidas de List<Partido>: \n \n"+ rondas);
        
		/*if(partidos.size() <= 2) {
			Ronda agregarRonda = new Ronda();
			agregarRonda.setArrayDePartidos(partidos);
			agregarRonda.setNumero(numeroRonda);
			listaDeRondas.add(agregarRonda);
			//System.out.println(agregarRonda);
			numeroRonda++;
			//partidos.clear();
		}*/

    	return rondas;
    }
    
    private static List<Pronostico> leerArchivoPronostico(String rutaPronostico, List<Pronostico> resultadosPronosticos){
    	
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
    			Equipo equipo1 = new Equipo(nextRecord[0]);
    			String gana = nextRecord[1];
    			String empata = nextRecord[2];
    			String pierde = nextRecord[3];
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
    	System.out.println("Pronosticos: " + resultadosPronosticos); 
    	return resultadosPronosticos;
    }
    
    private static void obtenerPuntaje(List<Ronda> rondas, List<Pronostico> pronosticos) {
    	
    	for(int i = 0; i < rondas.size(); i++) {
    		System.out.println(rondas.size());
    		for(int j = 0; j < rondas.get(i).getArrayDePartidos().size(); j++ ) {
    			System.out.println(rondas.get(i).getArrayDePartidos().get(j).getResutladoEquipo1() + "\n ---");
    			System.out.println(pronosticos.get(j).getResultado() + "\n --");
    		}
    	}
    	
    	
    	
    	/*for(int i = 0; i < rondas.size(); i++) {
    		int count = 0;
    		for(int j = 0; j < rondas.get(i).getArrayDePartidos().size(); j++) {
    			
    			if(rondas.get(i).getArrayDePartidos().get(j).getResutladoEquipo1() == pronosticos.get(count).getResultado()) {
    				//System.out.println("De este array "+rondas.get(i).getArrayDePartidos()+" con este: " + pronosticos.get(i));
    				System.out.println("Se compara: "+rondas.get(i).getArrayDePartidos().get(count).getResutladoEquipo1()
    						+ " con: " + pronosticos.get(j).getResultado());
    				System.out.println("+1");
    				count++;
    			}
    			else{
    				//System.out.println("De este array "+rondas.get(i).getArrayDePartidos()+" con este: " + pronosticos.get(i));
    				System.out.println("Se compara: "+rondas.get(i).getArrayDePartidos().get(j).getResutladoEquipo1()
    						+ " con: " + pronosticos.get(count).getResultado());
    				System.out.println("-1");
    				count++;
    			}
    		}
    	}*/
    }
}






