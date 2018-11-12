package monopoly;

import java.util.Random;


public class Avatar {

    //Atributos
    private Jugador jugador;
    private char simbolo;
    private String tipo;

    public Avatar(String tipo, String nombreJugador){
        //La casilla de salida es la inicial y el valor de la fortuna es un tercio del precio total de los solares
        //Se establecen desde el tablero
            this.simbolo = generarSimboloAleatorio();
            if(tipo.equals("pelota")|| tipo.equals("coche") || tipo.equals("esfinge") || tipo.equals("sombrero")) {
                this.tipo = tipo;
        }
        this.jugador = new Jugador(this, nombreJugador);
    }


    //Getters

    public Jugador getJugador(){
        return this.jugador;
    }

    public char getSimbolo(){return this.simbolo;}

    //Setters

    public void setJugador(Jugador jugador){
        this.jugador = jugador;
    }

    public void setSimbolo(char simbolo){
        this.simbolo = simbolo;
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


    public char generarSimboloAleatorio(){
        //Generamos una letra mayuscula aleatoria
        int max=90, min=65;
        Random rand = new Random();
        int  codigoNumerico = rand.nextInt(max-min +1) + min;
        char ascii = (char) codigoNumerico;
        System.out.println("hola");

        return ascii;
    }

}
