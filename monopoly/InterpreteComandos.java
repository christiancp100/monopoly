package monopoly;

import java.util.ArrayList;
import java.util.Scanner;

public class InterpreteComandos {

    private Tablero tablero;
    private Jugador jugador;
    private ArrayList<Avatar> avatares;
    
    public InterpreteComandos(Tablero tablero){
        this.tablero=tablero;
        this.avatares=tablero.getAvatares();
    }
    
    public String input(){
        
        Scanner reader=new Scanner(System.in);  // Reading from System.in
        
           System.out.println("\n->");
           String n = reader.next(); // Scans the next token of the input as an int.
           reader.close();
           
        return n;
    }
    
    public void eleccion(){
        
        String eleccion=input();
        String[] aux;
        
        if(eleccion.contains("crear jugador")){
            aux=eleccion.split("\\s+");
            darAltaJugador(aux[2],aux[3]);
        }
        
        if(eleccion=="jugador"){
            jugadorTurno();
        }
    }
    
    public void darAltaJugador(String nombre,String tipo){
        
        jugador.setNombreJugador(nombre);
        avatares.add(new Avatar(tipo,avatares.size(),nombre));
        
        System.out.println(avatares.get(avatares.size()-1));
    }
    
    public void jugadorTurno(){ 
        System.out.println("Nombre: "+jugador.getNombreJugador());
        //System.out.println("Avatar "+avatar.getSimbolo());
    }

}
