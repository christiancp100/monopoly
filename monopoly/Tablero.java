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

    public Tablero(ArrayList<Avatar> avatares, ArrayList<Jugador> jugadores) {
        //Hay aliasing, pero nos interesa tenerlo
        this.avatares = avatares;
        this.jugadores = jugadores;
        this.casillas = new ArrayList<>();

        for(int i=0;i<4;i++){
            this.casillas.add(new ArrayList<Casilla>());

            for(int j=0; j<10;j++){ //posicion (N,S,E,O)

                this.casillas.get(i).add(new Casilla());

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