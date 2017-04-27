#include "agrupacion-estatica.h"
#include "agrupacion-dinamica.h"
#include <iostream>

//Define la cabecera de esta función mediante programacion generica para que sea equivalente
//a declarar las dos funciones siguientes: 
//void anyadir_elementos(agrupacion_estatica<int>& ag)
//void anyadir_elementos(agrupacion_dinamica<int>& ag)
template<class T>
void anyadir_elementos(T& ag)
{
	ag.anyadir(42);
	ag.anyadir(15);
	ag.borrarUltimo();
	for (int i = 0; i < 100; i+=5) ag.anyadir(i);
}

//Define la cabecera de esta función mediante programacion generica para que sea equivalente
//a declarar las dos funciones siguientes: 
//void mostrar_elementos(const agrupacion_estatica<int>& ag)
//void mostrar_elementos(const agrupacion_dinamica<int>& ag)
template<class T>
void mostrar_elementos(const T& ag)
{
	for (int i : ag) std::cout<<i<<" ";
	std::cout<<std::endl;
}

int main(int argc,char* argv[])
{
	agrupacion_estatica<int> ag_e;
	agrupacion_dinamica<int> ag_d;

	anyadir_elementos(ag_e);
	anyadir_elementos(ag_d);

	mostrar_elementos(ag_e);
	mostrar_elementos(ag_d);	
}
