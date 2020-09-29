/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Jarod
 */
public class JugadorSimple extends Jugador{
        //por ahora la lista es de pares
    //no es de pares y valor
    public ArrayList<int[]> jugar (Ficha[][] matriz, Ficha[] mano){
        ArrayList<int[]> arr = new ArrayList();
        arr.addAll(0, jugar_aux(matriz, mano, mano.length, new ArrayList()));
        return arr;
    }
    
    
    //[(2,3),(12,4),(0,1),null,(4,3)]
    //integrar jugadaValida en el backtracking
    public ArrayList<ArrayList<Jugada>> jugar_aux (Ficha[][] matriz, Ficha[] mano, int num, ArrayList<ArrayList<Jugada>> solucion) {
        if (num==0){
            return solucion;
        }
        else {
            //recorre la mano
            //posiblemente usar swap que usa el profe
            for (int i = 0; i < num; i++) {
                ArrayList<Jugada> posicionesMano = posiblesPosiciones(matriz, mano, i, solucion);
                //ir probando cada uno de las posiciones de la ficha
                for (int j = 0; j < posicionesMano.size(); j++) {
                    //quizas aqui vaya el swap de fichas
                    solucion.add(posicionesMano.get(j));
                    jugar_aux (matriz, mano, num-1, solucion);
                    //deshace el swap
                }
            }
            return new ArrayList();
        }
    } 
}
