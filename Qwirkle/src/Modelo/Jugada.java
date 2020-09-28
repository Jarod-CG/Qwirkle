/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jarod
 */
public class Jugada {
    private int[] par;
    private int puntos;

    public Jugada() {
        this.par = new int[2];
        this.puntos = 0;
    }

    public int[] getPar() {
        return par;
    }

    public void setPar(int[] par) {
        this.par = par;
    }
    
    public void setPar(int x, int y) {
        this.par[0] = x;
        this.par[1] = y;
    }
    
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    public void incPuntos(int mas){
        this.puntos+=mas;
    }
    
    
}
