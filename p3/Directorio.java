import java.util.ArrayList;

/* La clase Directorio consiste en un Fichero
 * que puede tener dentro otros ficheros
 */
public class Directorio extends Fichero
{
    /* Constructor de Directorio*/
	Directorio(String nombre) throws ExcepcionArbolFicheros{
        if(nombreCorrecto(nombre)) {
            this.nombre = nombre;
            hijos = new ArrayList<Fichero>();
        }else{
            throw new errorNombre(nombre);
        }
    }
	
	/* Devuelve verdad si y solo si file no contiene ".", "..",
	 * "/" o " "
	 */
    boolean nombreCorrecto(String file){
        if(file.equals(".") || file.equals("..") || file.contains("/") || file.contains(" ")){
            return false;
        }else{
            return true;
        }
    }
    
    /* Devuelve el tamaño de los ficheros que contiene el Directorio*/
    int tamanyo()
    {
        int tamanyo = 0;
        for (Fichero aux : hijos) {
            tamanyo += aux.tamanyo();
        }
        return tamanyo;
    }

    /* Muestra por pantalla la lista de todos los ficheros que contiene el Directorio*/
    void listarHijos()
    {
        for (Fichero aux : hijos)
        {
            System.out.println(aux.miNombre());
        }
    }

    /* Devuelve falso, porque un Directrio no se puede cambiar su tamaño*/
    boolean cambiarTamanyo(int nuevo)
    {
        return false;
    }

    /* Devuelve verdad, porque un Directorio si que se puede abrir */
    boolean sePuedeAbrir()
    {
        return true;
    }

    /* Añade el Fichero f al Directorio*/
    void anyadirHijo(Fichero f)
    {
        hijos.add(f);
    }
}
