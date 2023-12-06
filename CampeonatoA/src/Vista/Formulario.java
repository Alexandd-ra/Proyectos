/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import Modelo.*;
import Control.*;


/**
 *
 * @author APL
 */
public class Formulario extends JFrame{
    JLabel labTitulo,labNombre,labId, labDireccion, labTelefono, labNivel, labPartidas, labColor1, labColor2, labJugador1;
        JLabel labNombre2,labId2, labDireccion2, labTelefono2, labNivel2, labPartidas2, labJugador2;
    JTextField jtfTitulo, jtfnombre, jtfId, jtfDireccion, jtfTelefono, jtfNivel, jtfPartidas, jtfColor1, jtfColor2;
    
    
    public Formulario(){
        super("Campeonato Ajedrez");
        
        setLayout(null);
        initComponents();
        setTitle("CAMPEONATO DE AJEDREZ");
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
        
        
    }
    
    
    public void initComponents(){
        
        
    
         
    labTitulo= new JLabel();
    labTitulo.setFont(new java.awt.Font("Lucida Console",0,28));
    labTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    labTitulo.setText("CAMPEONATO DE AJEDREZ");
    labTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    labTitulo.setBounds(185,20,400,40);
    add(labTitulo);
    
    labNombre= new JLabel();
    labNombre.setFont(new java.awt.Font("Lucida Console",0,12));
    labNombre.setText("Nombre: ");
    labNombre.setBounds(20,60,100,100);
    add(labNombre);
    
    
    
    
    
    
    setVisible(true);
    }
    
}
