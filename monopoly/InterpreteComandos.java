package monopoly;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class InterpreteComandos {


    private Jugador jugador;
    private ArrayList<Avatar> avatares;
    
    public InterpreteComandos(ArrayList<Avatar> avatares){

        this.avatares= avatares;
    }
    
    public String input(){
        
        Scanner reader=new Scanner(System.in);  // Reading from System.in
        
           System.out.println("\n->");
           String n = reader.nextLine(); // Scans the next token of the input as an int.
           reader.close();
           
        return n;
    }
    
    public void eleccion(){
        
        String eleccion=input();
        String[] aux;
        
        if(eleccion.contains("crear jugador")){
            aux=eleccion.split("\\s+");
            darAltaJugador(aux[2],aux[3]);
            System.out.println( this.avatares.get( this.avatares.size() -1 ) );
        }
        
        if(eleccion=="jugador"){
            //jugadorTurno();
        }
    }
    
    public void darAltaJugador(String nombre,String tipo){

        this.avatares.add(new Avatar(tipo,this.avatares.size() + 1,nombre));
    }
    
    /*public void jugadorTurno(){
        System.out.println("Nombre: "+jugador.getNombreJugador());
        //System.out.println("Avatar "+avatar.getSimbolo());
    }*/




}
