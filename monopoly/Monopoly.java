package monopoly;

import java.util.ArrayList;
import java.util.Scanner;

public class Monopoly {
    private ArrayList<Avatar> avatares;
    private InterpreteComandos interprete;
    private Tablero tablero;
    private Turno turno;
    private Dados dados;
    private Menu menu;

    public Monopoly(){
        this.avatares  = new ArrayList<>();
        this.tablero = new Tablero(this.avatares);
        this.interprete  = new InterpreteComandos(this.avatares, this.tablero);
        this.menu = new Menu(this.interprete, this.tablero, this.avatares);

    }

    public void inicializar () {



    }

    public void refrescarTablero(){
        System.out.println(this.tablero);
        menu.start();

    }
}
