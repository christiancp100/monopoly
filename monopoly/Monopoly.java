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

        do {

            interprete.eleccion();
            tablero.avataresEnSalida();
            this.turno = new Turno(this.avatares);
            tablero.refrescarTablero(turno);

            System.out.println("Pulse S si quiere salir y C para continuar.");
            Scanner sc=new Scanner(System.in);
            opcion=sc.nextLine(); 

        }while(opcion!="S");
    }
}
