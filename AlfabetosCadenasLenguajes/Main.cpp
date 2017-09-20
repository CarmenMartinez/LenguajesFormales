/*
 * Main.cpp
 *	Práctica 1
 *  Fecha de creación: 07/09/2017
 *  Integrantes:
 *  Mariana Sierra Vega 702782
 *  Julián De Jesús López López
 *  María del Carmen Martínez Nuño 703358
 *
 */

#include <stdio.h>
#include <cstdlib>
#include <string.h>
#include <iostream>

using namespace std;

/*
 * Esta es una función recursiva, la cual recibe:
 * int     principio 		->	el inicio de la cadena o subcadena para comparar
 * string* lenguaje 		->	es un apuntador al inicio del lenguaje, es decir a cada conjunto de símbolos del alfabeto,
 * 					 			el cual está almacenado en un arreglo
 * int*    cardinalidad		->	es un apuntador a la cardinalidad de cada lenguaje del conjunto.
 * int	   longitud			->	es un entero que nos dice la cantidad de lenguajes que sxisten
 * string  w				->	es la palabra a validar
 *
 * retorna:
 *
 * pertenece:	Valor verdadero o falso que indica si la palabra w pertenece a los lenguajes de sigma
 *
 *
 * Esta función recorre todos los lenguajes de nuestro conjunto y las compara por partes con la palabra w
 * de ser así, pasamos a la siguiente parte de la palabra como una subcadena, comparando nuevamente con cada
 * lenguaje del conjunto, en caso de ser la última parte de la palabra se termina la función y devuelve el resiltado final.
 */
int buscar(int principio, string * lenguaje, int * cardenalidad, int longitud, string w, int nivel){
	int pertenece = nivel;
	for(int i = 0; i < longitud; i++){
			if(lenguaje[i] == w.substr(principio, cardenalidad[i])){
				//cout<<lenguaje[i]<<"="<<w.substr(principio, cardenalidad[i])<<endl;
				//cout<<"pertenece "<<pertenece<<" = nivel "<<nivel<<endl;
				pertenece++;
				if((unsigned int)(cardenalidad[i]+principio) == w.length()){
					//cout<<"2pertenece "<<pertenece<<" = nivel "<<nivel<<endl;
					return pertenece;
				}
				if(buscar(cardenalidad[i]+principio, lenguaje, cardenalidad, longitud, w, pertenece) > -1){
					//cout<<"in"<<endl;
					return buscar(cardenalidad[i]+principio, lenguaje, cardenalidad, longitud, w, pertenece);
				}else{
					pertenece--;
				}
			}
			else if(i == (longitud - 1)){
				//cout<<i<<"="<<longitud-1<<endl;
				return -1;
			}
		}

	return pertenece;
}
/*
 * main() función principal del programa
 * está encargada de leer el alfabeto (sigma), leer los lenguajes del conjunto (l), leer la palabra a validar.
 * El lenguaje se valida para que tenga todos los elementos del alfabeto. Hasta no estar de manera corracta, se seguirá pidiendo.
 *
 * Una vez teniendo esos tres elementos se dividen en arreglos:
 * sigma -> se divide por espacios tomando cada símbolo como una posición en el arreglo.
 * l	 -> se divide por comas, y cada lenguaje se guarda en un arreglo
 * 			se crea un nuevo arreglo relacionado con l, donde se guarda la cardinalidad de cada lenguaje
 *
 *
 *El final de esta función imprime si la cadena a validar pertenece o no al conjunto sigma*
 * */
int main(){
	string sigma, l, w;

	//obtener las entradas
	cout<<"Ingresa el alfabeto separado por espacios: "<<endl<<"Ejemplo 'a b c'"<<endl;
	cout<<"\u03A3:";
	getline(cin, sigma);
	bool flag;
	do{
		flag = false;
		cout<<"Ingresa el lenguaje separado por comas"<<endl<<"Ejemplo 'ab,aa,bb'"<<endl<<"L = ";
		getline(cin, l);
		for(int i = 0; i < l.length(); i++){
			if(sigma.find(l[i]) == string::npos && l[i] != ','){
				flag = true;
			}
		}
	}while(flag);
	int veces  = 0;
	cout<<"Ingresa el numero de palabras a validar: ";
	cin>>veces;
	cin.ignore();
	fflush(stdin);

	do{

		cout<<"Ingresa la palabra:"<<endl<<"w = ";
		getline(cin, w);


		char * l_separado = new char[l.length()+1];
		strcpy (l_separado, l.c_str());
		l_separado = strtok(l_separado, ",");

		string * lenguaje = new string[l.length() + 1];
		int * cardenalidad = new int[l.length() + 1];


		int posicion = 0;
		while(l_separado != NULL){
			lenguaje[posicion] = l_separado;
			cardenalidad[posicion] = strlen(l_separado);
			l_separado = strtok(NULL, ",");
			posicion++;
		}
		int longitud = posicion;
		int nivel = 0;
		//Llamada a la funci�n recursiva
		int pertenece = buscar(0, lenguaje, cardenalidad, longitud, w, nivel);
		//cout<<pertenece<<endl;
		//analiza respuesta.
		if(pertenece > -1)
			cout<<"w pertenece a "<<"L"<<pertenece<<endl;
		else
			cout<<"No pertenece"<<endl;
		veces --;
	}while(veces > 0);
	return 0;
}




