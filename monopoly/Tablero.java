package monopoly;
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


    //Constructores

    public Tablero(ArrayList<Avatar> avatares) {

        this.avatares = avatares;

        this.casillas = new ArrayList<>();

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
                    this.casillas.get(i).get(j).setColor(Valores.NEGRO);
                }
            }
        }


        //Ponemos los avatares en la casilla de salida
        for(int i=0;i<this.avatares.size();i++){
            this.avatares.get(i).getJugador().setCasillaActual( this.casillas.get(0).get(0));
            this.avatares.get(i).getJugador().setFortuna(precioTotalSolares()/3);
        }

    }

    //Getters

    public Casilla getCasilla(int i, int j){
        return this.casillas.get(i).get(j);
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
                aux.append(Valores.VERDE +"&" + Valores.RESET + avatares.get(i).getSimbolo());
            }
            else{
                //aux.append(" ");
            }
        }
        aux.append(String.format("%"+ (12-aux.length()) + "s", ""));
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


    //Cuando TODOS los jugadores realizan 4 vueltas incrementamos el precio de las casillas
    /*public void incrementoPrecioCasillas(){
        int jugadoresCon4Vueltas = 0;
        for(int i=0; i<jugadores.size();i++){
            if(jugadores.get(i).getNumeroVueltas()%4 == 0){
                jugadoresCon4Vueltas++;
            }
        }
        if(jugadoresCon4Vueltas == jugadores.size()){
            for(int i=0;i<40;i++){
                //Incrementamos el precio de cada casilla en un 5%
                casillas.get(i).setPrecio(casillas.get(i).getPrecio()*1.05f);
            }
        }
    }
    
    public void refrescarTablero(){
        //Comprobar si los jugadores llevan 4 vueltas para incrementar los precios
        this.incrementoPrecioCasillas();
        //Imprimir tablero de nuevo con todos los cambios
    }*/
}