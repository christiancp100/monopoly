package monopoly;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class InterpreteComandos {


    private ArrayList<Avatar> avatares;
    private Tablero tablero;
    public InterpreteComandos(ArrayList<Avatar> avatares, Tablero tablero){

        this.avatares= avatares;
        this.tablero = tablero;
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
            System.out.println("Nombre: " + this.avatares.get(this.avatares.size()-1).getJugador().getNombreJugador());
            System.out.println("Avatar: " + this.avatares.get(this.avatares.size()-1).getSimbolo() + "\n");
        }
        
        if(eleccion=="jugador"){
            //jugadorTurno();
        }
    }
    
    public void darAltaJugador(String nombre,String tipo){

        Avatar avatarCreado = new Avatar(tipo, this.avatares.size(), nombre);
        avatarCreado.getJugador().setCasillaActual(this.tablero.getCasilla(0,0));
        this.avatares.add(avatarCreado);
    }
    
    /*public void jugadorTurno(){
        System.out.println("Nombre: "+jugador.getNombreJugador());
        //System.out.println("Avatar "+avatar.getSimbolo());
    }*/




}
