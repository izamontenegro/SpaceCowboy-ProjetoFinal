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
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener {
    private int x, y;
    private int dx, dy;
    private Image imagem;
    ImageIcon referencia = new ImageIcon("imagens\\NaveVermelha.gif");
    private int altura, largura;
    private List<AtaquePlayer> tiros;
    private boolean isVisivel;
    private Timer timer;
    private boolean colisao = false;
    private boolean escudo = false;
    private ImageIcon tiroRef = new ImageIcon("imagens//atkespecialplayer.png");
    private int centralizado = 1;
    private boolean comecaJogo = false;
    private boolean coletaBonus = false;
    private int qtdAtaquesEspeciais=0;
    private Clip clip;

    public Player() {
        this.x = 700;
        this.y = 650;
        isVisivel = true;

        tiros = new ArrayList<AtaquePlayer>();

        timer = new Timer(500, this);
        timer.start();

    }

    public void coletaBonus() {
        this.coletaBonus = true;
    }

    public void centraliza() {
        if (this.centralizado != 1) {

            if ((x < 650 || x > 670) || (y < 500 || y > 520)) {
                if (this.y < 500) {
                    this.y += 5;
                } else {
                    this.y -= 5;
                }
                if (this.x < 650) {
                    this.x += 5;
                } else {
                    this.x -= 5;
                } 	

            } else {
                this.centralizado = 1;
                this.comecaJogo = true;
            }
        }
    }

    public boolean getComecaJogo() {
        return this.comecaJogo;
    }

    public void setCentralizado(int x) {
        this.centralizado = x;
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
    	 ImageIcon referencia = new ImageIcon("imagens//NaveVermelha.gif");
         imagem = referencia.getImage();
         this.largura = imagem.getWidth(null);
         this.altura = imagem.getHeight(null);
    }

    public void sofrerDano() {
        referencia = new ImageIcon("imagens//naveVermelhaDano.gif");
        dadosImagem();
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

        if (codigo == KeyEvent.VK_SPACE) {
          
        	   
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
        		   qtdAtaquesEspeciais--;
        	   }
        	   
           

        }

        if (codigo == KeyEvent.VK_W) {

            dy = -6;

        }

        if (codigo == KeyEvent.VK_S) {

            dy = 6;

        }

        if (codigo == KeyEvent.VK_A) {

            dx = -6;
        }

        if (codigo == KeyEvent.VK_D) {

            dx = 6;
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

    public void setAtaqueEspecial(int n) {
        this.qtdAtaquesEspeciais += n;
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

    public boolean getColisao() {
        return this.colisao;
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
