#ifndef AGRUPACION_DINAMICA_H
#define AGRUPACION_DINAMICA_H

#include <iostream>
using namespace std;

template <typename T>
class agrupacion_dinamica{	
	private:		
		class nodo{
			private:				
				T dato;
				nodo* sig;
				nodo* ant;
			public:
				nodo(T d){ //constructor de la clase nodo
					dato = d;
					ant = sig = nullptr;
				}
				friend class agrupacion_dinamica;
		};
	
		/* Atributos privados de la clase agrupacion */
		int total; //Numero de datos
		nodo* primero; //Puntero al primer elemento
		nodo* ultimo; //Puntero al ultimo elemento

	public:
		agrupacion_dinamica(){ //Constructor de la clase agrupacion
			primero = nullptr;
			total = 0;
		}
		~agrupacion_dinamica(){//Destructor de la clase agrupacion
			typename agrupacion_dinamica<T>::nodo* aux;
			while(primero != nullptr){
				aux = primero;
				primero = primero->sig;
				delete(aux);
				total--;
			}
		}
		
		/*Añade el elemento d a la agrupacion*/	
		void anyadir(const T& d){
			if(primero == nullptr){ //Si la agrupacion es vacia
				primero = new typename agrupacion_dinamica::nodo(d);
				ultimo = primero;
				total = 1;
			}else{
				ultimo->sig = new typename agrupacion_dinamica::nodo(d);
				ultimo->sig->ant = ultimo;
				ultimo = ultimo->sig;
				ultimo->sig = nullptr;
				total++;
			}
		}
		
		/*Borra el ultimo elemento añadido a la agrupacion*/
		bool borrarUltimo(){
			if(total <= 0) return false;
			else{
				typename agrupacion_dinamica<T>::nodo* aux;				
				if(total == 1){ //Si solo hay un elemento
					aux = primero;
					primero = nullptr;
					delete(aux);
					total = 0;
				}else{
					aux = ultimo;
					ultimo = ultimo->ant;
					ultimo->sig = nullptr;
					delete(aux);
					total--;
				}
				return true;
			}
		}
		
		friend class const_iterator; //Hacemos que la clase iterador sea amiga para que pueda acceder a sus campos

		/*Clase iterador para poder mostrar los elementos de la agrupacion */
		class const_iterator{
			private:
				nodo* iter;
				const agrupacion_dinamica<T>& ag;
			public:
				const_iterator(const agrupacion_dinamica& c_, nodo* it) : iter(it), ag(c_) {  } //constructor
				
				//Este método redefine el operador de pre-incremento (++x).
				//Representa el avance del iterador.
				const_iterator& operator++(){ 	
				
					iter = iter->ant;
				  
					return (*this);
				}
				
				const T& operator*()   const {
					//Devuelve el elemento T al que apunta el iterador
					return iter->dato;
				}

				bool operator!=(const const_iterator& that) const { 
					//Devuelve true si y solo si este iterador y el iterador "that" apuntan
					//a sitios diferentes, y false en caso contrario.
					return (this->iter != that.iter); 
				}
		};
				
				const_iterator begin() const { //iterador al principio
					return const_iterator(*this, this->ultimo); 
				}
				const_iterator end()   const {  //iterador al final
					return const_iterator(*this, nullptr); 
				}
				
				
		
};


#endif
