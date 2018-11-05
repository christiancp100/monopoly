package monopoly;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Tablero {
    
    public static final float precioInicialPropiedades = 500000000;

    //Atributos
    private ArrayList<Avatar> avatares;
    private ArrayList<Jugador> jugadores;
    private ArrayList<ArrayList<Casilla>> casillas;
    private ArrayList<Tarjeta> tarjetas;
    private float precioTotalPropiedades;
    private Jugador banca;

    private int incrementosRealizados;


    //Constructores

    public Tablero(ArrayList<Avatar> avatares) {

        this.incrementosRealizados = 0;

        this.avatares = avatares;

        this.casillas = new ArrayList<>();

        this.banca = new Jugador();

        for(int i=0;i<4;i++){
            this.casillas.add(new ArrayList<Casilla>());

            for(int j=0; j<10;j++){ //posicion (N,S,E,O)
                this.casillas.get(i).add(new Casilla());
                this.casillas.get(i).get(j).setNombre(Valores.nombres[j + 10*i]); //Para recorrer el tablero del 0-39

                if(j==0){ //Es el numero de casilla en cada posicion (N,S,E,O) del tablero
                    if(i==0){
                        this.casillas.get(i).get(j).setTipo("Salida");

                    }
                    if(i==1){
                        this.casillas.get(i).get(j).setTipo("Carcel");
                    }
                    if(i==2){
                        this.casillas.get(i).get(j).setTipo("Parking");
                    }
                    if(i==3){
                        this.casillas.get(i).get(j).setTipo("IrCarcel");
                    }
                    this.casillas.get(i).get(j).setGrupo(10); //Este es el grupo de las casillas especiales
                }
                if(j==1){
                    this.casillas.get(i).get(j).setTipo("Solar");
                }
                if(j==2){
                    if(i==0 || i==3){
                        this.casillas.get(i).get(j).setTipo("Caja");
                    }
                    else{
                        this.casillas.get(i).get(j).setTipo("Servicio");
                    }
                }
                if(j==3){
                    this.casillas.get(i).get(j).setTipo("Solar");
                }
                if(j==4){
                    if(i==0 || i==3){
                        this.casillas.get(i).get(j).setTipo("Impuestos");
                    }
                    else{
                        this.casillas.get(i).get(j).setTipo("Solar");
                    }
                }
                if(j==5){
                    this.casillas.get(i).get(j).setTipo("Transportes");
                }
                if(j==6){
                    this.casillas.get(i).get(j).setTipo("Solar");
                }
                if(j==7){
                    if(i!=1){
                        this.casillas.get(i).get(j).setTipo("Suerte");
                    }
                    else{
                        this.casillas.get(i).get(j).setTipo("Caja");
                    }
                }
                if(j==8 || j==9){
                    this.casillas.get(i).get(j).setTipo("Solar");
                }
            }


        }
        //Establecemos los ATRIBUTOS de las Casillas
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                if(this.casillas.get(i).get(j).getTipo() == "Solar") {
                    //Las creamos con disponibilidad de compra
                    this.casillas.get(i).get(j).setDisponibilidad(true);

                    if (i == 0) {
                        if (j < 5) { //Antes que la casilla de transporte
                            this.casillas.get(i).get(j).setColor(Valores.NEGRO);
                            this.casillas.get(i).get(j).setGrupo(1);
                            this.casillas.get(i).get(j).setPrecio(1);
                        } else {
                            this.casillas.get(i).get(j).setColor(Valores.CIAN);
                            this.casillas.get(i).get(j).setGrupo(2);
                            this.casillas.get(i).get(j).setPrecio(2);

                        }
                    }
                    if (i == 1) {
                        if (j < 5) { //Antes que la casilla de transporte
                            this.casillas.get(i).get(j).setColor(Valores.MAGENTA);
                            this.casillas.get(i).get(j).setGrupo(3);
                            this.casillas.get(i).get(j).setPrecio(3);

                        } else {
                            this.casillas.get(i).get(j).setColor(Valores.AMARILLO);
                            this.casillas.get(i).get(j).setGrupo(4);
                            this.casillas.get(i).get(j).setPrecio(4);
                        }
                    }
                    if (i == 2) {
                        if (j < 5) { //Antes que la casilla de transporte
                            this.casillas.get(i).get(j).setColor(Valores.ROJO);
                            this.casillas.get(i).get(j).setGrupo(5);
                            this.casillas.get(i).get(j).setPrecio(5);

                        } else {
                            this.casillas.get(i).get(j).setColor(Valores.BLANCO);
                            this.casillas.get(i).get(j).setGrupo(6);
                            this.casillas.get(i).get(j).setPrecio(6);
                        }
                    }
                    if (i == 3) {
                        if (j < 5) { //Antes que la casilla de transporte
                            this.casillas.get(i).get(j).setColor(Valores.VERDE);
                            this.casillas.get(i).get(j).setGrupo(7);
                            this.casillas.get(i).get(j).setPrecio(7);
                        } else {
                            this.casillas.get(i).get(j).setColor(Valores.AZUL);
                            this.casillas.get(i).get(j).setGrupo(8);
                            this.casillas.get(i).get(j).setPrecio(8);

                        }
                    }
                }
                else{
                    if(this.casillas.get(i).get(j).getTipo().equals("Transportes")
                    || this.casillas.get(i).get(j).getTipo().equals("Servicio")){
                        //Las creamos con disponibilidad de compra
                        this.casillas.get(i).get(j).setDisponibilidad(true);
                    }
                    this.casillas.get(i).get(j).setColor(Valores.NEGRO);
                }
            }
        }

        //Creamos la banca
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                if(this.casillas.get(i).get(j).getDisponibilidad()){
                    this.banca.setPropiedades(this.casillas.get(i).get(j));
                }
            }
        }
    }

    //Getters

    public Casilla getCasilla(int i, int j){
        return this.casillas.get(i).get(j);
    }

    public ArrayList<Avatar> getAvatares(){
        return this.avatares;
    }

    private int getCoordenadaCasilla(Casilla casilla){
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                if(casilla.equals(this.casillas.get(i).get(j))){
                    return i;
                }
            }
        }
        return 5; //Fuera de rango
    }
    private int getPosicionCasilla(Casilla casilla){
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                if(casilla.equals(this.casillas.get(i).get(j))){
                    return j;
                }
            }
        }
        return 5; //Fuera de rango
    }

    //Setters

    //Metodos

    @Override
    public String toString() {

        StringBuffer salida = new StringBuffer();

        for (int j = 0; j < 10; j++) {//Parte norte del tablero + casilla Ir a la Cárcel
            salida.append ("|" + this.casillas.get(2).get(j).getColor() +
                    (this.casillas.get(2).get(j).getNombre() + "             ").substring(0, 16)+
                    //Si el avatar esta en esta casilla se imprime
                    AvatarEnCasilla(this.casillas.get(2).get(j)) +
                    Valores.RESET + "|");
            if (j == 9) {
                salida.append("|" + this.casillas.get(3).get(0).getColor() +
                        (this.casillas.get(3).get(0).getNombre() + "          ").substring(0, 16)+
                        AvatarEnCasilla(this.casillas.get(3).get(0)) +
                        Valores.RESET + "|\n");
            }
        }

        for (int i = 9; i >= 1; i--) {//Este y Oeste del tablero
            salida.append("|" + this.casillas.get(1).get(i).getColor() +
                    (this.casillas.get(1).get(i).getNombre() + "                  ").substring(0, 16) +
                    AvatarEnCasilla(this.casillas.get(1).get(i)) +
                    Valores.RESET + "|");

            salida.append(String.format(Valores.FONDO + "%" + ((32*8)+18) + "s", "" + Valores.RESET));

            salida.append("|" + this.casillas.get(3).get(10 - i).getColor() +
                    (this.casillas.get(3).get(10 - i).getNombre() + "               ").substring(0, 16) +
                    AvatarEnCasilla(this.casillas.get(3).get(10 - i)) +
                    Valores.RESET + "|\n");
        }
        //Parte Sur del tableto + Cárcel y Salida
        salida.append("|" + this.casillas.get(1).get(0).getColor() +
                (this.casillas.get(1).get(0).getNombre() + "                  ").substring(0, 16) +
                AvatarEnCasilla(this.casillas.get(1).get(0)) +
                Valores.RESET + "|");

        for (int i = 9; i >= 0; i--) {
            salida.append("|" + this.casillas.get(0).get(i).getColor() +
                    (this.casillas.get(0).get(i).getNombre() + "                  ").substring(0, 16) +
                    AvatarEnCasilla(this.casillas.get(0).get(i)) +
                    Valores.RESET + "|");
        }

        return salida.toString();
    }

    private String AvatarEnCasilla(Casilla casilla){

        StringBuffer aux = new StringBuffer();

        for(int i=0;i< this.avatares.size() ;i++){
            if(avatares.get(i).getJugador().getCasillaActual().equals(casilla)){
                aux.append(" &" + avatares.get(i).getSimbolo());
            }
        }
        aux.append(String.format("%"+ (12-aux.length()) + "s", " "));
        return aux.toString();
    }


    private float precioTotalSolares() {
        Casilla aux;
        float precioTotal = 0;
        //Iteramos sobre el arraylist de arraylist de casillas
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 10; j++) {
                //Comprobamos que la casilla sea de tipo solar
                aux = this.getCasilla(i, j);
                if (aux.getTipo().equals("Solar")) {
                    precioTotal += aux.getPrecio();
                }
            }
        //salida.appendln( "\n" + precioTotal + "\n");
        return precioTotal;
    }

    public void desplazarAvatar(Avatar avatar, int cantidadDesplazamiento){

        int coordenada = 0;
        int posicion = 0;
        //Recorremos el array de avatares en busca del que queremos mover
        for(int i = 0; i< this.avatares.size();i++){
            if(avatar.equals(this.avatares.get(i))){
                //Establecemos la posicion de la casilla en la que se encuentra
                posicion = getPosicionCasilla(avatar.getJugador().getCasillaActual());
                coordenada = getCoordenadaCasilla(avatar.getJugador().getCasillaActual());
                //hacemos el modulo porque no puede ser mayor que 10, ya que las secciones N S E O tienen 10 casillas
                //Si es mayor que 10, le sumamos uno a coordenada

                if( (posicion + cantidadDesplazamiento) > 9){
                    posicion = (posicion+cantidadDesplazamiento)%10;
                    if((posicion+cantidadDesplazamiento)>20){ //Si se da que esta en la posicion 9 y sacamos un 12 tendriamos que sumar 2
                        coordenada +=2;
                    }
                    else{
                        coordenada+=1;
                    }
                }
                else{
                    posicion+=cantidadDesplazamiento;
                }

                this.avatares.get(i).getJugador().setCasillaActual( this.casillas.get(coordenada).get(posicion));
            }
        }
    }

    public void avataresEnSalida(){
        //Ponemos los avatares en la casilla de salida
        for(int i=0;i<this.avatares.size();i++){
            this.avatares.get(i).getJugador().setCasillaActual( this.casillas.get(0).get(0));
            this.avatares.get(i).getJugador().setFortuna(precioTotalSolares()/3);
        }
    }


    //Cuando TODOS los jugadores realizan 4 vueltas incrementamos el precio de las casillas
    public void incrementoPrecioCasillas(){
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                if(this.casillas.get(i).get(j).getDisponibilidad() && this.casillas.get(i).get(j).getTipo().equals("Solar")){
                    this.casillas.get(i).get(j).setPrecio( this.casillas.get(i).get(j).getPrecio() * 1.05f);
                }
            }
        }

    }

    public void refrescarTablero(Turno turno){
        float bonusPorVuelta = 0, menorNumeroVueltas = 0;
        //Comprobar si los jugadores llevan 4 vueltas para incrementar los precios
        System.out.println(this);
        if(this.avatares.get(turno.getTurno()-1).getJugador().getCasillaActual().getNombre().equals("Salida")){
            this.avatares.get(turno.getTurno()-1).getJugador().setNumeroVueltas(1); //Le suma 1 al numero vueltas al jugador
            if(this.avatares.get(turno.getTurno()-1).getJugador().getNumeroVueltas() > 0){
                //Sumamos la media del valor total de los solares a la fortuna del jugador
                this.avatares.get(turno.getTurno()-1).getJugador().setFortuna(precioTotalSolares()/22, 1);
                //Comprobamos que jugador tiene menos vueltas
                for(int i=0;i<this.avatares.size();i++){
                    if((this.avatares.get(i).getJugador().getNumeroVueltas() > menorNumeroVueltas)){
                        menorNumeroVueltas = this.avatares.get(i).getJugador().getNumeroVueltas();
                    }
                }
                //Comprobamos que el usuario con menor numero de vueltas es modulo de 4 y restringimos a una ejecucion por multiplo
                if((menorNumeroVueltas % 4) == 0 && menorNumeroVueltas != 4*(incrementosRealizados+1)){

                }
            }
        }

        //System.out.println(this.avatares.get(turno.getTurno()-1).getJugador().getNumeroVueltas());
        //Imprimir tablero de nuevo con todos los cambios
    }
}