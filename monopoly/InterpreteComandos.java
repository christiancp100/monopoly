package monopoly;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class InterpreteComandos {


    private ArrayList<Avatar> avatares;
    private Tablero tablero;
    private int k;//aumenta cada vez que creamos un jugador, para saber que turno le corresponde
    
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
            int numJugadores=0;
            if(numJugadores(numJugadores)<6){
                darAltaJugador(aux[2],aux[3]);
                System.out.println("Nombre: " + this.avatares.get(this.avatares.size()-1).getJugador().getNombreJugador());
                System.out.println("Avatar: " + this.avatares.get(this.avatares.size()-1).getSimbolo() + "\n");
            }
            
        }
        
        if(eleccion=="jugador"){
                       
            //jugadorTurno(metodo de lanzarDados, indicar quien tiene el turno actualmente)
            System.out.println("Nombre: ");
            System.out.println("Avatar: ");
        }
        
        if(eleccion=="listar jugadores"){
            for(int i=0;i<this.avatares.size();i++){
                System.out.println("{");
                System.out.println(this.avatares.get(i).getJugador());
                System.out.println("}");
            }
        }
        
        if(eleccion=="listar avatares"){
            for(int i=0;i<this.avatares.size();i++){
                System.out.println("{");
                System.out.println(this.avatares.get(i).imprimirDatos());
                System.out.println("}");
            }
        }
        
        if(eleccion=="lanzar dados"){
            //lanzarDados
        }
        
        if(eleccion=="acabar turno"){
            //turnoActual
        }
        
        if(eleccion=="salir carcel"){
            for(int i=0;i<this.avatares.size();i++){
                if(this.avatares.get(i).getJugador().getCasillaActual().equals("Carcel")){
                    
                    if(this.avatares.get(i).getJugador().getFortuna()<Valores.PAGOSALIRCARCEL){
                        System.out.println("El jugador no tiene suficiente dinero para salir de la cárcel. Pierde el turno.");
                    }
                    else{
                        this.avatares.get(i).getJugador().setFortuna((float) Valores.PAGOSALIRCARCEL,-1);//le quitamos al jugador el dinero para salir de la carcel
                        System.out.print(this.avatares.get(i).getJugador().getNombreJugador());
                        System.out.println("paga "+Valores.PAGOSALIRCARCEL+" y sale de la cárcel. Puede lanzar los dados");
                        //lanzarDados();
                    }
                }
            }
        }
        
        if(eleccion=="describir"){
            aux=eleccion.split("\\s+");
            Casilla casilla=new Casilla();
            casilla.setNombre(aux[1]);
            
            if(casilla.getTipo()=="Solar"){
                System.out.println(casilla);
            }
            
            if(casilla.getTipo()=="Parking"){
                System.out.println("Bote: " +casilla.getBote());
                System.out.println(casilla.getJugadoresCasilla());
            }
            
            if(casilla.getTipo()=="Carcel"){
                System.out.println("Precio salida: "+Valores.PAGOSALIRCARCEL);
                System.out.println(casilla.getJugadoresCasilla());
            }

        }
        
        if(eleccion.contains("describir jugador")){
            
            aux=eleccion.split("\\s+");
            //buscamos al usuario e imprimimos sus datos
            for(int i=0;i<this.avatares.size();i++){
                if(this.avatares.get(i).getJugador().getNombreJugador().equals(aux[2])){
                    System.out.println(this.avatares.get(i).getJugador());
                }
            }
        }
        
        if(eleccion.contains("describir avatar")){
            
            aux=eleccion.split("\\s+");
            /*char av=aux.charAt(2);
            //buscamos al usuario e imprimimos sus datos
            for(int i=0;i<this.avatares.size();i++){
                if(this.avatares.get(i).getSimbolo()==aux[2]){
                    System.out.println(this.avatares.get(i).imprimirDatos());
                }
            }*/
        }
        
        if(eleccion=="comprar"){
        }
        
        if(eleccion=="listar enventa"){
            this.tablero.imprimirCasillasDisponibles();
        }
        
        if(eleccion=="ver tablero"){
            System.out.println(tablero);
        }
    }
    
    public void darAltaJugador(String nombre,String tipo){

        Avatar avatarCreado = new Avatar(tipo, this.avatares.size(), nombre);
        avatarCreado.getJugador().setCasillaActual(this.tablero.getCasilla(0,0));
        this.avatares.add(avatarCreado);
        avatarCreado.setTurno(k);
        k++;
    }
    
    public int numJugadores(int numJugadores){
        if(numJugadores>=6){
            System.out.println("El tablero está completo, no se pueden registrar más jugadores.");
            return numJugadores;
        }
        else{
            return numJugadores++;
        }
    }




}
