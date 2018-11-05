package monopoly;

import java.util.Random;


public class Avatar {

    //Atributos
    private Jugador jugador;

    private char simbolo;

    public Avatar(){
        //La casilla de salida es la inicial y el valor de la fortuna es un tercio del precio total de los solares
        //Se establecen desde el tablero
        this.jugador = new Jugador(this);
        this.simbolo = generarSimboloAleatorio();
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

        String info;
        info = "Representación en tablero: " + this.simbolo + "\n";
        info += this.jugador.toString();


        return info;
    }


    private char generarSimboloAleatorio(){
        //Generamos una letra mayuscula aleatoria
        int max=91, min=65;
        Random rand = new Random();
        int  codigoNumerico = rand.nextInt(max-min +1) + min;
        char ascii = (char) codigoNumerico;
        return ascii;
    }

}
