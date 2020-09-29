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
    public ArrayList<int[]> jugar_aux (Ficha[][] matriz, Ficha[] mano, int num, ArrayList<int[]> solucion) {
        if (num==0){
            return solucion;
        }
        else {
            //recorre la mano
            //posiblemente usar swap que usa el profe
            for (int i = 0; i < num; i++) {
                ArrayList<int[]> posicionesMano = posiblesPosiciones(matriz, mano, i, solucion);
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
    
    //busca todas las posiciones validas de ficha en matriz
    //tambien se debe considerar la solucion
    //porque las posiciones de ficha deben considerar la matriz + solucion
    public ArrayList<int[]> posiblesPosiciones (Ficha[][] matriz, Ficha[] fichas, int pos, ArrayList<int[]> solucion){
        ArrayList<int[]> posiciones = new ArrayList();
        Ficha[][] matrizTemporal = nuevaMatriz(matriz, fichas, solucion);
        //ahora hay que recorrer la matriz buscando donde la ficha pos puede entrar
        if (solucion.isEmpty()){//no hay posiciones, por lo tanto se recorre toda la matriz
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    int[] par = new int[2];
                    par[0] = j; 
                    par[1] = i;
                    if (super.jugadaValida(matriz, fichas[pos], par)){
                        posiciones.add(par);
                    }
                }
            }
        }
        else if (solucion.size()==1){//aun no hay orientacion
            //busca alrededor de la posicion
            //cuatro posiciones
            int[] parTemp = solucion.get(0);
            //que no supere el techo y que este disponible
            if (parTemp[1]+1>matrizTemporal.length && matrizTemporal[parTemp[0]][parTemp[1]+1]==null){ 
                parTemp[1] ++;
                if (super.jugadaValida(matriz, fichas[pos], parTemp)){
                        posiciones.add(parTemp);
                }
                parTemp[1] --;
            }
            //que no se pase del fondo y este disponible
            if (parTemp[1]-1>0 && matrizTemporal[parTemp[0]][parTemp[1]-1]==null){
                parTemp[1] --;
                if (super.jugadaValida(matriz, fichas[pos], parTemp)){
                        posiciones.add(parTemp);
                }
                parTemp[1] ++;
            }
            //no se pse de la derecha
            if (parTemp[0]+1>matrizTemporal[0].length && matrizTemporal[parTemp[0]+1][parTemp[1]]==null){ 
                parTemp[0] ++;
                if (super.jugadaValida(matriz, fichas[pos], parTemp)){
                        posiciones.add(parTemp);
                }
                parTemp[0] --;
            }
            //que no se pase del fondo y este disponible
            if (parTemp[0]-1>0 && matrizTemporal[parTemp[0]-1][parTemp[1]]==null){
                parTemp[0] --;
                if (super.jugadaValida(matriz, fichas[pos], parTemp)){
                        posiciones.add(parTemp);
                }
                parTemp[0] ++;
            }
        }
        else {//ya hay orientacion
            int[] par1 = null;
            int[] par2;
            for (int i = 0; i < solucion.size(); i++) {
                if (solucion.get(i)!=null){
                    if (par1==null) par1 = solucion.get(i);
                    else par2 = solucion.get(i);
                    
                }
            }
            //averiguar orientacion y tipo
        }
        return posiciones;
    }

    private Ficha[][] nuevaMatriz(Ficha[][] matriz, Ficha[] fichas, ArrayList<int[]> solucion) {
        Ficha[][] m = matriz.clone();
        for (int i = 0; i < solucion.size(); i++) {
            m[solucion.get(i)[1]][solucion.get(i)[0]] = fichas[i];
        }
        return m;
    }
    
   
    
}
