package monopoly;

import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public boolean isPrintableChar( char c ) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of( c );
        return (!Character.isISOControl(c)) &&
                c != KeyEvent.CHAR_UNDEFINED &&
                block != null &&
                block != Character.UnicodeBlock.SPECIALS;
    }

    public static void main(String[] args) {

        Avatar av1 = new Avatar();
        Avatar av2 = new Avatar();

        ArrayList<Avatar> avatares = new ArrayList<>();
        avatares.add(av1); avatares.add(av2);


        Tablero tablero = new Tablero(avatares);

        Scanner reader = new Scanner(System.in);  // Reading from System.in

        System.out.println(tablero);

/*
        System.out.println("\n->");
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
