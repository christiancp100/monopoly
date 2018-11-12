package tablero;
import monopoly.*;

import java.util.ArrayList;

public class Tablero {

    //Atributos
    private ArrayList<Avatar> avatares;
    private ArrayList<ArrayList<Casilla>> casillas;
    private ArrayList<Tarjeta> tarjetas;
    private Jugador banca;
    private Dados dados;
    private Turno turno;
    private boolean partidaIniciada;

    private int incrementosRealizados;


    //Constructores

    public Tablero(ArrayList<Avatar> avatares,Dados dados, Turno turno) {

        this.partidaIniciada = false;

        this.incrementosRealizados = 0;

        this.avatares = avatares;
        
        this.dados=dados;

        this.turno = turno;

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
                        } 
                        else {
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
                else{//Las creamos con disponibilidad de compra y con el precio inicial
                    if(this.casillas.get(i).get(j).getTipo().equals("Transportes")){
                        this.casillas.get(i).get(j).setDisponibilidad(true);
                        this.casillas.get(i).get(j).setPrecio(Valores.PRECIOTRANSPORTES);
                    }
                    if(this.casillas.get(i).get(j).getTipo().equals("Servicio")){
                        this.casillas.get(i).get(j).setDisponibilidad(true);
                        this.casillas.get(i).get(j).setPrecio(Valores.PRECIOSERVICIOS);
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
    
    public Jugador getBanca(){
        return this.banca;
    }

    public Casilla getCasilla(int i, int j){
        return this.casillas.get(i).get(j);
    }

    public ArrayList<Avatar> getAvatares(){
        return this.avatares;
    }

    public boolean getPartidaIniciada(){ return this.partidaIniciada;}

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

    public void setPartidaIniciada(boolean iniciada){
        this.partidaIniciada = iniciada;
    }

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


    public float precioTotalSolares() {
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

        int coordenadaAntes = 0, coordenadaMovida =0;
        int coordenada = 0;
        int posicion = 0;


        //Recorremos el array de avatares en busca del que queremos mover
        for(int i = 0; i< this.avatares.size();i++){
            //Comprobamos que no lleve 3 dobles
            if(avatar.equals(this.avatares.get(i))){
                if(this.dados.getRepetidos() == 3){
                    avatar.getJugador().setCasillaActual(this.casillas.get(3).get(0));
                    this.avatares.get(i).getJugador().setCasillaActual(this.casillas.get(1).get(0));
                    this.avatares.get(i).getJugador().setEstarCarcel(true);
                }
                else{
                //Establecemos la posicion de la casilla en la que se encuentra
                posicion = getPosicionCasilla(avatar.getJugador().getCasillaActual());
                coordenada = getCoordenadaCasilla(avatar.getJugador().getCasillaActual());
                coordenadaAntes = getCoordenadaCasilla(avatar.getJugador().getCasillaActual());

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

                this.avatares.get(i).getJugador().setCasillaActual(this.casillas.get(coordenada%4).get(posicion));
                coordenadaMovida = coordenada%4;

                //IR A LA CARCEL
                //comprobamos si la nueva casilla es del tipo IrCarcel y en ese caso desplazamos al jugador a la cárcel
                if(this.avatares.get(i).getJugador().getCasillaActual().getTipo().equals("IrCarcel")){
                    System.out.println("El jugador a caído en IrCarcel y se desplaza a la casilla Carcel.\n");
                    this.avatares.get(i).getJugador().setCasillaActual(this.casillas.get(1).get(0));
                    this.avatares.get(i).getJugador().setEstarCarcel(true);
                }

                //CASILLA PARKING
                else if(this.avatares.get(i).getJugador().getCasillaActual().getTipo().equals("Parking")){
                    //comprobamos que haya dinero en el parking y se lo damos al usuario, si es 0 se suma 0
                    this.avatares.get(i).getJugador().setFortuna(this.avatares.get(i).getJugador().getCasillaActual().getBote(),1);

                }

                //CASILLA IMPUESTOS
                else if(this.avatares.get(i).getJugador().getCasillaActual().getTipo().equals("Impuestos")) {
                    if (this.avatares.get(i).getJugador().getCasillaActual().getNombre().contains("1")) {
                        if (this.avatares.get(i).getJugador().getFortuna() >= Valores.TASAIMPUESTOS1) {
                            this.avatares.get(i).getJugador().setFortuna(Valores.TASAIMPUESTOS1, -1);
                            this.casillas.get(2).get(0).setBote(Valores.TASAIMPUESTOS1);
                        } else {
                            System.out.println("El jugador no puede permitirse pagar y se declara en bancarrota");
                            this.avatares.remove(i);
                            this.turno.setNumeroJugadores(-1);
                        }
                    } else {
                        if (this.avatares.get(i).getJugador().getFortuna() >= Valores.TASAIMPUESTOS2) {
                            this.avatares.get(i).getJugador().setFortuna(Valores.TASAIMPUESTOS2, -1);
                            this.casillas.get(2).get(0).setBote(Valores.TASAIMPUESTOS2);
                        } else {
                            System.out.println("El jugador no puede permitirse pagar y se declara en bancarrota");
                            this.avatares.remove(i);
                            this.turno.setNumeroJugadores(-1);
                        }
                    }

                }
                //ALQUILER CASILLAS CON DUEÑO (CASILLAS DE SERVICIO,SOLAR O TRANSPORTE
                //comprobamos si la nueva casilla en la que se situa el jugador pertenece a algun usuario
                else if(this.avatares.get(i).getJugador().getCasillaActual().getDisponibilidad()==false) {//falta meter en caso de que esté hipotecada
                    //si el propietario posee todas las casillas de un grupo entonces cobra el doble
                    if (this.avatares.get(i).getJugador().getCasillaActual().getTipo().equals("Solar")) {
                        if (this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).poseerGrupo(this.avatares.get(i).getJugador().getCasillaActual().getGrupo()) == true) {
                            if (this.avatares.get(i).getJugador().getFortuna() >= this.avatares.get(i).getJugador().getCasillaActual().getAlquiler() * 2) {

                                this.avatares.get(i).getJugador().setFortuna(((float) this.avatares.get(i).getJugador().getCasillaActual().getAlquiler() * 2), -1);
                                this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).setFortuna(((float) this.avatares.get(i).getJugador().getCasillaActual().getAlquiler() * 2), 1);
                            } else {
                                System.out.println("El jugador no puede permitirse pagar y se declara en bancarrota");
                                this.avatares.remove(i);
                                this.turno.setNumeroJugadores(-1);
                            }
                        } else {
                            if (this.avatares.get(i).getJugador().getFortuna() >= (float) this.avatares.get(i).getJugador().getCasillaActual().getAlquiler()) {
                                this.avatares.get(i).getJugador().setFortuna(((float) this.avatares.get(i).getJugador().getCasillaActual().getAlquiler()), -1);
                                this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).setFortuna(((float) this.avatares.get(i).getJugador().getCasillaActual().getAlquiler()), 1);
                            } else {
                                System.out.println("El jugador no puede permitirse pagar y se declara en bancarrota");
                                this.avatares.remove(i);
                                this.turno.setNumeroJugadores(-1);
                            }
                        }
                    } else if (this.avatares.get(i).getJugador().getCasillaActual().getTipo().equals("Servicio")) {
                        if (this.casillas.get(1).get(2).getPropietario(this.avatares).getNombreJugador().equals(this.casillas.get(1).get(2).getPropietario(this.avatares).getNombreJugador())) {

                            if (this.avatares.get(i).getJugador().getFortuna() >= (10 * Valores.FACTORSERVICIO * this.dados.getValorSuma())) {
                                this.avatares.get(i).getJugador().setFortuna(10 * Valores.FACTORSERVICIO * this.dados.getValorSuma(), -1);
                                this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).setFortuna(10 * Valores.FACTORSERVICIO * this.dados.getValorSuma(), 1);
                            } else {
                                System.out.println("El jugador no puede permitirse pagar y se declara en bancarrota");
                                this.avatares.remove(i);
                                this.turno.setNumeroJugadores(-1);
                            }
                        } else {
                            if (this.avatares.get(i).getJugador().getFortuna() >= (4 * Valores.FACTORSERVICIO * this.dados.getValorSuma())) {
                                this.avatares.get(i).getJugador().setFortuna(4 * Valores.FACTORSERVICIO * this.dados.getValorSuma(), -1);
                                this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).setFortuna(4 * Valores.FACTORSERVICIO * this.dados.getValorSuma(), 1);
                            } else {
                                System.out.println("El jugador no puede permitirse pagar y se declara en bancarrota");
                                this.avatares.remove(i);
                                this.turno.setNumeroJugadores(-1);
                            }
                        }
                    }
                    else if(this.avatares.get(i).getJugador().getCasillaActual().getTipo().equals("Transportes")){
                    } else if (this.avatares.get(i).getJugador().getCasillaActual().getTipo().equals("Transportes")) {
                        //comprobamos cuantas casillas de transporte posee el jugador que tiene la casilla en la que cae el jugador actual
                        int p = 0;
                        double factor = 1;

                        //llamamos a la funcion que cuenta cuantas casillas de transporte posee el usuario
                        p = poseerTransportes();

                        //dependiendo del numero de casillas que se posea se cobra una tasa u otra.
                        if (p == 0) factor = 0.25;
                        if (p == 1) factor = 0.5;
                        if (p == 2) factor = 0.75;
                        if (this.avatares.get(i).getJugador().getFortuna() >= (Valores.OPERACIONTRANSPORTE * factor)) {
                            this.avatares.get(i).getJugador().setFortuna(Valores.OPERACIONTRANSPORTE * factor, -1);
                            this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).setFortuna(Valores.OPERACIONTRANSPORTE * factor, 1);
                        } else {
                            System.out.println("El jugador no puede permitirse pagar y se declara en bancarrota");
                            this.avatares.remove(i);
                            this.turno.setNumeroJugadores(-1);
                        }
                    }
                }
                }

