package modelagem;

//bibliotecas
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener {
    private int x, y;
    private int dx, dy;
    private Image imagem;
    ImageIcon referencia = new ImageIcon("imagens//NaveVermelha.gif");
    private int altura, largura;
    private List<AtaquePlayer> tiros;
    private boolean isVisivel;
    private Timer timer;
    private boolean colisao = false;
    private boolean escudo = false;

    public Player() {
        this.x = 550;
        this.y = 480;
        isVisivel = true;

        tiros = new ArrayList<AtaquePlayer>();

        timer = new Timer(500, this);
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (colisao == true) {
            sofrerDano();
            colisao = false;
        } else if (colisao == false) {
            referencia = new ImageIcon("imagens//NaveVermelha.gif");
            dadosImagem();
        }

        if (escudo) {
            referencia = new ImageIcon("imagens//NaveAzul.gif");
            dadosImagem();
            escudo = false;
        }

    }

    public void dadosImagem() {
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void sofrerDano() {
        referencia = new ImageIcon("imagens//naveVermelhaDano.gif");
        dadosImagem();
    }

    public void movimenta() {

        y += dy;
        x += dx;

    }

    public void tiroSimples() {
        this.tiros.add(new AtaquePlayer(x + 17, y - 40));
        referencia = new ImageIcon("imagens//NaveVermelhaTiro.gif");
        dadosImagem();
    }

    public Rectangle getLimites() {
        return new Rectangle(x, y, largura, altura);
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_X) {
            tiroSimples();

        }

        if (codigo == KeyEvent.VK_UP) {

            dy = -3;

        }

        if (codigo == KeyEvent.VK_DOWN) {

            dy = 3;

        }

        if (codigo == KeyEvent.VK_LEFT) {

            dx = -3;
        }

        if (codigo == KeyEvent.VK_RIGHT) {

            dx = 3;
        }

    }

    public void keyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        }

        if (codigo == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

    }

    // Getters and Setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setEscudo(boolean e) {
        this.escudo = e;
    }

    public boolean getEscudo() {
        return this.escudo;
    }

    public List<AtaquePlayer> getTiros() {
        return tiros;
    }

    public void setColisao(boolean c) {
        this.colisao = c;
    }

    public void setTiros(List<AtaquePlayer> tiros) {
        this.tiros = tiros;
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

}
