package monopoly;

import java.util.ArrayList;

public class Monopoly {
    private ArrayList<Avatar> avatares;
    private InterpreteComandos interprete;
    private Tablero tablero;

    public Monopoly(){
        avatares  = new ArrayList<>();
        tablero = new Tablero(this.avatares);
        interprete  = new InterpreteComandos(this.avatares, this.tablero);

        System.out.println(tablero);
    }

    public void inicializar (){
        interprete.eleccion();
        tablero.avataresEnSalida();
        System.out.println(tablero);
    }

}
