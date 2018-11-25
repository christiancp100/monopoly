/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablero;

import java.util.ArrayList;
import monopoly.Valores;

/**
 *
 * @author christiancp
 */
public class Tarjeta {
    
    private String tipo; //determina si la tarjeta es de Suerte o de Caja de Comunidad
    private int numTarjeta;
    private String mensaje;
    
    public Tarjeta(String tipo,int numTarjeta){
        if(tipo.contains("Suerte")){
            this.tipo=tipo;
        }
        else if(tipo.contains("Caja")){
            this.tipo=tipo;
        }
        this.numTarjeta=numTarjeta;
        this.mensaje=" ";
    }
    
    //getters
    public String getTipo(){
        return this.tipo;
    }
    
    public int getNumero(){
        return this.numTarjeta;
    }
    
    public String getMensaje(){
        return this.mensaje;
    }

    
    //setters
    public void setTarjeta(String tipo,int numTarjeta){
        this.tipo=tipo;
        this.numTarjeta=numTarjeta;
    }
    
    public void setMensajeSuerte(int numTarjeta){
        this.mensaje=Valores.TARJETAS_SUERTE[numTarjeta];
    }
    
    public void setMensajeCaja(int numTarjeta){
        this.mensaje=Valores.TARJETAS_CAJA[numTarjeta];
    }
    
    //Métodos
    
}
