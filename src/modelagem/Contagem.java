package modelagem;

import java.awt.Image;

import javax.swing.ImageIcon;
public class Contagem {
    private String[] numeros={"imagens\\10.png","imagens\\9.png","imagens\\8.png","imagens\\7.png","imagens\\6.png","imagens\\5.png","imagens\\4.png","imagens\\3.png","imagens\\2.png","imagens\\1.png"};
    private int x,y;
    private Image imagem;
    private int i=0;
    public Contagem(){
        this.y=220;
        this.x=620;
    }

    public Image getImagem() {
        return imagem;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void load(){
        if(i==10){
            i=0;
        }
            ImageIcon referencia= new ImageIcon(numeros[i]);
            imagem=referencia.getImage();
            imagem.getHeight(null);
            imagem.getWidth(null);
            i++;
            
    }

}