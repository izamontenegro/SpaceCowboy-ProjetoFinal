package modelagem;

//bibliotecas
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player2 implements ActionListener {
    private int x, y;
    private int dx, dy;
    private Image imagem;
    ImageIcon referencia = new ImageIcon("imagens//NaveAzul.gif");
    private int altura, largura;
    private List<AtaquePlayer> tiros;
    private boolean isVisivel;
    private Timer timer;
    private boolean colisao = false;
    private boolean escudo = false;
    private int qtdAtaquesEspeciais = 0;
    private ImageIcon tiroRef = new ImageIcon("imagens//atkespecialplayer.png");
    private Clip clip;

    public Player2() {
        this.x = 900;
        this.y = 700;
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
            referencia = new ImageIcon("imagens//NaveAzul.gif");
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
        referencia = new ImageIcon("imagens//naveAzulDano.gif");
        dadosImagem();
    }

    public void movimenta() {

        y += dy;
        x += dx;

    }

    public void tiroSimples() {
        this.tiros.add(new AtaquePlayer(x + 17, y - 40));
        referencia = new ImageIcon("imagens//NaveAzulTiro.gif");
        dadosImagem();
    }

    public void tiroEspecial() {
        this.tiros.add(new AtaquePlayer(this.x, this.y, tiroRef));
        referencia = new ImageIcon("imagens//NaveVermelhaAtkEspecial.gif");
        dadosImagem();
        this.qtdAtaquesEspeciais -= 1;

    }

    public void playSound() {
        if (clip != null) {
            clip.start();
        }
    }

    public void stopSound() {
        if (clip != null) {
            clip.stop();
        }
    }

    public Rectangle getLimites() {
        return new Rectangle(x, y, largura, altura);
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_BACK_SPACE) {
            if (isVisivel) {
                try {
                    File audioFile = new File("sons//somTiro.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }

                playSound();
                if (qtdAtaquesEspeciais == 0) {
                    tiroSimples();
                } else if (qtdAtaquesEspeciais != 0) {
                    tiroEspecial();
                }
            }

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
