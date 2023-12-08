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

public class Player1 implements ActionListener {
    private int x, y;
    private int dx, dy;
    private Image imagem;
    ImageIcon referencia = new ImageIcon("imagens//NaveVermelha.gif");
    private int altura, largura;
    private List<AtaquePlayer> tiros;
    private boolean isVisivel;
    private Timer timer;
    private int qtdAtaquesEspeciais = 0;
    private int limiteEscudo = 3;
    private boolean colisao = false;
    private boolean escudo = false;
    private ImageIcon tiroRef = new ImageIcon("imagens//atkespecialplayer.png");
    private Clip clip;

    public Player1() {
        this.x = 600;
        this.y = 700;
        isVisivel = true;

        tiros = new ArrayList<AtaquePlayer>();

        timer = new Timer(300, this);
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

            if (limiteEscudo == 0) {
                escudo = false;
            }

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
        if (escudo) {
            limiteEscudo -= 1;
        }
    }

    public void movimentaInicio() {
        if (this.y > -150 || this.x < 1450) {
            x += 1;
            y -= 3;
        } else {
            this.x = 1100;
            this.y = 750;
        }

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

    public void tiroEspecial() {
        this.tiros.add(new AtaquePlayer(this.x, this.y, tiroRef));
        referencia = new ImageIcon("imagens//NaveVermelhaAtkEspecial.gif");
        dadosImagem();
        this.qtdAtaquesEspeciais -= 1;

    }

    public Rectangle getLimites() {
        return new Rectangle(x, y, largura, altura);
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

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE) {
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

        if (codigo == KeyEvent.VK_W)

        {
            if (this.y < 0) {
                dy = 0;
            } else
                dy = -3;

        }

        if (codigo == KeyEvent.VK_S) {
            if (this.y > 690) {
                dy = 0;
            } else
                dy = 3;

        }

        if (codigo == KeyEvent.VK_A) {
            if (this.x < 10) {
                dx = 0;
            } else
                dx = -3;
        }

        if (codigo == KeyEvent.VK_D) {
            if (this.x > 1460) {
                dx = 0;
            } else
                dx = 3;
        }

    }

    public void keyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_W) {
            dy = 0;
        }

        if (codigo == KeyEvent.VK_S) {
            dy = 0;
        }

        if (codigo == KeyEvent.VK_A) {
            dx = 0;
        }

        if (codigo == KeyEvent.VK_D) {
            dx = 0;
        }

    }

    // Getters and Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public void setAtaqueEspecial(int n) {
        this.qtdAtaquesEspeciais += n;
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
