package monopoly;

import tablero.Dados;
import tablero.Tablero;

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
           reader.reset();
           
        return n;
    }
    
    public void eleccion(String eleccion){
        
        String[] aux;
        
        if(eleccion.contains("crear jugador") && this.tablero.getPartidaIniciada()){
            
            aux=eleccion.split("\\s+");
            darAltaJugador(aux[2],aux[3]);
            if(k>6){
                System.out.println("No se pueden registrar más jugadores.¿Listos para jugar?\n");
            }
            else{
                System.out.println("Nombre: " + this.avatares.get(this.avatares.size()-1).getJugador().getNombreJugador());
                System.out.println("Avatar: " + this.avatares.get(this.avatares.size()-1).getSimbolo() + "\n");
            }
            //Le damos el dinero
            this.avatares.get(this.avatares.size()-1).getJugador().setFortuna(26600);
            //Aumentamos el numero de turnos disponibles
            this.turno.setNumeroJugadores(1);
        }
        
        else if(eleccion.equals("jugador")){
            System.out.println("Nombre: "+this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador());
            System.out.println("Avatar: "+this.avatares.get(this.turno.getTurno()).getSimbolo());
        }
        
        else if(eleccion.contains("listar jugadores")){
            for (Avatar avatare : this.avatares) {
                System.out.println(avatare.getJugador());
            }
            System.out.println(tablero);
        }
        
        else if(eleccion.contains("listar avatares")){
            for (Avatar avatare : this.avatares) {
                System.out.println(avatare.imprimirDatos());
            }
            System.out.println(tablero);
        }
        
        else if(eleccion.contains("lanzar dados")){
            lanzarDados();//funcion auxiliar
            System.out.println(tablero);
        }
        
        else if(eleccion.contains("acabar turno")){
            //aumentamos el numero de tiradas y, por consiguiente, tenemos turno+1 (siguiente jugador)
            this.dados.setNumeroTiradas(1);
            this.turno.setTurno(this.dados.getNumeroTiradas());
            this.avatares.get(this.turno.getTurno()).getJugador().setPuedeTirarOtraVez(true);
        }
        
        else if(eleccion.contains("salir carcel")){

                int i=turno.getTurno();

                if(this.avatares.get(i).getJugador().getCasillaActual().getNombre().equals("Carcel")){

                    if(this.dados.getValorDados().get(0) == this.dados.getValorDados().get(1)){

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
                else{
                    System.out.println("El jugador no está en la cárcel");
                }
        }
        //Describir {nombre de la casilla}
        else if(eleccion.split("\\s+")[0].equals("describir") &&
                !eleccion.split("\\s+")[1].equals("avatar") &&
                !eleccion.split("\\s+")[1].equals("jugador")){

            aux=eleccion.split("\\s+");

            for(int i=0;i<4;i++){
                for(int j=0;j<10;j++){

                    if(this.tablero.getCasilla(i,j).getNombre().equals(aux[1]) &&
                            !aux[1].contains("IrACarcel") ||
                            !aux[1].contains("Suerte") ||
                            !aux[1].contains("Caja")  ||
                            !aux[1].contains("Comjnidad"))
                    {
                        System.out.println(this.tablero.getCasilla(i,j));
                    }

                }
            }

        }
        
        else if(eleccion.split("\\s+")[0].equals("describir") && eleccion.split("\\s+")[1].equals("jugador")){
            
            aux=eleccion.split("\\s+");
            //buscamos al usuario e imprimimos sus datos
            for (Avatar avatare : this.avatares) {
                if (avatare.getJugador().getNombreJugador().equals(aux[2])) {
                    System.out.println(avatare.getJugador());
                }
            }
        }
        
        else if(eleccion.split("\\s+")[0].equals("describir") && eleccion.split("\\s+")[1].equals("avatar")){
            
            aux=eleccion.split("\\s+");
            char av=aux[2].charAt(0);
            //buscamos al usuario e imprimimos sus datos
            for (Avatar avatare : this.avatares) {
                if (av == avatare.getSimbolo()) {
                    System.out.println(avatare.imprimirDatos());
                }
            }
        }
        
        else if(eleccion.contains("comprar")){
            
            aux=eleccion.split("\\s+");
            tablero.comprarPropiedad(aux[1]);

        }
        
        else if(eleccion.contains("listar enventa")){
            this.tablero.imprimirCasillasDisponibles();
        }
        
        else if(eleccion.contains("ver tablero")){
            System.out.println(tablero);
            System.out.print("\033[H\033[2J");
        }
        if(eleccion.equals("iniciar partida")){
            this.tablero.setPartidaIniciada(true);
        }
        else{
            System.out.println("Opción no válida.\n");
        }
    }
    
    public void darAltaJugador(String nombre,String tipo){

        Avatar avatarCreado = new Avatar(tipo, nombre);
        avatarCreado.getJugador().setCasillaActual(this.tablero.getCasilla(0,0));
        this.avatares.add(avatarCreado);
        k++;
    }
    
    public void lanzarDados() {

        dados.tirarDados();

        if (this.avatares.get(this.turno.getTurno()).getJugador().getPuedeTirarOtraVez()) {


            System.out.println("El valor de los dados es " + dados.getValorDados().get(0) + "+" + dados.getValorDados().get(1));

            if (!this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Carcel")) {

                double auxParking = 0;//accedemos al bote del Parking antes de cobrarlo para poder imprimirlo
                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Parking")) {
                    auxParking = this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getBote();
                }

                System.out.print("\nEl avatar " + this.avatares.get(this.turno.getTurno()).getSimbolo() +
                        " avanza " + this.dados.getValorSuma() +
                        " posiciones, desde " + this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre() +
                        " hasta ");
                this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()), dados.getValorSuma());//movemos el avatar y obtenemos su nueva posicion
                System.out.print(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre() + "\n");

                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Solar")
                        && !this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getDisponibilidad()
                        && !this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario().getNombreJugador().equals(
                        this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador())) {

                    System.out.print("Se han pagado ");

                    //comprobamos si el jugador propietario posee todo el grupo para indicar que paga el doble
                    if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario().poseerGrupo(
                            this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getGrupo())) {

                        System.out.print(((this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getAlquiler()) * 2) + " de alquiler a " +
                                this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario() + "\n");
                    } else {
                        System.out.print(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getAlquiler() + " de alquiler a " +
                                this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario() + "\n");
                    }

                }

                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Parking")) {
                    System.out.println("El jugador " + this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador() + " obtiene "
                            + auxParking + "€, el bote de la banca.\n");
                }

                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Impuestos")) {
                    if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre().contains("1")) {
                        System.out.println("El jugador paga " + Valores.TASAIMPUESTOS1);
                    } else {
                        System.out.println("El jugador paga " + Valores.TASAIMPUESTOS2);
                    }
                }

                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("IrCarcel")) {
                    System.out.println("El avatar se sitúa en la casilla Cárcel.\n");
                }

                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Transportes")) {

                    double factor = 0;

                    int p = this.tablero.poseerTransportes();
                    if (p == 1) factor = 0.25;
                    if (p == 2) factor = 0.5;
                    if (p == 3) factor = 0.75;

                    System.out.println("Se han pagado " + Valores.OPERACIONTRANSPORTE * factor + " de alquiler.\n");
                }

                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Servicios")) {
                    if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre().contains("1")) {
                        System.out.println("Se han pagado " + 4 * Valores.FACTORSERVICIO * this.dados.getValorSuma() + " de alquiler\n");
                    } else {
                        System.out.println("Se han pagado " + 10 * Valores.FACTORSERVICIO * this.dados.getValorSuma() + " de alquiler\n");

                    }
                }
                if (dados.getValorDados().get(0) == dados.getValorDados().get(1)) {
                    this.avatares.get(this.turno.getTurno()).getJugador().setPuedeTirarOtraVez(true);
                } else this.avatares.get(this.turno.getTurno()).getJugador().setPuedeTirarOtraVez(false);
            } else {
                if (dados.getValorDados().get(0) == dados.getValorDados().get(1)) {
                    System.out.println("El jugador sacó dobles y por lo tanto, sale de la cárcel, se lanzarán los dados de nuevo\n");
                    lanzarDados();
                } else {
                    System.out.println("El Jugador, que está en la cárcel, no puede salir porque no ha sacado dobles");
                }
            }
        }else{
            System.out.println(Valores.ROJO +"¡El jugador no puede lanzar los dados!" + Valores.RESET);
        }
    }
}
