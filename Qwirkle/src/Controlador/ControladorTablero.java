/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Juego;
import Modelo.Jugador;
import Vista.PantallaTablero;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Jarod
 */
public class ControladorTablero implements ActionListener {

    private PantallaTablero pantallaTablero;
    private Juego juego;

    public ControladorTablero(Juego juego, ControladorMenu controladorMenu, int n) {
        this.juego = juego;
        this.pantallaTablero = new PantallaTablero();
        this.pantallaTablero.setN(n);
        this.pantallaTablero._init_(6, juego.getJugadores().size());
        this.pantallaTablero.setVisible(true);
        _init_();
        update();
    }
    
    public ControladorTablero(Juego juego,  int n) {
        this.juego = juego;
        this.pantallaTablero = new PantallaTablero();
        this.pantallaTablero.setN(n);
        this.pantallaTablero._init_(6, juego.getJugadores().size());
        this.pantallaTablero.setVisible(true);
        _init_();
        update();
    }

    private void _init_() {

        pantallaTablero.btnSalir.addActionListener(this);
        pantallaTablero.btnSiguiente.addActionListener(this);

        for (int i = 0; i < pantallaTablero.butArray.length; i++) {
            for (int j = 0; j < pantallaTablero.butArray[i].length; j++) {
                pantallaTablero.butArray[i][j].addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //else para la pantalla del juego
        if (pantallaTablero.isActive()) {
            if (e.getSource().equals(pantallaTablero.btnSiguiente)) {
                juego.jugar();
                update();
            } else if (e.getSource().equals(pantallaTablero.btnSalir)) {
                pantallaTablero.dispose();

            } else {
                for (int i = 0; i < pantallaTablero.butArray.length; i++) {
                    for (int j = 0; j < pantallaTablero.butArray[i].length; j++) {
                        if (e.getSource().equals(pantallaTablero.butArray[i][j])) {
                            //no lo usamos pero por si acaso

                        }
                    }
                }
            }

        }
    }
    
    private void update(){
        setImagenes();
        puntajes();
    }
    
    private void puntajes(){
        String str = "<html>";
        for (int i = 0; i < juego.getJugadores().size(); i++) {
            str += "Jugador " + Integer.toString(i+1) + " : " + Float.toString((float) juego.getJugadores().get(i).getPuntaje());
            str += "<br/>";
            
        }
        str += "<html/>";
        
        pantallaTablero.lblTurno.setText(str);
    }

    private void setImagenes() {
        setTablero();
        setManos();
    }

    private void setTablero() {
        for (int i = 0; i < juego.getMatrizFichas().length; i++) {
            for (int j = 0; j < juego.getMatrizFichas()[i].length; j++) {
                if (juego.getMatrizFichas()[i][j] != null) {
                    
                    JButton b = pantallaTablero.butArray[i][j];
                    b.setIcon(new ImageIcon(juego.getMatrizFichas()[i][j].getImagen()));
                    b.setOpaque(false);
                    b.setContentAreaFilled(false);
                    b.setBorderPainted(false);
                }
            }
        }
    }

    private void setManos() {
        for (int i = 0; i < pantallaTablero.fichasJugadores.length; i++) {
            Jugador jugador = juego.getJugadores().get(i);
            for (int j = 0; j < pantallaTablero.fichasJugadores[i].length; j++) {
                if (jugador.getMano()[j] != null) {
                    //setea imagen
                    JButton b = pantallaTablero.fichasJugadores[i][j];

                    
                    
                    b.setIcon(new ImageIcon(jugador.getMano()[j].getImagen()));
        
                    b.setOpaque(false);
                    b.setContentAreaFilled(false);
                    b.setBorderPainted(false);

                }
            }
        }
    }

}
