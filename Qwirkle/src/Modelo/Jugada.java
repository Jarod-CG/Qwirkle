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
public class Jugada {
    
    int puntajeTotal = 0;
    ArrayList<Movimiento> movimientos = new ArrayList<>();
    OrientacionType orientacion = null;
    

    public Jugada() {
        
    }
    
    public Jugada(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
        for (Movimiento movimiento : movimientos) {
            this.sumarPuntos(movimiento.getPuntos());
        }
    }
    
    public void add(Movimiento mov){
        this.getMovimientos().add(mov);
        this.sumarPuntos(mov.getPuntos());
    }
    
    public void sumarPuntos(int puntos){
        this.puntajeTotal+= puntos;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public OrientacionType getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(OrientacionType orientacion) {
        this.orientacion = orientacion;
    }
    
    
    
}
