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
 * @author Jarod
 */
public class JugadorSimple extends Jugador {

    
    //mano
    //saca combinaciones de mano
    //luego las combinaciones que comparten una caracteristica (figura/color)
    //luego las combinaciones validas las procede a permutar
    //y ahora a cada ficha de las permutaciones busca las posiciones
    //y despues las va combinando entre ellas
    
    
    
    //por ahora la lista es de pares
    //no es de pares y valor

    //matriz de soluciones
    public ArrayList<ArrayList<Movimiento>> jugar(Ficha[][] matriz, Ficha[] mano) {
        ArrayList<ArrayList<Movimiento>> arr = new ArrayList();

        //obtengo todos los subconjuntos de posibles de la mano
        ArrayList<ArrayList<Ficha>> combinaciones = combinaciones(mano);
        for (int i = 0; i < combinaciones.size(); i++) {
            //permuto cada combinacion
            ArrayList<ArrayList<Ficha>> permutacionesDe = new ArrayList();
            permutarFichas (combinaciones.get(i), combinaciones.get(i).size(), permutacionesDe);
            //ahora recorro cada una de las permutaciones
            for (int j = 0; j < permutacionesDe.size(); j++) {
                jugar_aux (0, matriz, permutacionesDe.get(i), new ArrayList(), arr);
            }
        }
        return arr;
    }

    
    //[(2,3),(12,4),(0,1),null,(4,3)]
    //integrar jugadaValida en el backtracking
    //lista de jugadas
    private void jugar_aux(int num, Ficha[][] matriz, ArrayList<Ficha> permutacion, ArrayList<Movimiento> solucion,ArrayList<ArrayList<Movimiento>> arr) {
        if (num==permutacion.size()) {//cambia cuando el num(indice) sea igual al tamaño de permutacion
            arr.add(solucion);
        } else {

            //Ficha fichaActual = siguienteFicha(permutacion, solucion);//obtengo la primer ficha de la mano que no este en la solucion
            //la linea anterior se elimina pues el avance en permutacion es secuencial
            //primero pos 0, luego pos 1, ...
            
            //deshacer esta linea
            ArrayList<Movimiento> posicionesMano = posiblesPosiciones(matriz, permutacion.get(num), solucion);//me retorna todas la posibles mano
            //¿que si no jay pos validas?
            //utilizo la funcion de Carlos
            for (int i = 0; i < posicionesMano.size(); i++) {
                //asegurarme que permuta
                solucion.add(posicionesMano.get(i));
                jugar_aux(num+1, matriz, permutacion, solucion,arr);
                solucion.remove(posicionesMano.get(i));
            }
        } 
    }
    
    
    public void permutarFichas (ArrayList<Ficha> conjunto, int tamano, ArrayList<ArrayList<Ficha>> todas) {
        if (0 == tamano) {
            
            ArrayList<Ficha> nuevo = (ArrayList<Ficha>) conjunto.clone();
            
            todas.add(nuevo);
        }
        else {
            for (int i = 0; i < tamano; i++) {
                Collections.swap(conjunto, tamano-1, i);
                permutarFichas (conjunto, tamano-1, todas);
                Collections.swap(conjunto, tamano-1, i);
            }
        }
    }

    //retorna todas las combinaciones que comparten una propiedad
    private ArrayList<ArrayList<Ficha>> combinaciones(Ficha[] mano) {
        ArrayList<ArrayList<Ficha>> lista = new ArrayList();
        partesDe(lista, new ArrayList(), mano, 0);
        ArrayList<ArrayList<Ficha>> remover = new ArrayList();
        //validar que compartand una propiedad
        //recorre cada subconjunto
        for (int i = 0; i < lista.size(); i++) {
            //recorre el subconjunto
            ArrayList<Ficha> sub = lista.get(i);
            if (sub.size() > 1) {//todos los de tamaño 1 son validos
                for (int j = 1; j < sub.size(); j++) {
                    if ((!sub.get(j - 1).getColor().equals(sub.get(i).getColor()))
                            | (!sub.get(j - 1).getFigura().equals(sub.get(i).getFigura()))) {
                        //si entra es porque el subconjunto no cumple con color o figura
                        remover.add(sub);
                        break;
                    }
                }
            }

        }
        for (int i = 0; i < remover.size(); i++) {
            lista.remove(remover.get(i));
        }
        //retorna todas
        return lista;

    }
    

