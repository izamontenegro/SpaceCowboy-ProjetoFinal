package modelagem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Home extends JPanel{

    private Image background;
   
    public Home(){
        ImageIcon referencia = new ImageIcon("imagens//backgroundHome.png");
        background = referencia.getImage();
    }

    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(background, 0, 0, null);
        g.dispose();
    }
}