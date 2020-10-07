/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Arrays;

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
    private DireccionType direccionSegundaFicha;
    private Movimiento primerJugada = null;
    private boolean segundaJugada = false;
    private Movimiento ultimaJugada = null;
    private Ficha[][] matrizFichas; //no se cosideran los bordes de las matriz
    //de 19x15 fichas visibles, asi que 21x17 por que los bordes no se ven
    

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

    public DireccionType getDireccionSegundaFicha() {
        return direccionSegundaFicha;
    }

    public void setDireccionSegundaFicha(DireccionType direccionSegundaFicha) {
        this.direccionSegundaFicha = direccionSegundaFicha;
    }
    
    public ArrayList<Movimiento> getListaPrimerosMovimientos(Ficha ficha,ArrayList<Ficha> subconjunto){
        ArrayList<Movimiento> primerosMovimientos = new ArrayList<>();
        for (int fila = 0; fila < this.matrizFichas.length; fila++) {
            for (int columna = 0; columna < this.matrizFichas.length; columna++) {
                if (this.matrizFichas[fila][columna] == null) {
                    int puntos = calcularPuntos(ficha, fila, columna, subconjunto);
                    if (puntos > 0) {
                        primerosMovimientos.add(new Movimiento(fila, columna, puntos, ficha));
                    }
                }
            }
        }
        return primerosMovimientos;
    }
    
    public ArrayList<Movimiento> backTrackingMovimientos(ArrayList<Ficha> sub, OrientacionType ori,int fil,int col, ArrayList<Movimiento> movimientos){
        if(sub.size() == movimientos.size()){
            return movimientos;
        }else{
            for (Ficha ficha : sub) {
                for (int j = 0; j < this.matrizFichas.length; j++) {
                    if(ori == OrientacionType.COLUMNA){
                        int puntos = calcularPuntos(ficha, fil, col, sub);
                        if(puntos>0){
                            this.matrizFichas[j][col] = ficha;
                            Movimiento mov = new Movimiento(fil, col, puntos, ficha);
                            movimientos.add(mov);
                            backTrackingMovimientos(sub,ori,fil+1,col, movimientos);// AQUI EL BACKTRACKING
                            this.matrizFichas[j][col] = null;
                            movimientos.remove(mov);
                        }
                        
                    }else{
                    
                    }
                }
            }
        }
        return null;
    } 
    
    public void backtrackingJugadas(int num, ArrayList<Ficha> perm, ArrayList<Movimiento> solucion, ArrayList<ArrayList<Movimiento>> arr, Movimiento primerMov, OrientacionType ori) {
        if (num == perm.size()) {
            arr.add(solucion);
        } else {
            ArrayList<Movimiento> movimientos = getMovimientosValidos(perm.get(num), ori, perm, primerMov);
            for (int i = 0; i < movimientos.size(); i++) {
                solucion.add(movimientos.get(i));
                this.matrizFichas[movimientos.get(i).getFila()][movimientos.get(i).getColumna()] = movimientos.get(i).getFicha();
                backtrackingJugadas(num+1, perm, solucion, arr, primerMov, ori);
                this.matrizFichas[movimientos.get(i).getFila()][movimientos.get(i).getColumna()] = null;
                solucion.remove(movimientos.get(i));
            }
        }
    }

    public ArrayList<Movimiento> getMovimientosValidos(Ficha ficha, OrientacionType orientacion, ArrayList<Ficha> perm, Movimiento primerMov) {
        ArrayList<Movimiento> listMovimientos = new ArrayList<Movimiento>();
        for (int i = 0; i < this.matrizFichas.length; i++) {
            if (orientacion == OrientacionType.COLUMNA) {
                if (this.matrizFichas[i][primerMov.getColumna()] == null && calcularPuntos(ficha, i, primerMov.getColumna(), perm) > 0) {
                    listMovimientos.add(new Movimiento(i, primerMov.getColumna(), calcularPuntos(ficha, i, primerMov.getColumna(), perm), ficha));
                }
            } else {
                if (this.matrizFichas[primerMov.getFila()][i] == null && calcularPuntos(ficha, primerMov.getFila(), i, perm) > 0) {
                    listMovimientos.add(new Movimiento(primerMov.getFila(), i, calcularPuntos(ficha, primerMov.getFila(), i, perm), ficha));
                }
            }
        }
        return listMovimientos;
    }
    
    
        
    public ArrayList<Jugada> getListaJugadas(ArrayList<Ficha> subconjunto) { //AQUI LAS PERMUTACIONES DE LA MANO 
        ArrayList<Jugada> jugadas = new ArrayList<>();
        ArrayList<Movimiento> primerosMovimientos = getListaPrimerosMovimientos(subconjunto.get(0),subconjunto);
        if (subconjunto.size() > 1) {
            ArrayList<ArrayList<Movimiento>> arr = new ArrayList<ArrayList<Movimiento>>();
            for (Movimiento primerMovimiento : primerosMovimientos) {                
                for (OrientacionType orientacion : OrientacionType.values()) {
                    ArrayList<Movimiento> solucion = new ArrayList<Movimiento>();
                    solucion.add(primerMovimiento);
                    backtrackingJugadas(1,subconjunto, solucion , arr, primerMovimiento, orientacion);
                }
            }
            for (ArrayList<Movimiento> movimientos : arr) {
                jugadas =  convertMoviAJugada(movimientos);
            }
        }else{
            jugadas =  convertMoviAJugada(primerosMovimientos);
        }        
        return jugadas;
    }
    
    public ArrayList<Jugada> convertMoviAJugada(ArrayList<Movimiento> movimientos){
        ArrayList<Jugada> jugadas = new ArrayList<>();
        for (Movimiento movimiento : movimientos) {
            jugadas.add(new Jugada(movimientos));
        }
        return jugadas;
    }

    public int calcularPuntos(Ficha ficha, int fila, int columna, ArrayList<Ficha> subconjunto){
        int puntos = 0;
        if (validarFigura(ficha, fila, columna, subconjunto) > 0) {
            puntos += validarFigura(ficha, fila, columna, subconjunto);
        }
        if (validarColor(ficha, fila, columna, subconjunto) > 0) {
            puntos += validarColor(ficha, fila, columna, subconjunto);
        }        
        if (puntos > 0) {
            return puntos;
        }
        return 0;
    }
    
    public int validarFigura(Ficha fichaActual,int fila, int columna, ArrayList<Ficha> subconjunto){ //determina si la figura calza en la posición
        int puntos = 0;
        if(this.matrizFichas[fila][columna-1]!= null){
            int izquierda = validarFiguraHaciaLaIzquierda(fichaActual,fila,columna,subconjunto); // retorna los puntos y 0 (no hay fichas alrededor)
            puntos = izquierda >= 0 ? puntos + izquierda : puntos - 1000;
            puntos = izquierda == 6 ? puntos + 6 : puntos + 0;
        }
        if(this.matrizFichas[fila][columna+1]!= null){
            int derecha = validarFiguraHaciaLaDerecha(fichaActual,fila,columna,subconjunto);;
            puntos = derecha >= 0 ? puntos + derecha : puntos - 1000;
            puntos = derecha == 6 ? puntos + 6 : puntos + 0;
        }
        if(this.matrizFichas[fila-1][columna]!= null){
            int arriba = validarFiguraHaciaArriba(fichaActual,fila,columna,subconjunto);;
            puntos = arriba >= 0 ? puntos + arriba : puntos - 1000;
            puntos = arriba == 6 ? puntos + 6 : puntos + 0;
        }
        if(this.matrizFichas[fila+1][columna]!= null){
            int abajo = validarFiguraHaciaAbajo(fichaActual,fila,columna,subconjunto);;
            puntos = abajo >= 0 ? puntos + abajo : puntos - 1000;
            puntos = abajo == 6 ? puntos + 6 : puntos + 0;
        }
        
        if(puntos>0){
            return puntos;
        }else{
            return 0;
        }
    }
    
    public int validarColor(Ficha fichaActual,int fila, int columna, ArrayList<Ficha> subconjunto){ //determina si la figura calza en la posición
        int puntos = 0;
        //boolean res = false;
        if(this.matrizFichas[fila][columna-1]!= null){
            int izquierda = validarColorHaciaLaIzquierda(fichaActual,fila,columna,subconjunto); // retorna los puntos y 0 (no hay fichas alrededor)
            puntos = izquierda >= 0 ? puntos + izquierda : puntos - 1000;
            puntos = izquierda == 6 ? puntos + 6 : puntos + 0;
        }
        if(this.matrizFichas[fila][columna+1]!= null){
            int derecha = validarColorHaciaLaDerecha(fichaActual,fila,columna,subconjunto);
            puntos = derecha >= 0 ? puntos + derecha : puntos - 1000;
            puntos = derecha == 6 ? puntos + 6 : puntos + 0;
        }
        if(this.matrizFichas[fila-1][columna]!= null){
            int arriba = validarColorHaciaArriba(fichaActual,fila,columna,subconjunto);
            puntos = arriba >= 0 ? puntos + arriba : puntos - 1000;
            puntos = arriba == 6 ? puntos + 6 : puntos + 0;
        }
        if(this.matrizFichas[fila+1][columna]!= null){
            int abajo = validarColorHaciaAbajo(fichaActual,fila,columna,subconjunto);
            puntos = abajo >= 0 ? puntos + abajo : puntos - 1000;
            puntos = abajo == 6 ? puntos + 6 : puntos + 0;
        }
        if(puntos > 0){
            return puntos;
        }else{
            return puntos;
        }
    }
    
    public int validarFiguraHaciaLaIzquierda(Ficha fichaActual,int fila, int columna, ArrayList<Ficha> subconjunto){
        int puntos = 0;
        for (int i = columna-1; matrizFichas[fila][i] != null && i > 0 ; i--) { 
            if ( matrizFichas[fila][i].getColor() != fichaActual.getColor() || 
                    matrizFichas[fila][i].getFigura() == fichaActual.getFigura()) {    
                //return false;
                return -1;
            }
            if(estaEnLaSubconjunto(subconjunto,fichaActual) == true){
                return 1;
            }
            puntos++;
        }
        return puntos+1;
    }
    
    public int validarFiguraHaciaLaDerecha(Ficha fichaActual,int fila, int columna, ArrayList<Ficha> subconjunto){
        int puntos = 0;
        for (int i = columna+1; matrizFichas[fila][i] != null && i < matrizFichas.length-1 ; i++) { 
            if ( matrizFichas[fila][i].getColor() != fichaActual.getColor() || 
                    matrizFichas[fila][i].getFigura() == fichaActual.getFigura()) {    
                return -1;
            }
            if(estaEnLaSubconjunto(subconjunto,fichaActual) == true){
                return 1;
            }
            puntos++;
        }
        return puntos+1;
    }
    
    public int validarFiguraHaciaArriba(Ficha fichaActual,int fila, int columna, ArrayList<Ficha> subconjunto){
        int puntos = 0;
        for (int i = fila-1; matrizFichas[i][columna] != null && i > 0 ; i--) { 
            if ( matrizFichas[i][columna].getColor() != fichaActual.getColor() || 
                    matrizFichas[i][columna].getFigura() == fichaActual.getFigura()) {    
                return -1;
            }
            if(estaEnLaSubconjunto(subconjunto,fichaActual) == true){
                return 1;
            }
            puntos++;
        }
        return puntos+1;    
    }
    
    public int validarFiguraHaciaAbajo(Ficha fichaActual,int fila, int columna, ArrayList<Ficha> subconjunto){
        int puntos = 0;
        for (int i = fila+1; matrizFichas[i][columna] != null && i < matrizFichas.length-1 ; i++) { 
            if ( matrizFichas[i][columna].getColor() != fichaActual.getColor() || 
                    matrizFichas[i][columna].getFigura() == fichaActual.getFigura()) {    
                return -1;
            }
            if(estaEnLaSubconjunto(subconjunto,fichaActual) == true){
                return 1;
            }
            puntos++;
        }
        return puntos+1;
    }
    
    public int validarColorHaciaLaIzquierda(Ficha fichaActual,int fila, int columna, ArrayList<Ficha> subconjunto){
        int puntos = 0;
        for (int i = columna-1; matrizFichas[fila][i] != null && i > 0 ; i--) { 
            if ( matrizFichas[fila][i].getColor() == fichaActual.getColor() || 
                    matrizFichas[fila][i].getFigura() != fichaActual.getFigura()) {    
                return -1;
            }
            if(estaEnLaSubconjunto(subconjunto,fichaActual) == true){
                return 1;
            }
            puntos++;
        }
        return puntos+1;
    }
    
    public int validarColorHaciaLaDerecha(Ficha fichaActual,int fila, int columna, ArrayList<Ficha> subconjunto){
        int puntos = 0;
        for (int i = columna+1; matrizFichas[fila][i] != null && i < matrizFichas.length-1 ; i++) { 
            if ( matrizFichas[fila][i].getColor() == fichaActual.getColor() || 
                    matrizFichas[fila][i].getFigura() != fichaActual.getFigura()) {    
                return -1;
            }
            if(estaEnLaSubconjunto(subconjunto,fichaActual) == true){
                return 1;
            }
            puntos++;
        }
        return puntos+1;
    }
    
    public int validarColorHaciaArriba(Ficha fichaActual,int fila, int columna, ArrayList<Ficha> subconjunto){
        int puntos = 0;
        for (int i = fila-1; matrizFichas[i][columna] != null && i > 0 ; i--) { 
            if ( matrizFichas[i][columna].getColor() == fichaActual.getColor() || 
                    matrizFichas[i][columna].getFigura() != fichaActual.getFigura()) {    
                return -1;
            }
            if(estaEnLaSubconjunto(subconjunto,fichaActual) == true){
                return 1;
            }
            puntos++;
        }
        return puntos+1;
    }
    
    public int validarColorHaciaAbajo(Ficha fichaActual,int fila, int columna, ArrayList<Ficha> subconjunto){
        int puntos = 0;
        for (int i = fila+1; matrizFichas[i][columna] != null && i < matrizFichas.length-1 ; i++) { 
            if ( matrizFichas[i][columna].getColor() == fichaActual.getColor() || 
                    matrizFichas[i][columna].getFigura() != fichaActual.getFigura()) {    
                return -1;
            }
            if(estaEnLaSubconjunto(subconjunto,fichaActual) == true){
                return 1;
            }
            puntos++;
        }
        return puntos+1;
    }
    
    public boolean estaEnLaSubconjunto(ArrayList<Ficha> subconjunto,Ficha ficha){
        for (Ficha ficha1 : subconjunto) {
            if(ficha == ficha1){
                return true;
            }
        }
        return false;
    }
    
    //aqui asignamos  tipo y orientacion
    public void determinarTipoJugada(Ficha ficha, int fila, int columna){
        if(ficha.getTipo() != primerJugada.getFicha().getTipo()){
            //AQUI DETERMINA EL TIPO DE JUGADA
            if(ficha.getColor() == primerJugada.getFicha().getColor()){
                this.setTipoJugadaActual(JugadaType.PORCOLOR);
            }else if(ficha.getFigura() == primerJugada.getFicha().getFigura()) {
                this.setTipoJugadaActual(JugadaType.PORFIGURA);
            }else{
                this.setTipoJugadaActual(null);
            }
            //AQUI DETERMINA EL TIPO DE ORIENTACION
            if (fila == primerJugada.getFila()){
                this.setOrientacion(OrientacionType.FILA);
            
            }else if (columna == primerJugada.getColumna()){
                this.setOrientacion(OrientacionType.COLUMNA);
            
            }else{
                this.setOrientacion(null);
            }
        }else{
            this.setTipoJugadaActual(null);
            this.setOrientacion(null);
        }    
    }
}
