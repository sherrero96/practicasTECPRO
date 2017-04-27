// Interfaz de la agrupacion
// Obliga a implementar una clase iterable 
// y con los metodos anyadir(T t) y borrarUltimo()
interface Agrupacion<T> extends Iterable<T>
{
    boolean anyadir(T t);
    boolean borrarUltimo();
}


