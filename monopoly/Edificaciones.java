/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly;

/**
 * 
 * @author María Caseiro <mariacaseiro24@gmail.com>
 */
public class Edificaciones {

    private String nombre;
    private double precio;
    private int numCasas=0;

    private Casilla casilla;
    
    public Edificaciones(String nombre,Casilla casilla){
        
        if(nombre.equals("casa")){
            this.nombre="casa";
            this.precio=casilla.getPrecio()*0.6;
            if(this.casilla.getNumeroCasas()>4){
                System.out.println("No puede construír más casas.");
            }
            else{
                this.casilla.setCasas(1);
            }
        }
        //mirar cuantos puede haber de cada tipo
        if(nombre.equals("hotel")){
            this.nombre="hotel";
            this.precio=casilla.getPrecio()*0.6;
        }
        if(nombre.equals("piscina")){
            this.nombre="piscina";
            this.precio=casilla.getPrecio()*0.4;
        }
        if(nombre.equals("pista deportes")){
            this.nombre="pista deportes";
            this.precio=casilla.getPrecio()*1.25;
        }
    }
    
    public double alquilerEdificaciones(Casilla casilla){
                
            if(casilla.getNumeroCasas()==1){
                return this.casilla.getAlquiler()*5;
            }
            else if(casilla.getNumeroCasas()==2){
                return this.casilla.getAlquiler()*15;
            }
            else if(casilla.getNumeroCasas()==3){
                return this.casilla.getAlquiler()*35;
            }
            else if(casilla.getNumeroCasas()==4){
                return this.casilla.getAlquiler()*50;
            }
            else if(casilla.getNumeroCasas()==0){
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
