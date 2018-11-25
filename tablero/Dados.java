/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablero;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Dados {

    private ArrayList<Integer> valorDados;
    private int numeroTiradas;
    private Random r;
    private int repetidos;

    public Dados(){
        r = new Random();
        this.numeroTiradas=0;
        valorDados = new ArrayList<>();
        valorDados.add(0);
        valorDados.add(0);
        this.repetidos = 0;
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
        this.valorDados.set(1,r.nextInt(6)+1);
        this.valorDados.set(1,r.nextInt(6)+1);
        if(this.valorDados.get(0) == this.valorDados.get(1)){
            this.repetidos++;
        }
        else{
            this.repetidos = 0;
        }
        return this.valorDados;

    }
    public int getRepetidos(){
        return this.repetidos;
    }
    
    public int getValorSuma(){
        return this.valorDados.get(0)+this.valorDados.get(1);
    }

    //Setters

    public void setRepetidos(int repetido){
        this.repetidos = repetido;
    }


}
