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
    String tipo;
    String grupo;
    private Valores precio;
    boolean hipotecada;
    int numeroCasas;
    int numeroHoteles;
    
    public Casilla(String tipo){ 
        
    }
    
    //Getters
    public float getPrecio(){
        return this.precio.getValorPropiedad();
    }

    
    
    //Setters
    
    public void setPrecio(float precio){
        if(precio != 0){
            this.precio.setValorPropiedad(precio);
        }
    }
}
