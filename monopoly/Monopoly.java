package monopoly;

import java.util.ArrayList;
import java.util.Scanner;

public class Monopoly {
    private ArrayList<Avatar> avatares;
    private InterpreteComandos interprete;
    private Tablero tablero;
    private Turno turno;

    public Monopoly(){
        this.avatares  = new ArrayList<>();
        this.tablero = new Tablero(this.avatares);
        this.interprete  = new InterpreteComandos(this.avatares, this.tablero);
        System.out.println(tablero);
    }

    public void inicializar (){

        String opcion;

        interprete.eleccion();
        tablero.avataresEnSalida();
        this.turno = new Turno(this.avatares);
        tablero.refrescarTablero(turno);
    }
}
