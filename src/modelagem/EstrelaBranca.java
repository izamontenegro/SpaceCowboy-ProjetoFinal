package modelagem;

//Bibliotecas
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class EstrelaBranca {
    private Image imagem;
    private int x, y;
    private boolean isVisible;
    private static int VELOCIDADE = 3;

    public EstrelaBranca(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    public void load() {
        ImageIcon referencia = new ImageIcon("imagens//estrelaBranca.png");
        imagem = referencia.getImage();
    }

    public void update() {
        if (this.y > 560) {
            this.y = -300;
            Random a = new Random();
            int m = a.nextInt(768);
            this.y = m;
            Random r = new Random();
            int n = r.nextInt(1724);
            this.x = n - 300;

        } else
            this.y += VELOCIDADE;
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
