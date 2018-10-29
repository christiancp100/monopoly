package monopoly;
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

    public Tablero(/*ArrayList<Avatar> avatares, ArrayList<Jugador> jugadores*/) {
        //Hay aliasing, pero nos interesa tenerlo
        //this.avatares = avatares;
        //this.jugadores = jugadores;
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
        //Establecemos los COLORES de los GRUPOS de Casillas
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                if(this.casillas.get(i).get(j).getTipo() == "Solar") {

                    if (i == 0) {
                        if (j < 5) { //Antes que la casilla de transporte
                            this.casillas.get(i).get(j).setColor(Valores.NEGRO);
                        } else {
                            this.casillas.get(i).get(j).setColor(Valores.CIAN);
                        }
                    }
                    if (i == 1) {
                        if (j < 5) { //Antes que la casilla de transporte
                            this.casillas.get(i).get(j).setColor(Valores.MAGENTA);
                        } else {
                            this.casillas.get(i).get(j).setColor(Valores.AMARILLO);
                        }
                    }
                    if (i == 2) {
                        if (j < 5) { //Antes que la casilla de transporte
                            this.casillas.get(i).get(j).setColor(Valores.ROJO);
                        } else {
                            this.casillas.get(i).get(j).setColor(Valores.BLANCO);
                        }
                    }
                    if (i == 3) {
                        if (j < 5) { //Antes que la casilla de transporte
                            this.casillas.get(i).get(j).setColor(Valores.VERDE);
                        } else {
                            this.casillas.get(i).get(j).setColor(Valores.AZUL);
                        }
                    }
                }
            }
        }
    }


    public void imprimir(){


        for(int j=0;j<10;j++){//Parte norte del tablero + casilla Ir a la Cárcel
            System.out.print("|"+ this.casillas.get(2).get(j).getColor() +
                    (this.casillas.get(2).get(j).getNombre()+"             ").substring(0,16)+ Valores.RESET +"|");
            if(j == 9){
                System.out.println("|" + this.casillas.get(3).get(0).getColor() +
                        (this.casillas.get(3).get(0).getNombre() + "          ").substring(0,16) + Valores.RESET+ "|");
            }
        }
        for(int i=9;i>=1;i--){//Este y Oeste del tablero
            System.out.print("|"+ this.casillas.get(1).get(i).getColor() +
                    (this.casillas.get(1).get(i).getNombre()+"                  ").substring(0,16)+ Valores.RESET + "|" );
            System.out.format("%162s", " ");
            System.out.print("|" + this.casillas.get(3).get(10-i).getColor() +
                    (this.casillas.get(3).get(10-i).getNombre() + "               ").substring(0, 16) + Valores.RESET + "|\n");
        }
        //Parte Sur del tableto + Cárcel y Salida
        System.out.print("|"+ this.casillas.get(1).get(0).getColor() +
                (this.casillas.get(1).get(0).getNombre()+"                  ").substring(0,16)+ Valores.RESET +"|");

        for(int i=9;i>=0;i--) {
            System.out.print("|"+ this.casillas.get(0).get(i).getColor() +
                    (this.casillas.get(0).get(i).getNombre()+"                  ").substring(0,16)+Valores.RESET+ "|");
        }
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