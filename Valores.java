package monopoly;

public class Valores {
   
    
    //Atributos
    private float valorPropiedad;
    //Constructores
    
    public Valores(){
        this.valorPropiedad = 0;
    }
    
    public Valores(int valorPropiedad){
        this.valorPropiedad = valorPropiedad;
    }
    
    //Getters
    public float getValorPropiedad(){
        return valorPropiedad;
    }
    
    //Setters
    public void setValorPropiedad(float valorPropiedad){
        if(valorPropiedad != 0){
            this.valorPropiedad = valorPropiedad;
        }
    }
    
    //Metodos
    
    public void incrementarValor(){
       this.valorPropiedad *=1.05f;
    }
}
