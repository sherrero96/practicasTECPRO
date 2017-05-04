//Autores:  Sergio Herrero Barco 698521
//          Alex Oarga Hategan   718123

/* La clase Servivo representa el objeto Ser Vivo, que implementa la interfaz
 * cosa, ya que puede ser guardado.
 */
public class SerVivo implements Cosa {

	/*Un serVivo se caracteriza por tener un nombre y un volumen */
	private String nombre; //Nombre del Ser vivo
	private double volumen; //Volumen del ser Vivo

	/*Constructor de la clase SerVivo*/
	public SerVivo (double volumen, String nombre) {
		this.nombre = nombre;
		this.volumen = volumen;
	}

	/*Devuelve el valor del volumen del SerVivo*/
	public double MiVolumen() {
		return this.volumen;
	}

}
