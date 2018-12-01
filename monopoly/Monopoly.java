package monopoly;

import tablero.Dados;
import tablero.Tablero;

import java.util.ArrayList;

public class Monopoly {
    private ArrayList<Avatar> avatares;
    private InterpreteComandos interprete;
    private Tablero tablero;
    private Turno turno;
    private Dados dados;
    private Menu menu;


    public Monopoly(){
        this.avatares  = new ArrayList<>();
        this.dados = new Dados();
        this.turno = new Turno(this.avatares);
        this.tablero = new Tablero(this.avatares,this.dados, this.turno);
        this.interprete  = new InterpreteComandos(this.avatares,this.tablero,this.turno,this.dados);
        this.menu = new Menu(this.interprete, this.avatares);
    }

    public void inicializar () {
        menu.start();

        while (avatares.size() > 1){
            /*for(Avatar av : this.avatares){
                if(av.getJugador().getFortuna()<0){
                    avatares.remove(av);
                    this.turno.setNumeroJugadores(-1);
                }
            }*/

            menu.desarrolloPartida();

        }


        System.out.println("\n¡La partida ha terminado!");
        System.out.println("El jugador vencedor es: " + this.avatares.get(0).getJugador().getNombreJugador());

    }
}
