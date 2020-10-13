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
public class JugadorAvanzado extends Jugador {

    public JugadorAvanzado(Ficha[][] matrizFichas) {
        super(matrizFichas);
    }

    public Jugada getMejorJugada() {
        ArrayList<Jugada> jugadas = getTodasLasJugadas();
        //System.out.println("tamaño de todas las jugadas " + jugadas.size());

        noRegalarQwirkle(jugadas);

        if (jugadas.size() == 0) {
            return null;
        }

        Jugada mayor = jugadas.get(0);

        if (jugadas.size() > 0) {
            for (int i = 0; i < jugadas.size(); i++) {
                //System.out.println("puntaje de " + i + " jugada : " +  jugadas.get(i).getPuntajeTotal()) ;
                if (mayor.getPuntajeTotal() < jugadas.get(i).getPuntajeTotal()) {
                    //System.out.println("ANTES -> MAYOR: " + mayor.getPuntajeTotal() + " -> JUGADA MAYOR : " +  jugadas.get(i).getPuntajeTotal());
                    mayor = jugadas.get(i);
                    //System.out.println("DESPUES -> MAYOR: " + jugadas.get(i).getPuntajeTotal() + " -> JUGADA MENOS : " +  mayor.getPuntajeTotal());
                }
            }
        }
        /*
        System.out.println("********JUGADA CON MAS PUNTOS*********");
        for (Movimiento movimiento : mayor.getMovimientos()) {
            System.out.println(movimiento.getFicha().getTipo() + " fila : " + movimiento.getFila() + " col : "
                    + movimiento.getColumna() + " p : " + movimiento.getPuntos());
        }
        System.out.println("Total de puntos: " + mayor.getPuntajeTotal());
        System.out.println("**************************************");
         */

 
        if (mayor.getPuntajeTotal()<3){
            return null;
        }
         
        return mayor;
    }

    private void noRegalarQwirkle(ArrayList<Jugada> jugadas) {
        ArrayList<Jugada> remover = new ArrayList();
        for (int i = 0; i < jugadas.size(); i++) {
            if (regalaQwirkle(jugadas.get(i).getMovimientos())) {
                remover.add(jugadas.get(i));
            }
        }
        for (int i = 0; i < remover.size(); i++) {
            jugadas.remove(remover.get(i));
        }
    }

    private boolean regalaQwirkle(ArrayList<Movimiento> movimientos) {

        //setea los movimientos en la matriz
        ponerFichas(movimientos);
        //evalua en fila y evalua en columna

        for (Movimiento movimiento : movimientos) {
            //System.out.println("ficha : " + movimiento.getFicha().getTipo());
            if (regalaQwirkleFila(movimiento.getFila(), movimiento.getColumna())
                    || regalaQwirkleColumna(movimiento.getFila(), movimiento.getColumna())) {
                quitarFichas(movimientos);
                return true;
            }
        }

        //quita los movimientos de la matriz
        quitarFichas(movimientos);

        return false;
    }

    

    private boolean regalaQwirkleFila(int fila, int columna) {
        int columTmp = columna;

        //hacer que se vaya a la columna de menor valor 
        while (this.matrizFichas[fila][columTmp - 1] != null) {
            columTmp--;
        }
        int contador = 1;
        while (this.matrizFichas[fila][columTmp + 1] != null) {
            columTmp++;
            contador++;
        }
        
        return contador==5;
    }

    private boolean regalaQwirkleColumna(int fila, int columna) {
        int filaTmp = fila;

        while (this.matrizFichas[filaTmp - 1][columna] != null) {
            filaTmp--;
        }
        int contador = 1;
        while (this.matrizFichas[filaTmp + 1][columna] != null) {
            filaTmp++;
            contador++;
        }
        
        return contador == 5;
    }

}
