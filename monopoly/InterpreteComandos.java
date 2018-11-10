package monopoly;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class InterpreteComandos {


    private ArrayList<Avatar> avatares;
    private Tablero tablero;
    private int k; //aumenta cada vez que creamos un jugador, para saber que turno le corresponde
    private Turno turno;
    private Dados dados;
    
    public InterpreteComandos(ArrayList<Avatar> avatares, Tablero tablero,Turno turno,Dados dados){

        this.avatares= avatares;
        this.tablero = tablero;
        this.k=0;//contador del numero de usuarios en el sistema
        this.turno=turno;
        this.dados=dados;
    }
    
    public String input(){
        
        Scanner reader=new Scanner(System.in);  // Reading from System.in
        
           System.out.println("\n->");
           String n = reader.nextLine(); // Scans the next token of the input as an int.
           reader.close();
           
        return n;
    }
    
    public void eleccion(String eleccion){
        
        String[] aux;
        
        if(eleccion.contains("crear jugador")){
            
            aux=eleccion.split("\\s+");
            darAltaJugador(aux[2],aux[3]);
            if(k>6){
                System.out.println("No se pueden registrar más jugadores.¿Listos para jugar?\n");
            }
            else{
                System.out.println("Nombre: " + this.avatares.get(this.avatares.size()-1).getJugador().getNombreJugador());
                System.out.println("Avatar: " + this.avatares.get(this.avatares.size()-1).getSimbolo() + "\n");
            }
        }
        
        else if(eleccion.contains("jugador")){
            System.out.println("Nombre: "+this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador());
            System.out.println("Avatar: "+this.avatares.get(this.turno.getTurno()).getSimbolo());
        }
        
        else if(eleccion.contains("listar jugadores")){
            for(int i=0;i<this.avatares.size();i++){
                System.out.println("{");
                System.out.println(this.avatares.get(i).getJugador());
                System.out.println("}");
            }
        }
        
        else if(eleccion.contains("listar avatares")){
            for(int i=0;i<this.avatares.size();i++){
                System.out.println("{");
                System.out.println(this.avatares.get(i).imprimirDatos());
                System.out.println("}");
            }
        }
        
        //////////////////////////////////////////////////////////acabar
        else if(eleccion.contains("lanzar dados")){
            lanzarDados();//funcion auxiliar
        }
        
        else if(eleccion.contains("acabar turno")){
            //aumentamos el numero de tiradas y, por consiguiente, tenemos turno+1 (siguiente jugador)
            this.dados.setNumeroTiradas(1);
        }
        
        else if(eleccion.contains("salir carcel")){

                int i=turno.getTurno();

                if(this.avatares.get(i).getJugador().getCasillaActual().equals("Carcel")){

                    if(this.dados.getValorDados().get(0)==this.dados.getValorDados().get(1)){
                        this.tablero.desplazarAvatar(this.avatares.get(i),this.dados.getValorSuma());
                    }
                    else if(this.avatares.get(i).getJugador().getFortuna()<Valores.PAGOSALIRCARCEL){
                        System.out.println("El jugador no tiene suficiente dinero para salir de la cárcel. Pierde el turno.");
                    }
                    else{
                        this.avatares.get(i).getJugador().setFortuna((float) Valores.PAGOSALIRCARCEL,-1);//le quitamos al jugador el dinero para salir de la carcel
                        System.out.print(this.avatares.get(i).getJugador().getNombreJugador());
                        System.out.println("paga "+Valores.PAGOSALIRCARCEL+" y sale de la cárcel. Puede lanzar los dados");
                        lanzarDados();
                        
                    }
                }
        }
        
        else if(eleccion.contains("describir")){
            aux=eleccion.split("\\s+");

            for(int i=0;i<4;i++){
                for(int j=0;j<10;j++){
                    if(this.tablero.getCasilla(i,j).getTipo()=="Solar"){
                        System.out.println(this.tablero.getCasilla(i,j));
                    }

                    if(this.tablero.getCasilla(i,j).getTipo()=="Parking"){
                        System.out.println("Bote: " +this.tablero.getCasilla(i,j).getBote());
                        System.out.println(this.tablero.getCasilla(i,j).getJugadoresCasilla());
                    }

                    if(this.tablero.getCasilla(i,j).getTipo()=="Carcel"){
                        System.out.println("Precio salida: "+Valores.PAGOSALIRCARCEL);
                        System.out.println(this.tablero.getCasilla(i,j).getJugadoresCasilla());
                    }
                }  
            }

        }
        
        else if(eleccion.contains("describir jugador")){
            
            aux=eleccion.split("\\s+");
            //buscamos al usuario e imprimimos sus datos
            for(int i=0;i<this.avatares.size();i++){
                if(this.avatares.get(i).getJugador().getNombreJugador().equals(aux[2])){
                    System.out.println(this.avatares.get(i).getJugador());
                }
            }
        }
        
        else if(eleccion.contains("describir avatar")){
            
            aux=eleccion.split("\\s+");
            char av=aux[2].charAt(0);
            //buscamos al usuario e imprimimos sus datos
            for(int i=0;i<this.avatares.size();i++){
                if(av == this.avatares.get(i).getSimbolo() ){
                    System.out.println(this.avatares.get(i).imprimirDatos());
                }
            }
        }
        
        else if(eleccion.contains("comprar")){
            
            aux=eleccion.split("\\s+");

            for(int i=0;i<4;i++){
                for(int j=0;j<10;j++){
                    if(this.tablero.getCasilla(i,j).getDisponibilidad()==true && this.tablero.getCasilla(i,j).equals(aux[1])){
                        if(this.tablero.getAvatares().get(this.turno.getTurno()).getJugador().getFortuna()>=this.tablero.getCasilla(i,j).getPrecio()){
                        //le quitamos el dinero al jugador, le asignamos la propiedad y la establecemos como no disponible
                        this.tablero.getAvatares().get(this.turno.getTurno()).getJugador().setFortuna(this.avatares.get(i).getJugador().getCasillaActual().getPrecio(),-1);
                        this.tablero.getAvatares().get(this.turno.getTurno()).getJugador().setPropiedades(this.avatares.get(i).getJugador().getCasillaActual());
                        this.tablero.getAvatares().get(this.turno.getTurno()).getJugador().getCasillaActual().setDisponibilidad(false);
                        this.tablero.getBanca().quitarPropiedad(this.tablero.getCasilla(i,j));
                        //la variable turno representa al jugador en el array de jugadores (orden en el que fueron registrados)
                            System.out.println("El jugador "+this.tablero.getAvatares().get(this.turno.getTurno()).getJugador().getNombreJugador()
                                    +" compra la casilla "+this.tablero.getCasilla(i,j).getNombre()+" por "+this.tablero.getCasilla(i,j).getPrecio());
                            System.out.println("Su fortuna actual es: "+this.tablero.getAvatares().get(this.turno.getTurno()).getJugador().getFortuna());
                        }
                        else{
                            System.out.println("El jugador no tiene suficiente dinero para comprar la propiedad.\n");
                        }
                    }
                    else{
                        System.out.println("La casilla es propiedad de: "+this.avatares.get(i).getJugador().getCasillaActual().getPropietario());
                    }
                }
            }         

        }
        
        else if(eleccion.contains("listar en venta")){
            this.tablero.imprimirCasillasDisponibles();
        }
        
        else if(eleccion.contains("ver tablero")){
            System.out.println(tablero);
        }
        else{
            System.out.println("Opción no válida.\n");
        }
    }
    
    public void darAltaJugador(String nombre,String tipo){

        Avatar avatarCreado = new Avatar(tipo, this.avatares.size(), nombre);
        avatarCreado.getJugador().setCasillaActual(this.tablero.getCasilla(0,0));
        this.avatares.add(avatarCreado);
        k++;
    }
    
    public void lanzarDados(){
        dados.tirarDados();
        
        double auxParking=0;//accedemos al bote del Parking antes de cobrarlo para poder imprimirlo
        if(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Parking")){
            auxParking=this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getBote();
        }
        
        System.out.print("\nEl avatar "+this.avatares.get(this.turno.getTurno()).getSimbolo()+
                " avanza "+this.dados.getValorSuma()+
                " posiciones, desde "+this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre()+
                " hasta ");
        this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()),dados.getValorSuma());//movemos el avatar y obtenemos su nueva posicion
        System.out.print(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre()+"\n");
        
        if(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Solar") 
                && this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getDisponibilidad()==false 
                && !this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario().getNombreJugador().equals(
                        this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador())){
            System.out.print("Se han pagado ");
            //comprobamos si el jugador propietario posee todo el grupo para indicar que paga el doble
            if(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario().poseerGrupo(
                    this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getGrupo())==true){
                System.out.print((this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getAlquiler())*2+" de alquiler a "+
                    this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario()+"\n");
            }
            else{
                System.out.print(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getAlquiler()+" de alquiler a "+
                    this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario()+"\n");
            }
            
        }
        
        if(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Parking")){
            System.out.println("El jugador "+this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador()+" obtiene "
            +auxParking+"€, el bote de la banca.\n");
        } 
        
        if(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Impuestos")){        
            if(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre().contains("1")){
                System.out.println("El jugador paga "+Valores.TASAIMPUESTOS1);
            }
            else{
                System.out.println("El jugador paga "+Valores.TASAIMPUESTOS2);
            }
        }
        
        if(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("IrCarcel")){ 
            System.out.println("El avatar se sitúa en la casilla Cárcel.\n");
        }       
        
        if(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Transportes")){ 
            
            int p=0;
            double factor=0;
            
            p=this.tablero.poseerTransportes();
                if(p==1) factor=0.25;
                if(p==2) factor=0.5;
                if(p==3) factor=0.75;
                
            System.out.println("Se han pagado "+Valores.OPERACIONTRANSPORTE*factor+" de alquiler.\n");
        }
        
        if(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Servicios")){ 
            if(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre().contains("1")){
                System.out.println("Se han pagado "+4*Valores.FACTORSERVICIO*this.dados.getValorSuma()+" de alquiler\n");
            }
            else{
                System.out.println("Se han pagado "+10*Valores.FACTORSERVICIO*this.dados.getValorSuma()+" de alquiler\n");

            }
        }


    }
}
