
/* Clase archivo que consiste en un archivo con tamaño y nombre */
public class Archivo extends Fichero
{
    //campos archivo
    int tamanyo;

    /* Constructor*/
    Archivo(String nombre, int tamanyo)
    {
        this.nombre = nombre;
        this.tamanyo = tamanyo;
    }

    int tamanyo()
    {
        return tamanyo;
    }

    void listarHijos()
    {
        /* Un archivo no contiene ficheros, por lo que no muestra nada */
    	System.out.println("No se puede...............");
    }

    /* Funcion para cambiar el tamaño */
    boolean cambiarTamanyo(int nuevo)
    {
        tamanyo = nuevo;
        return true;
    }

    /* Un fichero no se puede abrir, devuelve falso siempre */
    boolean sePuedeAbrir()
    {
        return false;
    }
}
