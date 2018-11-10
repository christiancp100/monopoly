package monopoly;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class InterpreteComandos {


    private ArrayList<Avatar> avatares;
    private Tablero tablero;
    private int k; //aumenta cada vez que creamos un jugador, para saber que turno le corresponde
    private Turno turno;
    
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
        
        if(eleccion.contains("jugador")){
                       
            //jugadorTurno(metodo de lanzarDados, indicar quien tiene el turno actualmente)
            System.out.println("Nombre: ");
            System.out.println("Avatar: ");
        }
        
        if(eleccion.contains("listar jugadores")){
            for(int i=0;i<this.avatares.size();i++){
                System.out.println("{");
                System.out.println(this.avatares.get(i).getJugador());
                System.out.println("}");
            }
        }
        
        if(eleccion.contains("listar avatares")){
            for(int i=0;i<this.avatares.size();i++){
                System.out.println("{");
                System.out.println(this.avatares.get(i).imprimirDatos());
                System.out.println("}");
            }
        }
        
        if(eleccion.contains("lanzar dados")){
            //lanzarDados
        }
        
        if(eleccion.contains("acabar turno")){
            //turnoActual
        }
        
        if(eleccion.contains("salir carcel")){
            //hay que comprobar que jugador tiene el turno

            int i = turno.getTurno() -1;

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
        
        if(eleccion.contains("describir")){
            aux=eleccion.split("\\s+");

            for(int i=0;i<4;i++){
                for(int j=0;j<10;j++){
                    if(this.tablero.getCasilla(i,j).getTipo()=="Solar"){
                        System.out.println(this.tablero.getCasilla(i,j));
                    }

                    if(this.tablero.getCasilla(i,j).getTipo()=="Parking"){
                        System.out.println("Bote: " +this.tablero.getCasilla(i,j).getBote());
                        System.out.println(this.tablero.getCasilla(i,j).getJugadoresCasilla());
                    }

                    if(this.tablero.getCasilla(i,j).getTipo()=="Carcel"){
                        System.out.println("Precio salida: "+Valores.PAGOSALIRCARCEL);
                        System.out.println(this.tablero.getCasilla(i,j).getJugadoresCasilla());
                    }
                }  
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
            char av=aux[2].charAt(0);
            //buscamos al usuario e imprimimos sus datos
            for(int i=0;i<this.avatares.size();i++){
                if(av == this.avatares.get(i).getSimbolo() ){
                    System.out.println(this.avatares.get(i).imprimirDatos());
                }
            }
        }
        
        if(eleccion.contains("comprar")){
            
            aux=eleccion.split("\\s+");

            for(int i=0;i<4;i++){
                for(int j=0;j<10;j++){
                    if(this.tablero.getCasilla(i,j).getDisponibilidad()==true && this.tablero.getCasilla(i,j).equals(aux[1])){
                        if(this.tablero.getAvatares().get(this.turno.getTurno()).getJugador().getFortuna()>=this.tablero.getCasilla(i,j).getPrecio()){
                        //le quitamos el dinero al jugador, le asignamos la propiedad y la establecemos como no disponible
                        this.tablero.getAvatares().get(this.turno.getTurno()).getJugador().setFortuna((float) this.avatares.get(i).getJugador().getCasillaActual().getPrecio(),-1);
                        this.tablero.getAvatares().get(this.turno.getTurno()).getJugador().setPropiedades(this.avatares.get(i).getJugador().getCasillaActual());
                        this.tablero.getAvatares().get(this.turno.getTurno()).getJugador().getCasillaActual().setDisponibilidad(false);
                        //la variable turno representa al jugador en el array de jugadores (orden en el que fueron registrados)
                            System.out.println("El jugador "+this.tablero.getAvatares().get(this.turno.getTurno()).getJugador().getNombreJugador()
                                    +" compra la casilla "+this.tablero.getCasilla(i,j).getNombre()+" por "+this.tablero.getCasilla(i,j).getPrecio());
                            System.out.println("Su fortuna actual es: "+this.tablero.getAvatares().get(this.turno.getTurno()).getJugador().getFortuna());
                        }
                        else{
                            System.out.println("El jugador no tiene suficiente dinero para comprar la propiedad.");
                        }
                    }
                    else{
                        System.out.println("La casilla es propiedad de: "+this.avatares.get(i).getJugador().getCasillaActual().getPropietario());
                    }
                }
            }         

        }
        
        if(eleccion.contains("listar en venta")){
            this.tablero.imprimirCasillasDisponibles();
        }
        
        if(eleccion.contains("ver tablero")){
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
}
