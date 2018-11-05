package monopoly;

import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Avatar av1 = new Avatar("coche", 1, "Pedro");
        Avatar av2 = new Avatar("esfinge", 2, "Pedro");

        ArrayList<Avatar> avatares = new ArrayList<>();
        avatares.add(av1); avatares.add(av2);


        Tablero tablero = new Tablero(avatares);

        Scanner reader = new Scanner(System.in);  // Reading from System.in


        System.out.println(tablero);

        tablero.desplazarAvatar(av1, 5);

        System.out.println(tablero);

        System.out.println(av1);
        /*System.out.println("\n->");
        String n = reader.next(); // Scans the next token of the input as an int.
        reader.close();
        if(n.equals("info")){
            System.out.println(av1.toString());
        }
        else{
            System.out.println("hola");
        }*/

    }
}
