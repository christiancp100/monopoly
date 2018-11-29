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
        this.turno.setNumeroJugadores(2);
        interprete.darAltaJugador("christian", "esfinge");
        interprete.darAltaJugador("maria", "esfinge");
        this.avatares.get(0).getJugador().setCasillaActual(this.tablero.getCasillaByName("N.Zelanda"));
        this.avatares.get(1).getJugador().setCasillaActual(this.tablero.getCasillaByName("N.Zelanda"));
        this.avatares.get(0).getJugador().setFortuna(2000000);
        interprete.eleccion("iniciar partida");
        interprete.eleccion("comprar N.Zelanda");
        interprete.eleccion("lanzar dados");
        interprete.eleccion("comprar Australia");
        interprete.eleccion("edificar casa");
        interprete.eleccion("edificar casa");
        interprete.eleccion("edificar casa");
        interprete.eleccion("edificar casa");
        interprete.eleccion("edificar hotel");
        interprete.eleccion("edificar casa");
        interprete.eleccion("edificar casa");
        interprete.eleccion("edificar casa");
        interprete.eleccion("edificar casa");
        interprete.eleccion("edificar hotel");
        //menu.start();
        do{
            menu.desarrolloPartida();
        }
        while (avatares.size() > 1);

        System.out.println("\n¡La partida ha terminado!");
        System.out.println("El jugador vencedor es: " + this.avatares.get(0).getJugador().getNombreJugador());

    }
}
