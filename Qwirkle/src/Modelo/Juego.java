/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Juego {
    private Ficha[][] MatrizFichas;
    private ArrayList<Jugador> jugadores;
    private Ficha fichaActual;
    private ArrayList<Jugador> fichas;

    public Juego(Ficha[][] MatrizFichas, ArrayList<Jugador> jugadores, Ficha fichaActual, ArrayList<Jugador> fichas) {
        this.MatrizFichas = MatrizFichas;
        this.jugadores = jugadores;
        this.fichaActual = fichaActual;
        this.fichas = fichas;
    }

    public Ficha[][] getMatrizFichas() {
        return MatrizFichas;
    }

    public void setMatrizFichas(Ficha[][] MatrizFichas) {
        this.MatrizFichas = MatrizFichas;
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

    public ArrayList<Jugador> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Jugador> fichas) {
        this.fichas = fichas;
    }
    
}
