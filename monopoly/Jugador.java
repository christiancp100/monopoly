package monopoly;
import tablero.Casilla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private String nombreCasillaAnterior;
    private boolean compraEfectuada;
    private double dineroIntertido;
    private ArrayList<Casilla> propiedades;
    private double pagoAlquileres;
    private int vecesEnCarcel;
    private double premiosInvOBote;
    private double cobroAlquileres;

    public Jugador(Avatar avatar,float fortuna){
        this.avatar = avatar;
        this.fortuna = fortuna;
        this.casillaActual = null;
        this.propiedades = new ArrayList<>();
        this.numeroVueltas = -1; //Le damos el valor de -1 para que cuando se cree, al estar en la casilla de salida, sea 0
        this.numDobles=0;
        this.puedeTirarOtraVez = false;
        this.dineroIntertido = 0;
        this.pagoAlquileres = 0;
        this.premiosInvOBote = 0;
        this.cobroAlquileres = 0;
    }

    public Jugador(Avatar avatar, String nombreJugador){
        this.avatar = avatar;
        this.casillaActual = null;
        this.propiedades = new ArrayList<>();
        this.nombreJugador = nombreJugador;
        this.numeroVueltas = -1;
        this.numDobles=0;
        puedeTirarOtraVez = true;
        this.compraEfectuada = false;
        this.dineroIntertido = 0;
        this.pagoAlquileres = 0;
        this.premiosInvOBote = 0;
        this.cobroAlquileres = 0;

    }

    public Jugador(Avatar avatar, Casilla casilla, float fortuna){
        this.avatar = avatar;
        this.casillaActual = casilla;
        this.fortuna = fortuna;
        this.propiedades = new ArrayList<>();
        this.numeroVueltas = -1;
        this.numDobles=0;
        puedeTirarOtraVez = true;
        this.compraEfectuada = false;
        this.dineroIntertido = 0;
        this.pagoAlquileres = 0;
        this.premiosInvOBote = 0;
        this.cobroAlquileres = 0;
    }

    public Jugador(){ //Constructor de la banca
        this.fortuna = Float.POSITIVE_INFINITY ;
        this.propiedades = new ArrayList<>();
    }




    //Getters

    public double getCobroAlquileres(){
        return this.cobroAlquileres;
    }

    public double getPremiosInversionesBote() {
        return premiosInvOBote;
    }

    public double getPagoAlquileres(){
        return pagoAlquileres;
    }

    public double getDineroIntertido() {
        return dineroIntertido;
    }

    public Casilla getCasillaActual(){
        return this.casillaActual;
    }
    
    public String getNombreCasillaAnterior(){
        return nombreCasillaAnterior;
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
    
    public boolean getCompraEfectuada(){
        return this.compraEfectuada;
    }

    public int getVecesEnCarcel() {
        return vecesEnCarcel;
    }

    //Setters

    public void setCobroAlquileres(double cobroAlquileres){
        this.cobroAlquileres = cobroAlquileres;
    }

    public void setVecesEnCarcel(){
        this.vecesEnCarcel++;
    }

    public void setPagoAlquileres(double pagoAlquileres) {
        this.pagoAlquileres += pagoAlquileres;
    }

    public void setNombreJugador(String nombreJugador){
        this.nombreJugador = nombreJugador;
    }

    public void setCasillaActual(Casilla casilla){
        this.casillaActual = casilla;
    }
    
    public void setNombreCasillaAnterior(String casilla){
        this.nombreCasillaAnterior=casilla;
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

    public void setDineroIntertido(double dineroIntertido) {
        this.dineroIntertido += dineroIntertido;
    }
    
    public void setPremiosInversionesBote(double premiosInvBote){
        this.premiosInvOBote += premiosInvBote;
    }
    
    public void setCompraEfectuada(boolean compra){
        this.compraEfectuada=compra;
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
            this.avatar.getJugador().setFortuna((float) (this.avatar.getJugador().getCasillaActual().getHipoteca()),-1);

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
                        this.dineroIntertido += edificio.getPrecio();
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

    public int venderEdificio(Jugador jugador, String tipo, Casilla casilla, int cantidad) {

        int auxCantAEliminar = cantidad;
        int eliminadas = 0;
        String auxTipo= " ";
        int count = 0;
        Iterator edKey = casilla.getEdificaciones().keySet().iterator();

        if(!casilla.getJugadorQueTieneLaCasilla().equals(this)) return -1;

        if (tipo.contains("casa")) {

            if(cantidad > casilla.getNumeroCasas()) auxCantAEliminar = casilla.getNumeroCasas();
            auxTipo = "casa";
        }
        else if (tipo.contains("hotel")){
            if(cantidad > casilla.getNumeroHoteles()) auxCantAEliminar = casilla.getNumeroHoteles();
            auxTipo = "hotel";
        }

        else if (tipo.contains("piscina")) {
            if(cantidad > casilla.getNumeroPiscinas()) auxCantAEliminar = casilla.getNumeroPiscinas();
            auxTipo = "piscina";
        }
        else if (tipo.contains("pistaDep")) {
            if(cantidad > casilla.getNumeroPistasDep()) auxCantAEliminar = casilla.getNumeroPistasDep();
            auxTipo = "pistaDep";
        }

         while(edKey.hasNext()) {

             String aux = edKey.next().toString();

            if ((count<auxCantAEliminar) && casilla.getEdificaciones().get(aux).getTipo().equals(auxTipo)) {

                if (tipo.contains("casa"))
                    casilla.setNumeroCasas(-1);
                else if (tipo.contains("hotel"))
                    casilla.setNumeroHoteles(-1);
                else if (tipo.contains("piscina"))
                    casilla.setNumeroPiscinas(-1);
                else if (tipo.contains("pistaDep"))
                    casilla.setNumeroPistasDep(-1);

                jugador.setFortuna(casilla.getEdificaciones().get(aux).hipotecaEdificaciones(), 1);
                edKey.remove();
                eliminadas++;
            }
            count++;
        }

        if (eliminadas == 0) return 0;
        else if (eliminadas == cantidad) return 1;
        else if (auxCantAEliminar != cantidad) return 2;
        else return 3;
    }

    public void venderTodosEdificios(Casilla casilla){

        Iterator it = casilla.getEdificaciones().keySet().iterator();

        while(it.hasNext()){
            String edKey = it.next().toString();
            this.fortuna+= casilla.getEdificaciones().get(edKey).getPrecioHipoteca();
            casilla.getEdificaciones().remove(edKey);
        }
    }
}
