package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//bibliotecas
import javax.swing.JFrame;
import javax.swing.Timer;

import modelagem.Horda1;
import modelagem.Horda2;

public class Container extends JFrame implements ActionListener {
    Timer timer;
    int contador;
    Horda1 fase1 = new Horda1();
    Horda2 fase2 = new Horda2();
    boolean faseAtiva = false;

    public Container() {
        timer = new Timer(1000, this);
        timer.start();

        setTitle("Space Cowboy - intergalactic hunt");
        setSize(1440, 728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);

        alternarFase();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        contador++;
        System.out.println(fase1.getPontos());
        System.out.println(contador);

        if (contador == 10) {
            alternarFase();
        }

    }

    private void alternarFase() {
        if (faseAtiva) {
            remove(fase1);
            add(fase2);
            fase2.requestFocusInWindow();
        } else {
            remove(fase2);
            add(fase1);
            fase1.requestFocusInWindow();
        }

        faseAtiva = !faseAtiva;
        revalidate();
        repaint();
    }

    public static void main(String[] args) {

        new Container();

    }

}