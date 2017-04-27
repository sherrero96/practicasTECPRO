import java.util.ArrayList;

/* Un enlace consiste en un Fichero que puede enlazar a un directorio
 * a otro enlace o a un Archivo
 */
public class Enlace extends Fichero
{
    //campos enclace

    /*
     *  CONSTRUCTOR ENLACE
     *  Enlace como fichero tiene un solo hijo, el elemento al que enlaza.
     */
    Enlace(String nombre, Fichero f)
    {
        this.nombre = nombre;
        hijos = new ArrayList<Fichero>();
        hijos.add(f);
    }

    /*
     *  Para evitar redundancias a la hora de calcular los tamaños se considera el tamaño de los enlaces nulo.
     */
    int tamanyo()
    {
        return 0;
    }

    /*
     * Enlace solo tiene un hijo (elemento enlazado)
     * LLama a la funcion listarHijos de dicho elemento enlazado
     */
    void listarHijos()
    {
        hijos.get(0).listarHijos();
    }

    boolean cambiarTamanyo(int nuevo)
    {
        return hijos.get(0).cambiarTamanyo(nuevo);
    }

    boolean sePuedeAbrir()
    {
        return false;
    }

}
