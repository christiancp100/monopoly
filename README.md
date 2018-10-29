# MONOPOLY ![CI status](https://img.shields.io/badge/V1-primera_entrega-brightgreen.svg)

En este proyecto se desarrollará una versión funcional y completa del juego clásico, que se extenderá con una serie de nuevas reglas para hacerlo más interesante. Además, la interacción entre los jugadores y el juego tendrá lugar a través de una interfaz de texto, con una consola en la que los jugadores introducen una serie de comandos en cada turno de juego, realizando así las acciones que consideran convenientes.

## Paquetes

### monopoly (clases)
* Tablero
* Casilla
* Jugador
* Avatar

`package monopoly`

### principal (clase)
* Main

## Descripción de las clases de `monopoly`

### Casilla:

* Atributos:

  * ```java
    private String tipo; //solar, transporte, impuestos, suerte, comunidad, servicios, carcel, parking, salida, ir  a la carcel
    private String color;
    private int grupo;
    private String nombre;
    private float precio; //Futura modificacion aqui
    private boolean hipotecada;
    private int numeroCasas;
    private int numeroHoteles;
    ```

* Constructores:

  * ```java
    public Casilla()
    
    public Casilla(String tipo, String color, String nombre, float precio)
    ```

* Getters:

* ```java
  public String getTipo()
  public String getColor() 
  public String getNombre()
  public String getGrupo()
  public float setPrecio(int grupo)
  ```

* Setters:

* ```java
  public void setGrupo(int grupo)
  public void setTipo(String tipo)
  public void setColor(String color)
  public void setNombre(String nombre)
  public void setPrecio(int grupo)
  ```

### Tablero:

* Constructor:

  * ```java
    public Tablero(ArrayList<Avatar> avatares, ArrayList<Jugador> jugadores)
    ```

* Métodos:

  * ```java
    public void imprimir()
    ```


## License
[Reconocimiento-NoComercial 4.0 Internacional](http://creativecommons.org/licenses/by-nc/4.0/)