package monopoly;

import java.util.Random;


public class Avatar {

    //Atributos
    private Jugador jugador;

    private char simbolo;

    public Avatar(){
        //La casilla de salida es la inicial y el valor de la fortuna es un tercio del precio total de los solares
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

        info = "\nFigura que lo representa: " + this.simbolo + "\nDinero del jugador:" + Float.toString(this.jugador.getFortuna()) + "\n";

        return info;
    }


    private char generarSimboloAleatorio(){
        Random rand = new Random();
        char  codigoNumerico = (char) (rand.nextInt(255) + 0);
        return codigoNumerico;
    }



    }