                if( (coordenadaAntes == 2 || coordenadaAntes == 3) && (coordenadaMovida == 0 || coordenadaMovida == 1)  ){
                    this.avatares.get(i).getJugador().setNumeroVueltas(1);
                    this.avatares.get(i).getJugador().setFortuna(Valores.PRECIOJUGADORVUELTA, 1);
                }

                for(Avatar avatare : avatares){
                    if(avatare.getJugador().getNumeroVueltas() >= 4*(incrementosRealizados+1)){
                        this.incrementoPrecioCasillas();
                        this.incrementosRealizados++;
                    }
                }
            }
        }

    }

    public void comprarPropiedad(String nombrePropiedad){

        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){

                if(this.getCasilla(i,j).getNombre().equals(nombrePropiedad)){
                    
                    if(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre().equals(nombrePropiedad)) {

                        if (this.getCasilla(i,j).getDisponibilidad() == true) {
                            if (this.getAvatares().get(this.turno.getTurno()).getJugador().getFortuna() >= this.getCasilla(i,j).getPrecio()) {
                                //le quitamos el dinero al jugador, le asignamos la propiedad y la establecemos como no disponible
                                this.getAvatares().get(this.turno.getTurno()).getJugador().pagar(this.banca, this.getCasilla(i,j).getPrecio());
                                this.banca.cedePropiedad(this.getCasilla(i, j));
                                this.getBanca().cedePropiedad(this.getCasilla(i, j));
                                this.getAvatares().get(this.turno.getTurno()).getJugador().nuevaPropiedad(this.getCasilla(i, j)); //Ya pone disponibilidad a false
                                this.getCasilla(i,j).setJugadorQueTieneLaCasilla(this.getAvatares().get(this.turno.getTurno()).getJugador());
                                System.out.println("El jugador " + this.getAvatares().get(this.turno.getTurno()).getJugador().getNombreJugador()
                                        + " compra la casilla " + this.getCasilla(i, j).getNombre() + " por " + this.getCasilla(i,j).getPrecio());
                                System.out.println("Su fortuna actual es: " + this.getAvatares().get(this.turno.getTurno()).getJugador().getFortuna());
                            } else {
                                System.out.println("El jugador no tiene suficiente dinero para comprar la propiedad.\n");
                            }
                        } else {
                            System.out.println("La casilla es propiedad de: " + this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario(this.avatares).getNombreJugador());
                        }
                    }
                    else{
                        System.out.println("No se puede comprar porque el jugador no está situado en ella.\n");
                  
                    }
                }
            }
        }
    }

    public void avataresEnSalida(){
        //Ponemos los avatares en la casilla de salida
        for(int i=0;i<this.avatares.size();i++){
            this.avatares.get(i).getJugador().setCasillaActual(this.casillas.get(0).get(0));
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

    
    public void imprimirCasillasDisponibles(){
        
        for(int p=0;p<4;p++){//numero de filas
            for(int q=0;q<10;q++){//numero de columnas
                if(this.getCasilla(p,q).getDisponibilidad()==true){
                    System.out.println("{");
                    System.out.println(this.getCasilla(p,q));
                    System.out.println("}");
                }
            }
        }
    }
    
    public int poseerTransportes(){
        
        int p=0;
        
        for(int i = 0; i< this.avatares.size();i++){

            for(int k=0;k<4;k++){
                for(int l=0;l<10;l++){
                    if(this.casillas.get(k).get(l).getTipo().equals("Transporte") && 
                            this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).getNombreJugador().equals(
                                    this.casillas.get(k).get(l).getPropietario(this.avatares).getNombreJugador())){
                        p++;
                    }
                }
            }
        }
        return p;
    }

}
