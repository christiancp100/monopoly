# MONOPOLY ![CI status](https://img.shields.io/badge/V1-primera_entrega-brightgreen.svg)

En este proyecto se desarrollará una versión funcional y completa del juego clásico, que se extenderá con una serie de nuevas reglas para hacerlo más interesante. Además, la interacción entre los jugadores y el juego tendrá lugar a través de una interfaz de texto, con una consola en la que los jugadores introducen una serie de comandos en cada turno de juego, realizando así las acciones que consideran convenientes.

## Paquetes

### monopoly (clases)
* Tablero
* Casilla
* Jugador
* Avatar

`import monopoly`
### principal (clase)
* Main

## Descripción de las clases de `monopoly`

```java
public Class Casilla{
    private String tipo;
    private String grupo;
    private Valores precio;
    private boolean hipotecada;
    private int numeroCasas;
    private int numeroHoteles;
    //Getters y Setters
    public float getPrecio();
    public void setPrecio();
}

```
```java
public Class Casilla{
    private String tipo;
    private String grupo;
    private Valores precio;
    private boolean hipotecada;
    private int numeroCasas;
    private int numeroHoteles;
}

```


## License
[Reconocimiento-NoComercial 4.0 Internacional](http://creativecommons.org/licenses/by-nc/4.0/)