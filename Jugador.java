
package monopoly;
import java.util.ArrayList;

/**
 *
 * @author christiancp
 */
public class Jugador {
    
    //Atributos
    private Casilla casillaActual;
    private float dinero;
    private ArrayList<Casilla> propiedades; 
    private int numeroVueltas;
    
    //Constructores
    
    public Jugador(){
        //El dinero al comenzar es un tercio del precio de totas las propiedades
        this.dinero = Tablero.precioInicialPropiedades;
        this.numeroVueltas = 0;
        //this.casillaActual; // = casillaSalida
        
    }
    
    //Getters
    
    public int getNumeroVueltas(){
        return this.numeroVueltas;
    }
    
}
