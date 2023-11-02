package modelagem;

//bibliotecas
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Player {
    private int x, y; // posição da nave
    private int dx, dy; // variação da posição do player
    private Image imagem; // imagem da nave
    private int altura, largura; // tamanho da imagem da nave
    private List<AtaquePlayer> tiros; // Lista que vai conter todos os tiros disparados
    private boolean isVisivel;

    // Construtor para que ao ser inicializado o player esteja no centro da tela e a
    // lista de tiros seja inicializada
    public Player() {
        this.x = 550;
        this.y = 480;
        isVisivel = true;

        tiros = new ArrayList<AtaquePlayer>();
    }

    // Pegando todos os dados da imagem do player
    public void load() {
        ImageIcon referencia = new ImageIcon("imagens//naveJogo.png");
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    // Métodos para realizar o movimento, se baseando na posição atual e ma variação
    // feita ao pressionar teclas específicas
    public void update() {

        y += dy;
        x += dx;

    }

    // Método que adiciona um ataque do tipo AtaquePlayer á lista de ataques da
    // classe
    public void tiroSimples() {
        this.tiros.add(new AtaquePlayer(x - 10, y - 40));
    }


    public void calculaPontos(){
        
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    // Método que verifica qual tecla foi pressionada para realizar a variação de
    // movimento
    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_X) {
            tiroSimples();
        }

        if (codigo == KeyEvent.VK_UP) {
            if (y - 3 < -100) {
                dy = 0;
            } else
                dy = -3;
        }

        if (codigo == KeyEvent.VK_DOWN) {
            if (y + 3 > 510) {
                dy = 0;
            } else
                dy = 3;

        }

        if (codigo == KeyEvent.VK_LEFT) {
            if (x - 3 < -100) {
                dx = 0;
            } else
                dx = -3;
        }

        if (codigo == KeyEvent.VK_RIGHT) {
            if (x + 3 > 1260) {
                dx = 0;
            } else
                dx = 3;
        }

    }

    // Método para verificar quando a tecla foi solta, ou o player continuaria
    // seguindo infinitamente nas direções que foram inicialmente escolhidas
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
