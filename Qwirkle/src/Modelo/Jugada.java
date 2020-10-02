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
    Movimiento primerMovimiento = null;
    boolean segundoMovimiento = false;
    OrientacionType orientacion = null;
    DireccionType direccionSegundaFicha = null;
    JugadaType tipoJugadaActual = null;
    

    public Jugada() {
        
    }
    
    public void add(Movimiento mov){
        this.getMovimientos().add(mov);
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

    public Movimiento getPrimerMovimiento() {
        return primerMovimiento;
    }

    public void setPrimerMovimiento(Movimiento primerMovimiento) {
        this.primerMovimiento = primerMovimiento;
    }

    public boolean isSegundoMovimiento() {
        return segundoMovimiento;
    }

    public void setSegundoMovimiento(boolean segundoMovimiento) {
        this.segundoMovimiento = segundoMovimiento;
    }

    public OrientacionType getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(OrientacionType orientacion) {
        this.orientacion = orientacion;
    }

    public DireccionType getDireccionSegundaFicha() {
        return direccionSegundaFicha;
    }

    public void setDireccionSegundaFicha(DireccionType direccionSegundaFicha) {
        this.direccionSegundaFicha = direccionSegundaFicha;
    }

    public JugadaType getTipoJugadaActual() {
        return tipoJugadaActual;
    }

    public void setTipoJugadaActual(JugadaType tipoJugadaActual) {
        this.tipoJugadaActual = tipoJugadaActual;
    }
    
    
    
    
    
}
