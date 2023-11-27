package modelagem;

//Bibliotecas
import java.awt.Image;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class InimigoAzul implements ActionListener {
    private Image imagem;
    ImageIcon referencia = new ImageIcon("imagens//inimigoAzul.gif");
    private int x, y;
    private int largura, altura;
    private boolean isVisible;
    private static int VELOCIDADE = 2;
    private Timer timer;
    private boolean colisao = false;

    public InimigoAzul(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;

        timer = new Timer(200, this);
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (colisao) {
            sofrerDano();

             isVisible = false;

        } else {
            referencia = new ImageIcon("imagens//inimigoAzul.gif");
            dadosImagem();
        }
    }

    public void sofrerDano() {
        referencia = new ImageIcon("imagens//inimigoAzulDanoSofrido.gif");
        dadosImagem();
        
    }

    public void dadosImagem() {
        imagem = referencia.getImage();
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void movimenta() {
        if (this.y > 700) {
            this.y = -300;
            Random r = new Random();
            int n = r.nextInt(1724);
            this.x = n - 300;

        } else
            this.y += VELOCIDADE;
    }

    public Rectangle getLimites() {
        return new Rectangle(x, y, largura, altura);
    }

    // Getters and Setters
    public void setColisao(boolean c) {
        this.colisao = c;
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
