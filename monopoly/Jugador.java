
package monopoly;
import java.lang.reflect.Array;
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

    private ArrayList<Casilla> propiedades;

    public Jugador(Avatar avatar,float fortuna){
        this.avatar = avatar;
        this.fortuna = fortuna;
        this.casillaActual = null;
        this.propiedades = new ArrayList<>();

    }

    public Jugador(Avatar avatar){
        this.avatar = avatar;
        this.casillaActual = null;
        this.propiedades = new ArrayList<>();

    }

    public Jugador(Avatar avatar,Casilla casilla, float fortuna){
        this.avatar = avatar;
        this.casillaActual = casilla;
        this.fortuna = fortuna;
        this.propiedades = new ArrayList<>();
    }

    public Jugador(){
        this.avatar = null;
        this.casillaActual = null;
        this.fortuna = Float.POSITIVE_INFINITY ;
        this.propiedades = new ArrayList<>();
    }



    //Getters

    public Casilla getCasillaActual(){
        return this.casillaActual;
    }

    public float getFortuna(){
        return this.fortuna;
    }

    public ArrayList<Casilla> getPropiedades(){
        return this.propiedades;
    }

    //Setters

    public void setCasillaActual(Casilla casilla){
        this.casillaActual = casilla;
    }

    public void setFortuna(float fortuna){
        this.fortuna = fortuna;
    }

    public void setPropiedades(Casilla casilla){
        this.propiedades.add(casilla);
    }

    public void quitarPropiedad(Casilla casilla){
        this.propiedades.remove(casilla);
    }

    @Override
    public String toString() {
        String aux;

        aux = "Fortuna: " + Valores.VERDE + this.fortuna + " \uD83D\uDCB8️\n"+ Valores.RESET;
        if(this.propiedades.size() != 0){
            aux += "Propiedades: \n";
            for(int i=0; i<this.propiedades.size();i++){
                aux += "\t->" + this.propiedades.get(i).toString();
            }
        }
        else aux +=Valores.ROJO + "-> El usuario no tiene propiedades, debería ponerse las pilas...\n" + Valores.RESET;

        return aux;
    }




}
