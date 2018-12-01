package monopoly;

import tablero.Casilla;
import tablero.Dados;
import tablero.Tablero;

import java.util.ArrayList;
import java.util.HashSet;
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

        if(eleccion.contains("crear jugador") && !this.tablero.getPartidaIniciada()){

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
            this.avatares.get(this.avatares.size()-1).getJugador().setFortuna(Valores.PRECIOTOTALSOLARES/3);
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
                System.out.println(avatare);
            }
            System.out.println(tablero);
        }

        else if(eleccion.contains("lanzar dados")){
            lanzarDados();//funcion auxiliar
            System.out.println(tablero);
        }

        else if(eleccion.contains("acabar turno")){
            //aumentamos el numero de tiradas y, por consiguiente, tenemos turno+1 (siguiente jugador)
            this.avatares.get(this.turno.getTurno()).getJugador().setPuedeTirarOtraVez(true);
            this.dados.setNumeroTiradas(1);
            this.turno.setTurno(this.dados.getNumeroTiradas());
            this.dados.setRepetidos(0);

            System.out.println("El jugador actual es " + this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador());
        }

        else if(eleccion.contains("salir carcel")){

            int i=turno.getTurno();

            if(this.avatares.get(i).getJugador().getEstarCarcel()){

                if (this.dados.getValorDados().get(0)==this.dados.getValorDados().get(1)){
                    this.avatares.get(this.turno.getTurno()).getJugador().setNumDobles(1,1);
                }

                if(this.avatares.get(this.turno.getTurno()).getJugador().getNumDobles()==3){

                    System.out.println("Se han lanzado los dados.\n");
                    System.out.println("El jugador ha sacado 3 veces dobles y sale de la cárcel");
                    this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()),this.dados.getValorSuma());
                    this.avatares.get(this.turno.getTurno()).getJugador().setEstarCarcel(false);
                    this.avatares.get(this.turno.getTurno()).getJugador().setNumDobles(0,2);

                }
                else {
                    System.out.println("Se han lanzado los dados.\n");
                    this.avatares.get(this.turno.getTurno()).getJugador().setNumTiradasCarcel(1,1);

                    //
                    if(this.avatares.get(this.turno.getTurno()).getJugador().getNumTiradasCarcel()==3){

                        System.out.println("El jugador ya ha tirado 3 veces, está obligado a pagar.");
                        this.avatares.get(this.turno.getTurno()).getJugador().setNumTiradasCarcel(0,2);
                        if(this.avatares.get(this.turno.getTurno()).getJugador().getFortuna() >= Valores.PAGOSALIRCARCEL){
                            this.avatares.get(this.turno.getTurno()).getJugador().setFortuna((float) Valores.PAGOSALIRCARCEL, -1);//le quitamos al jugador el dinero para salir de la carcel
                            System.out.print(this.avatares.get(i).getJugador().getNombreJugador());
                            System.out.println(" paga " + Valores.PAGOSALIRCARCEL + " y sale de la cárcel. Lanza los dados:");
                            this.avatares.get(this.turno.getTurno()).getJugador().setEstarCarcel(false);
                            this.avatares.get(this.turno.getTurno()).getJugador().setNumDobles(0, 2);
                            lanzarDados();
                        }
                        else{
                            System.out.println("El jugador no puede permitirse salir de la cárcel, cae en bancarrota.");
                            //this.avatares.remove(this.turno.getTurno());
                            eleccion("acabar turno");
                        }

                    }else {

                        System.out.println("El jugador ha sacado "
                                + this.avatares.get(this.turno.getTurno()).getJugador().getNumDobles() + " veces dobles.");
                        System.out.println("LLegue a 3 para salir.\n");
                        if (this.avatares.get(i).getJugador().getFortuna() >= Valores.PAGOSALIRCARCEL) {
                            System.out.println("El jugador tiene suficiente dinero para salir de la cárcel.¿Quiere pagar? (si/no)\n");
                            Scanner opcion = new Scanner(System.in);  // Reading from System.in
                            System.out.println("\n->");
                            String n = opcion.nextLine(); // Scans the next token of the input as an int.
                            opcion.reset();
                            if (n.contains("si")) {
                                this.avatares.get(i).getJugador().setFortuna((float) Valores.PAGOSALIRCARCEL, -1);//le quitamos al jugador el dinero para salir de la carcel
                                System.out.print(this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador());
                                System.out.println(" paga " + Valores.PAGOSALIRCARCEL + " y sale de la cárcel. Lanza los dados:");
                                this.avatares.get(this.turno.getTurno()).getJugador().setEstarCarcel(false);
                                this.avatares.get(this.turno.getTurno()).getJugador().setNumDobles(0, 2);
                                lanzarDados();
                            } else {
                                System.out.println("El jugador pierde el turno porque no quiere pagar.\n");
                                eleccion("acabar turno");
                            }
                        } else {
                            System.out.println("El jugador pierde el turno porque no tiene dinero para salir.\n");
                            eleccion("acabar turno");
                        }
                    }
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
                            !aux[1].contains("IrACarcel") &&
                            !aux[1].contains("Suerte") &&
                            !aux[1].contains("Caja")  &&
                            !aux[1].contains("Comunidad"))
                    {
                        if(this.tablero.getCasilla(i,j).getTipo().equals("Parking")){
                            System.out.println("bote: " + this.tablero.getCasilla(i,j).getBote());
                            System.out.println("jugadores: " + this.tablero.getCasilla(i,j).getJugadoresCasilla(this.avatares));
                        }
                        else if(this.tablero.getCasilla(i,j).getTipo().equals("Carcel")){
                            System.out.println("salir: " + Valores.PAGOSALIRCARCEL);
                            System.out.println("jugadores: " + this.tablero.getCasilla(i,j).getJugadoresCasilla(this.avatares));
                        }

                        else{
                            System.out.println(this.tablero.getCasilla(i,j));
                        }
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
                    System.out.println(avatare);
                }
            }
        }
        
        else if(eleccion.contains("comprar")){
            
            aux=eleccion.split("\\s+");
            if(!aux[1].equals("Carcel") && !aux[1].equals("IrCarcel") &&
                    !aux[1].equals("Parking") && !aux[1].contains("Suerte") &&
                    !aux[1].contains("Impuestos") && !aux[1].contains("Caja") &&
                    !aux[1].equals("Salida") && 
                    this.avatares.get(this.turno.getTurno()).getJugador().getCompraEfectuada()==false){

                tablero.comprarPropiedad(aux[1]);
                this.avatares.get(this.turno.getTurno()).getJugador().setCompraEfectuada(true);
                
            }
            else{
                System.out.println("Esta casilla no se puede comprar");
                if(this.avatares.get(this.turno.getTurno()).getJugador().getCompraEfectuada()){
                    System.out.println("Ya ha comprado otra propiedad durante su turno.");
                }
            }

        }
        
        else if(eleccion.contains("listar enventa")){
            this.tablero.imprimirCasillasDisponibles();
        }
        
        else if(eleccion.contains("ver tablero")){
            System.out.println(tablero);
            System.out.print("\033[H\033[2J");
        }
        else if(eleccion.equals("iniciar partida")){
            this.tablero.setPartidaIniciada(true);
            System.out.println(this.tablero);
        }
        else if(eleccion.contains("edificar")){
            aux=eleccion.split("\\s+");
            this.avatares.get(this.turno.getTurno()).getJugador().edificar(
                                this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual(),
                                aux[1]
                                );
        }

        else if(eleccion.equals("deshipotecar")){
            //Compruebo que la casilla pertenezca al jugador
            if(this.avatares.get(this.turno.getTurno()).getJugador().equals(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getJugadorQueTieneLaCasilla())) {
                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getHipotecada()) {
                    //Si el jugador posee la casilla
                    boolean deshipotecada = this.avatares.get(this.turno.getTurno()).getJugador().deshipotecar(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual());
                    if (deshipotecada) System.out.println("Has deshipotecado la casilla correctamente. Has pagado " +
                            this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getHipoteca()*1.1f);
                    else System.out.println("¡Oh, oh! Creo que no tienes dinero para pagar tus deudas...");
                }else System.out.println("Esta casilla no está hipotecada, no la puedes deshipotecar.");
            }
            else System.out.println("No puedes deshipotecar esta casilla, ¡No es tuya!");
        }

        else if(eleccion.contains("hipotecar")) {
            aux = eleccion.split("\\s+");
            //Comprobamos que la casilla pertenezca al jugador
            if (this.tablero.getCasillaByName(aux[1]).getJugadorQueTieneLaCasilla() != null
                    && this.tablero.getCasillaByName(aux[1]).getJugadorQueTieneLaCasilla().equals(this.avatares.get(this.turno.getTurno()).getJugador())) {
                if (!this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getHipotecada()) {
                    if (!this.tablero.getCasillaByName(aux[1]).equals(null)) {

                        if(this.tablero.getCasillaByName(aux[1]).getNumeroEdificaciones() > 0){
                            this.avatares.get(this.turno.getTurno()).getJugador().venderTodosEdificios(this.tablero.getCasillaByName(aux[1]));
                            System.out.println("Tenías edificios en esta casilla, pero para hipotecarla se venderán.");
                        }

                        this.avatares.get(this.turno.getTurno()).getJugador().hipotecar(this.tablero.getCasillaByName(aux[1]));

                        System.out.println("La casilla se ha hipotecado " +
                                this.tablero.getCasillaByName(aux[1]).getNombre()+
                                " correctamente y recibe " +
                                this.tablero.getCasillaByName(aux[1]).getHipoteca() +
                                ". ¡Disfrute de su dinero!");
                    } else System.out.println("La casilla introducida no existe.");
                }else System.out.println("¡Esta casilla ya está hipotecada!");
            }else System.out.println("No puedes hipotecar una casilla que no es tuya.");
        }

        else if(eleccion.contains("vender")){
            aux = eleccion.split("\\s+");
            Casilla casilla = this.tablero.getCasillaByName(aux[2]);
            int res = this.avatares.get(this.turno.getTurno()).getJugador().venderEdificio(
                                                                    this.avatares.get(this.turno.getTurno()).getJugador(),
                                                                    aux[1],
                                                                    casilla,
                                                                    Integer.parseInt(aux[3]));

            if(res == 0) System.out.println("No se ha vendido nada.");
            else if(res == 1) System.out.println("Se han vendido " + aux[3] + " edificaciones. En la propiedad queda(n) " +
                    casilla.getNumeroEdificaciones() +
                    " edificacion(es)" );
            else if(res == 2) System.out.println("No disponías de " + aux[3] + " edificaciones. Hemos vendido todas las que tenías.");
            else if(res == 3) System.out.println("No se ha podido ejecutar esta acción.");
            else if(res == -1) System.out.println("Esta casilla no pertenece a " + this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador());

        }
        
        else if(eleccion.contains("cambiar modo")){
            
            if(this.avatares.get(this.turno.getTurno()).getTipoEspecial()==true){
                this.avatares.get(this.turno.getTurno()).setTipoEspecial(false);
            }else{
                this.avatares.get(this.turno.getTurno()).setTipoEspecial(true);
            }
        }
        
        else if(eleccion.contains("listar edificios")){
           
            int k=0;
            
            aux = eleccion.split("\\s+");

            if(aux.equals("verde") || aux.equals("rojo") || aux.equals("azul") || aux.equals("magenta") ||
                    aux.equals("amarillo") || aux.equals("blanco") || aux.equals("cian") || aux.equals("negro")){
                for(int i=0;i<4;i++){
                    for(int j=0;j<10;j++){
                        if(this.tablero.getCasilla(i,j).getColor().equals(aux) && 
                                this.tablero.getCasilla(i,j).getEdificaciones().isEmpty()==false){
                            System.out.println("{");
                            System.out.println("Propiedad: "+this.tablero.getCasilla(i,j).getNombre());
                            System.out.println("Hoteles: "+this.tablero.getCasilla(i,j).getNumeroHoteles());
                            this.tablero.getCasilla(i,j).setNumeroHotelesGrupo(this.tablero.getCasilla(i,j).getNumeroHoteles());                          
                            System.out.println("Casas: "+this.tablero.getCasilla(i,j).getNumeroCasas());
                            this.tablero.getCasilla(i,j).setNumeroCasasGrupo(this.tablero.getCasilla(i,j).getNumeroCasas());                                                      
                            System.out.println("Piscinas: "+this.tablero.getCasilla(i,j).getNumeroPiscinas());
                            this.tablero.getCasilla(i,j).setNumeroPiscinasGrupo(this.tablero.getCasilla(i,j).getNumeroPiscinas());                                                      
                            System.out.println("Pistas de deporte: "+this.tablero.getCasilla(i,j).getNumeroPistasDep());
                            this.tablero.getCasilla(i,j).setNumeroPistasDepGrupo(this.tablero.getCasilla(i,j).getNumeroPistasDep());                                                      
                            System.out.println("Alquiler: "+this.tablero.getCasilla(i,j).getAlquiler());
                            System.out.println("}");       
                        }
                    }
                }
                
                System.out.print("Aun se pueden edificar ");
                  
                if(!aux.equals("verde") && !aux.equals("negro")){
                    
                    System.out.print(3-this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNumeroHotelesGrupo()+" hoteles, ");
                    System.out.print(3-this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNumeroCasasGrupo()+" casas, ");
                    System.out.print(3-this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNumeroPiscinasGrupo()+" piscinas, ");
                    System.out.print(3-this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNumeroPistasDepGrupo()+" pistas de deporte.");
                }else{
                    
                    System.out.print(2-this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNumeroHotelesGrupo()+" hoteles, ");
                    System.out.print(2-this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNumeroCasasGrupo()+" casas, ");
                    System.out.print(2-this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNumeroPiscinasGrupo()+" piscinas, ");
                    System.out.print(2-this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNumeroPistasDepGrupo()+" pistas de deporte.");
                }  
                //limpiamos los atributos
                limpiezaAtributosGrupo();
                
            }else{
                for(int i=0;i<4;i++){
                    for(int j=0;j<10;j++){
                        if(this.tablero.getCasilla(i,j).getTipo().equals("Solar") && 
                                this.tablero.getCasilla(i,j).getEdificaciones().isEmpty()==false){
                            System.out.println("{");
                            System.out.println("Edificaciones: "+this.tablero.getCasilla(i,j).getEdificaciones());
                            System.out.println("Propietario: "+this.tablero.getCasilla(i,j).getPropietario(avatares).getNombreJugador());
                            System.out.println("Casilla: "+this.tablero.getCasilla(i,j).getNombre());
                            System.out.println("Grupo: "+this.tablero.getCasilla(i,j).getColor());
                            System.out.println("Coste: "+this.tablero.getCasilla(i,j).getPrecio());
                            System.out.println("}");
                        }else{
                            k++;
                        }
                    }
                }
                if(k==40){
                    System.out.println("Todavía no hay edificios en ninguna casilla.");
                }
            }
        }
    }

    public void darAltaJugador(String nombre,String tipo){

        Avatar avatarCreado = new Avatar(tipo, nombre);
        avatarCreado.getJugador().setCasillaActual(this.tablero.getCasilla(0,0));
        this.avatares.add(avatarCreado);
        k++;
    }
    
    public void imprimirLanzarDados(int valorDados){
        if (!this.avatares.get(this.turno.getTurno()).getJugador().getEstarCarcel()) {   
            
                double auxParking = 0;//accedemos al bote del Parking antes de cobrarlo para poder imprimirlo
                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Parking")) {
                    auxParking = this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getBote();
                }
                                
                System.out.print("\nEl avatar " + this.avatares.get(this.turno.getTurno()).getSimbolo() +
                        " se desplaza " + valorDados +
                        " posiciones, desde " + this.avatares.get(this.turno.getTurno()).getJugador().getNombreCasillaAnterior() +
                        " hasta " +this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre() + "\n");
                
                //ofrecemos comprar la casilla al jugador
                if((this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().contains("Solar") ||
                        this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().contains("Servicio") || 
                        this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().contains("Transporte")) &&
                        this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getDisponibilidad()==true){
                    
                    System.out.println("¿Quiere comprar la casilla? (si|no)");
                    
                    Scanner opcion = new Scanner(System.in);  // Reading from System.in
                            System.out.println("\n->");
                            String n = opcion.nextLine(); // Scans the next token of the input as an int.
                            opcion.reset();
                    
                    if (n.contains("si")) {
                        //ejecutamos la funcion comprar
                        this.eleccion("comprar "+this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre());
                    }
                    
                }

                if(this.dados.getRepetidos() == 3){
                    System.out.println("El jugador ha sacado 3 veces dobles, por eso se situa en la carcel");
                    this.dados.setRepetidos(0);
                }
                else if(this.avatares.get(this.turno.getTurno()).getJugador().getEstarCarcel()==true){
                    System.out.println("El jugador ha caído en IrCarcel y por ello se desplaza a la casilla Carcel.\n");
                }    
                //SOLAR
                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Solar")
                        && !this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getDisponibilidad()
                        && !this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario(this.avatares).getNombreJugador().equals(
                        this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador())) {

                    System.out.print("Se han pagado ");

                    //comprobamos si el jugador propietario posee to.do el grupo para indicar que paga el doble
                    if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario(this.avatares).poseerGrupo(
                            this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getGrupo())) {

                        System.out.print(((this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getAlquiler()) * 2) + " de alquiler a " +
                                this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario(this.avatares) + "\n");
                    } else {
                        System.out.print(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getAlquiler() + " de alquiler a " +
                                this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario(this.avatares) + "\n");
                    }

                }
                //PARKING
                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Parking")) {
                    System.out.println("El jugador " + this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador() + " obtiene "
                            + auxParking + "€, el bote de la banca.\n");
                }
                //IMPUESTOS
                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Impuestos")) {
                    if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre().contains("1")) {
                        System.out.println("El jugador paga " + Valores.TASAIMPUESTOS1);
                    } else {
                        System.out.println("El jugador paga " + Valores.TASAIMPUESTOS2);
                    }
                }
                //TRANSPORTES
                if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Transportes")) {

                    double factor = 1;

                    int p = this.tablero.poseerTransportes();
                    if (p == 0) factor =  0.25;
                    if (p == 1) factor = 0.5;
                    if (p == 2) factor = 0.75;

                    System.out.println("Se han pagado " + Valores.OPERACIONTRANSPORTE * factor + " de alquiler.\n");
                }
                //SERVICIOS
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
                System.out.println("El usuario está en la cárcel. Escriba 'salir carcel' o 'acabar turno'");
            }
    }
    
    public void lanzarDados() {

        dados.tirarDados();

        if (this.avatares.get(this.turno.getTurno()).getJugador().getPuedeTirarOtraVez() || 
                this.avatares.get(this.turno.getTurno()).getTipoEspecial()) {

            if(this.avatares.get(this.turno.getTurno()).getJugador().getEstarCarcel()==false){
                System.out.println("El valor de los dados es " + dados.getValorDados().get(0) + "+" + dados.getValorDados().get(1));

                //almacenamos el nombre de la casilla anterior para imprimirlo
                this.avatares.get(this.turno.getTurno()).getJugador().setNombreCasillaAnterior(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre());

                if(this.avatares.get(this.turno.getTurno()).getTipoEspecial()==true){
                    movimientoEspecial();
                }else{
                    this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()), dados.getValorSuma());//movemos el avatar y obtenemos su nueva posicion
                }
            }
            
            imprimirLanzarDados(this.dados.getValorSuma());   
            
        }else{
            System.out.println(Valores.ROJO +"¡El jugador no puede lanzar los dados!" + Valores.RESET);
        }
    }
        
    int numTiradas=0;

    public void movimientoEspecial(){

        if(this.avatares.get(this.turno.getTurno()).getTipo().contains("pelota")){
            if(this.dados.getValorSuma()>4){

                System.out.println("Has sacado un número mayor que 4, avanzas.");
                int j=0; //variable auxiliar para restar la posicion del avatar

                for(int i=1;i<=this.dados.getValorSuma();i++){
                    if(this.avatares.get(this.turno.getTurno()).getJugador().getEstarCarcel()==true){
                            System.out.println("El jugador está en la cárcel, no se puede seguir moviendo.");
                    }else{
                        if((i>4 && (i%2)!=0) || (i==this.dados.getValorSuma()) ){//comprobamos que sea impar o el ultimo valor

                            System.out.println(" ");
                            System.out.println("El jugador ha avanzado "+i+" posiciones desde la casilla de origen.");
                            this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()),(i-j));
                            if(i!=this.dados.getValorSuma()){//en este caso se imprime en lanzarDados
                                imprimirLanzarDados(i-j);
                            }
                            j=i;
                        }
                    }
                }
            }else{

                System.out.println("Has sacado un número menor o igual que 4, retrocede.");
                int j=0; //variable auxiliar para restar la posicion del avatar

                for(int i=1;i<=this.dados.getValorSuma();i++){
                    if(this.avatares.get(this.turno.getTurno()).getJugador().getEstarCarcel()==true){
                            System.out.println("El jugador está en la cárcel, no se puede seguir moviendo.");
                    }else{
                        if(i%2!=0 || (i==this.dados.getValorSuma()) ){//comprobamos que sea impar o el ultimo valor
                            
                            System.out.println(" ");
                            System.out.println("El jugador ha retrocedido "+i+" posiciones desde la casilla de origen.");
                            this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()),-(i-j));
                            if(i!=this.dados.getValorSuma()){//en este caso se imprime en lanzarDados
                                imprimirLanzarDados(i-j);
                            }
                            j=i;
                        }
                    }
                }
            }
        }
                
        if(this.avatares.get(this.turno.getTurno()).getTipo().contains("coche")){
            
            int k=0;
            
            if(this.dados.getValorSuma()>4 && numTiradas!=4){
                numTiradas++;
                this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()),this.dados.getValorSuma());
                imprimirLanzarDados(this.dados.getValorSuma());
                System.out.println("Valor mayor que 4, ¿quiere seguir tirando?. (si|no)");
                Scanner opcion = new Scanner(System.in);  // Reading from System.in
                        System.out.println("\n->");
                        String n = opcion.nextLine(); // Scans the next token of the input as an int.
                        opcion.reset();
                if(n.contains("si")){
                    numTiradas++;
                    lanzarDados();
                    this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()),this.dados.getValorSuma());
                    imprimirLanzarDados(this.dados.getValorSuma());
                    movimientoEspecial();
                    k=1;
                }
            
            }else{
                if(k==1){
                    if(numTiradas==4){
                        System.out.println("Ya ha lanzado 4 veces los dados.");
                        this.avatares.get(this.turno.getTurno()).getJugador().setCompraEfectuada(false);
                        numTiradas=0;
                    }else{
                        System.out.println("Ha sacado un número menor o igual que 4, no puede seguir tirando y retrocede.");
                        this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()),-(this.dados.getValorSuma()));
                        imprimirLanzarDados(this.dados.getValorSuma());
                        this.avatares.get(this.turno.getTurno()).getJugador().setCompraEfectuada(false);
                        numTiradas=0;
                    }
                }
            }
        }

    }
    
    public void limpiezaAtributosGrupo(){
        this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().setNumeroHotelesGrupo(10);
        this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().setNumeroCasasGrupo(10);
        this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().setNumeroPiscinasGrupo(10);
        this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().setNumeroPistasDepGrupo(10);

    }
}
