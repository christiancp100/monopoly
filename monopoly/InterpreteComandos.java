 package monopoly;

 import tablero.Casilla;
 import tablero.Dados;
 import tablero.Tablero;

 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;


 public class InterpreteComandos {


     private ArrayList<Avatar> avatares;
     private Tablero tablero;
     private int k; //aumenta cada vez que creamos un jugador, para saber que turno le corresponde
     private Turno turno;
     private Dados dados;
     private int numTiradas;



     public InterpreteComandos(ArrayList<Avatar> avatares, Tablero tablero,Turno turno,Dados dados){

         this.avatares= avatares;
         this.tablero = tablero;
         this.k=0;//contador del numero de usuarios en el sistema
         this.turno=turno;
         this.dados=dados;
         this.numTiradas = 0;
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
             this.avatares.get(this.turno.getTurno()).getJugador().setCompraEfectuada(false);
             this.avatares.get(this.turno.getTurno()).getJugador().setPuedeTirarOtraVez(true);
             this.dados.setNumeroTiradas(1);
             this.turno.setTurno(this.dados.getNumeroTiradas());
             this.dados.setRepetidos(0);

             numTiradas=0;

             System.out.println("El jugador actual es " + this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador());

             if(this.avatares.get(this.turno.getTurno()).getPierdeTurno()){
                 this.avatares.get(this.turno.getTurno()).setNumeroTurnos(1);
                 //ponemos 3 pq esta condicion se va a ejecutar al terminar el turno en el que pierde los 2 turnos siguientes
                 System.out.println("Le quedan "+(3-this.avatares.get(this.turno.getTurno()).getNumeroTurnos())+" turnos para poder tirar");
             }
             if(this.avatares.get(this.turno.getTurno()).getNumeroTurnos()==3){
                 this.avatares.get(this.turno.getTurno()).setNumeroTurnos(0);
                 this.avatares.get(this.turno.getTurno()).setPierdeTurno(false);
             }

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
                     !aux[1].equals("Salida")){

                     tablero.comprarPropiedad(aux[1]);
                     if(this.avatares.get(this.turno.getTurno()).getTipo().equals("coche") 
                             && this.avatares.get(this.turno.getTurno()).getTipoEspecial()){
                        this.avatares.get(this.turno.getTurno()).getJugador().setCompraEfectuada(true);
                     }
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
         
         else if(eleccion.contains("nada")){
             System.out.println(" ");
         }

         else if(eleccion.contains("listar edificios")){

             int k=0, max=0, numCasas=0,numHoteles=0,numPiscinas=0,numPistas=0;

             aux = eleccion.split("\\s+");

             if(aux.length == 3 && (aux[2].equals("verde") || aux[2].equals("rojo") || aux[2].equals("azul") || aux[2].equals("magenta") ||
                     aux[2].equals("amarillo") || aux[2].equals("blanco") || aux[2].equals("cian") || aux[2].equals("negro"))){
                 for(int i=0;i<4;i++){
                     for(int j=0;j<10;j++){
                         if(this.tablero.getCasilla(i,j).getGrupoColor().equals(aux[2]) &&
                                 this.tablero.getCasilla(i,j).getNumeroEdificaciones()>0) {
                             for (Edificaciones ed : this.tablero.getCasilla(i, j).getEdificaciones().values()) {
                                 System.out.println(ed);
                                 k++;
                             }
                         }
                     }
                 }
                 for(int i=0;i<4;i++) {
                     for (int j = 0; j < 10; j++) {
                         if(this.tablero.getCasilla(i,j).getTipo().equals("Solar")){
                             if (this.tablero.getCasilla(i, j).getGrupoColor().equals(aux[2])) {
                                 if(this.tablero.getCasilla(i,j).getNumeroEdificaciones() > 0){
                                     System.out.println(this.tablero.getCasilla(i, j).edificacionesDisponibles());
                                 }
                                 else{
                                     if(this.avatares.get(this.turno.getTurno()).getJugador().poseerGrupo(this.tablero.getCasilla(i,j).getGrupo())){
                                         System.out.println("-En la casilla " + this.tablero.getCasilla(i,j).getNombre() + ": ");
                                         System.out.println("Se puede construir una casa\n");
                                     }
                                 }
                             }
                         }
                     }
                 }


             }else{
                 for(int i=0;i<4;i++){
                     for(int j=0;j<10;j++){
                         if(this.tablero.getCasilla(i,j).getTipo().equals("Solar") && this.tablero.getCasilla(i,j).getNumeroEdificaciones()>0){
                               for(Edificaciones ed : this.tablero.getCasilla(i,j).getEdificaciones().values()){
                                   System.out.println(ed);
                                   k++;
                               }
                         }
                     }
                 }
             }
             if(k==0) System.out.println("Todavía no hay edificios construidos");
         }

         else if(eleccion.contains("estadisticas")){
             aux= eleccion.split("\\s+");

             if(aux.length == 2){                 //_____________ESTADISTICAS DE JUGADORES____________

                 for(Avatar av: this.avatares){
                     if(av.getJugador().getNombreJugador().equals(aux[1])){
                         System.out.println("{\nDinero invertido: " + av.getJugador().getDineroIntertido());
                         System.out.println("Pago de alquileres: " + av.getJugador().getPagoAlquileres());
                         System.out.println("Cobro de alquileres: " + av.getJugador().getCobroAlquileres());
                         if(av.getJugador().getNumeroVueltas() > 0){
                             System.out.println("Pasar por casilla de salida: " + av.getJugador().getNumeroVueltas() * Valores.PRECIOJUGADORVUELTA);
                         }
                         else System.out.println("El jugador aún no ha pasado por la casilla de salida");
                         System.out.println("Premios por inversiones o bote: "+ av.getJugador().getPremiosInversionesBote());
                         System.out.println("Veces que ha estado en la carcel: " + av.getJugador().getVecesEnCarcel() + "\n}");
                     }
                 }

             }

             else{
                  // ________________________ESTADISTICAS GLOBALES________________________
                  this.tablero.estadisticasGlobales();
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
                 if(this.avatares.get(this.turno.getTurno()).getJugador().getCompraEfectuada()){

                     System.out.println("No puede comprar más propiedades en este turno.");

                 }else if((this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().contains("Solar") ||
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

                 }else if((this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().contains("Solar") ||
                         this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().contains("Servicio") ||
                         this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().contains("Transporte")) 
                         && this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario(avatares)
                                 .equals(this.avatares.get(this.turno.getTurno()).getJugador())){
                     System.out.println("Esta casilla es de su propiedad. (Si no quiere realizar ninguna accion en ella escriba nada)");
                     Scanner opcion = new Scanner(System.in);  // Reading from System.in
                             System.out.println("\n->");
                             String n = opcion.nextLine(); // Scans the next token of the input as an int.
                             eleccion(n);
                             opcion.reset();
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
                 if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Transportes")
                         && !this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getDisponibilidad()
                         && !this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario(this.avatares).getNombreJugador().equals(
                         this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador())) {

                     double factor = 1;

                     int p = this.tablero.poseerTransportes();
                     if (p == 0) factor =  0.25;
                     if (p == 1) factor = 0.5;
                     if (p == 2) factor = 0.75;

                     System.out.println("Se han pagado " + Valores.OPERACIONTRANSPORTE * factor + " de alquiler.\n");
                 }
                 //SERVICIOS
                 if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getTipo().equals("Servicio")
                         && !this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getDisponibilidad()
                         && !this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getPropietario(this.avatares).getNombreJugador().equals(
                         this.avatares.get(this.turno.getTurno()).getJugador().getNombreJugador())) {
                     if (this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre().contains("1")) {
                         System.out.println("Se han pagado " + 4 * Valores.FACTORSERVICIO * this.dados.getValorSuma() + " de alquiler\n");
                     } else {
                         System.out.println("Se han pagado " + 10 * Valores.FACTORSERVICIO * this.dados.getValorSuma() + " de alquiler\n");

                     }
                 }
                 if (dados.getValorDados().get(0) == dados.getValorDados().get(1)) {
                     //comprobamos que esté como un jugador normal para que no le afecten las modificaciones
                    if(this.avatares.get(this.turno.getTurno()).getTipo().equals("coche") 
                            && !this.avatares.get(this.turno.getTurno()).getTipoEspecial()){
                        this.avatares.get(this.turno.getTurno()).getJugador().setCompraEfectuada(false);
                    }
                    this.avatares.get(this.turno.getTurno()).getJugador().setPuedeTirarOtraVez(true);
                 } else this.avatares.get(this.turno.getTurno()).getJugador().setPuedeTirarOtraVez(false);
             } else {
                 System.out.println("El usuario está en la cárcel. Escriba 'salir carcel' o 'acabar turno'");
             }
     }

     public void lanzarDados() {

         dados.tirarDados();

         if (this.avatares.get(this.turno.getTurno()).getJugador().getPuedeTirarOtraVez()
                 && !this.avatares.get(this.turno.getTurno()).getPierdeTurno()) {

             if(numTiradas<=4){

                 if(this.avatares.get(this.turno.getTurno()).getJugador().getEstarCarcel()==false){
                     //puede tirar hasta 4 veces si el numero es mayor que 4
                     if(this.avatares.get(this.turno.getTurno()).getTipo().equals("coche") &&
                             this.avatares.get(this.turno.getTurno()).getTipoEspecial()){
                         System.out.println("Está en el turno número "+(numTiradas+1));
                     }

                     System.out.println("El valor de los dados es " + dados.getValorDados().get(0) + "+" + dados.getValorDados().get(1));

                     //almacenamos el nombre de la casilla anterior para imprimirlo
                     this.avatares.get(this.turno.getTurno()).getJugador().setNombreCasillaAnterior(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre());

                     if(this.avatares.get(this.turno.getTurno()).getTipoEspecial()==true){
                         numTiradas++;
                         movimientoEspecial();
                     }else{
                         this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()), dados.getValorSuma());//movemos el avatar y obtenemos su nueva posicion
                         imprimirLanzarDados(this.dados.getValorSuma());
                     }
                 }
                   /* if(this.numTiradas!=4 || (this.avatares.get(this.turno.getTurno()).getTipo().equals("coche")
                            && this.avatares.get(this.turno.getTurno()).getJugador().getPuedeTirarOtraVez())){
                        if(this.avatares.get(this.turno.getTurno()).getTipoEspecial()){
                            imprimirLanzarDados(this.dados.getValorSuma());
                    }
                 }*/
                 else {
                    System.out.println("El usuario está en la cárcel. Escriba 'salir carcel' o 'acabar turno'");
                }
             }
         }else{
             System.out.println(Valores.ROJO +"¡El jugador no puede lanzar los dados!" + Valores.RESET);
         }
     }



     public void movimientoEspecial(){

         if(this.avatares.get(this.turno.getTurno()).getTipo().contains("pelota")){
             if(this.dados.getValorSuma()>4){

                 System.out.println("Has sacado un número mayor que 4, avanzas.");
                 int j=0; //variable auxiliar para restar la posicion del avatar

                 for(int i=1;i<=this.dados.getValorSuma();i++){
                     if(this.avatares.get(this.turno.getTurno()).getJugador().getEstarCarcel()==false){
                         if((i>4 && (i%2)!=0) || (i==this.dados.getValorSuma()) ){//comprobamos que sea impar o el ultimo valor

                             System.out.println(" ");
                             System.out.println("El jugador lleva "+i+" posiciones desde la casilla de origen.");
                             this.avatares.get(this.turno.getTurno()).getJugador().setNombreCasillaAnterior(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre());
                             this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()),(i-j));
                             imprimirLanzarDados(i-j);
                             j=i;
                         }
                     }
                 }
             }else{

                 System.out.println("Has sacado un número menor o igual que 4, retrocede.");
                 int j=0; //variable auxiliar para restar la posicion del avatar

                 for(int i=1;i<=this.dados.getValorSuma();i++){
                     if(this.avatares.get(this.turno.getTurno()).getJugador().getEstarCarcel()==false){
                         if(i%2!=0 || (i==this.dados.getValorSuma()) ){//comprobamos que sea impar o el ultimo valor

                             System.out.println(" ");
                             System.out.println("El jugador ha retrocedido "+i+" posiciones desde la casilla de origen.");
                             this.avatares.get(this.turno.getTurno()).getJugador().setNombreCasillaAnterior(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre());
                             this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()),-(i-j));
                             if(i!=this.dados.getValorSuma()){//en este caso se imprime en lanzarDados
                                 imprimirLanzarDados(i-j);
                             }
                             j=i;
                         }
                     }
                 }
             }
             
            if(this.avatares.get(this.turno.getTurno()).getJugador().getEstarCarcel()==true){
                             System.out.println("El jugador está en la cárcel, no se puede seguir moviendo.");
            }
             
         }

         if(this.avatares.get(this.turno.getTurno()).getTipo().contains("coche")){


             if(this.dados.getValorSuma()>4 && numTiradas!=4 && this.avatares.get(this.turno.getTurno()).getJugador().getPuedeTirarOtraVez()){
                 this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()),this.dados.getValorSuma());
                 imprimirLanzarDados(this.dados.getValorSuma());
                 System.out.println("Valor mayor que 4, ¿quiere seguir tirando?. (si|no)");
                 Scanner opcion = new Scanner(System.in);  // Reading from System.in
                         System.out.println("\n->");
                         String n = opcion.nextLine(); // Scans the next token of the input as an int.
                         opcion.reset();
                 if(n.contains("si")){
                     this.avatares.get(this.turno.getTurno()).getJugador().setPuedeTirarOtraVez(true);
                     lanzarDados();
                     //movimientoEspecial(numTiradas++);
                 }else{
                     this.avatares.get(this.turno.getTurno()).getJugador().setPuedeTirarOtraVez(false);
                 }

             }else{
                 if(numTiradas==4){
                     System.out.println("Ya ha lanzado 4 veces los dados.");
                     //almacenamos el nombre de la casilla anterior
                     this.avatares.get(this.turno.getTurno()).getJugador().setNombreCasillaAnterior(this.avatares.get(this.turno.getTurno()).getJugador().getCasillaActual().getNombre());
                     //movemos al avatar e imprimimos los datos de la ultima casilla
                     this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()),(this.dados.getValorSuma()));
                     imprimirLanzarDados(this.dados.getValorSuma());
                     this.avatares.get(this.turno.getTurno()).getJugador().setPuedeTirarOtraVez(false);
                 }else{
                     System.out.println("Ha sacado un número menor o igual que 4, no puede seguir tirando y retrocede.");
                     System.out.println("Además, pierde el turno durante 2 rondas.");
                     this.tablero.desplazarAvatar(this.avatares.get(this.turno.getTurno()),-(this.dados.getValorSuma()));
                     this.avatares.get(this.turno.getTurno()).getJugador().setPuedeTirarOtraVez(false);

                     this.avatares.get(this.turno.getTurno()).setPierdeTurno(true);

                 }
             }
         }

     }
 }
