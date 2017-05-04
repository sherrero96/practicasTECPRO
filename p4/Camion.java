//Autores: Sergio Herrero Barco 698521
//         Alex Oarga Hategan   718123

/*La clase Camion, heredada de Almacen, ya que puede contener dentro otros
 * objetos, representa un camion con una capacidad*/
public class Camion extends Almacen {

	/* Constructor de la clase Camion*/
	public Camion (double capacidad) {
		this.capacidad = capacidad;
	}

	
	/* Un camion no puede almacenar directamente Seres Vivos o Toxicos. Solamente
	 * puede guardar directamente GENERICOS (Productos y Contenedores). Por eso, 
         * el objeto que se le pasa es de tipo Generico
	 */
	public boolean guardar(Generico g) {
		/*Llama a la funcion guardar de la clase heredada (Almacen)*/
		return super.guardar(g.MiVolumen());
	}

}
