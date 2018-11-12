package monopoly;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Valores {

    //Colores
    public static final String NEGRO =  "\u001b[30m";
    public static final String ROJO =  "\u001b[31m";
    public static final String VERDE =  "\u001b[32m";
    public static final String AMARILLO =  "\u001b[33m";
    public static final String AZUL =  "\u001b[34m";
    public static final String MAGENTA =  "\u001b[35m";
    public static final String CIAN =  "\u001b[36m";
    public static final String BLANCO =  "\u001b[37m";
    public static final String RESET =  "\u001b[0m";
    public static final String SUBRAYADO = "\033[4;37m";
    public static final String FONDO = "\033[47m";


    public static final String[] nombres = {"Salida", "N.Zelanda","Caja 1", "Australia",
                                            "Impuestos 1", "RENFE", "Sudáfrica",
                                            "Suerte1", "Kenia", "CaboVerde", "Carcel", "Brasil",
                                            "IBERDROLA", "Argentina", "Venezuela", "Puerto",
                                            "Canadá", "Caja 2", "EE.UU", "México", "Parking",
                                            "Finlandia", "REPSOL", "Rusia", "Estonia",
                                            "Aeropuerto", "China", "Suerte 2", "Japón", "India", "IrCarcel",
                                            "España", "Caja3", "Inglaterra", "Impuestos 2", "NASA" ,"Yemen",
                                            "Suerte3", "E.Árabes", "Qatar"};


    //Precios de las casillas (iniciales) que se mantienen constantes
    public static final double PRECIOTOTALSOLARES = 78895.51;
    public static final float PRECIOINICIALGRUPO1 = 1200;
    public static final double PRECIOJUGADORVUELTA = PRECIOTOTALSOLARES/22;
    public static final double PRECIOTRANSPORTES = PRECIOJUGADORVUELTA;
    public static final double PRECIOSERVICIOS = (PRECIOJUGADORVUELTA*0.75);   
    
    //Precios de las casillas servicios y transporte (a pagar)
    public static final double FACTORSERVICIO = PRECIOJUGADORVUELTA-200;
    public static final double OPERACIONTRANSPORTE = PRECIOJUGADORVUELTA;
    
    //Casillas de impuestos
    public static final double TASAIMPUESTOS1 = PRECIOJUGADORVUELTA;
    public static final double TASAIMPUESTOS2 = PRECIOJUGADORVUELTA/2;
    
    //Tasa para salir de la cárcel
    public static final double PAGOSALIRCARCEL = PRECIOJUGADORVUELTA*25;
}
    
