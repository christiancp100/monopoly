package monopoly;
import tablero.Casilla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Jugador {

    //Atributos
    private String nombreJugador;
    private Avatar avatar;
    private Casilla casillaActual;
    private double fortuna;
    private int numeroVueltas;
    private boolean puedeTirarOtraVez;
    private boolean estarCarcel;
    private int numDobles;
    private int numTiradasCarcel;

    private ArrayList<Casilla> propiedades;
    public Jugador(Avatar avatar,float fortuna){
        this.avatar = avatar;
        this.fortuna = fortuna;
        this.casillaActual = null;
        this.propiedades = new ArrayList<>();
        this.numeroVueltas = -1; //Le damos el valor de -1 para que cuando se cree, al estar en la casilla de salida, sea 0
        this.numDobles=0;
        puedeTirarOtraVez = false;

    }

    public Jugador(Avatar avatar, String nombreJugador){
        this.avatar = avatar;
        this.casillaActual = null;
        this.propiedades = new ArrayList<>();
        this.nombreJugador = nombreJugador;
        this.numeroVueltas = -1;
        this.numDobles=0;
        puedeTirarOtraVez = true;


    }

    public Jugador(Avatar avatar,Casilla casilla, float fortuna){
        this.avatar = avatar;
        this.casillaActual = casilla;
        this.fortuna = fortuna;
        this.propiedades = new ArrayList<>();
        this.numeroVueltas = -1;
        this.numDobles=0;
        puedeTirarOtraVez = true;


    }

    public Jugador(){ //Constructor de la banca
        this.fortuna = Float.POSITIVE_INFINITY ;
        this.propiedades = new ArrayList<>();
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

    public boolean getPuedeTirarOtraVez(){
        return this.puedeTirarOtraVez;
    }
    
    public boolean getEstarCarcel(){
        return this.estarCarcel;
    }
    
    public int getNumDobles(){
        return this.numDobles;
    }

    public int getNumTiradasCarcel(){
        return this.numTiradasCarcel;
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
        if(operacion == -1){
            this.fortuna -= valor;
        }
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

    public void setNumTiradasCarcel(int tiradas,int situacion){
        if(situacion==1){
            this.numTiradasCarcel+=tiradas;
        }
        if(situacion==2){
            this.numTiradasCarcel=tiradas;
        }
    }


    public void setNumeroVueltas(int numeroVueltas){
        this.numeroVueltas += numeroVueltas;
    }

    public void setPuedeTirarOtraVez(boolean puede){
        this.puedeTirarOtraVez = puede;
    }
    
    public void setEstarCarcel(boolean estarCarcel) {
        //Si todavía no ha empezado la partida o la casilla actual no es de tipo cárcel => es que no está en la carcel.
        this.estarCarcel=estarCarcel;
    }
    
    public void setNumDobles(int numDobles,int situacion){
        //contamos cuantas veces saca dobles, y como solo nos interesa para la carcel desde aqui establecemos el valor a 0 cuando llega a 3
        if(situacion==1){
            if(this.numDobles==3){
                this.numDobles=0;
            }
            else{
                this.numDobles+=numDobles;
            }
        }
        else if(situacion==2){
            this.numDobles=numDobles;
        }
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

        aux = "{\n    Nombre: " + this.nombreJugador + "\n";
        //aux += "Avatar del Jugador: " + this.avatar + "\n";
        aux += "    Fortuna: " + Valores.VERDE + this.fortuna + " Trillones de euro\n"+ Valores.RESET;
        aux += "    Casilla actual: " + this.casillaActual.getNombre() + "\n";

        if(this.propiedades.size() != 0){
            aux += "Propiedades: [";
            for(int i=0; i<this.propiedades.size();i++){
                aux += " "+ this.propiedades.get(i).getNombre().toString()+ " ";
            }
            aux += "]";
        }
        
        //Hipotecas
        //Edificios
        else aux +=Valores.ROJO + "-> El usuario no tiene propiedades, debería ponerse las pilas...\n" + Valores.RESET;

        aux += "\n}\n";
        return aux;
    }

    public void pagar(Jugador jugadorRecibe, double cantidad){
        this.setFortuna(cantidad, -1);
        jugadorRecibe.setFortuna(cantidad, 1);
    }

    public void edificar(Casilla casilla, String tipo){

        Scanner reader=new Scanner(System.in);  // Reading from System.in
        Edificaciones edificio = new Edificaciones(tipo, casilla);

        if(casilla.getJugadorQueTieneLaCasilla().equals(this)){

            if(poseerGrupo(casilla.getGrupo()) || casilla.getVeces(this) > 2) { //Si tiene el grupo entero o estuvo mas de 2 veces en esa casilla, puede comprarla

                if (this.fortuna > edificio.getPrecio()) {
                    if(casilla.setEdificaciones(edificio)){
                        System.out.println(Valores.VERDE + "El jugador ha construido 1 " + tipo + ", ¡ENHORABUENA!" + Valores.RESET);
                        System.out.println("El precio actual del alquiler es: " + casilla.getAlquiler());
                    }else{
                        System.out.println(Valores.ROJO + "No se ha podido construir la edificación" + Valores.RESET);
                    }
                } else if (this.propiedades.size() > 0) {

                    //Recorremos las propiedades del jugador
                    for(Casilla cas : this.propiedades){
                        if(!cas.getHipotecada()){
                            if(cas.getNumeroEdificaciones() > 0){
                                for(Edificaciones ed : casilla.getEdificaciones().values()){
                                    if(ed.getPrecioHipoteca() > edificio.getPrecio()){
                                        System.out.println("Podrías hipotecar una de tus edificaciones en la casilla " + ed.getCasillaEdificio());
                                        System.out.println("¿Deseas hacerlo?");
                                        System.out.println("\n->");
                                        String n = reader.nextLine(); // Scans the next token of the input as an int.
                                        reader.reset();
                                        if(n.equals("Si")){
                                            //Hipotecar el edificio
                                            ed.setHipotecada(true);
                                            //Sumar a la fortuna el precio de la hipoteca
                                            this.fortuna+= ed.getPrecioHipoteca();
                                            //Restamos el coste de la edificacion a la fortuna del jugador
                                            this.fortuna -= edificio.getPrecio();
                                            //Añadimos el edificio a la casilla
                                            cas.setEdificaciones(edificio);
                                            System.out.println(Valores.VERDE + "El jugador ha construido 1 " + tipo + ", ¡ENHORABUENA!" + Valores.RESET);
                                            System.out.println("El precio actual del alquiler es: " + casilla.getAlquiler());

                                        }
                                        else{
                                            System.out.println("No se hipotecará la edificacion");
                                        }
                                    }
                                }
                                if(cas.getPrecioHipoteca() > edificio.getPrecio()){
                                    System.out.println("Podrías hipotecar la propiedad:  " + cas.getNombre());
                                    System.out.println("¿Deseas hacerlo?");
                                    System.out.println("\n->");
                                    String n = reader.nextLine(); // Scans the next token of the input as an int.
                                    reader.reset();
                                    if(n.equals("Si")){
                                        //Hipotecar propiedad y comprar el edificio nuevo
                                        cas.setHipotecada(true);
                                        //sumamos el precio de la hipoteca a la fortuna del jugador
                                        this.fortuna += cas.getPrecioHipoteca();
                                        //Restamos el coste del edificio a la fortuna del jugador
                                        this.fortuna -= edificio.getPrecio();
                                        //Añadimos el edificio a la casilla
                                        cas.setEdificaciones(edificio);
                                        System.out.println(Valores.VERDE + "El jugador ha construido 1 " + tipo + ", ¡ENHORABUENA!" + Valores.RESET);
                                        System.out.println("El precio actual del alquiler es: " + casilla.getAlquiler());
                                    }
                                    else{
                                        System.out.println("No se hipotecara la propiedad");
                                    }
                                }
                            }
                        }
                    }


                    //TODO Recorrer todas las propiedades, comprobar si tienen edificios, si los tienen, comprobar si su hipoteca es mayor o igual
                    //que su fortuna, si no, comprobar si la hipoteca de la casilla es mayor que lo que debe pagar, para esto, voy a crear una funcion auxiliar


                } else {
                    System.out.println(Valores.ROJO + "El jugador no puede permitirse llevar a cabo esta acción" + Valores.RESET);
                }
            }else{
                System.out.println("El jugador no posee todo el grupo de Casillas y tampoco estuvo más de dos veces en esta casilla. No puede edificar");
            }
        }
    }

    public void hipotecar(Casilla casilla){
        casilla.setHipotecada(true);
        this.fortuna += casilla.getHipoteca();
    }

    public boolean deshipotecar(Casilla casilla){
        double cantidad = casilla.getHipoteca() * 1.1f;
        if(this.fortuna >= cantidad) {
            this.fortuna -= cantidad;
            casilla.setHipotecada(false);
            return true;
        }
        return false;
    }

    public boolean venderEdificio(String tipo, Casilla casilla, int cantidad){
        if(tipo.contains("casa")){
            for(int i=0;i<cantidad;i++){

            }
        }
        return false;
    }
}
