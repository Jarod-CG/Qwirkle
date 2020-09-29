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
public class Jugador {

    protected double puntaje;
    protected ArrayList<Ficha> fichas;
    protected Ficha[] mano;

    public Jugador() {
        this.fichas = new ArrayList();
        this.mano = new Ficha[6];

    }

    public void addFichas(Ficha ficha) {
        fichas.add(ficha);
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public ArrayList<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Ficha> fichas) {
        this.fichas = fichas;
    }
    
    
    //retorna true si cumple figura
    private boolean jugadaDeFigura (Ficha[][] matrizFichas, Ficha ficha, int[] par ){
        //par = [x,y]
        //primero por fila
        //largo de filas
        //retrocede
        boolean is = true;
        for (int i = par[0]; i > 0; i--) {
            //asumiendo que en los espacios donde no hay ficha hay un null
            if (matrizFichas[par[1]][i].equals(null)) break;
            //valida que si hay ficha, si la hay y no coincide el tipo, break
            if (!matrizFichas[par[1]][i].getFigura().equals(ficha.getFigura())){
                is = false; break;
            }
        }
        if (is){
            //largo de la fila
            //avanza
            for (int i = par[0]; i < matrizFichas[par[1]].length; i++){
                if (matrizFichas[par[1]][i].equals(null)) break;
                if (!matrizFichas[par[1]][i].getFigura().equals(ficha.getFigura())){
                    is = false; break;
                }
            }
        }
        if (is){
            //recorre la columna de y hasta 0
            for (int i = par[1]; i >0; i--) {
                if (matrizFichas[i][par [0]].equals(null)) break;
                if (!matrizFichas[i][par [0]].getFigura().equals(ficha.getFigura())){
                    is = false; break;
                }
            }
        }
        if (is){
            
            //avanza en columna desde y hasta el final
            for (int i = par[1]; i < matrizFichas.length; i++){
                if (matrizFichas[i][par [0]].equals(null)) break;
                if (!matrizFichas[i][par [0]].getFigura().equals(ficha.getFigura())){
                    is = false; break;
                }
            }
        }
        return is;
    }

    
    //retorna true si cumple figura
    private boolean jugadaDeColor (Ficha[][] matrizFichas, Ficha ficha, int[] par ){
        //par = [x,y]
        //primero por fila
        //largo de filas
        //retrocede
        boolean is = true;
        for (int i = par[0]; i > 0; i--) {
            //asumiendo que en los espacios donde no hay ficha hay un null
            if (matrizFichas[par[1]][i] == null) break;
            //valida que si hay ficha, si la hay y no coincide el tipo, break
            if (!matrizFichas[par[1]][i].getColor().equals(ficha.getFigura())){
                is = false; break;
            }
        }
        if (is){
            //largo de la fila
            //avanza
            for (int i = par[0]; i < matrizFichas[par[1]].length; i++){
                if (matrizFichas[par[1]][i] == null) break;
                if (!matrizFichas[par[1]][i].getColor().equals(ficha.getFigura())){
                    is = false; break;
                }
            }
        }
        if (is){
            //recorre la columna de y hasta 0
            for (int i = par[1]; i >0; i--) {
                if (matrizFichas[i][par [0]] == null) break;
                if (!matrizFichas[i][par [0]].getColor().equals(ficha.getFigura())){
                    is = false; break;
                }
            }
        }
        if (is){
            
            //avanza en columna desde y hasta el final
            for (int i = par[1]; i < matrizFichas.length; i++){
                if (matrizFichas[i][par [0]] == null) break;
                if (!matrizFichas[i][par [0]].getColor().equals(ficha.getFigura())){
                    is = false; break;
                }
            }
        }
        return is;
    }

    public boolean jugadaValida (Ficha[][] matrizFichas, Ficha ficha, int[] par){
        return jugadaDeColor(matrizFichas, ficha,par) && jugadaDeFigura(matrizFichas, ficha,par);
    }

    //entra a esta funcion cuando se sepa que la jugada es valida
    //true, mismo color
    //false, misma figura
    public boolean determinarTipoJugada(Ficha ficha1, Ficha ficha2){
        return ficha1.getColor().equals(ficha2.getColor());
    }

    //entra a esta funcion cuando se sepa que la jugada es valida
    //true, x, misma fila
    //false, y, misma columna
    public boolean determinarOrientacion(int[] par1, int[] par2){
        return par1[0]==par2[0];
    }
    

}
