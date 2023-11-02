package main;

import javax.swing.JFrame;

import modelagem.Fase1;

//Conteiner é a janela principal do jogo, onde as fases e outras telas estarão
public class Container extends JFrame {
    public Container() {
        add(new Fase1());
        setTitle("Meu Jogo");
        setSize(1440, 728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }

    // chamando Conteiner e rodando tudo que está nele
    public static void main(String[] args) {
        new Container();
    }
}