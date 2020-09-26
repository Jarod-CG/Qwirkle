/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Juego;
import Vista.PantallaMenu;
import Vista.PantallaTablero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jarod
 */
public class Controlador implements ActionListener{
    
    private PantallaMenu pantallaMenu;
    private PantallaTablero pantallaTablero;
    private Juego juego;

    public Controlador( ) {
        this.pantallaMenu = new PantallaMenu();
        this.pantallaTablero = new PantallaTablero();
        pantallaMenu.setVisible(true);
        _init_();
    }
    
    private void _init_(){
        pantallaMenu.btnAlgSimMin.addActionListener(this);
        pantallaMenu.btnAlgSimMax.addActionListener(this);
        pantallaMenu.btnAlgAvaMin.addActionListener(this);
        pantallaMenu.btnAlgAvaMax.addActionListener(this);
        pantallaMenu.btnPlayMin.addActionListener(this);
        pantallaMenu.btnPlaMax.addActionListener(this);
        pantallaMenu.btnJugar.addActionListener(this);
        pantallaMenu.btnSalir.addActionListener(this);
        
        pantallaTablero.btnAbajo.addActionListener(this);
        pantallaTablero.btnArriba.addActionListener(this);
        pantallaTablero.btnDerecha.addActionListener(this);
        pantallaTablero.btnIzquierda.addActionListener(this);
        pantallaTablero.btnSalir.addActionListener(this);
        pantallaTablero.btnSiguiente.addActionListener(this);
        
        
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pantallaMenu.isVisible()){
            if (e.getSource().equals(pantallaMenu.btnAlgSimMin)){
                int num = Integer.parseInt(pantallaMenu.lblAlgSim.getText());
                if (num>0){
                    pantallaMenu.lblAlgSim.setText(Integer.toString(num-1));
                }
            }
            else if (e.getSource().equals(pantallaMenu.btnAlgSimMax)){
                int num = Integer.parseInt(pantallaMenu.lblAlgSim.getText());
                if (num<3){
                   pantallaMenu.lblAlgSim.setText(Integer.toString(num+1)); 
                }
            }
            else if (e.getSource().equals(pantallaMenu.btnAlgAvaMin)){
                int num = Integer.parseInt(pantallaMenu.lblAlgAva.getText());
                if (num>0){
                    pantallaMenu.lblAlgAva.setText(Integer.toString(num-1));
                }
            }
            else if (e.getSource().equals(pantallaMenu.btnAlgAvaMax)){
                int num = Integer.parseInt(pantallaMenu.lblAlgAva.getText());
                if (num<3){
                    pantallaMenu.lblAlgAva.setText(Integer.toString(num+1));
                }
            }
            else if (e.getSource().equals(pantallaMenu.btnPlayMin)){
                int num = Integer.parseInt(pantallaMenu.lblPla.getText());
                if (num>0){
                    pantallaMenu.lblPla.setText(Integer.toString(num-1));
                }
            }
            else if (e.getSource().equals(pantallaMenu.btnPlaMax)){
                int num = Integer.parseInt(pantallaMenu.lblPla.getText());
                if (num<1){
                    pantallaMenu.lblPla.setText(Integer.toString(num+1));
                }
            }
            else if (e.getSource().equals(pantallaMenu.btnJugar)){
                //validar que haya al menos un jugador de cada tipo
                int numSim = Integer.parseInt(pantallaMenu.lblAlgSim.getText());
                int numAva = Integer.parseInt(pantallaMenu.lblAlgAva.getText());
                int numHum = Integer.parseInt(pantallaMenu.lblPla.getText());
                if (numSim>0 && numAva >0) {
                    this.juego = new Juego (numSim, numAva, numHum);
                    //cerrar esta pantalla 
                    pantallaMenu.dispose();
                    //abrir la del tablero
                    pantallaTablero.setVisible(true);
                }
                
            }
            else if (e.getSource().equals(pantallaMenu.btnSalir)){
                pantallaMenu.dispose();
            }
        }
        //else para la pantalla del juego
        else if (pantallaTablero.isActive()){
            if (e.getSource().equals(pantallaTablero.btnArriba)){
                
            }
            else if (e.getSource().equals(pantallaTablero.btnAbajo)){
                
            }
            else if (e.getSource().equals(pantallaTablero.btnIzquierda)){
                
            }
            else if (e.getSource().equals(pantallaTablero.btnDerecha)){
                
            }
            else if (e.getSource().equals(pantallaTablero.btnSiguiente)){
                
            }
            else if (e.getSource().equals(pantallaTablero.btnSalir)){
                pantallaTablero.dispose();
                pantallaMenu.setVisible(true);
            }
        }
    }
    
}
