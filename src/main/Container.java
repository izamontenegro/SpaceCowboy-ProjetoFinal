package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import modelagem.Home;

public class Container extends JFrame{

    public Container(){
        add(new Home());
        setTitle("Meu Jogo");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);

    }

    //teste
    public static void main(String[] args){
        new Container();
    }
}

