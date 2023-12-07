package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//bibliotecas
import javax.swing.JFrame;
import javax.swing.Timer;

import modelagem.Fase;
import modelagem.FaseGeral;
import modelagem.Home;


public class Container extends JFrame  {
  

    public Container() {
    	add(new Fase());
        setTitle("Space Cowboy - intergalactic hunt");
        setSize(1440, 728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);  
    }

 



    public static void main(String[] args) {

        new Container();

    }

}