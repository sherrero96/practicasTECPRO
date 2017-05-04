//Autores:  Sergio Herrero Barco 698521
//          Alex Oarga Hategan   718123

/* La clase Toxico representa el objeto Toxico, que implementa la interfaz
 * cosa, ya que puede ser guardado.
 */
public class Toxico implements Cosa {


	/*Un Toxico se caracteriza por tener un nombre y un volumen */
	private String nombre; //Nombre del Toxico
	private double volumen; //Volumen del Toxico



	/*Constructor de la clase Toxico*/
	public Toxico (double volumen, String nombre) {
		this.nombre = nombre;
		this.volumen = volumen;
	}


	/*Devuelve el valor del volumen del SerVivo*/
	public double MiVolumen() {
		return this.volumen;
	}

}
