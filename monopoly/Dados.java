/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly;

import java.util.Random;

/**
 * 
 * @author María Caseiro <mariacaseiro24@gmail.com>
 */
public class Dados {

    private int dado;
    
    //CONSTRUCTORES
    public Dados(){
        this.dado=2;
    }
    
    //set dado no tiene sentido pq es un valor aleatorio en el que el usuario no toma ninguna decisión
    
    //GETTERS
    public int getDado(){
        return dado;
    }
    
    //MÉTODOS
    public int tirarDados(){
        
        int max=6;
        int min=1;
        
        Random r=new Random();      
        return r.nextInt(max-min+1)+min;
    }
    
    public void comparar(Dados d2){
        
        if(this.dado==d2.getDado()){
            System.out.println("Doble, lance otra vez.");
        }
    }
    
}
