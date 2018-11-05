/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;
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
    private float precio;
    private Avatar avatarEnEstaCasilla;
    private boolean sePuedeComprar;

    private boolean hipotecada;
    private int numeroCasas;
    private int numeroHoteles;



    public Casilla(){
        this.tipo = "";
        this.color = Valores.NEGRO;
        this.nombre = "";
        this.precio = 0;
        avatarEnEstaCasilla = null;
        this.sePuedeComprar = false;
    }
    
    public Casilla(String tipo, String color, String nombre, float precio){
        this.tipo = tipo;
        this.color = color;
        this.nombre = nombre;
        this.precio = precio;
        avatarEnEstaCasilla = null;
        if(tipo.equals("Solar") || tipo.equals("Transporte")){
            this.sePuedeComprar = true;
        }
    }


    public String getTipo() {
        return tipo;
    }

    public String getColor() {
        return color;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        if(this.tipo == "Transporte" || this.tipo == "Solar" || this.tipo == "Impuestos"){
            return this.precio;
        }
        else{
            return 0;
        }
    }

    public boolean getDisponibilidad(){
        return this.sePuedeComprar;
    }

    //Setters

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

    public void setPrecio(float precio){
        this.precio = precio;
    }

    public void setDisponibilidad(boolean disponibilidad){
        this.sePuedeComprar = disponibilidad;
    }


    //Metodos
    @Override
    public String toString(){
        String aux;

        aux = "Nombre: " + this.nombre;
        aux += "\n        -Tipo: " + this.tipo;
        aux += "\n        -Grupo: " + this.grupo;
        aux += "\n        -precio:" + this.precio;
        if(sePuedeComprar) aux+= Valores.VERDE +"\n        ->Se puede comprar\n\n" + Valores.RESET;
        else aux += Valores.ROJO + "\n        ->La propiedad no está disponible\n\n" + Valores.RESET;

        return aux;
    }
}