    //https://java2blog.com/find-subsets-set-power-set/
    //retorna todos los subconjuntos
    private void partesDe(ArrayList<ArrayList<Ficha>> lista, ArrayList<Ficha> resultado, Ficha[] mano, int inic) {
        lista.add(new ArrayList(resultado));
        for (int i = inic; i < mano.length; i++) {
            resultado.add(mano[i]);
            partesDe(lista, resultado, mano, i + 1);
            resultado.remove(resultado.size() - 1);
        }
    }

    //convierte de arraylist a arreglo 
    private Ficha[] convertir (ArrayList<Ficha> lista){
        Ficha[] lisFi = new Ficha[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            lisFi[i] = lista.get(i);
        }
        return lisFi;
    }
    
    
    private ArrayList<Movimiento> posiblesPosiciones(Ficha[][] matriz, Ficha ficha, ArrayList<Movimiento> solucion) {
        //ArrayList<Jugada> posiciones = new ArrayList();
        //Ficha[][] matrizTemp = generarMatriz (matriz, solucion);//me retorna una matriz donde tengo la solucion insertada en la matriz

        /*switch(solucion.size()){
            case 0://si tamaño solucion es cero, recorro toda la matriz buscando TODAS las posibles soluciones
                posiciones = todaLaMatriz(matriz, ficha);//recorre toda la matriz en busca de todos los lugares que quepa
                //si la matriz esta vacia se inicia fijo en el centro
                break;
            case 1://si tamño solucion es uno, recorro alrededor de la posicion
                break;
            case 2://si tamaño es dos, asigno los tipos de jugada color/figuro y orientacion
                break;
            default://si el tamaño es mayor a dos, pregunto tipo de figura y orientacion y donde se cumpla la inserto
        }*/
        return todaLaMatriz(matriz, ficha);//recorre toda la matriz en busca de todos los lugares que quepa
    }

    private ArrayList<Movimiento> todaLaMatriz(Ficha[][] matriz, Ficha ficha) {
        ArrayList<Movimiento> posibles = new ArrayList();
        if (matriz[7][9] == null) {//no hay nada en el centro
            posibles.add(new Movimiento(7, 9, 1, ficha));
        } else {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                   /* int puntos = jugadaValida(ficha, i, j);
                    if (puntos>0){//si es mayor a cero es que es valida
                        posibles.add(new Movimiento(i,j,puntos,ficha));
                    }*/
                }
            }
        }
        return posibles;
    }

    //retorna la siguiente ficha que no este en la mano
    private Ficha siguienteFicha(ArrayList<Ficha> mano, ArrayList<Movimiento> solucion) {
        for (int i = 0; i < mano.size(); i++) {
            if (!isInSolucion(mano.get(i), solucion)) {
                return mano.get(i);
            }
        }
        return null;
    }

    //retorna truee si la ficha ya esta en la solucion
    private boolean isInSolucion(Ficha ficha, ArrayList<Movimiento> solucion) {
        for (int i = 0; i < solucion.size(); i++) {
            if (ficha.equals(solucion.get(i).getFicha())) {
                return true;
            }
        }
        return false;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Integer> conjunto = new ArrayList();
        conjunto.add(1);
        conjunto.add(2);
        conjunto.add(3);
        
        ArrayList<Integer> solucion = new ArrayList();
        
        int posicion = 1;
        
        
        ArrayList<ArrayList<Integer>> todas = new ArrayList();
        
        permutaciones(conjunto, conjunto.size(), todas);
        System.out.println("gato");
        for (int i = 0; i < todas.size(); i++) {
            System.out.println("g");
            for (int j = 0; j < todas.get(i).size(); j++) {
                
                System.out.print(todas.get(i).get(j));
                System.out.print("");
            }
            System.out.println("pero");
            System.out.println("");
        }
        
    }
    
    public static void permutaciones ( ArrayList<Integer> conjunto, int tamano, ArrayList<ArrayList<Integer>> todas ) {
        
        if (0 == tamano) {
            
            ArrayList<Integer> nuevo = (ArrayList<Integer>) conjunto.clone();
            
            todas.add(nuevo);
        }
        else {
            for (int i = 0; i < tamano; i++) {
                Collections.swap(conjunto, tamano-1, i);
                permutaciones (conjunto, tamano-1, todas);
                Collections.swap(conjunto, tamano-1, i);
            }
        }
        
    }
    
    
    
    
    
    
    
    
}
