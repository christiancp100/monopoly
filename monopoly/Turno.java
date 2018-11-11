package monopoly;

import java.util.ArrayList;

public class Turno {

    private int numeroJugadores;
    private int turnoActual;

    public Turno(ArrayList<Avatar> avatares){
        this.numeroJugadores = avatares.size();
        this.turnoActual = 0;
    }

    //Getters

    public int getTurno(){
        return this.turnoActual;
    }

    public int getNumeroJugadores() {
        return  this.numeroJugadores;
    }

    //Setters
    public void setTurno(int numeroTiradas){
        this.turnoActual = numeroTiradas%this.numeroJugadores;
    }

    public void setNumeroJugadores(int otro){
        this.numeroJugadores += otro;
    }

}
