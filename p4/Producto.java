//Autores:  Sergio Herrero Barco 698521
//          Alex Oarga Hategan   718123

/* La clase Producto representa un producto que no es ni un ser Vivo ni un toxico.
 * Implementa la clase Generico
 */
public class Producto implements Generico {

	/* Un producto se caracteriza por tener un nombre y un volumen*/
	private String nombre;  //Nombre del producto
	private double volumen; //Volumen del producto

	/* Constructor de la clase Volumen */
	public Producto(double volumen, String nombre) {
		this.nombre = nombre;
		this.volumen = volumen;
	}

	/* Funcion que devuelve el volumen del Producto*/
	public double MiVolumen () {
		return this.volumen;
	}

}
