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
    private String nombre;
    private float precio; //Futura modificacion aqui
    private boolean hipotecada;
    private int numeroCasas;
    private int numeroHoteles;

    public Casilla(){
        this.tipo = "";
        this.color = Valores.NEGRO;
        this.nombre = "";
        this.precio = 0;
    }
    
    public Casilla(String tipo, String color, String nombre, float precio){
        this.tipo = tipo;
        this.color = color;
        this.nombre = nombre;
        this.precio = precio;
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
        if(this.tipo == "transporte" || this.tipo == "solar" || this.tipo == "impuestos"){
            return precio;
        }
        else{
            return 0;
        }
    }

    //Setters

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setColor(String color){

        /*if(this.nombre == "Solar"){
            this.color = Valores.FONDO_BLANCO + color;
        }
        else{
            this.color = Valores.FONDO_BLANCO + Valores.NEGRO;
        }*/

        this.color=color;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setPrecio(float precio){
        this.precio = precio;
    }

}
