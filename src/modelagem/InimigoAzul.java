package modelagem;

//Bibliotecas
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class InimigoAzul {
    private Image imagem; // Imagem do inimigo1
    private int x, y; // Posição do inimigo
    private int largura, altura; // Altura e largura da imagem
    private boolean isVisible; // Visibilidade do inimigo
    private static int VELOCIDADE = 3; // Velocidade com que ele irá cair

    // Construtor que irá colocar o inimigo numa posição x,y
    public InimigoAzul(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    // Método que pega as informações da imagem
    public void load() {
        ImageIcon referencia = new ImageIcon("imagens//inimigoAzul.png");
        imagem = referencia.getImage();
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    // Método que realiza a movimentação do inimigo a partir da posição de origem e
    // a velocidade estipulada
    public void update() {
        this.y += VELOCIDADE;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    // Getters and Setters
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
