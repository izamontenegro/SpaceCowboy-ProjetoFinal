package modelagem;

//bibliotecas
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;


public class Player{
    private int x, y;
    private int dx, dy;
    private Image imagem;
    ImageIcon referencia = new ImageIcon("imagens//naveJogo (3) (1).gif");
    private int altura, largura;
    private List<AtaquePlayer> tiros;
    private boolean isVisivel;
    
    

    public Player() {
        this.x = 550;
        this.y = 480;
        isVisivel = true;

        tiros = new ArrayList<AtaquePlayer>();

    }

    public void dadosImagem() {
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void sofrerDano() {
        referencia = new ImageIcon("imagens//naveAzul.gif");
    }

    public void recuperaDano() {
        referencia = new ImageIcon("imagens//naveJogo (3) (1).gif");
    }

    public void movimenta() {

        y += dy;
        x += dx;

    }

    public void tiroSimples() {
        this.tiros.add(new AtaquePlayer(x + 17, y - 40));
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

    public List<AtaquePlayer> getTiros() {
        return tiros;
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
