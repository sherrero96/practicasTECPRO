//Autores:  Sergio Herrero Barco 698521
//          Alex Oarga HAtegan   718123

/*La clase Contenedor es derivada de la clase Almacen, ya que puede almacenar cosas.
 * Implementa la interfaz Generico porque se puede guardar directamente en un camion
 * o en otro Contenedor. Ademas, es una clase generica ya que el contenedor puede
 * contener objetos de tipo Generico (Contenedores y productos), SerVivo y Toxico.
 */
public class Contenedor<T extends Cosa> extends Almacen implements Generico {

	/*Un contenedor se caracteriza por poseer un volumen y una capacidad*/
	private double volumen; //Volumen del Contenedor
	//Al ser derivada de la clase Almacen, posee el campo capacidad

	/* Constructor de la clase Contenedor. El contenedor tendra una capacidad "capacidad"
	 * y asi mismo tendra un volumen "capacidad"
	 */
	public Contenedor (double capacidad) {
		this.volumen = this.capacidad = capacidad;
	}

	/* Devuelve el volumen del Contenedor*/
	public double MiVolumen () {
		return this.volumen;
	}

	
	/*Funcion que almacena un dato de tipo T (que sera una Cosa), llamando al metodo
	 * guardar de la clase Almacen.
	 */
	public boolean guardar(T t) {
		Cosa c = (Cosa) t;  //c va a ser igual a t interpretado como una cosa
		return super.guardar(c.MiVolumen()); //Se llama a la funcion de Almacen
	}

}
