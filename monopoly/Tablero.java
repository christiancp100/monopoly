package monopoly;
import java.util.ArrayList;

public class Tablero {
    
    public static final float precioInicialPropiedades = 500000000;
    
    //Atributos
    private ArrayList<Jugador> jugadores;
    private ArrayList<Casilla> casillas;
    private ArrayList<Tarjeta> tarjetas;
    private float precioTotalPropiedades;
    
    //Constructores
    public Tablero(){
        this.precioTotalPropiedades = precioInicialPropiedades; //Precio sujeto a cambios
    }
    //Getters
    
    public float getPrecioTotalPropiedades(){
        return this.precioTotalPropiedades;
    }
    
    //Setters
    
    //Metodos
    
    //Calcula el precio total de las propiedades sumando el atributo precio de cada casilla
    private float calcularPrecioTotalPropiedades(){
        float valorTotal=0;
        for(int i=0;i<40;i++){
            valorTotal+= casillas.get(i).getPrecio();
        }
        return valorTotal;
    }
    
    //Cuando TODOS los jugadores realizan 4 vueltas incrementamos el precio de las casillas
    public void incrementoPrecioCasillas(){
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
    }
}