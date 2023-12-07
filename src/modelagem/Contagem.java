package modelagem;

import java.awt.Image;

import javax.swing.ImageIcon;
public class Contagem {
    private String[] numeros={"img\\10.png","img\\9.png","img\\8.png","img\\7.png","img\\6.png","img\\5.png","img\\4.png","img\\3.png","img\\2.png","img\\1.png"};
    private int x,y;
    private Image imagem;
    private int altura,largura;
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
            altura=imagem.getHeight(null);
            largura=imagem.getWidth(null);
            i++;
            
    }

}
