/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Carlos
 */
public class Juego {

    private Ficha[][] matrizFichas;
    private ArrayList<Jugador> jugadores;
    private Ficha fichaActual;
    private ArrayList<Ficha> fichas;

    public Juego(int numSim, int numAva, int numHum) {
        this.jugadores = new ArrayList();
        if (numHum == 1) {
            //crea un jugador humano
            jugadores.add(new Jugador());
        }
        for (int i = 0; i < numSim; i++) {
            //aca creo numSim cantidad de algoritmos simples
            
        }
        for (int i = 0; i < numAva; i++) {
            //aca creo numAva cantidad de algoritmos avanzados
            
        }
        //crear la matriz tablero
        this.matrizFichas = new Ficha[9][17];//el tamaño variaria
        //asignar las fichas
        this.fichas = new ArrayList();
        for (int i = 0; i < 3; i++) {
            for (FichaType ficha : FichaType.values()) {

                fichas.add(new Ficha(ficha));
            }
        }
        Collections.shuffle(fichas);//revuelve las fichas
        int contador=1;
        int x = fichas.size()/jugadores.size();
        for (int i = 0; i<jugadores.size();i++){
            for (int j = 0;j <x ;j++){
                jugadores.get(i).addFichas(fichas.get(j+(x*(contador-1))));
            } 
            contador++;
        }
    }

    public Juego(Ficha[][] MatrizFichas, ArrayList<Jugador> jugadores, Ficha fichaActual, ArrayList<Ficha> fichas) {
        this.matrizFichas = MatrizFichas;
        this.jugadores = jugadores;
        this.fichaActual = fichaActual;
        this.fichas = fichas;
    }

    public Ficha[][] getMatrizFichas() {
        return matrizFichas;
    }

    public void setMatrizFichas(Ficha[][] MatrizFichas) {
        this.matrizFichas = MatrizFichas;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Ficha getFichaActual() {
        return fichaActual;
    }

    public void setFichaActual(Ficha fichaActual) {
        this.fichaActual = fichaActual;
    }

    public ArrayList<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Ficha> fichas) {
        this.fichas = fichas;
    }

}
