import java.util.List;

/* La clase fichero consiste en un fichero que puede ser un Directorio
 * un Archivo o un enlace
 */
public abstract class Fichero {
    String nombre;
    List<Fichero> hijos;

    abstract int tamanyo();
    abstract void listarHijos();
    abstract boolean cambiarTamanyo(int nuevo);
    abstract boolean sePuedeAbrir();

    String miNombre()
    {
        return nombre;
    }
}
