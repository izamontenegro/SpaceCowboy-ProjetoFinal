package main;
import modelagem.TelaInicio;

//bibliotecas
import javax.swing.JFrame;

public class Container extends JFrame  {
  

    public Container() {
    	add(new TelaInicio());
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