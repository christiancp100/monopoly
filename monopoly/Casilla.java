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

    private boolean hipotecada;
    private int numeroCasas;
    private int numeroHoteles;



    public Casilla(){
        this.tipo = "";
        this.color = Valores.NEGRO;
        this.nombre = "";
        this.precio = 0;
        avatarEnEstaCasilla = null;
    }
    
    public Casilla(String tipo, String color, String nombre, float precio){
        this.tipo = tipo;
        this.color = color;
        this.nombre = nombre;
        this.precio = precio;
        avatarEnEstaCasilla = null;
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
}
