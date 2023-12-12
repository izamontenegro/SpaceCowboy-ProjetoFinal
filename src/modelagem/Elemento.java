package modelagem;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Elemento {
    protected Image imagem;
    protected int x, y;
    protected int largura;
    protected int altura;
    protected boolean isVisible;

    public Elemento(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    public void dadosImagem(String imagePath) {
        ImageIcon referencia = new ImageIcon(imagePath);
        imagem = referencia.getImage();
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public Rectangle getLimites() {
        return new Rectangle(x, y, largura, altura);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }
}
