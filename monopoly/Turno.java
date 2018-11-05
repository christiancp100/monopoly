package monopoly;

import java.util.ArrayList;

public class Turno {

    private int numeroTurnos;
    private int numeroTiradas;

    public Turno(ArrayList<Avatar> avatares){
        numeroTiradas = 0;
        this.numeroTurnos = avatares.size();
    }

    public void setNumeroTiradas(int tirada){
        numeroTiradas += tirada;
    }

    public int getTurno(){
        return ((this.numeroTiradas%this.numeroTurnos) + 1);
    }

}
