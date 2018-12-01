package monopoly;

import java.util.Random;


public class Avatar {

    //Atributos
    private Jugador jugador;
    private char simbolo;
    private String tipo;
    private boolean especial;
    private boolean pierdeTurno;
    private int numeroTurnos;


    public Avatar(String tipo, String nombreJugador){
        //La casilla de salida es la inicial y el valor de la fortuna es un tercio del precio total de los solares
        //Se establecen desde el tablero
            this.simbolo = generarSimboloAleatorio();
            if(tipo.equals("pelota")|| tipo.equals("coche") || tipo.equals("esfinge") || tipo.equals("sombrero")) {
                this.tipo = tipo;
            }else{
                System.out.println("No has escrito bien el tipo de avatar, se asignará uno por defecto (pelota)");
                this.tipo = "pelota";
            }
        this.jugador = new Jugador(this, nombreJugador);
        this.especial=false;
    }


    //Getters

    public boolean getPierdeTurno(){
        return this.pierdeTurno;
    }
    
    public int getNumeroTurnos(){
        return this.numeroTurnos;
    }
    
    public Jugador getJugador(){
        return this.jugador;
    }
    
    public char getSimbolo(){return this.simbolo;}

    public String getTipo(){
        return this.tipo;
    }
    
    public boolean getTipoEspecial(){
        return this.especial;
    }

    //Setters
    
    public void setPierdeTurno(boolean turno){
        this.pierdeTurno=turno;
    }
    
    public void setNumeroTurnos(int turno){
        if(turno==0){
            this.numeroTurnos=0;
        }else{
            this.numeroTurnos+=turno;
        }
    }

    public void setJugador(Jugador jugador){
        this.jugador = jugador;
    }

    public void setSimbolo(char simbolo){
        this.simbolo = simbolo;
    }
    
    public void setTipoEspecial(boolean especial){
        this.especial=especial;
    }

    //Métodos

    @Override
    public String toString(){
        String datos;

        datos="{\n    ID: "+this.simbolo+"\n";
        datos+="    Tipo: "+this.tipo+"\n";
        datos+="    Casilla: "+this.jugador.getCasillaActual().getNombre()+"\n";
        datos+="    Jugador: "+this.jugador.getNombreJugador()+"\n}\n";

        return datos;
    }


    public static final char generarSimboloAleatorio(){
        //Generamos una letra mayuscula aleatoria
        int max=90, min=65;
        Random rand = new Random();
        int  codigoNumerico = rand.nextInt(max-min +1) + min;
        char ascii = (char) codigoNumerico;
        return ascii;
    }

}
