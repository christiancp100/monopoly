package monopoly;

import java.util.ArrayList;

public class Menu {

    private InterpreteComandos interprete;
    private ArrayList<Avatar> avatares;
    private boolean partidaIniciada;


    //menu para el desarrollo del juego
    public Menu(InterpreteComandos interprete, ArrayList<Avatar> avatares){
        this.interprete = interprete;
        this.avatares = avatares;
        this.partidaIniciada=false;
    }

    public void start(){
        
        String opcion;
        do{
            if(this.avatares.size()==0){
                System.out.println("Todavía no hay jugadores registrados.¿A qué estáis esperando?\n");
                opcion=interprete.input();
                //comprobamos que la unica opcion disponible para el usuario sea registrar usuarios.
                if(opcion.contains("crear jugador")){
                    interprete.eleccion(opcion);
                }
                else{
                    System.out.println("Opción no válida. Registre jugadores.\n");
                }
            }
            else if(this.avatares.size()==1){
                System.out.println("Sola hay un jugador en el tablero.¿Pretendes jugar solo?\n");
                System.out.println("¡Añade al menos un amigo más!");
                opcion=interprete.input();
                //como en el caso anterior, solo le damos la opción de registrar jugadores
                if(opcion.contains("crear jugador")){
                    interprete.eleccion(opcion);
                }
                else{
                    System.out.println("Opción no válida. Registre jugadores.\n");
                }
            }
            else if(this.avatares.size()>1){
                System.out.println("Hay "+this.avatares.size()+" jugadores en el tablero");
                System.out.println("Opciones disponibles:\n"+
                        "   -crear jugador {nombreJugador} {tipoAvatar}\n"+    
                        "   -describir jugador {nombreJugador}\n"+
                        "   -listar jugadores\n"+
                        "   -listar avatares\n"+
                        "   -ver tablero\n"+
                        "   -iniciar partida\n"
                        );

                opcion=interprete.input();
                interprete.eleccion(opcion);

             if(opcion.contains("iniciar partida")){
                    partidaIniciada=true;
                }
            }
        }while(!partidaIniciada);
        
    }
    
    public void desarrolloPartida(){
        
        String opcion;
    
        do{
            System.out.println("->ver opciones");
            opcion=interprete.input();

            if(opcion.equals("ver opciones")){
                System.out.println("Opciones:\n"+
                        "   -describir jugador {nombreJugador}\n"+
                        "   -jugador (muestra quien tiene el turno)\n"+
                        "   -lanzar dados\n"+
                        "   -listar jugadores\n"+
                        "   -listar avatares\n"+
                        "   -describir {nombreCasilla}\n"+
                        "   -describir avatar {simboloAvatar}\n"+
                        "   -comprar {propiedad}\n"+
                        "   -salir carcel\n"+
                        "   -listar enventa\n"+
                        "   -ver tablero\n"+
                        "   -edificar {tipo edificación}\n"+
                        "   -cambiar modo\n"+
                        "   -listar edificios\n"+
                        "   -listar edificios {grupo}\n"+
                        "   -estadisticas {jugador}\n"+
                        "   -estadisticas\n"+
                        "   -acabar turno\n");

            }else{
                interprete.eleccion(opcion);
            }
        //TODO añadir comprobacion de bancarrota aqui
        }while(!opcion.equals("acabar turno") ) ;
    }
}
