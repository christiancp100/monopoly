/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Dados {

    private ArrayList<Integer> valorDados;
    private int numeroTiradas;

    public Dados(){
        this.numeroTiradas=0;
        valorDados = new ArrayList<>();
        valorDados.add(0);
        valorDados.add(0);
    }

    public ArrayList<Integer> getValorDados(){
        return this.valorDados;
    }
    public int getNumeroTiradas(){
        return this.numeroTiradas;
    }

    //Setters

    //Set valorDados no tiene sentido, porque se crea de forma aleatoria

    public void setNumeroTiradas(int numeroTiradas){
        this.numeroTiradas += numeroTiradas;
    }

    public ArrayList<Integer> tirarDados(){
        int max=6,min=1;
        Random r = new Random();
        this.valorDados.set(0, r.nextInt((max - min + 1) + min));
        this.valorDados.set(1, r.nextInt((max - min + 1) + min));

        return this.valorDados;

    }


}
