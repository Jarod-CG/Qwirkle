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
    
    public ArrayList<Movimiento> getListaPrimerosMovimientos(Ficha ficha){
        ArrayList<Movimiento> primerosMovimientos = new ArrayList<>();
        for (int fila = 0; fila < this.matrizFichas.length; fila++) {
            for (int columna = 0; columna < this.matrizFichas.length; columna++) {
                if (this.matrizFichas[fila][columna] == null) {
                    int puntos = calcularPuntos(ficha, fila, columna);
                    if (puntos > 0) {
                        primerosMovimientos.add(new Movimiento(fila, columna, puntos, ficha));
                    }
                }
            }
        }
        return primerosMovimientos;
    }
    
    /*
    [0,1,2,3,4,5,6,7,8,9]
    [%,%,%,%,%,%,%,%,%,%]
    [%,%,%,y,h,t,%,%,%,%]
    [%,%,%,h,%,%,%,%,%,%]
    [%,%,%,%,%,%,%,%,%,%]
    [%,%,%,%,%,%,%,%,%,%]
    */
    public Jugada crearJugada(ArrayList<Ficha> subconjunto, DireccionType direccion, Movimiento primerMov) {
        Jugada jugada = new Jugada();
        jugada.setPrimerMovimiento(primerMov);
        jugada.add(jugada.getPrimerMovimiento());
        for (int i = 1; i < subconjunto.size(); i++) {
            if (direccion == DireccionType.DERECHA || direccion == DireccionType.IZQUIERDA) {
                jugada.setOrientacion(OrientacionType.FILA);
                for (int j = primerMov.getColumna() + direccion.getValor(); matrizFichas[primerMov.getFila()][j] == null && j > 0 && j < matrizFichas.length; j += direccion.getValor()){
                    int puntos = calcularPuntos(subconjunto.get(i), primerMov.getFila(), j);
                    if (puntos > 0) {
                        Movimiento movimiento = new Movimiento(primerMov.getFila(), j, puntos, subconjunto.get(i));
                        jugada.add(movimiento);
                        jugada.sumarPuntos(puntos);
                    }else{
                        return null;
                    }
                }
            }else{
                jugada.setOrientacion(OrientacionType.COLUMNA);
                for (int j = primerMov.getFila() + direccion.getValor(); matrizFichas[j][primerMov.getColumna()] == null && j > 0 && j < matrizFichas.length; j += direccion.getValor()){
                    int puntos = calcularPuntos(subconjunto.get(i), j, primerMov.getColumna());
                    if (puntos > 0) {
                        Movimiento movimiento = new Movimiento(j, primerMov.getColumna(), puntos, subconjunto.get(i));
                        jugada.add(movimiento);
                        jugada.sumarPuntos(puntos);
                    }else{
                        return null;
                    }
                }
            }
        }
        return jugada;
    }
    
    
    
    public ArrayList<Jugada> getListaJugadas(ArrayList<Ficha> subconjunto) { //AQUI LAS PERMUTACIONES DE LA MANO 
        ArrayList<Jugada> jugadas = new ArrayList<>();
        
        ArrayList<Movimiento> primerosMovimientos = getListaPrimerosMovimientos(subconjunto.get(0));
        if (subconjunto.size() > 1) {
            for (Movimiento primerMovimiento : primerosMovimientos) {
                for (DireccionType direccion : DireccionType.values()) {
                    Jugada jugada = crearJugada(subconjunto, direccion,primerMovimiento);
                    if(jugada != null){
                        jugadas.add(jugada);
                    }
                }
            }
        }
        /*for (Ficha ficha : subconjunto) {            
            if (jugada.getPrimerMovimiento() == null) { //CREA LA PRIMERA JUGADA
                
            } else {
                if (this.segundaJugada == false) {
                    
                }
            }   
        }*/
        //jugadas.add(jugada);
        return jugadas;
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
    
    /*  
        Posibles jugadas
        [{Movimiento(2,3->5),Movimiento(5,6->2)},
        {Movimiento(2,3->5)},
        {Movimiento(2,3->5),Movimiento(5,6->2)},Movimiento(2,3->5),Movimiento(5,6->2)},
        {},
        {Movimiento(2,3->5),Movimiento(5,6->2)}},
        {Movimiento(2,3->5),Movimiento(5,6->2)},Movimiento(2,3->5)}] 
         */
    public int calcularPuntos(Ficha ficha, int fila, int columna){
        int puntos = 0;
        if (validarFigura(ficha, fila, columna) > 0) {
            puntos += validarFigura(ficha, fila, columna);
        }
        if (validarColor(ficha, fila, columna) > 0) {
            puntos += validarColor(ficha, fila, columna);
        }        
        if (puntos > 0) {
            return puntos;
        }
        return 0;
    }
    
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
