package monopoly;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class Jugador {

    //Atributos
    private String nombreJugador;
    private Avatar avatar;
    private Casilla casillaActual;
    private float fortuna;
    private int numeroVueltas;

    private ArrayList<Casilla> propiedades;

    public Jugador(Avatar avatar,float fortuna){
        this.avatar = avatar;
        this.fortuna = fortuna;
        this.casillaActual = null;
        this.propiedades = new ArrayList<>();
        this.numeroVueltas = -1; //Le damos el valor de -1 para que cuando se cree, al estar en la casilla de salida, sea 0
    }

    public Jugador(Avatar avatar, String nombreJugador){
        this.avatar = avatar;
        this.casillaActual = null;
        this.propiedades = new ArrayList<>();
        this.nombreJugador = nombreJugador;
        this.numeroVueltas = -1;

    }

    public Jugador(Avatar avatar,Casilla casilla, float fortuna){
        this.avatar = avatar;
        this.casillaActual = casilla;
        this.fortuna = fortuna;
        this.propiedades = new ArrayList<>();
        this.numeroVueltas = -1;

    }

    public Jugador(){ //Constructor de la banca
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

    public String getNombreJugador(){
        return this.nombreJugador;
    }

    public int getNumeroVueltas(){
        //incrementamos el num de vueltas y la fortuna en caso de que el jugador pase por la casilla de salida (es decir, de la fila 3 o 4 a la 1 o 2)
        
        
        return this.numeroVueltas;
    }

    //Setters

    public void setNombreJugador(String nombreJugador){
        this.nombreJugador = nombreJugador;
    }

    public void setCasillaActual(Casilla casilla){
        this.casillaActual = casilla;
    }

    public void setFortuna(float fortuna){
        this.fortuna = fortuna;
    }

    public void setFortuna(float valor, int operacion ){ //1 para incremento y 0 para decremento
        if(operacion == 1) this.fortuna += valor;
        if(operacion == 0) this.fortuna -= valor;
    }

    public void setPropiedades(Casilla casilla){
        this.propiedades.add(casilla);
    }

    public void quitarPropiedad(Casilla casilla){
        this.propiedades.remove(casilla);
    }

    public void setNumeroVueltas(int numeroVueltas){
        this.numeroVueltas += numeroVueltas;
    }
    
    public boolean enCarcel() {
        //Si todavía no ha empezado la partida o la casilla actual no es de tipo cárcel => es que no está en la carcel.
        if(!(casillaActual.equals("Carcel"))){
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String aux;

        aux = "Nombre del Jugador: " + this.nombreJugador + "\n";
        aux += "Avatar del Jugador: " + this.avatar + "\n";
        aux += "Casilla actual: " + this.casillaActual.getNombre() + "\n";
        aux += "Fortuna: " + Valores.VERDE + this.fortuna + " \uD83D\uDCB8️\n"+ Valores.RESET;
        if(this.propiedades.size() != 0){
            aux += "Propiedades: \n";
            for(int i=0; i<this.propiedades.size();i++){
                aux += "\t->" + this.propiedades.get(i).toString();
            }
        }
        
        //Hipotecas
        //Edificios
        else aux +=Valores.ROJO + "-> El usuario no tiene propiedades, debería ponerse las pilas...\n" + Valores.RESET;

        return aux;
    }




}
