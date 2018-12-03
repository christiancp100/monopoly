package tablero;
import monopoly.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Tablero {

    //Atributos
    private ArrayList<Avatar> avatares;
    private ArrayList<ArrayList<Casilla>> casillas;
    private ArrayList<Tarjeta> Suerte; //tarjetas de suerte
    private ArrayList<Tarjeta> Caja; //tarjetas de Caja de Comunidad
    private Jugador banca;
    private Dados dados;
    private Turno turno;
    private boolean partidaIniciada;

    private int incrementosRealizados;
    
    //variables estadisticasGlobales
    private int casillaMasVisitada;
    private int mayorNumVueltas;
    private double mayorPagoAlquileres;
    private double fortunaMaxima;
    private Jugador masActivo;
    private Jugador masVueltasTablero;
    private Casilla masFrecuentada;
    private Casilla masVecesPagos;
    private double fortunaGrupo;
    private int numeroGrupo;
    private ArrayList<Double> g;


    //Constructores

    public Tablero(ArrayList<Avatar> avatares, Dados dados, Turno turno) {

        this.Caja = new ArrayList();

        this.Suerte = new ArrayList();

        this.partidaIniciada = false;

        this.incrementosRealizados = 0;

        this.avatares = avatares;

        this.dados = dados;

        this.turno = turno;

        this.casillas = new ArrayList<>();

        this.banca = new Jugador();

        for (int i = 0; i < 4; i++) {
            this.casillas.add(new ArrayList<Casilla>());

            for (int j = 0; j < 10; j++) { //posicion (N,S,E,O)
                this.casillas.get(i).add(new Casilla());
                this.casillas.get(i).get(j).setNombre(Valores.nombres[j + 10 * i]); //Para recorrer el tablero del 0-39

                if (j == 0) { //Es el numero de casilla en cada posicion (N,S,E,O) del tablero
                    if (i == 0) {
                        this.casillas.get(i).get(j).setTipo("Salida");

                    }
                    if (i == 1) {
                        this.casillas.get(i).get(j).setTipo("Carcel");
                    }
                    if (i == 2) {
                        this.casillas.get(i).get(j).setTipo("Parking");
                    }
                    if (i == 3) {
                        this.casillas.get(i).get(j).setTipo("IrCarcel");
                    }
                    this.casillas.get(i).get(j).setGrupo(10); //Este es el grupo de las casillas especiales
                }
                if (j == 1) {
                    this.casillas.get(i).get(j).setTipo("Solar");
                }
                if (j == 2) {
                    if (i == 0 || i == 3) {
                        this.casillas.get(i).get(j).setTipo("Caja");
                    } else {
                        this.casillas.get(i).get(j).setTipo("Servicio");
                    }
                }
                if (j == 3) {
                    this.casillas.get(i).get(j).setTipo("Solar");
                }
                if (j == 4) {
                    if (i == 0 || i == 3) {
                        this.casillas.get(i).get(j).setTipo("Impuestos");
                    } else {
                        this.casillas.get(i).get(j).setTipo("Solar");
                    }
                }
                if (j == 5) {
                    this.casillas.get(i).get(j).setTipo("Transportes");
                }
                if (j == 6) {
                    this.casillas.get(i).get(j).setTipo("Solar");
                }
                if (j == 7) {
                    if (i != 1) {
                        this.casillas.get(i).get(j).setTipo("Suerte");
                    } else {
                        this.casillas.get(i).get(j).setTipo("Caja");
                    }
                }
                if (j == 8 || j == 9) {
                    this.casillas.get(i).get(j).setTipo("Solar");
                }
            }


        }
        //Establecemos los ATRIBUTOS de las Casillas
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.casillas.get(i).get(j).getTipo() == "Solar") {
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
                } else {//Las creamos con disponibilidad de compra y con el precio inicial
                    if (this.casillas.get(i).get(j).getTipo().equals("Transportes")) {
                        this.casillas.get(i).get(j).setDisponibilidad(true);
                        this.casillas.get(i).get(j).setPrecio(Valores.PRECIOTRANSPORTES);
                    }
                    if (this.casillas.get(i).get(j).getTipo().equals("Servicio")) {
                        this.casillas.get(i).get(j).setDisponibilidad(true);
                        this.casillas.get(i).get(j).setPrecio(Valores.PRECIOSERVICIOS);
                    }
                    this.casillas.get(i).get(j).setColor(Valores.NEGRO);
                }
            }
        }

        //Creamos la banca
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.casillas.get(i).get(j).getDisponibilidad()) {
                    this.banca.setPropiedades(this.casillas.get(i).get(j));
                }
            }
        }

        //CREAMOS LOS MAZOS DE LAS CARTAS SUERTE Y CAJA COMUNIDAD
        //número de tarjetas de cada mazo fijo
        for (int i = 0; i < 14; i++) {
            Tarjeta tarjeta = new Tarjeta("Suerte", i+1);
            tarjeta.setMensajeSuerte(i);
            this.Suerte.add(tarjeta);
        }
        for (int i = 0; i < 10; i++) {
            Tarjeta tarjeta = new Tarjeta("Caja", i+1);
            tarjeta.setMensajeCaja(i);
            this.Caja.add(tarjeta);
        }
    }

    //Getters

    public Jugador getBanca() {
        return this.banca;
    }

    public Casilla getCasilla(int i, int j) {
        return this.casillas.get(i).get(j);
    }

    public ArrayList<Avatar> getAvatares() {
        return this.avatares;
    }

    public boolean getPartidaIniciada() {
        return this.partidaIniciada;
    }

    private int getCoordenadaCasilla(Casilla casilla) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (casilla.equals(this.casillas.get(i).get(j))) {
                    return i;
                }
            }
        }
        return 5; //Fuera de rango
    }

    private int getPosicionCasilla(Casilla casilla) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (casilla.equals(this.casillas.get(i).get(j))) {
                    return j;
                }
            }
        }
        return 5; //Fuera de rango
    }

    //Setters

    public void setPartidaIniciada(boolean iniciada) {
        this.partidaIniciada = iniciada;
    }

    //Metodos

    @Override
    public String toString() {

        StringBuffer salida = new StringBuffer();

        for (int j = 0; j < 10; j++) {//Parte norte del tablero + casilla Ir a la Cárcel
            salida.append("|" + this.casillas.get(2).get(j).getColor() +
                    (this.casillas.get(2).get(j).getNombre() + "             ").substring(0, 16) +
                    //Si el avatar esta en esta casilla se imprime
                    AvatarEnCasilla(this.casillas.get(2).get(j)) +
                    Valores.RESET + "|");
            if (j == 9) {
                salida.append("|" + this.casillas.get(3).get(0).getColor() +
                        (this.casillas.get(3).get(0).getNombre() + "          ").substring(0, 16) +
                        AvatarEnCasilla(this.casillas.get(3).get(0)) +
                        Valores.RESET + "|\n");
            }
        }

        for (int i = 9; i >= 1; i--) {//Este y Oeste del tablero
            salida.append("|" + this.casillas.get(1).get(i).getColor() +
                    (this.casillas.get(1).get(i).getNombre() + "                  ").substring(0, 16) +
                    AvatarEnCasilla(this.casillas.get(1).get(i)) +
                    Valores.RESET + "|");

            salida.append(String.format(Valores.FONDO + "%" + ((32 * 8) + 18) + "s", "" + Valores.RESET));

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

    private String AvatarEnCasilla(Casilla casilla) {

        StringBuffer aux = new StringBuffer();

        for (int i = 0; i < this.avatares.size(); i++) {
            if (avatares.get(i).getJugador().getCasillaActual().equals(casilla)) {
                aux.append(" &" + avatares.get(i).getSimbolo());
            }
        }
        aux.append(String.format("%" + (12 - aux.length()) + "s", " "));
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

    public void desplazarAvatar(Avatar avatar, int cantidadDesplazamiento) {

        int coordenadaAntes = 0, coordenadaMovida = 0;
        int coordenada = 0;
        int posicion = 0;


        //Recorremos el array de avatares en busca del que queremos mover
        for (int i = 0; i < this.avatares.size(); i++) {
            //Comprobamos que no lleve 3 dobles
            if (avatar.equals(this.avatares.get(i))) {
                if (this.dados.getRepetidos() == 3) {
                    //avatar.getJugador().setCasillaActual(this.casillas.get(3).get(0));
                    this.avatares.get(i).getJugador().setCasillaActual(this.casillas.get(1).get(0));
                    this.avatares.get(i).getJugador().setEstarCarcel(true);
                } else {

                    if (cantidadDesplazamiento > 0) {
                        //Establecemos la posicion de la casilla en la que se encuentra
                        posicion = getPosicionCasilla(avatar.getJugador().getCasillaActual());
                        coordenada = getCoordenadaCasilla(avatar.getJugador().getCasillaActual());
                        coordenadaAntes = getCoordenadaCasilla(avatar.getJugador().getCasillaActual());

                        //hacemos el modulo porque no puede ser mayor que 10, ya que las secciones N S E O tienen 10 casillas
                        //Si es mayor que 10, le sumamos uno a coordenada

                        if ((posicion + cantidadDesplazamiento) > 9) {
                            posicion = (posicion + cantidadDesplazamiento) % 10;
                            if ((posicion + cantidadDesplazamiento) > 20) { //Si se da que esta en la posicion 9 y sacamos un 12 tendriamos que sumar 2
                                coordenada += 2;
                            } else {
                                coordenada += 1;
                            }
                        } else {
                            posicion += cantidadDesplazamiento;
                        }

                        this.avatares.get(i).getJugador().setCasillaActual(this.casillas.get(coordenada % 4).get(posicion));
                        coordenadaMovida = coordenada % 4;
                        //Aumentamos el numero de  veces que estuvo en esta casilla
                        this.avatares.get(i).getJugador().getCasillaActual().setVeces(this.avatares.get(i).getJugador());
                        //para las estadisticas de juego
                        this.avatares.get(i).getJugador().getCasillaActual().setVecesCasilla(1);
                    }
                    else if(cantidadDesplazamiento<0){
                        
                        //getCoordenada devuelve la i y getCasilla devuelve la j
                        posicion = getPosicionCasilla(avatar.getJugador().getCasillaActual());
                        coordenada = getCoordenadaCasilla(avatar.getJugador().getCasillaActual());
                        coordenadaAntes = getCoordenadaCasilla(avatar.getJugador().getCasillaActual());
                        
                        if ((posicion + cantidadDesplazamiento) < 0) {
                            posicion = 10-(-(posicion+cantidadDesplazamiento));
                            if(coordenada==0){
                                coordenada=3;
                            }else{
                                coordenada -= 1;
                            }
                            
                        } else {
                            posicion += cantidadDesplazamiento;
                        }

                        this.avatares.get(i).getJugador().setCasillaActual(this.casillas.get(coordenada % 4).get(posicion));
                        coordenadaMovida = coordenada % 4;
                        
                        //Aumentamos el numero de  veces que estuvo en esta casilla
                        this.avatares.get(i).getJugador().getCasillaActual().setVeces(this.avatares.get(i).getJugador());
                        this.avatares.get(i).getJugador().getCasillaActual().setVecesCasilla(1);

                    }

                    //IR A LA CARCEL
                    //comprobamos si la nueva casilla es del tipo IrCarcel y en ese caso desplazamos al jugador a la cárcel
                    if (this.avatares.get(i).getJugador().getCasillaActual().getTipo().equals("IrCarcel")) {
                        this.avatares.get(i).getJugador().setCasillaActual(this.casillas.get(1).get(0));
                        this.avatares.get(i).getJugador().setEstarCarcel(true);
                        this.avatares.get(i).getJugador().setVecesEnCarcel();
                    }

                    //CASILLA PARKING
                    else if (this.avatares.get(i).getJugador().getCasillaActual().getTipo().equals("Parking")) {
                        //comprobamos que haya dinero en el parking y se lo damos al usuario, si es 0 se suma 0
                        this.avatares.get(i).getJugador().setFortuna(this.avatares.get(i).getJugador().getCasillaActual().getBote(), 1);
                        this.avatares.get(i).getJugador().setPremiosInversionesBote(this.avatares.get(i).getJugador().getCasillaActual().getBote());

                    }

                    //CASILLA IMPUESTOS
                    else if (this.avatares.get(i).getJugador().getCasillaActual().getTipo().equals("Impuestos")) {
                        if (this.avatares.get(i).getJugador().getCasillaActual().getNombre().contains("1")) {
                            pagar(this.avatares.get(i).getJugador(), banca,Valores.TASAIMPUESTOS1 );
                            this.casillas.get(2).get(0).setBote(Valores.TASAIMPUESTOS1);
                        } else {
                            pagar(this.avatares.get(i).getJugador(), banca,Valores.TASAIMPUESTOS2 );
                            this.casillas.get(2).get(0).setBote(Valores.TASAIMPUESTOS2);
                        }

                    }

                    //CASILLA DE SUERTE O CAJA COMUNIDAD
                    else if (this.avatares.get(i).getJugador().getCasillaActual().getTipo().contains("Suerte")) {
                        barajar(Suerte);
                        //elegir una aleatoria entre las disponibles
                        System.out.println("Elija un número entre el 1 y el 14.");
                        Scanner reader = new Scanner(System.in);

                        System.out.println("\n->");
                        int n = reader.nextInt();
                        reader.reset();

                        cogerTarjetaSuerte(this.Suerte.get(n-1));//el indice del array no tiene pq coincidir con el numero de la tarjeta
                    } else if (this.avatares.get(i).getJugador().getCasillaActual().getTipo().contains("Caja")) {
                        barajar(Caja);
                        //elegir una aleatoria entre las disponibles
                        System.out.println("Elija un número entre el 1 y el 10.");
                        Scanner reader = new Scanner(System.in);

                        System.out.println("\n->");
                        int n = reader.nextInt();
                        reader.reset();

                        cogerTarjetaCaja(this.Caja.get(n-1));
                    }


                    //ALQUILER CASILLAS CON DUEÑO (CASILLAS DE SERVICIO,SOLAR O TRANSPORTE)
                    //comprobamos si la nueva casilla en la que se situa el jugador pertenece a algun usuario
                    else if (!this.avatares.get(i).getJugador().getCasillaActual().getDisponibilidad() &&  !this.avatares.get(i).getJugador().getCasillaActual().getHipotecada()) {
                        //si el propietario posee todas las casillas de un grupo entonces cobra el doble
                        if (this.avatares.get(i).getJugador().getCasillaActual().getTipo().equals("Solar")) {
                            if (this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).poseerGrupo(this.avatares.get(i).getJugador().getCasillaActual().getGrupo()) == true) {
                                this.avatares.get(i).getJugador().setPagoAlquileres( this.avatares.get(i).getJugador().getCasillaActual().getAlquiler() * 2);
                                this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).setCobroAlquileres(this.avatares.get(i).getJugador().getCasillaActual().getAlquiler() * 2);
                                this.pagar(this.avatares.get(i).getJugador(),this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares), this.avatares.get(i).getJugador().getCasillaActual().getAlquiler() * 2);
                                //this.avatares.get(i).getJugador().getCasillaActual().setVecesPagadas(1);

                            } else {
                                this.avatares.get(i).getJugador().setPagoAlquileres( this.avatares.get(i).getJugador().getCasillaActual().getAlquiler());
                                this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).setCobroAlquileres(this.avatares.get(i).getJugador().getCasillaActual().getAlquiler());
                                this.pagar(this.avatares.get(i).getJugador(),this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares), this.avatares.get(i).getJugador().getCasillaActual().getAlquiler());
                                //this.avatares.get(i).getJugador().getCasillaActual().setVecesPagadas(1);
                            }
                        } else if (this.avatares.get(i).getJugador().getCasillaActual().getTipo().equals("Servicio")) {
                            if (this.casillas.get(1).get(2).getPropietario(this.avatares).getNombreJugador().equals(this.casillas.get(1).get(2).getPropietario(this.avatares).getNombreJugador())) {
                                this.avatares.get(i).getJugador().setPagoAlquileres(10 * Valores.FACTORSERVICIO * this.dados.getValorSuma());
                                this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).setCobroAlquileres(10 * Valores.FACTORSERVICIO * this.dados.getValorSuma());
                                this.pagar(this.avatares.get(i).getJugador(),this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares), 10 * Valores.FACTORSERVICIO * this.dados.getValorSuma());
                                //this.avatares.get(i).getJugador().getCasillaActual().setVecesPagadas(1);

                            } else {
                                this.avatares.get(i).getJugador().setPagoAlquileres(4 * Valores.FACTORSERVICIO * this.dados.getValorSuma());
                                this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).setCobroAlquileres(4 * Valores.FACTORSERVICIO * this.dados.getValorSuma());
                                this.pagar(this.avatares.get(i).getJugador(),this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares), 4 * Valores.FACTORSERVICIO * this.dados.getValorSuma());
                                //this.avatares.get(i).getJugador().getCasillaActual().setVecesPagadas(1);
                            }
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
                            this.avatares.get(i).getJugador().setPagoAlquileres(Valores.OPERACIONTRANSPORTE * factor);
                            this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).setCobroAlquileres(Valores.OPERACIONTRANSPORTE * factor);
                            this.pagar(this.avatares.get(this.turno.getTurno()).getJugador(),this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares), Valores.OPERACIONTRANSPORTE * factor);
                        }
                    }
                }
                if ((coordenadaAntes == 2 || coordenadaAntes == 3) && (coordenadaMovida == 0 || coordenadaMovida == 1)) {
                    this.avatares.get(i).getJugador().setNumeroVueltas(1);
                    this.avatares.get(i).getJugador().setFortuna(Valores.PRECIOJUGADORVUELTA, 1);
                    this.avatares.get(this.turno.getTurno()).getJugador().setNumeroVueltas(1);
                }

                for (Avatar avatare : avatares) {
                    if (avatare.getJugador().getNumeroVueltas() >= 4 * (incrementosRealizados + 1)) {
                        this.incrementoPrecioCasillas();
                        this.incrementosRealizados++;
                    }
                }
            }
        }

    }

    public void comprarPropiedad(String nombrePropiedad) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {

                if (this.getCasilla(i, j).getNombre().equals(nombrePropiedad)) {

                    if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre().equals(nombrePropiedad)) {

                        if (this.getCasilla(i, j).getDisponibilidad() == true) {
                            if (this.getAvatares().get(this.turno.getTurno()).getJugador().getFortuna() >= this.getCasilla(i, j).getPrecio()) {
                                //le quitamos el dinero al jugador, le asignamos la propiedad y la establecemos como no disponible
                                this.getAvatares().get(this.turno.getTurno()).getJugador().pagar(this.banca, this.getCasilla(i, j).getPrecio());
                                this.banca.cedePropiedad(this.getCasilla(i, j));
                                this.getBanca().cedePropiedad(this.getCasilla(i, j));
                                this.getAvatares().get(this.turno.getTurno()).getJugador().nuevaPropiedad(this.getCasilla(i, j)); //Ya pone disponibilidad a false
                                this.getCasilla(i, j).setJugadorQueTieneLaCasilla(this.getAvatares().get(this.turno.getTurno()).getJugador());
                                this.getAvatares().get(this.turno.getTurno()).getJugador().setDineroIntertido(this.getCasilla(i, j).getPrecio());
                                System.out.println("El jugador " + this.getAvatares().get(this.turno.getTurno()).getJugador().getNombreJugador()
                                        + " compra la casilla " + this.getCasilla(i, j).getNombre() + " por " + this.getCasilla(i, j).getPrecio());
                                System.out.println("Su fortuna actual es: " + this.getAvatares().get(this.turno.getTurno()).getJugador().getFortuna());
                            } else {
                                System.out.println("El jugador no tiene suficiente dinero para comprar la propiedad.\n");
                                if(this.avatares.get(this.turno.getTurno()).getJugador().getPropiedades().size()>0){
                                    System.out.println("Sin embargo, posee propiedades, si lo desea puede escribir 'hipotecar [nombre_casilla]' y procederemos con la compra");
                                }
                            }
                        } else {
                            System.out.println("La casilla es propiedad de: " + this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario(this.avatares).getNombreJugador());
                        }
                    } else {
                        System.out.println("No se puede comprar porque el jugador no está situado en ella.\n");

                    }
                }
            }
        }
    }

    public void avataresEnSalida() {
        //Ponemos los avatares en la casilla de salida
        for (int i = 0; i < this.avatares.size(); i++) {
            this.avatares.get(i).getJugador().setCasillaActual(this.casillas.get(0).get(0));
            this.avatares.get(i).getJugador().setFortuna(precioTotalSolares() / 3);
        }
    }


    //Cuando TODOS los jugadores realizan 4 vueltas incrementamos el precio de las casillas
    public void incrementoPrecioCasillas() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.casillas.get(i).get(j).getDisponibilidad() && this.casillas.get(i).get(j).getTipo().equals("Solar")) {
                    this.casillas.get(i).get(j).setPrecio(this.casillas.get(i).get(j).getPrecio() * 1.05f);
                }
            }
        }

    }


    public void imprimirCasillasDisponibles() {

        for (int p = 0; p < 4; p++) {//numero de filas
            for (int q = 0; q < 10; q++) {//numero de columnas
                if (this.getCasilla(p, q).getDisponibilidad() == true) {
                    System.out.println("{");
                    System.out.println(this.getCasilla(p, q));
                    System.out.println("}");
                }
            }
        }
    }

    public int poseerTransportes() {

        int p = 0;

        for (int i = 0; i < this.avatares.size(); i++) {

            for (int k = 0; k < 4; k++) {
                for (int l = 0; l < 10; l++) {
                    if (this.casillas.get(k).get(l).getTipo().equals("Transporte") &&
                            this.avatares.get(i).getJugador().getCasillaActual().getPropietario(this.avatares).getNombreJugador().equals(
                                    this.casillas.get(k).get(l).getPropietario(this.avatares).getNombreJugador())) {
                        p++;
                    }
                }
            }
        }
        return p;
    }


    public boolean JugadorBancarrota(Jugador jugador, int cantidadAPagar) {

        double sumaValorPropiedades = 0;

        if (jugador.getFortuna() < cantidadAPagar) {
            System.out.println("El jugador no puede pagar esa cantidad de dinero");
            if (jugador.getPropiedades().size() > 0) {
                for (int i = 0; i < jugador.getPropiedades().size(); i++) {
                    sumaValorPropiedades += jugador.getPropiedades().get(i).getPrecio();
                }
            }
            //for(jugador.getPropiedades());
            this.avatares.remove(this.turno.getTurno());
        }

        return true;
    }


    //ACCIONES DE LAS TARJETAS (SEGÚN CUAL SE SAQUE DEL MAZO)
    public void barajar(ArrayList<Tarjeta> tarjetas) {
        //al barajar las cartas estas cambian su posicion en el vector, pero la variable numTarjeta se mantiene intacta
        Collections.shuffle(tarjetas);
    }

    public void cogerTarjetaSuerte(Tarjeta tarjeta) {

        int i= tarjeta.getNumero();

        //simepre imprimimos el mensaje de la tarjeta
        System.out.println(tarjeta.getMensaje());


        if (i == 1) {

            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 10; j++) {
                    if ((i == 2 && j > 5) || (i == 3)) {//si el jugador esta entre las casillas (2,6) y (3,9) => pasas por la salida
                        this.avatares.get(this.turno.getTurno()).getJugador().setFortuna(Valores.PRECIOJUGADORVUELTA, 1);
                        this.avatares.get(this.turno.getTurno()).getJugador().setNumeroVueltas(1);
                    }
                }
            }
            this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(2, 5));//cooredenadas aeropuerto
            desplazarAvatar(this.avatares.get(this.turno.getTurno()), 0);//invocamos a desplazar para hacer las comprobraciones de la nueva casilla
        } else if (i == 2) {
            //situamos al avatar en EEUU
            this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(1, 8));
            desplazarAvatar(this.avatares.get(this.turno.getTurno()), 0);//invocamos a desplazar para hacer las comprobraciones de la nueva casilla
        } else if (i == 3) {
            //recibe 5.000€
            this.avatares.get(this.turno.getTurno()).getJugador().setFortuna(5000, 1);
            this.avatares.get(this.turno.getTurno()).getJugador().setPremiosInversionesBote(5000);
        } else if (i == 4) {

            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 10; j++) {
                    if ((i == 2 && j > 6) || (i == 3)) {//si el jugador esta entre las casillas (2,7) y (3,9) => pasas por la salida
                        this.avatares.get(this.turno.getTurno()).getJugador().setFortuna(Valores.PRECIOJUGADORVUELTA, 1);
                        this.avatares.get(this.turno.getTurno()).getJugador().setNumeroVueltas(1);

                    }
                }
            }
            //situamos al jugador en China
            this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(2, 6));
            desplazarAvatar(this.avatares.get(this.turno.getTurno()), 0);//invocamos a desplazar para hacer las comprobraciones de la nueva casilla

        } else if (i == 5) {
            //mandamos al jugador a la carcel (va a Ir Carcel para que se apliquen las condiciones
            this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(3, 0));
            desplazarAvatar(this.avatares.get(this.turno.getTurno()), 0);//invocamos a desplazar para hacer las comprobraciones de la nueva casilla
        } else if (i == 6) {
            //recibes 100.000€
            this.avatares.get(this.turno.getTurno()).getJugador().setFortuna(100000, 1);
            this.avatares.get(this.turno.getTurno()).getJugador().setPremiosInversionesBote(100000);
        } else if (i == 7) {
            //pagas 15.000€
            pagar(this.avatares.get(this.turno.getTurno()).getJugador(), banca, 15000);
            this.casillas.get(2).get(0).setBote(15000);

        } else if (i == 8) {

            int casas=0,hoteles=0,piscinas=0,pistasDep=0;
            
            for(int j=0;j<4;j++){
                for(int k=0;k<10;k++){
                    if(this.getCasilla(j,k).getJugadorQueTieneLaCasilla().equals(this.avatares.get(this.turno.getTurno()).getJugador())){
                        casas+=this.getCasilla(j,k).getNumeroCasas();
                        hoteles+=this.getCasilla(j,k).getNumeroHoteles();
                        piscinas+=this.getCasilla(j,k).getNumeroPiscinas();
                        pistasDep+=this.getCasilla(j,k).getNumeroPistasDep();
                    }
                }
            }
            
            //el usuario paga 400€ por casita, 1.150€ por hotel, 600€ por piscina y 800€ por pista de deporte AL PARKING
            pagar(this.avatares.get(this.turno.getTurno()).getJugador(), banca, 
                    (casas*400)+(hoteles*1150)+(piscinas*600)+(800*pistasDep));
            this.casillas.get(2).get(0).setBote((casas*400)+(hoteles*1150)+(piscinas*600)+(800*pistasDep));

        } else if (i == 9) {

            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 10; j++) {
                    if ((i == 0 && j == 9) || (i == 3) || (i == 2) || (i == 1)) {//si el jugador esta entre las casillas (0,9) y (3,9) => pasas por la salida
                        this.avatares.get(this.turno.getTurno()).getJugador().setFortuna(Valores.PRECIOJUGADORVUELTA, 1);
                        this.avatares.get(this.turno.getTurno()).getJugador().setNumeroVueltas(1);
                    }
                }
            }
            //situamos al jugador en Kenia
            this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(0, 8));
            desplazarAvatar(this.avatares.get(this.turno.getTurno()), 0);//invocamos a desplazar para hacer las comprobraciones de la nueva casilla
        } else if (i == 10) {
            //le damos 2.500 a todos los jugadores menos el mismo (en caso de darlo no cambiaría la situación)
            for (int j = 0; j < this.avatares.size(); j++) {
                if (j != this.turno.getTurno()) {
                    this.avatares.get(j).getJugador().setFortuna(2500, 1);
                }
            }
            //le restamos al usuario la cantidad por el numero de jugadores
            //ponemos de destinatario la banca pq la funcion necesita un receptor, pero no es relevante
            pagar(this.avatares.get(this.turno.getTurno()).getJugador(), banca, (2500) * (this.avatares.size() - 1));

        } else if (i == 11) {
            //retrocede 3 casillas
            desplazarAvatar(this.avatares.get(this.turno.getTurno()), -3);
        } else if (i == 12) {
            //paga 15.000€
            pagar(this.avatares.get(this.turno.getTurno()).getJugador(), banca, 15000);
            this.casillas.get(2).get(0).setBote(15000);
            
        } else if (i == 13) {
            //recibe 150.000€
            this.avatares.get(this.turno.getTurno()).getJugador().setFortuna(150000, 1);
            this.avatares.get(this.turno.getTurno()).getJugador().setPremiosInversionesBote(150000);
        } else if (i == 14) {
            //desplazar a la casilla de transporte mas cercana
            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 10; j++) {
                    if ((i == 0 && j < 5) || (i == 3 && j >= 5)) {//antes que RENFE
                        this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(0, 5));
                    } else if ((i == 0 && j >= 5) || (i == 1 && j < 5)) {//antes que Puerto
                        this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(1, 5));
                    } else if ((i == 1 && j >= 5) || (i == 2 && j < 5)) {//antes que Aeropuerto
                        this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(2, 5));
                    } else if ((i == 2 && j >= 5) || (i == 3 && j < 5)) {//antes que NASA
                        this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(3, 5));
                    }
                }
            }
            desplazarAvatar(this.avatares.get(this.turno.getTurno()), 0);//invocamos a desplazar para hacer las comprobraciones de la nueva casilla
        }
    }

    public void cogerTarjetaCaja(Tarjeta tarjeta) {

        int i = tarjeta.getNumero();

        //simepre imprimimos el mensaje de la tarjeta
        System.out.println(tarjeta.getMensaje());

        if (i == 1) {
            pagar(this.avatares.get(this.turno.getTurno()).getJugador(), banca, 5000);
        } else if (i == 2) {
            //mandamos al jugador a la carcel (va a Ir Carcel para que se apliquen las condiciones
            this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(3, 0));
            desplazarAvatar(this.avatares.get(this.turno.getTurno()), 0);//invocamos a desplazar para hacer las comprobraciones de la nueva casilla
        } else if (i == 3) {
            //va a la salida
            this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(0, 0));
            this.avatares.get(this.turno.getTurno()).getJugador().setFortuna(Valores.PRECIOJUGADORVUELTA, 1);
            this.avatares.get(this.turno.getTurno()).getJugador().setNumeroVueltas(1);
            desplazarAvatar(this.avatares.get(this.turno.getTurno()), 0);//invocamos a desplazar para hacer las comprobraciones de la nueva casilla
        } else if (i == 4) {
            //cobra 200.000€
            this.avatares.get(this.turno.getTurno()).getJugador().setFortuna(200000, 1);
            this.avatares.get(this.turno.getTurno()).getJugador().setPremiosInversionesBote(200000);
        } else if (i == 5) {
            //paga 10.000€
            pagar(this.avatares.get(this.turno.getTurno()).getJugador(), banca, 10000);
            this.casillas.get(2).get(0).setBote(10000);
        } else if (i == 6) {
            //recibes 5.000€
            this.avatares.get(this.turno.getTurno()).getJugador().setFortuna(5000, 1);
            this.avatares.get(this.turno.getTurno()).getJugador().setPremiosInversionesBote(5000);
        } else if (i == 7) {
            //retrocede hasta E.Árabes
            this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(3, 8));
            desplazarAvatar(this.avatares.get(this.turno.getTurno()), 0);
        } else if (i == 8) {
            //le damos 2.000 a todos los jugadores menos el mismo (en caso de darlo no cambiaría la situación)
            for (int j = 0; j < this.avatares.size(); j++) {
                if (j != this.turno.getTurno()) {
                    this.avatares.get(j).getJugador().setFortuna(2000, 1);
                }
            }
            pagar(this.avatares.get(this.turno.getTurno()).getJugador(), banca, (2000) * (this.avatares.size() - 1));
        } else if (i == 9) {
            //recibe 10.000€
            this.avatares.get(this.turno.getTurno()).getJugador().setFortuna(10000, 1);
            this.avatares.get(this.turno.getTurno()).getJugador().setPremiosInversionesBote(10000);
        } else if (i == 10) {
            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 10; j++) {
                    if ((i == 2 && j > 6) || (i == 3)) {//si el jugador esta entre las casillas (2,7) y (3,9) => pasas por la salida
                        this.avatares.get(this.turno.getTurno()).getJugador().setFortuna(Valores.PRECIOJUGADORVUELTA, 1);
                        this.avatares.get(this.turno.getTurno()).getJugador().setNumeroVueltas(1);
                    }
                }
            }
            //situamos al jugador en Brasil
            this.avatares.get(this.turno.getTurno()).getJugador().setCasillaActual(this.getCasilla(1, 1));
            desplazarAvatar(this.avatares.get(this.turno.getTurno()), 0);//invocamos a desplazar para hacer las comprobraciones de la nueva casilla
        }
    }



    public void pagar(Jugador elQuePaga, Jugador elQueCobra, double deuda) {

        if(deuda <= elQuePaga.getFortuna()){
            elQuePaga.setFortuna(deuda, -1);
            elQueCobra.setFortuna(deuda, 1);
            if((this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Solar")
                    ||this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Servicio")
                    ||this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Transportes"))
                    && this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario(this.avatares).equals(elQueCobra)){
                this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().setVecesPagadas(deuda);
            }
        }else{
            if(elQuePaga.getPropiedades().size() >0){
                for(Casilla cas: elQuePaga.getPropiedades()){
                    if(!cas.getHipotecada()){
                        elQuePaga.hipotecar(cas);
                    }
                    if(deuda <= elQuePaga.getFortuna()){
                        elQuePaga.setFortuna(deuda, -1);
                        elQueCobra.setFortuna(deuda, 1);
                        if((this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Solar")
                                ||this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Servicio")
                                ||this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Transportes"))
                                && this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario(this.avatares).equals(elQueCobra)){
                            this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().setVecesPagadas(deuda);
                        }
                        break;
                    }
                }
            }else{
                System.out.println("El jugador no tiene dinero ni propiedades, se declara en bancarrota. La banca cubre su deuda.");
                System.out.println("El turno pasa al siguiente jugador");
                elQueCobra.setFortuna(deuda, 1);
                //No tiene dinero ni propiedades, se declara en bancarrota
                for (Casilla casilla : this.avatares.get(this.turno.getTurno()).getJugador().getPropiedades()) {
                    casilla.setJugadorQueTieneLaCasilla(banca);
                    casilla.setDisponibilidad(true);
                }
                this.avatares.remove(avatares.get(this.turno.getTurno()));
                this.turno.setNumeroJugadores(-1);
                this.dados.setNumeroTiradas(1);
                this.turno.setTurno(this.dados.getNumeroTiradas());
                this.dados.setRepetidos(0);
            }

        }


    }

    public Casilla getCasillaByName(String nombre){
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                if(this.casillas.get(i).get(j).getNombre().equals(nombre)){
                    return this.casillas.get(i).get(j);
                }
            }
        }
        return null;
    }
    
    public void estadisticasGlobales(){
        
        casillaMasVisitada=0;
        mayorNumVueltas=0;
        mayorPagoAlquileres=0;
        fortunaMaxima=0;
        masActivo=new Jugador();
        masVueltasTablero=new Jugador();
        masFrecuentada=new Casilla();
        masVecesPagos=new Casilla();
        fortunaGrupo=0;
        numeroGrupo=0;
        g=new ArrayList();
        double cantAux=0;
        
        //casilla más rentable (receptora de más alquileres)
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
               if(this.getCasilla(i,j).getTipo().contains("Solar")||this.getCasilla(i,j).getTipo().contains("Transportes")
                       ||this.getCasilla(i,j).getTipo().contains("Servicio")){
                    if(mayorPagoAlquileres<this.getCasilla(i,j).getVecesPagadas()){
                        mayorPagoAlquileres=this.getCasilla(i,j).getVecesPagadas();
                        masVecesPagos=this.getCasilla(i,j);
                    }
               }
            }
        }
        System.out.print("Casilla más rentable: "+masVecesPagos.getNombre());
        if(mayorPagoAlquileres!=0){
            for(int i=0;i<4;i++){
                for(int j=0;j<10;j++){
                   if(this.getCasilla(i,j).getTipo().contains("Solar")||this.getCasilla(i,j).getTipo().contains("Transportes")
                           ||this.getCasilla(i,j).getTipo().contains("Servicio")){
                        if(mayorPagoAlquileres==this.getCasilla(i,j).getVecesPagadas() 
                                && !this.getCasilla(i,j).equals(masVecesPagos)){
                            System.out.println(", "+this.getCasilla(i,j).getNombre());
                        }
                   }
                }
            }
        }
        System.out.println(".");
        
        //grupo más rentable (suma de la rentabilidad de sus casillas)
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                if(this.getCasilla(i,j).getTipo().equals("Solar")){
                    for(int k=0;k<8;k++){//hay 8 grupos
                        //tenemos un vector en el que cada posicion almacena la cant.total de un grupo
                        if(this.getCasilla(i,j).getGrupo()==(k+1) && this.getCasilla(i,j).getVecesPagadas()!=0){
                            cantAux=g.get(k);
                            g.set(k,cantAux+this.getCasilla(i,j).getVecesPagadas());
                        }
                    }
                }
            }
        }
        
        for(int i=0;i<g.size();i++){
            if(fortunaGrupo<g.get(i)){
                fortunaGrupo=g.get(i);
                numeroGrupo=(i+1);
            }
        }
        System.out.print("El grupo más rentable es: ");
        if(numeroGrupo==1){
            System.out.print("Negro");
        }else if(numeroGrupo==2){
            System.out.print("Cian");
        }else if(numeroGrupo==3){
            System.out.print("Magenta");
        }else if(numeroGrupo==4){
            System.out.print("Amarillo");
        }else if(numeroGrupo==5){
            System.out.print("Rojo");
        }else if(numeroGrupo==6){
            System.out.print("Blanco");
        }else if(numeroGrupo==7){
            System.out.print("Verde");
        }else if(numeroGrupo==8){
            System.out.print("Azul");
        }
        System.out.println(".");

                
        //casilla más frecuentada
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                if(casillaMasVisitada<this.getCasilla(i,j).getVecesCasilla()){
                  casillaMasVisitada=this.getCasilla(i,j).getVecesCasilla();
                  masFrecuentada=this.getCasilla(i,j);
                }
            }
        }
        System.out.print("Casilla más frecuentada: "+masFrecuentada.getNombre());

        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                    if(casillaMasVisitada==this.getCasilla(i,j).getVecesCasilla() && !this.getCasilla(i,j).equals(masFrecuentada)){
                        System.out.print(", "+this.getCasilla(i,j).getNombre());
                    }
            }
        }
        System.out.println(".");

        //jugador que dió más vueltas al tablero
        for(int i=0;i<this.avatares.size();i++){
            if(mayorNumVueltas<this.avatares.get(i).getJugador().getNumeroVueltas()){
                mayorNumVueltas=this.avatares.get(i).getJugador().getNumeroVueltas();
                masVueltasTablero=this.avatares.get(i).getJugador();
            }
        }
        System.out.print("Jugador que ha dado más vueltas al tablero: "+masVueltasTablero.getNombreJugador());

        for(int i=0;i<this.avatares.size();i++){
            if(mayorNumVueltas==this.avatares.get(i).getJugador().getNumeroVueltas() && !this.avatares.get(i).getJugador().equals(masVueltasTablero)){
                System.out.println(", "+this.avatares.get(i).getJugador().getNombreJugador());
            }
        }
        System.out.println(".");

        //jugador con la mayor fortuna (dinero+valor propiedades+valor edificios)
        for(int i=0;i<this.avatares.size();i++){
            //dinero del que dispone
            this.avatares.get(i).getJugador().setFortunaTotal(this.avatares.get(i).getJugador().getFortuna());
            
            //valor de las propiedades y de sus edificios
            for(int j=0;j<this.avatares.get(i).getJugador().getPropiedades().size();j++){
                
                //precio propiedad
                this.avatares.get(i).getJugador().setFortunaTotal(
                        this.avatares.get(i).getJugador().getPropiedades().get(j).getPrecio());
                
                //precio edificaciones
                if(!this.avatares.get(i).getJugador().getPropiedades().get(j).getEdificaciones().isEmpty()){
                    
                    for(Edificaciones ed : this.avatares.get(i).getJugador().getPropiedades().get(j).getEdificaciones().values()){
                        this.avatares.get(i).getJugador().setFortunaTotal(ed.getPrecio());
                    }
                }
                
                if(fortunaMaxima<this.avatares.get(i).getJugador().getFortunaTotal()){
                    fortunaMaxima=this.avatares.get(i).getJugador().getFortunaTotal();
                    masActivo=this.avatares.get(i).getJugador();
                }
            }
                   
        }
        
        System.out.print("El jugador más activo es:"+masActivo.getNombreJugador());
        
        for(int i=0;i<this.avatares.size();i++){
            if(fortunaMaxima==this.avatares.get(i).getJugador().getFortunaTotal() && !this.avatares.get(i).getJugador().equals(masActivo)){
                System.out.println(", "+this.avatares.get(i).getJugador().getNombreJugador());
            }
        }
        System.out.println(".");


    }
}
