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


    public static final String[] nombres = {
                                            "Salida", "N.Zelanda","Caja 1", "Australia",
                                            "Impuestos 1", "RENFE", "Sudáfrica",
                                            "Suerte1", "Kenia", "CaboVerde", "Carcel", "Brasil",
                                            "IBERDROLA", "Argentina", "Venezuela", "Puerto",
                                            "Canadá", "Caja 2", "EE.UU", "México", "Parking",
                                            "Finlandia", "REPSOL", "Rusia", "Estonia",
                                            "Aeropuerto", "China", "Suerte 2", "Japón", "India", "IrCarcel",
                                            "España", "Caja 3", "Inglaterra", "Impuestos 2", "NASA" ,"Yemen",
                                            "Suerte3", "E.Árabes", "Qatar"
                                            };

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
    public static final double PAGOSALIRCARCEL = PRECIOJUGADORVUELTA*5;
    
        //Mensajes de las tarjetas suerte y caja de comunidad
    public static final String[] TARJETAS_SUERTE = {
        "1.-Ve al aeropuerto y coge un avión. Si pasas por la casilla de Salida cobras.",
        "2.-Decides hacer un viaje con tu familia. Avanza hasta EE.UU",
        "3.-Vendes tu billete de avión para Estonia. Cobra 5.000€",
        "4.-Ve a China. Si pasas por la casilla de Salida cobras.",
        "5.-Los acreedores te persiguen por impago,ve a la Cárcel. Si pasas por la casilla de Salida no cobras.",
        "6.-¡Has ganado la lotería! Recibe 100.000€",
        "7.-Paga 15000€ por la matrícula del colegio privado.",
        "8.-El aumento del impuesto sobre bienes inmuebles afecta a todas tus propiedades. Paga 400.000€ por casa, 1.150.000€ por hotel, 200.000€ por piscina y 750.000€ por pista de deportes.",
        "9.-Ve a Kenia. Si pasas por la casilla de Salida cobras.",
        "10.-Has sido elegido presidente de la junta directiva. Paga a cada jugador 2.500€.",
        "11.-¡Hora punta de tráfico! Retrocede tres casillas.",
        "12.-Te multan por usar el móvil mientras conduces. Paga 1.500€.",
        "13.-Beneficio por la venta de tus acciones. Recibe 150.000€.",
        "14.-Avanza hasta la casilla de transporte más cercana. Si no tiene dueño, puedes comprársela a la banca. Si tiene dueño, paga al dueño el doble de la operación indicada."
        };
        
    public static final String[] TARJETAS_CAJA = {
        "1.-Paga 5.000€ por un fin de semana en un balneario de 5 estrellas.",
        "2.-Te investigan por fraude de identidad. Ve a la Cárcel. Ve directamente sin pasar por la casilla de Salida y sin cobrar.",
        "3.-Colócate en la casilla de Salida y cobra.",
        "4.-Tu compañía de Internet obtiene beneficios. Recibe 200.000€.",
        "5.-Paga 10.000€ por invitar a todos tus amigos a un viaje a Francia.",
        "6.-Devolución de Hacienda. Cobra 5.000€.",
        "7.-Retrocede hasta E.Árabes para comprar antigüedades exóticas.",
        "8.-Alquilas a tus compañeros una villa en Cannes durante una semana. Paga 2.000€ a cada jugador.",
        "9.-Recibe 100.000€ de beneficios por alquilar los servicios de tu jet privado.",
        "10.-Ve a Brasil para disfrutar de los Carnavales. Si pasas por la casilla de Salida cobras."
        };

    //Maximo numero de edificios

    public static final int MAXCASAS = 4;
    public static final int MAXHOTELES = 2;
    public static final int MAXPISCINAS = 2;
    public static final int MAXPISTAS = 2;

}
    
