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

    private Ficha[][] matrizFichas; //no se cosideran los bordes de las matriz
    //de 19x15 fichas visibles, asi que 21x17 por que los bordes no se ven
    private ArrayList<Jugador> jugadores;
    private Ficha fichaActual;
    private ArrayList<Ficha> fichas;
    private JugadaType tipoJugadaActual;
    private Movimiento primerJugada;

    public Juego(int numSim, int numAva, int numHum) {
        this.tipoJugadaActual = null;
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
    
    
    public int jugadaValida(Ficha ficha, int fila, int columna) {
        int puntos = 0;
        if (primerJugada != null) {
            this.determinarTipoJugada(ficha);
            if (this.tipoJugadaActual == JugadaType.PORFIGURA) {
                if (validarFigura(ficha, fila, columna) > 0) {
                    puntos += validarFigura(ficha, fila, columna);
                }
            } else {
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
        }

        return puntos;
    }

    public void determinarTipoJugada(Ficha ficha){
        if(ficha.getTipo() != primerJugada.getFicha().getTipo()){
            if (ficha.getColor() == primerJugada.getFicha().getColor()) {
                this.setTipoJugadaActual(JugadaType.PORCOLOR);
            } else if(ficha.getFigura() == primerJugada.getFicha().getFigura()) {
                this.setTipoJugadaActual(JugadaType.PORFIGURA);
            }
        }else{
            this.setTipoJugadaActual(null);
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

    public JugadaType getTipoJugadaActual() {
        return tipoJugadaActual;
    }

    public void setTipoJugadaActual(JugadaType tipoJugadaActual) {
        this.tipoJugadaActual = tipoJugadaActual;
    }

    public Movimiento getPrimerJugada() {
        return primerJugada;
    }

    public void setPrimerJugada(Movimiento primerJugada) {
        this.primerJugada = primerJugada;
    }
    
    

}
