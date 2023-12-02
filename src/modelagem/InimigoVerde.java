package modelagem;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class InimigoVerde implements ActionListener{
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisible;
    private static int VELOCIDADE = 1;
    private int vida = 2;

    private List<AtaqueInimigo> ataques;
    private Timer timer;

    public List<AtaqueInimigo> getAtaques() {
        return ataques;
    }

    public InimigoVerde(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;

        ataques = new ArrayList<AtaqueInimigo>();

        timer = new Timer(500, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.x >= -10 && y <= 800) {
            atacar();
        } else
            ;

    }

    public void atacar() {
        this.ataques.add(new AtaqueInimigo(this.x + 12, this.y + 25));
    }

    public int getVida() {
        return this.vida;
    }

    public void setVida(int x) {
        this.vida = this.vida - x;
    }

    public void dadosImagem() {
        ImageIcon referencia = new ImageIcon("imagens//InimigoVerde.gif");
        imagem = referencia.getImage();
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void movimenta() {
        if (this.y > 900) {
            this.y = -300;
            Random r = new Random();
            int n = r.nextInt(1724);
            this.x = n - 300;

        } else
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
