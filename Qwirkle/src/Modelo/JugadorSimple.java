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
    
    //matriz de soluciones
    public ArrayList<ArrayList<Movimiento>> jugar (Ficha[][] matriz, Ficha[] mano){
        ArrayList<ArrayList<Movimiento>> arr = new ArrayList();
        arr.addAll(0, jugar_aux(matriz, mano, mano.length, new ArrayList()));
        return arr;
    }

    
    //[(2,3),(12,4),(0,1),null,(4,3)]
    //integrar jugadaValida en el backtracking
    //lista de jugadas
    private ArrayList<Movimiento> jugar_aux (Ficha[][] matriz, Ficha[] mano, int num, ArrayList<Movimiento> solucion) {
        if (listo(mano, solucion)){//cambiar condicion de parada
            return (ArrayList<Movimiento>) solucion;//hacer una copia
        }
        else {
            //recorre la mano
            //posiblemente usar swap que usa el profe
            /*
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
            */
            //----------------------------------
            //crear subconjuntos de soluciones de la mano
            //que cimpartan figura o color
            
            
            Ficha fichaActual = siguienteFicha (mano, solucion);//obtengo la primer ficha de la mano que no este en la solucion
            ArrayList<Movimiento> posicionesMano = posiblesPosiciones(matriz, fichaActual, solucion);//me retorna todas la posibles mano
            //utilizo la funcion de Carlos
            for (int i = 0; i < posicionesMano.size(); i++) {
                solucion.add(posicionesMano.get(i));
                jugar_aux(matriz, mano, num,solucion);
                solucion.remove(posicionesMano.get(i));
            }
        }
        return null;
    } 
    
    //retorna true si ya no hay más fichas no posibles jugadas
    //si existe una jugada de tres fichas no considera la misma jugada con dos fichas
    private boolean listo(Ficha[] mano, ArrayList<Movimiento> solucion){
        boolean done = false;
        if (siguienteFicha(mano , solucion)==null ){
            done = true;
        }
        
        
        return done;
    }
    
    private ArrayList<Movimiento> posiblesPosiciones (Ficha[][] matriz, Ficha ficha, ArrayList<Movimiento> solucion){
        //ArrayList<Jugada> posiciones = new ArrayList();
        //Ficha[][] matrizTemp = generarMatriz (matriz, solucion);//me retorna una matriz donde tengo la solucion insertada en la matriz

        /*switch(solucion.size()){
            case 0://si tamaño solucion es cero, recorro toda la matriz buscando TODAS las posibles soluciones
                posiciones = todaLaMatriz(matriz, ficha);//recorre toda la matriz en busca de todos los lugares que quepa
                //si la matriz esta vacia se inicia fijo en el centro
                break;
            case 1://si tamño solucion es uno, recorro alrededor de la posicion
                break;
            case 2://si tamaño es dos, asigno los tipos de jugada color/figuro y orientacion
                break;
            default://si el tamaño es mayor a dos, pregunto tipo de figura y orientacion y donde se cumpla la inserto
        }*/
        return todaLaMatriz(matriz, ficha);//recorre toda la matriz en busca de todos los lugares que quepa
    }
    
    private ArrayList<Movimiento> todaLaMatriz (Ficha[][] matriz, Ficha ficha){
        ArrayList<Movimiento> posibles = new ArrayList();
        if (matriz[7][9]==null){//no hay nada en el centro
            posibles.add(new Movimiento(7,9,1,ficha));
        }
        else {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                   /* int puntos = jugadaValida(ficha, i, j);
                    if (puntos>0){//si es mayor a cero es que es valida
                        posibles.add(new Movimiento(i,j,puntos,ficha));
                    }*/
                }
            }
        }
        return posibles;
    }
    
    //retorna la siguiente ficha que no este en la mano
    private Ficha siguienteFicha (Ficha[] mano, ArrayList<Movimiento> solucion){
        for (int i = 0; i < mano.length; i++) {
            if (!isInSolucion(mano[i], solucion)){
                return mano[i];
            }
        }
        return null;
    }
    
    //retorna truee si la ficha ya esta en la solucion
    private boolean isInSolucion(Ficha ficha, ArrayList<Movimiento> solucion){
        for (int i = 0; i < solucion.size(); i++) {
            if (ficha.equals(solucion.get(i).getFicha())){
                return true;
            }
        }
        return false;
    }
}
