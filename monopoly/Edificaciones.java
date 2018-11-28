/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import tablero.Casilla;

public class Edificaciones {

    private String tipo;
    private double precio;
    private Casilla casilla;
    private double alquiler;
    private boolean hipotecada;
    private double precioHipoteca;

    public Edificaciones(String nombre,Casilla casilla){

        this.hipotecada = false;

        if(nombre.equals("casa")){
            this.tipo ="casa";
            this.precio=casilla.getPrecio()*0.6;
            if(casilla.getNumeroCasas() == 0){
                this.alquiler = 5 * casilla.getAlquiler();
            }
            else if( casilla.getNumeroCasas() == 1){
                this.alquiler = 15 * casilla.getAlquiler();
            }
            else if( casilla.getNumeroCasas() == 2){
                this.alquiler = 35 * casilla.getAlquiler();
            }
            else if( casilla.getNumeroCasas() == 3){
                this.alquiler = 50 * casilla.getAlquiler();
            }

        }
        //mirar cuantos puede haber de cada tipo
        if(nombre.equals("hotel")){
            this.tipo ="hotel";
            this.precio=casilla.getPrecio()*0.6;
            this.alquiler = 70 * casilla.getAlquiler();
        }
        if(nombre.equals("piscina")){
            this.tipo ="piscina";
            this.precio=casilla.getPrecio()*0.4;
            this.alquiler = 25 * casilla.getPrecio();
        }
        if(nombre.equals("pista deportes")){
            this.tipo ="pista deportes";
            this.precio=casilla.getPrecio()*1.25;
            this.alquiler = 25*casilla.getPrecio();
        }

        this.precioHipoteca = this.precio/2;
    }

    public double getPrecio() {
        return this.precio;
    }
    public String getTipo(){
        return this.tipo;
    }

    public double getAlquiler(){
        return this.alquiler;
    }

    public double getPrecioHipoteca(){
        return this.precioHipoteca;
    }

    public Casilla getCasillaEdificio(){
        return this.casilla;
    }

    public boolean getHipotecada(){
        return this.hipotecada;
    }

    //Setters

    public void setHipotecada(boolean hipotecada){
        this.hipotecada = hipotecada;
    }


    public double alquilerEdificaciones(Casilla casilla){
                
            if(casilla.getNumeroCasas()==0){
                if(casilla.getNumeroHoteles()>0){
                    return (this.casilla.getAlquiler()*70);
                }
                return this.casilla.getAlquiler()*5;
            }
            else if(casilla.getNumeroCasas()==1){
                return this.casilla.getAlquiler()*15;
            }
            else if(casilla.getNumeroCasas()==2){
                return this.casilla.getAlquiler()*35;
            }
            else if(casilla.getNumeroCasas()==3){
                return this.casilla.getAlquiler()*50;
            }
            else if(casilla.getNumeroCasas()==4){
                if(casilla.getNumeroHoteles()==1){
                    return this.casilla.getAlquiler()*70;
                }
                else if(casilla.getNumeroPiscinas()==1 || casilla.getNumeroPistasDep()==1){
                    return this.casilla.getAlquiler()*25;
                }
            }
            return 0;
    }
    
    public double hipotecaEdificaciones(){
        return (this.precio/2);
    }   
}
