/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablero;

import monopoly.Avatar;
import monopoly.Edificaciones;
import monopoly.Jugador;
import monopoly.Valores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author christiancp
 */
public class Casilla {
    //Atributos
    private String tipo; //solar, transporte, impuestos, suerte, comunidad, servicios, carcel, parking, salida, ir  a la carcel
    private String color;
    private int grupo;
    private String nombre;
    private double precio;
    private Jugador jugadorQueTieneLaCasilla;
    private boolean sePuedeComprar;
    private double alquiler;

    private boolean hipotecada;
    private double precioHipoteca;
    private int numeroCasas;
    private int numeroHoteles;
    private int numeroPiscinas;
    private int numeroPistasDep;
    private double bote;
    private HashMap<String, Edificaciones> edificaciones;
    private HashMap<Jugador, Integer> vecesCaidoEnEstaCasilla;

    Random rand;



    public Casilla(){
        this.tipo = "";
        this.color = Valores.NEGRO;
        this.nombre = "";
        this.precio = 0;
        jugadorQueTieneLaCasilla = null;
        this.sePuedeComprar = false;
        this.numeroCasas=0;
        edificaciones = new HashMap<>();
        vecesCaidoEnEstaCasilla = new HashMap<>();
        int numeroCasas = 0;
        int numeroHoteles = 0;
        int numeroPiscinas = 0;
        int numeroPistasDep = 0;
    }
    
    public Casilla(String tipo, String color, String nombre, float precio){
        this.tipo = tipo;
        this.color = color;
        this.nombre = nombre;
        this.precio = precio;
        if(tipo.equals("Solar") || tipo.equals("Transporte")){
            this.sePuedeComprar = true;
        }
        edificaciones = new HashMap<>();
        vecesCaidoEnEstaCasilla = new HashMap<>();
        this.precioHipoteca = this.precio / 2;
        int numeroCasas = 0;
        int numeroHoteles = 0;
        int numeroPiscinas = 0;
        int numeroPistasDep = 0;
    }

    public HashMap<String, Edificaciones> getEdificaciones() {
        return edificaciones;
    }

    public Jugador getJugadorQueTieneLaCasilla() {
        return jugadorQueTieneLaCasilla;
    }
    public String getTipo() {
        return tipo;
    }
    
    public int getGrupo(){
        return grupo;
    }

    public String getColor() {
        return color;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        if(this.tipo == "Transportes" || this.tipo == "Solar" || this.tipo == "Impuestos" || this.tipo == "Servicio"){
            return this.precio;
        }
        else{
            return 0;
        }
    }

    public double getHipoteca(){
        
        if(this.tipo.equals("Solar")){
            return (Valores.PRECIOINICIALGRUPO1*(Math.pow(1.3f,(grupo-1))))/2;
        }
        else if(this.tipo.equals("Servicio")){
            return Valores.PRECIOSERVICIOS/2;
        }
        else if(this.tipo.equals("Transporte")){
            return Valores.PRECIOTRANSPORTES/2;
        }
        else{
            return 0;
        }
    }

    public boolean getHipotecada(){
        return this.hipotecada;
    }

    public double getPrecioHipoteca(){
        return this.precio/2;
    }

    public boolean getDisponibilidad(){
        return this.sePuedeComprar;
    }

     //devolvemos el nombre del jugador que posee la casilla en caso de que no esté disponible
    public Jugador getPropietario(ArrayList<Avatar> avatares){

        if(this.sePuedeComprar==false){
            for(int k=0;k<avatares.size();k++){//buscamos que jugador posee la propiedad
                for(int j=0;j<avatares.get(k).getJugador().getPropiedades().size();j++){
                    if(avatares.get(k).getJugador().getPropiedades().get(j).getNombre().equals(this.nombre)){
                        return avatares.get(k).getJugador();
                    }
                }
            }
        }
        return null;
    }

    //accedemos a la cantidad de dinero que hay en el bote del Parking
    public double getBote(){
        return this.bote;
    }
    
    //contamos cuantos jugadores hay en la casilla
    public StringBuffer getJugadoresCasilla(ArrayList<Avatar> avatares){
        
        StringBuffer jugadoresCasilla=new StringBuffer();
        
        System.out.print("[");
        for(int i=0;i<avatares.size();i++){
            if(avatares.get(i).getJugador().getCasillaActual().getNombre().equals(this.nombre)){
                jugadoresCasilla.append(avatares.get(i).getJugador().getNombreJugador()+" ");
            }
        }
        System.out.println("]");
        
        return jugadoresCasilla;
    }
    
    //accedemos al precio de alquiler de esa casilla (siempre solar)
    public double getAlquiler(){//hay que revisar para que no aumente el 5%
        return this.alquiler;//10% de su precio inicial
    }
    
    public int getNumeroCasas(){
        return this.numeroCasas;
    }
    
    public int getNumeroHoteles(){
        return this.numeroHoteles;
    }
    
    public int getNumeroPiscinas(){
        return this.numeroPiscinas;
    }
    
    public int getNumeroPistasDep(){
        return this.numeroPistasDep;
    }

    public int getVeces(Jugador jugador){
        return this.vecesCaidoEnEstaCasilla.get(jugador);
    }

    public int getNumeroEdificaciones(){
        return (this.numeroCasas + this.numeroHoteles + this.numeroPistasDep + this.numeroPiscinas);
    }
    //Setters

    //No hay setter para numero de edificaciones porque es la suma de los tipos de edificaciones ya construidos

    public void setHipotecada(boolean hipotecada){
        this.hipotecada = hipotecada;
    }

    public void setAlquiler(double alquiler) {
        this.alquiler = alquiler;
    }

    public void setAlquiler(double alquiler, int op){
        if(op == 1) this.alquiler += alquiler;
        else this.alquiler -= alquiler;
    }

