/*
 *  Clase base jerarquia excepciones
 */
public abstract class ExcepcionArbolFicheros extends Exception
{
}

/*
 * Excepcion al intentar cambia rl tamaño de un directorio.
 */
class CambiarTamanyoDir extends ExcepcionArbolFicheros
{

    public String toString()
    {
        return "No se puede cambiar el tamaño de un directorio";
    }
}

/*
 * Clase abstracta para los  errores en el nombre
 */
abstract class ErrorRuta extends ExcepcionArbolFicheros
{
    private String file;
    public ErrorRuta(String filename){
        file = filename;
    }
    public String nombre(){
        return file;
    }
}

/*
 * Clase abstracta para los errores con ficheros
 */
abstract class ErrorFichero extends ExcepcionArbolFicheros
{
    private String file;
    public ErrorFichero(String filename){
        file = filename;
    }
    public String nombre(){
        return file;
    }
}

/*
 * Excepcion: no existe el directorio al que se intenta acceder
 */
class NoExisteDirectorio extends ErrorFichero
{
    public NoExisteDirectorio(String fileName){
        super(fileName);
    }

    public String toString()
    {
        return "No existe el directorio: " + nombre();
    }
}

/*
 * Excepcion: no existe un elemento cualquiera al que se intenta acceder
 */
class NoExisteFichero extends ErrorFichero
{
    public NoExisteFichero(String fileName){
        super(fileName);
    }

    public String toString()
    {
        return "No existe el fichero: " + nombre();
    }
}

/*
 * Excepcion: si se intenta acceder a un elemento que no es un directorio
 */
class NoEsDirectorio extends ErrorFichero
{
    public NoEsDirectorio(String fileName){
        super(fileName);
    }

    public String toString()
    {
        return nombre() + " no es un directorio";
    }
}

/*
 * Excepcion: cuando se introduce una ruta absoluta que no comienza por el caracter '/' (raiz)
 */
class rutaSinRaiz extends ErrorRuta
{
    public rutaSinRaiz(String fileName){
        super(fileName);
    }

    public String toString()
    {
        return nombre() + " es una ruta completa pero no comienza por el directorio raiz: /";
    }
}

/*
 * Excepcion: si filename es un nombre no permitido
 */
class errorNombre extends ErrorRuta
{
    public errorNombre(String fileName){
        super(fileName);
    }

    public String toString()
    {
        return "'"+nombre()+"' " + " El nombre no puede ser \".\", \"..\" o contener espacios o el caracter '/'";
    }
}

class FicheroRepetido extends ErrorFichero
{
    public FicheroRepetido(String nombre)
    {
        super(nombre);
    }

    public String toString()
    {
        return "Ya existe un fichero con nombre: " + nombre() + " en el destino";
    }

}