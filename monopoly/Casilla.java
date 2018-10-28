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
    private String grupo;
    private String nombre;
    private float precio; //Futura modificacion aqui
    private boolean hipotecada;
    private int numeroCasas;
    private int numeroHoteles;
    
    public Casilla(String tipo, String grupo, String nombre, float precio){
        this.tipo = tipo;
        this.grupo = grupo;
        this.nombre = nombre;
        this.precio = precio;
    }


    public String getTipo() {
        return tipo;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }


    
    
    //Setters
    
    public void setPrecio(float precio){
        if(precio != 0){
            this.precio = precio;
        }
    }
}
