/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablero;

import monopoly.Avatar;
import monopoly.Jugador;
import monopoly.Valores;

import java.util.ArrayList;

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

    private boolean hipotecada;
    private int numeroCasas;
    private int numeroHoteles;
    private int numeroPiscinas;
    private int numeroPistasDep;
    private double bote;



    public Casilla(){
        this.tipo = "";
        this.color = Valores.NEGRO;
        this.nombre = "";
        this.precio = 0;
        jugadorQueTieneLaCasilla = null;
        this.sePuedeComprar = false;
        this.numeroCasas=0;
    }
    
    public Casilla(String tipo, String color, String nombre, float precio){
        this.tipo = tipo;
        this.color = color;
        this.nombre = nombre;
        this.precio = precio;
        if(tipo.equals("Solar") || tipo.equals("Transporte")){
            this.sePuedeComprar = true;
        }
        this.numeroCasas=0;

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
        if(this.tipo == "Transporte" || this.tipo == "Solar" || this.tipo == "Impuestos"){
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

    public boolean getDisponibilidad(){
        return this.sePuedeComprar;
    }

     //devolvemos el nombre del jugador que posee la casilla en caso de que no esté disponible
    public Jugador getPropietario(ArrayList<Avatar> avatares){

        if(this.sePuedeComprar==false){
            for(int k=0;k<avatares.size();k++){//buscamos que jugador posee la propiedad
                for(int j=0;j<avatares.get(k).getJugador().getPropiedades().size();j++){
                    if(avatares.get(k).getJugador().getPropiedades().get(j).getNombre().equals(this.jugadorQueTieneLaCasilla.getNombreJugador())){
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
        return this.precio*0.1;//10% de su precio inicial
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

    //Setters


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
    
    //establecemos la cantidad de dinero que hay en el Parking
    public void setBote(double bote){
        if(bote==0){
            this.bote=0;
        }
        else{
            this.bote+=bote;
        }
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
}
