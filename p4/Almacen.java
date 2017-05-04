// Autores: Sergio Herrero Barco 698521
//          Alex Oarga Hategan   718123

/* La clase Almacen representa objetos que pueden contener a otros objetos dentro
 * de ellos. Almacenes serian los objetos Contenedores y Camiones.
 */
public class Almacen{
	/* Un almacen se caracteriza por la capacidad o volumen que soporta */
	protected double capacidad;

	/* Devuelve verdad si y solo si el objeto Almacen tiene la suficiente capacidad
	 * para guardar otro objeto que tenga un volumen 'volumen. Si es asi, el valor
	 * de capacidad disminuye tanto como volumen tenga el objeto.
	 */
	public boolean guardar(double volumen) {
		if(volumen <= capacidad) { //Si volumen es menor que capacidad, cabe
			capacidad -= volumen;
			return true;
		}
		else { //Si el volumen es mayor que capacidad, no cabe
			return false;
		}
	}

}
