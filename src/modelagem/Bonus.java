package modelagem;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bonus {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisible;
    private ImageIcon referencia;
    private static int VELOCIDADE = 1;

    public Bonus(int x, int y, int tipo) {
        this.x = x;
        this.y = y;
        isVisible = true;

        if (tipo == 1) {
            referencia = new ImageIcon("imagens//moedaEscudo.gif");
            imagem = referencia.getImage();
        } else if (tipo == 2) {
            referencia = new ImageIcon("imagens//moedaAtaqueEspecial.gif");
            imagem = referencia.getImage();
        } else {
            referencia = new ImageIcon("imagens//moedaVida.gif");
            imagem = referencia.getImage();
        }
    }

    public void dadosImagem() {
        imagem = referencia.getImage();
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void movimenta() {
        this.y += VELOCIDADE;
    }

    // Getters and Setters

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

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

}
