
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Ruta {
    LinkedList<Fichero> path;
    int elementos;

/*
 * Constructor Ruta
 * Se inicializa con el directorio raiz
 * path contiene la lista de directorios abiertos
 */
    Ruta(Directorio d) throws ExcepcionArbolFicheros
    {
            path = new LinkedList<Fichero>();
            path.add(d);
            elementos = 1;
    }

    /*
     *  PRE: Fichero actual debe ser un directorio
     *  POST: Devuelve la posicion del Fichero con nombre busqueda dentro de los hijos del directorio Fichero actual
     *          Devuelve -1 si no existe el elemento busqueda
     */
    int existeFichero( Fichero actual, String busqueda)
    {
        int posicion = 0;
        Iterator<Fichero> it = actual.hijos.iterator();
        while (it.hasNext())
        {
            Fichero aux = it.next();
            if( busqueda.equals( aux.miNombre() ) )
            {
                return posicion;
            }
            posicion++;
        }
        return -1;
    }

    /*
     * PRE: Fichero actual debe ser un directorio
     * POST: Comprueba si el elemento busqueda existe en el directorio actual
     *       Si existe comprueba si se trata de un directorio.
     *       Si es un directorio se añade a la lista de ficheros path como ultimo elemento.
     */
    boolean abrirDirectorio( Fichero actual, String busqueda , List<Fichero> path) throws ExcepcionArbolFicheros{
            boolean error = false;
            int posicion = existeFichero(actual, busqueda);
            if (posicion < 0) {
                error = true;
                throw new NoExisteDirectorio( busqueda );
            } else {
                if (actual.hijos.get(posicion).sePuedeAbrir()) {
                    path.add(actual.hijos.get(posicion));
                } else {
                    error = true;
                    throw new NoEsDirectorio( busqueda );
                }
            }
            return error;


    }

    boolean nombreCorrecto(String file){
        if(file.equals(".") || file.equals("..") || file.contains("/") || file.contains(" ")){
            return false;
        }else{
            return true;
        }
    }

    /*
     * Lista los elementos del directorio actual
     */
    void ls()
    {
        path.get(elementos - 1).listarHijos();
    }

    /*
     * Muestra la ruta actual (contenido de la variable path)
     */
    String pwd()
    {
          String ruta = "";
          for(Fichero aux : path)
          {
              ruta += aux.miNombre() + "/";
          }
          return ruta;
    }

    /*
     * Comprueba la ruta del directorio que se quiere abrir.
     * Si es "/" se eliminan todos los elementos de la variable path excepto el primero (raiz)
     * Si es "." no se hace nada
     * Si es ".." se elimina el ultimo directorio de la lista path
     * Si el destino no contiene el caracter "/" se busca el directorio en el directorio actual
     * Si el destino tiene "/" se considera una ruta absoluta y se intenta abrir.
     *      Se comprueba uno a uno los directorios y se abren en una lista auxiliar. Si la ruta es correcta se copia
     *      esta nueva ruta en la lista de la ruta (path)
     */
    void cd(String path) throws ExcepcionArbolFicheros{
        if(path.equals("/")){
            // deja solo el primer directorio
            while(elementos!=1)
            {
                this.path.remove(elementos-1);
                elementos--;
            }
        }else if(path.equals(".")){
            //Nada
        }else if(path.equals("..")){
            this.path.remove(elementos-1);
            elementos--;
        }else if(path.contains("/")){
            //          ruta completa

                String[] directorios = path.split("/");
                LinkedList<Fichero> aux = new LinkedList<Fichero>();
                if( !this.path.get(0).miNombre().equals(directorios[0]) ){
                    //ruta completa no empieza por raiz ("/")
                    throw new rutaSinRaiz(path);
                }else {
                    boolean rutaEsCorrecta = true;
                    aux.add(this.path.get(0));
                    //recorre todos
                    int i = 1;
                    for (i = 1; i < directorios.length; i++) {
                        if(     abrirDirectorio(aux.get(i - 1), directorios[i], aux)    ){
                            //error en ruta
                            rutaEsCorrecta = false;
                        }
                    }
                    if(rutaEsCorrecta){
                        this.path.clear();
                        this.path = (LinkedList<Fichero>)aux.clone();
                        this.elementos = directorios.length;
                    }
                }

        }else{
            // se busca en el directorio actual
            if( !abrirDirectorio(this.path.get(elementos-1), path , this.path)  ) {
                // si se abre sin error el directorio aumenta el tamañp del path
                elementos++;
            }
        }
    }

    /*
     * Muestra el tamaño de un fichero
     * Si element no contiene "/" se busca el fichero en el directorio actualç
     * Si element tiene "/" se considera una ruta absoluta. Se comprueba que la ruta sea correcta y si existe el
     * fichero destino
     */
    void stat(String element) throws ExcepcionArbolFicheros{

            if (    !element.contains("/")  ) {
                // directorio actual
				if(element.equals(".")){
					System.out.println("Tamaño: " + this.path.get(elementos - 1).tamanyo());
				}else{
                	int posicion = existeFichero(this.path.get(elementos - 1), element);
                	if (posicion >= 0) {
                    	//fichero existe
                    	System.out.println("Tamaño: " + this.path.get(elementos - 1).hijos.get(posicion).tamanyo());
                	} else {
                    	throw new NoExisteFichero(element);
                	}
				}
            } else {
                //  ruta completa
                String[] path = element.split("/");
                LinkedList<Fichero> aux = new LinkedList<Fichero>();
                if (!this.path.get(0).miNombre().equals(path[0])) {
                    throw new rutaSinRaiz(element);
                } else {

                    aux.add(this.path.get(0));
                    //recorre todos menos el ultimo
                    int i = 1;
                    for (i = 1; i < path.length - 1; i++) {
                        abrirDirectorio(aux.get(i - 1), path[i], aux);
                    }

                    int posicion = existeFichero(aux.get(i - 1), path[i]);
                    if (posicion >= 0) {
                        // existe el fichero
                        System.out.println("Tamaño: " + aux.get(i - 1).hijos.get(posicion).tamanyo());
                    } else {
                        throw new NoExisteFichero(element);
                    }
                }
            }

    }

    /*
     * Si el archivo existe cambia su tamaño
     * Si no crea archivo
     */
    void vim(String file, int size) throws ExcepcionArbolFicheros{

            boolean completado = false;
            boolean fin = false;
            Iterator<Fichero> it = path.get(elementos - 1).hijos.iterator();
            while (it.hasNext() && !fin) {
                Fichero aux = it.next();
                if (file.equals(aux.miNombre())) {
                    fin = true;
                    completado = aux.cambiarTamanyo(size);
                    if (!completado) {
                        throw new CambiarTamanyoDir();
                    }
                }

            }
            if(!fin){
                //crea el fichero porque no existe
                if(nombreCorrecto(file)){
                    path.get(elementos - 1).hijos.add(new Archivo(file, size));
                }else{
                    throw new errorNombre(file);
                }
            }

    }

    /*
     * Crea un directorio nuevo
     */
    void mkdir(String dir) throws ExcepcionArbolFicheros{

            if (existeFichero(path.get(elementos - 1), dir) < 0) {
                // no existe el directorio
                if(nombreCorrecto(dir)) {
                    path.get(elementos - 1).hijos.add(new Directorio(dir));
                }else{
                    throw new errorNombre(dir);
                }
            } else {
                throw new FicheroRepetido(dir);
            }

    }

    /*
     * Crea un fichero enlace dest que enlaza a orig
     * Si orig es una ruta absoluta comprueba si es correcta y si lo es crea el enlace.
     * Si no es ruta absoluta busca orig en el directorio actual
     */
    void ln(String orig, String dest) throws ExcepcionArbolFicheros{

            if (!orig.contains("/")) {
                // directorio actual
                int posicion = existeFichero(path.get(elementos - 1), orig);
                if (posicion >= 0) {
                    path.get(elementos - 1).hijos.add(
                            new Enlace(dest, path.get(elementos - 1).hijos.get(posicion)));
                } else {
                    throw new NoExisteFichero(orig);
                }
            } else {
                String[] path = orig.split("/");
                LinkedList<Fichero> aux = new LinkedList<>();
                if (!this.path.get(0).miNombre().equals(path[0])) {
                    //raiz no coincide
                    throw new rutaSinRaiz(orig);
                } else {

                    aux.add(this.path.get(0));
                    //recorre todos menos el ultimo
                    int i = 1;
                    for (i = 1; i < path.length - 1; i++) {
                        abrirDirectorio(aux.get(i - 1), path[i], aux);
                    }

                    int posicion = existeFichero(aux.get(i - 1), path[i]);
                    if (posicion >= 0) {
                        // añade en directorio actual
                        this.path.get(elementos - 1).hijos.add(
                                new Enlace(dest, aux.get(i - 1).hijos.get(posicion)));
                    } else {
                        throw new NoExisteFichero(orig);
                    }
                }
            }

    }

    /*
     * Elimina un fichero
     * Si es ruta absoluta comprueba si es correcta y si existe el fichero lo elimina
     * Si no busca el fichero en el directorio actual y lo elimina
     */
    void rm(String e) throws ExcepcionArbolFicheros{

            if (!e.contains("/")) {

                // directorio actual
                int posicion = existeFichero(path.get(elementos - 1), e);
                if (posicion >= 0) {
                    // elimina elemento
                    path.get(elementos - 1).hijos.remove(posicion);
                } else {
                    throw new NoExisteFichero(e);
                }

            } else {

                // ruta completa
                String[] path = e.split("/");
                LinkedList<Fichero> aux = new LinkedList<>();
                if (!this.path.get(0).miNombre().equals(path[0])) {
                    //raiz no coincide
                    //error
                    throw new rutaSinRaiz(e);
                } else {

                    aux.add(this.path.get(0));
                    //recorre todos menos el ultimo
                    int i = 1;
                    for (i = 1; i < path.length - 1; i++) {
                        abrirDirectorio(aux.get(i - 1), path[i], aux);
                    }

                    int posicion = existeFichero(aux.get(i - 1), path[i]);
                    if (posicion >= 0) {
                        // elimna elemento
                        aux.get(i - 1).hijos.remove(posicion);
                    } else {
                        throw new NoExisteFichero(e);
                    }
                }

            }

    }

}
