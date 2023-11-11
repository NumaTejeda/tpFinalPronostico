package com.utn.pronostico;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.utn.pronostico.*;

import com.opencsv.CSVReader;



public class App 
{
    public static void main( String[] args ) {
    	
    	//Creacion de equipos 
    	Equipo Argentina = new Equipo("Argentina", "Campeon del Mundo");
    	Equipo Brasil = new Equipo("Brasil", "Jogo bonito");
    	Equipo Holanda = new Equipo("Holanda", "Naranja Mecanica");
    	Equipo Alemania = new Equipo("Alemania", "StrugenBajen");
    	
    	//Creacion de partidos
    	Partido partido_1 = new Partido(Argentina, Brasil, 4, 2); 
    	Partido partido_2 = new Partido(Holanda, Alemania, 3, 1);
    	Partido partido_3 = new Partido(Argentina, Holanda, 2, 2);
    	
    	ArrayList<Partido> partidosRonda1 = new ArrayList<>();
    	partidosRonda1.add(partido_1);
    	partidosRonda1.add(partido_2);
    	partidosRonda1.add(partido_3);
    	
    	//Crear primera ronda harcodeada
    	//Ronda ronda1 = new Ronda(1, partidosRonda1);
    	
    	//Creacion de pronostico harcodeado
    	//Pronostico pronosticoDeNuma = new Pronostico(partido_1, Argentina, ResultadoEnum.empate);
    	
    	

        try { 
      
            // Creacion del objeto FileReader
            // se le pasa path CSV como parametro 
            FileReader fileReader = new FileReader("src\\main\\java\\com\\utn\\pronostico\\datosPartidos.csv"); 
      
            // Creacion de objeto csvReader
            // fileReader como parametro
            CSVReader csvReader = new CSVReader(fileReader); 
            String[] nextRecord; 
      
            // Leemos el archivo linea por linea
            while ((nextRecord = csvReader.readNext()) != null) { 
                for (String cell : nextRecord) { 
                    System.out.print(cell + " "); 
                } 
                System.out.println(); 
            } 
            csvReader.close();
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    	
    }
}