    public void setVeces(Jugador jugador){

        if(this.vecesCaidoEnEstaCasilla.containsKey(jugador)){
            //Sumamos una vez a caido en casilla
            this.vecesCaidoEnEstaCasilla.put(jugador, this.vecesCaidoEnEstaCasilla.get(jugador) + 1);
        }else{ //Si no se encuentra en el hashmap, lo añadimos y le establecemos las veces a uno
            this.vecesCaidoEnEstaCasilla.put(jugador, 0);
        }

    }

    public void setJugadorQueTieneLaCasilla(Jugador jugadorQueTieneLaCasilla) {
        this.jugadorQueTieneLaCasilla = jugadorQueTieneLaCasilla;
    }

    public void setGrupo(int grupo){
        this.grupo = grupo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setColor(String color){
        this.color=  color;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setPrecio(int grupo) {
        this.precio= (float) (Valores.PRECIOINICIALGRUPO1*(Math.pow(1.3f,(grupo-1))));
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    public void setDisponibilidad(boolean disponibilidad){
        this.sePuedeComprar = disponibilidad;
    }
    
    public void setCasas(int numeroCasas){
        this.numeroCasas+=numeroCasas;
    }

    public void setPrecioHipoteca(){
        precioHipoteca = this.precio/2;
    }
    
    //establecemos la cantidad de dinero que hay en el Parking
    public void setBote(double bote){
        if(bote==0){
            this.bote=0;
        }
        else{
            this.bote+=bote;
        }
    }

    public boolean setEdificaciones(Edificaciones edificio){
        int maxGrupo2 = 2;
        int maxGrupo3 = 3;

        if( (this.grupo == 1) || (this.grupo == 7) ) { //Grupos de 2 casillas

            return edificarAuxiliar(edificio, maxGrupo2);

        }
        else{
            return edificarAuxiliar(edificio, maxGrupo3);
        }
    }

    public void deleteEdificaciones(Edificaciones ed){
        Iterator it = edificaciones.values().iterator();
        while(it.hasNext()){
            if(it.equals(ed)){
                it.remove();
            }
        }
    }

    private boolean edificarAuxiliar(Edificaciones edificio, int max){
        if((this.numeroPistasDep < max) && (this.numeroPiscinas<max)){
            if(edificio.getTipo().equals("casa") && this.numeroHoteles <max){ //Si tenemos menos de MAX hoteles, podemos construir hasta 4 casas
                if ((this.numeroCasas < Valores.MAXCASAS)) {
                    this.edificaciones.put(generarClave(), edificio);
                    this.numeroCasas++;
                    this.alquiler += edificio.getAlquiler();
                    return true;
                }
            }else if(numeroHoteles == max){ //Si tenemos 2 hoteles, solo podemos contruir 2 casas
                if (edificio.getTipo().equals("casa") && this.numeroCasas <= 2) {
                    this.edificaciones.put(generarClave(), edificio);
                    this.numeroCasas++;
                    this.alquiler += edificio.getAlquiler();
                    return true;
                }
            }
            else if (edificio.getTipo().equals("hotel") && (this.numeroCasas == 4) && (this.numeroHoteles<=max)) {
                this.numeroCasas = 0;
                this.edificaciones.put(generarClave(), edificio);
                this.numeroHoteles++;
                this.alquiler += edificio.getAlquiler();
                return true;
            }
            else if (edificio.getTipo().equals("piscina") && (this.numeroHoteles >= 1) && (this.numeroCasas >= 2)) {
                this.numeroPiscinas++;
                this.edificaciones.put(generarClave(), edificio);
                this.alquiler += edificio.getAlquiler();
                return true;
            }
            else if (edificio.getTipo().equals("pistaDep") && (this.numeroHoteles >= 2)) {
                this.numeroPistasDep++;
                this.edificaciones.put(generarClave(), edificio);
                this.alquiler += edificio.getAlquiler();
                return true;
            }
        }
        return false;
    }

    //Metodos
    @Override
    public String toString(){
        String aux;
        if(this.getTipo().equals("Solar") || this.getTipo().equals("Transportes") || this.getTipo().equals("Servicio") ) {
            aux = "{\nNombre: " + this.nombre;
            aux += "\n        -Tipo: " + this.tipo;
            aux += "\n        -Grupo: " + this.grupo;
            aux += "\n        -precio: " + this.precio;
            if (jugadorQueTieneLaCasilla != null) {
                aux += "\n       -Propietario: " + this.jugadorQueTieneLaCasilla;
            }
            aux += "\n       -Alquiler: " + getAlquiler();
            aux += "\n       -Valor hotel: " + "Imprimir valor hotel";
            aux += "\n       -Valor casa: " + "Imprimir valor casa";
            aux += "\n       -Valor piscina: " + "Imprimir valor piscina";
            if (sePuedeComprar) aux += Valores.VERDE + "\n        ->Se puede comprar\n\n" + Valores.RESET;
            else aux += Valores.ROJO + "\n        ->La propiedad no está disponible\n\n" + Valores.RESET;
            return aux;
        }
        else if(this.getTipo().contains("Impuesto")){
            aux = "tipo: " + this.tipo;
            aux += "a pagar" + this.precio;
        }

        return null;
    }

    private String generarClave(){ //Genera una clave aleatoria compuesta por 2 letras mayusculas
        StringBuffer res = new StringBuffer();
        int min= 65, max = 90;
        rand = new Random();

        int codigo1 = rand.nextInt(max + 1 - min);
        char ascii = (char) codigo1;
        int codigo2 = rand.nextInt(max + 1 - min);
        char ascii2 = (char) codigo2;

        return res.append(ascii).append(ascii2).toString();

    }
}
