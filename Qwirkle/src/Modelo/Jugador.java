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

    private double puntaje;
    private ArrayList<Ficha> fichas;
    private Ficha[] mano;
    
    private JugadaType tipoJugadaActual;
    private OrientacionType orientacion;
    private Movimiento primerJugada;
    private Movimiento ultimaJugada;
    private Ficha[][] matrizFichas; //no se cosideran los bordes de las matriz
    //de 19x15 fichas visibles, asi que 21x17 por que los bordes no se ven
    private boolean segundaJugada=false;
    

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
    
    public JugadaType getTipoJugadaActual() {
        return tipoJugadaActual;
    }

    public void setTipoJugadaActual(JugadaType tipoJugadaActual) {
        this.tipoJugadaActual = tipoJugadaActual;
    }

    public OrientacionType getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(OrientacionType orientacion) {
        this.orientacion = orientacion;
    }
    
    
    
    public int jugadaValida(Ficha ficha, int fila, int columna) {
        int puntos = 0;
        if (primerJugada != null) {
            ultimaJugada = new Movimiento(fila,columna,puntos,ficha);
            if (!segundaJugada) {
                this.determinarTipoJugada(ficha, fila, columna);
                segundaJugada=true;
            }
            
            if (this.tipoJugadaActual == JugadaType.PORFIGURA && this.orientacion == OrientacionType.COLUMNA) {
                
                    if (validarFigura(ficha, fila, primerJugada.getColumna()) > 0) {
                        puntos += validarFigura(ficha, fila, columna);
                    }
            } 
            else if (this.tipoJugadaActual == JugadaType.PORFIGURA && this.orientacion == OrientacionType.FILA) {
                
            }
            else if (this.tipoJugadaActual == JugadaType.PORCOLOR && this.orientacion == OrientacionType.COLUMNA) {
                
            }
            else {//ESTE ES PORCOLOR Y FILA
                if (validarColor(ficha, fila, columna) > 0) {
                    puntos += validarColor(ficha, fila, columna);
                }
            }
        }

        if (primerJugada == null) {
            if (validarFigura(ficha, fila, columna) > 0) {
                puntos += validarFigura(ficha, fila, columna);
            }
            if (validarColor(ficha, fila, columna) > 0) {
                puntos += validarColor(ficha, fila, columna);
            }            
            this.primerJugada = new Movimiento(fila,columna,puntos,ficha);
            this.ultimaJugada = primerJugada;
        }

        return puntos;
    }
    //aqui asignamos  tipo y orientacion
    public void determinarTipoJugada(Ficha ficha, int fila, int columna){
        if(ficha.getTipo() != primerJugada.getFicha().getTipo()){
            if (ficha.getColor() == primerJugada.getFicha().getColor()) {
                this.setTipoJugadaActual(JugadaType.PORCOLOR);
            } else if(ficha.getFigura() == primerJugada.getFicha().getFigura()) {
                this.setTipoJugadaActual(JugadaType.PORFIGURA);
            }
            else {
                this.setTipoJugadaActual(null);
            }  
            
            if (fila == primerJugada.getFila()){
                this.setOrientacion(OrientacionType.FILA);
            }
            else if (columna == primerJugada.getColumna()){
                this.setOrientacion(OrientacionType.COLUMNA);
            }
            else {
                this.setOrientacion(null);
            }
        }else{
            this.setTipoJugadaActual(null);
            this.setOrientacion(null);
        }    
    }
    
    /*  
        Posibles jugadas
        [{Movimiento(2,3->5),Movimiento(5,6->2)},
        {Movimiento(2,3->5)},
        {Movimiento(2,3->5),Movimiento(5,6->2)},Movimiento(2,3->5),Movimiento(5,6->2)},
        {},
        {Movimiento(2,3->5),Movimiento(5,6->2)}},
        {Movimiento(2,3->5),Movimiento(5,6->2)},Movimiento(2,3->5)}] 
         */
    public int validarFigura(Ficha fichaActual,int fila, int columna){ //determina si la figura calza en la posición
        int puntos = 0;
        //boolean res = false;
        if(this.matrizFichas[fila][columna-1]!= null){
            int izquierda = validarFiguraHaciaLaIzquierda(fichaActual,fila,columna); // retorna los puntos y 0 (no hay fichas alrededor)
            puntos += izquierda >= 0 ? puntos + izquierda : puntos - 100;
        }
        if(this.matrizFichas[fila][columna+1]!= null){
            int derecha = validarFiguraHaciaLaDerecha(fichaActual,fila,columna);
            puntos += derecha >= 0 ? puntos + derecha : puntos - 100;
        }
        if(this.matrizFichas[fila-1][columna]!= null){
            int arriba = validarFiguraHaciaArriba(fichaActual,fila,columna);
            puntos += arriba >= 0 ? puntos + arriba : puntos - 100;
        }
        if(this.matrizFichas[fila+1][columna]!= null){
            int abajo = validarFiguraHaciaAbajo(fichaActual,fila,columna);
            puntos += abajo >= 0 ? puntos + abajo : puntos - 100;
        }
        
        if(puntos>0){
            return puntos;
        }else{
            return 0;
        }
  
    }
    
    public int validarColor(Ficha fichaActual,int fila, int columna){ //determina si la figura calza en la posición
        int puntos = 0;
        //boolean res = false;
        if(this.matrizFichas[fila][columna-1]!= null){
            int izquierda = validarColorHaciaLaIzquierda(fichaActual,fila,columna); // retorna los puntos y 0 (no hay fichas alrededor)
            puntos += izquierda >= 0 ? puntos + izquierda : puntos - 100;
        }
        if(this.matrizFichas[fila][columna+1]!= null){
            int derecha = validarColorHaciaLaDerecha(fichaActual,fila,columna);
            puntos += derecha >= 0 ? puntos + derecha : puntos - 100;
        }
        if(this.matrizFichas[fila-1][columna]!= null){
            int arriba = validarColorHaciaArriba(fichaActual,fila,columna);
            puntos += arriba >= 0 ? puntos + arriba : puntos - 100;
        }
        if(this.matrizFichas[fila+1][columna]!= null){
            int abajo = validarColorHaciaAbajo(fichaActual,fila,columna);
            puntos += abajo >= 0 ? puntos + abajo : puntos - 100;
        }
        
        if(puntos > 0){
            return puntos;
        }else{
            return puntos;
        }
        
    }
    
    public int validarFiguraHaciaLaIzquierda(Ficha fichaActual,int fila, int columna){
        int puntos = 0;
        for (int i = columna-1; matrizFichas[fila][i] != null && i > 0 ; i--) { 
            if ( matrizFichas[fila][i].getColor() != fichaActual.getColor() || 
                    matrizFichas[fila][i].getFigura() == fichaActual.getFigura()) {    
                //return false;
                return -1;
            }
            puntos++;
        }
        return puntos;
    }
    
    public int validarFiguraHaciaLaDerecha(Ficha fichaActual,int fila, int columna){
        int puntos = 0;
        for (int i = columna+1; matrizFichas[fila][i] != null && i < matrizFichas.length-1 ; i++) { 
            if ( matrizFichas[fila][i].getColor() != fichaActual.getColor() || 
                    matrizFichas[fila][i].getFigura() == fichaActual.getFigura()) {    
                return -1;
            }
            puntos++;
        }
        return puntos;
    }
    
    public int validarFiguraHaciaArriba(Ficha fichaActual,int fila, int columna){
        int puntos = 0;
        for (int i = fila-1; matrizFichas[i][columna] != null && i > 0 ; i--) { 
            if ( matrizFichas[i][columna].getColor() != fichaActual.getColor() || 
                    matrizFichas[i][columna].getFigura() == fichaActual.getFigura()) {    
                return -1;
            }
            puntos++;
        }
        return puntos;    
    }
    
    public int validarFiguraHaciaAbajo(Ficha fichaActual,int fila, int columna){
        int puntos = 0;
        for (int i = fila+1; matrizFichas[i][columna] != null && i < matrizFichas.length-1 ; i++) { 
            if ( matrizFichas[fila][i].getColor() != fichaActual.getColor() || 
                    matrizFichas[fila][i].getFigura() == fichaActual.getFigura()) {    
                return -1;
            }
            puntos++;
        }
        return puntos;
    }
    
    public int validarColorHaciaLaIzquierda(Ficha fichaActual,int fila, int columna){
        int puntos = 0;
        for (int i = columna-1; matrizFichas[fila][i] != null && i > 0 ; i--) { 
            if ( matrizFichas[fila][i].getColor() == fichaActual.getColor() || 
                    matrizFichas[fila][i].getFigura() != fichaActual.getFigura()) {    
                return -1;
            }
            puntos++;
        }
        return puntos;
    }
    
    public int validarColorHaciaLaDerecha(Ficha fichaActual,int fila, int columna){
        int puntos = 0;
        for (int i = columna+1; matrizFichas[fila][i] != null && i < matrizFichas.length-1 ; i++) { 
            if ( matrizFichas[fila][i].getColor() == fichaActual.getColor() || 
                    matrizFichas[fila][i].getFigura() != fichaActual.getFigura()) {    
                return -1;
            }
            puntos++;
        }
        return puntos;
    }
    
    public int validarColorHaciaArriba(Ficha fichaActual,int fila, int columna){
        int puntos = 0;
        for (int i = fila-1; matrizFichas[i][columna] != null && i > 0 ; i--) { 
            if ( matrizFichas[i][columna].getColor() == fichaActual.getColor() || 
                    matrizFichas[i][columna].getFigura() != fichaActual.getFigura()) {    
                return -1;
            }
            puntos++;
        }
        return puntos;
    }
    
    public int validarColorHaciaAbajo(Ficha fichaActual,int fila, int columna){
        int puntos = 0;
        for (int i = fila+1; matrizFichas[i][columna] != null && i < matrizFichas.length-1 ; i++) { 
            if ( matrizFichas[fila][i].getColor() == fichaActual.getColor() || 
                    matrizFichas[fila][i].getFigura() != fichaActual.getFigura()) {    
                return -1;
            }
            puntos++;
        }
        return puntos;
    }
    

}
