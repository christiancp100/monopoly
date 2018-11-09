package monopoly;

import java.util.Random;


public class Avatar {

    //Atributos
    private Jugador jugador;
    private char simbolo;
    private String tipo;
    private int turno;

    public Avatar(String tipo, int turno, String nombreJugador){
        //La casilla de salida es la inicial y el valor de la fortuna es un tercio del precio total de los solares
        //Se establecen desde el tablero
            this.jugador = new Jugador(this, nombreJugador );
            this.simbolo = generarSimboloAleatorio();
            this.turno = turno;
            if(tipo == "pelota" || tipo == "coche" || tipo == "esfinge" || tipo == "sombrero") {
                this.tipo = tipo;
        }
    }


    //Getters

    public Jugador getJugador(){
        return this.jugador;
    }

    public char getSimbolo(){return this.simbolo;}

    public int getTurno(){ 
        return this.turno;
    }

    //Setters

    public void setJugador(Jugador jugador){
        this.jugador = jugador;
    }

    public void setSimbolo(char simbolo){
        this.simbolo = simbolo;
    }

    public void setTurno(int turno){
        this.turno = turno;
    }

    //Métodos

    @Override
    public String toString(){

        String info;
        info = "Representación en tablero: " + this.simbolo + "\n";
        info += this.jugador.toString();


        return info;
    }


    public char generarSimboloAleatorio(){
        //Generamos una letra mayuscula aleatoria
        int max=90, min=65;
        Random rand = new Random();
        int  codigoNumerico = rand.nextInt(max-min +1) + min;
        char ascii = (char) codigoNumerico;
        return ascii;
    }
    
    public String imprimirDatos(){
        
        String datos;
        
        datos="ID: "+this.simbolo+"\n";
        datos+="Tipo: "+this.tipo+"\n";
        datos+="Casilla: "+this.jugador.getCasillaActual()+"\n";
        datos+="Jugador: "+this.jugador+"\n";
        
        return datos;
    }

}
