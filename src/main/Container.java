package main;

//bibliotecas
import javax.swing.JFrame;

import modelagem.FaseGeral;

public class Container extends JFrame {

    public Container() {
        add(new FaseGeral());

        setTitle("Space Cowboy - intergalactic hunt");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(true);
        setVisible(true);

    }

    public static void main(String[] args) {

        new Container();

    }

}