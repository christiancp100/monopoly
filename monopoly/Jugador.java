
package monopoly;
import java.util.ArrayList;

/**
 *
 * @author christiancp
 */
public class Jugador {

    //Atributos
    private Avatar avatar;
    private Casilla casillaActual;
    private float fortuna;

    private Casilla[] propiedades;

    public Jugador(Avatar avatar,float fortuna){
        this.avatar = avatar;
        this.fortuna = fortuna;
        this.casillaActual = null;

    }

    public Jugador(Avatar avatar){
        this.avatar = avatar;
        this.casillaActual = null;

    }

    public Jugador(Avatar avatar,Casilla casilla, float fortuna){
        this.avatar = avatar;
        this.casillaActual = casilla;
        this.fortuna = fortuna;
        this.propiedades = null;
    }



    //Getters

    public Casilla getCasillaActual(){
        return this.casillaActual;
    }

    public float getFortuna(){
        return this.fortuna;
    }

    //Setters

    public void setCasillaActual(Casilla casilla){
        this.casillaActual = casilla;
    }

    public void setFortuna(float fortuna){
        this.fortuna = fortuna;
    }


}
