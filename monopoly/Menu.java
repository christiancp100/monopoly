package monopoly;

import java.util.ArrayList;

public class Menu {

    private InterpreteComandos interprete;
    private ArrayList<Avatar> avatares;
    private Tablero tablero;

    public Menu(InterpreteComandos interprete, Tablero tablero, ArrayList<Avatar> avatares){
        this.interprete = interprete;
        this.avatares = avatares;
        this.tablero = tablero;
    }

    public void start(){


    }
}
