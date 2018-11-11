package monopoly;
import tablero.Casilla;

import java.util.ArrayList;


public class Jugador {

    //Atributos
    private String nombreJugador;
    private Avatar avatar;
    private Casilla casillaActual;
    private double fortuna;
    private int numeroVueltas;
    private boolean puedeTirarOtraVez;

    private ArrayList<Casilla> propiedades;

    public Jugador(Avatar avatar,float fortuna){
        this.avatar = avatar;
        this.fortuna = fortuna;
        this.casillaActual = null;
        this.propiedades = new ArrayList<>();
        this.numeroVueltas = -1; //Le damos el valor de -1 para que cuando se cree, al estar en la casilla de salida, sea 0
        puedeTirarOtraVez = false;

    }

    public Jugador(Avatar avatar, String nombreJugador){
        this.avatar = avatar;
        this.casillaActual = null;
        this.propiedades = new ArrayList<>();
        this.nombreJugador = nombreJugador;
        this.numeroVueltas = -1;
        puedeTirarOtraVez = false;


    }

    public Jugador(Avatar avatar,Casilla casilla, float fortuna){
        this.avatar = avatar;
        this.casillaActual = casilla;
        this.fortuna = fortuna;
        this.propiedades = new ArrayList<>();
        this.numeroVueltas = -1;
        puedeTirarOtraVez = false;


    }

    public Jugador(){ //Constructor de la banca
        this.fortuna = Float.POSITIVE_INFINITY ;
        this.propiedades = new ArrayList<>();
        puedeTirarOtraVez = false;
    }




    //Getters

    public Casilla getCasillaActual(){
        return this.casillaActual;
    }

    public double getFortuna(){
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

    public void setFortuna(double fortuna){
        this.fortuna = fortuna;
    }

    public void setFortuna(double valor, int operacion ){ //1 para incremento y 0 para decremento
        if(operacion == 1) this.fortuna += valor;
        if(operacion == -1) this.fortuna -= valor;
    }

    public void setPropiedades(Casilla casilla){
        this.propiedades.add(casilla);
    }

    public void quitarPropiedad(Casilla casilla){
        this.propiedades.remove(casilla);
        casilla.setDisponibilidad(true);
    } //Pone disponibilidad a true

    public void nuevaPropiedad(Casilla propiedad){
        this.propiedades.add(propiedad);
        propiedad.setDisponibilidad(false);
    } //Pone disponibilidad a false

    public void cedePropiedad(Casilla propiedad){
        this.propiedades.remove(propiedad);
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
    
    //MÉTODOS
    public void desHipotecar(){
        //si tiene suficiente dinero => puede deshipotecar la propiedad
        if(this.avatar.getJugador().getFortuna()>=(this.avatar.getJugador().getCasillaActual().getHipoteca()*1.1)){
            this.avatar.getJugador().setFortuna((float) (this.avatar.getJugador().getCasillaActual().getHipoteca()*1.1),-1);   
        }
    }
    
    public boolean poseerGrupo(int grupo){
        
        int g=0;
        
        for(int i=0;i<this.propiedades.size();i++){
            if(this.propiedades.get(i).getTipo().equals("Solar")){
                if(this.propiedades.get(i).getGrupo()==grupo){
                    g++;
                }
            }
        }
        if(g==3){//si g=3 entonces el usuario posee todas las casillas del grupo
            return true;
        }
        else if((grupo==1||grupo==7) && g==2){//comprobamos si es un grupo formado por solo 2 casillas
            return true;
        }
        else{
            return false;
        }
    }
    

    @Override
    public String toString() {
        String aux;

        aux = "Nombre del Jugador: " + this.nombreJugador + "\n";
        //aux += "Avatar del Jugador: " + this.avatar + "\n";
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


    public void pagar(Jugador jugadorRecibe, double cantidad){
        this.setFortuna(cantidad, -1);
        jugadorRecibe.setFortuna(cantidad, 1);
    }



}
