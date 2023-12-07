package main;

//bibliotecas
import javax.swing.JFrame;
import modelagem.FaseGeral;
import modelagem.FaseMultiplayer;
import modelagem.TelaGameOver;
import modelagem.TelaInicio;



public class Container extends JFrame {

    public Container() {
        add(new TelaGameOver());

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