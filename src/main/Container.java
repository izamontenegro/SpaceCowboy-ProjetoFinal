package main;

//bibliotecas
import javax.swing.JFrame;
import modelagem.Fase;
import modelagem.Home;

public class Container extends JFrame {

    public Container() {

        add(new Fase());
        setTitle("Space Cowboy - intergalactic hunt");
        setSize(1440, 728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo( null);
        this.setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {

        new Container();

    }
}