package modelagem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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
import javax.swing.JPanel;
import javax.swing.Timer;

public class ModoMultiplayer extends JPanel implements ActionListener {
    private List<Estrelas> Estrelas;
    private Image fundoFaseX;
    private Timer timer;
    private boolean emJogo;
    private int vidaPlayer1 = 6;
    private int vidaPlayer2 = 6;
    private Clip clip;

    private Player1 player1;
    private Player2 player2;
    private List<InimigoAzul> inimigoAzul;
    private List<InimigoVerde> inimigoVerde;
    private List<InimigoRosa> inimigoRosa;
    private List<InimigoLaranja> inimigoLaranja;
    private List<Meteoro> meteoros;
    private List<Asteroide> asteroides;
    private List<Bonus> bonus;
    private int abateInimigoAzul = 0;
    private int abateInimigoRosa = 0;
    private int abateInimigoVerde = 0;
    private int abateInimigoLaranja = 0;
    private int pontuacaoTotal = 0;
    private Coracao1 coracao1;
    private Coracao2 coracao2;

    public ModoMultiplayer() {
        setFocusable(true);
        setDoubleBuffered(true);

        inicializaElementos();
        adicionaSomFundo();

        coracao1 = new Coracao1(0, -50);
        coracao1.dadosImagem();

        coracao2 = new Coracao2(1390, -50);
        coracao2.dadosImagem();
    }

    public void inicializaElementos() {
        ImageIcon referencia = new ImageIcon("imagens//fundodTeste.png");
        fundoFaseX = referencia.getImage();
        emJogo = true;
        addKeyListener(new TecladoAdapter());
        timer = new Timer(10, this);
        timer.start();

        player1 = new Player1();
        player1.dadosImagem();

        player2 = new Player2();
        player2.dadosImagem();

        inicializaBonus();
        inicializaEstrelas();
        inicializaInimigosVerde();
        inicializaInimigosAzuis();
        inicializaInimigosRosa();
        inicializaInimigosLaranja();
        inicializaMeteoros();
        inicializaAsteroides();
    }

    public void adicionaSomFundo() {
        try {
            File audioFile = new File("sons//somfundo.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        playSound();

    }

    public void playSound() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopSound() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void inicializaBonus() {
        int quantidade[] = new int[5];
        bonus = new ArrayList<Bonus>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1400);
            int y = (int) (Math.random() * -3500);
            bonus.add(new Bonus(x, y, 1));
            int x2 = (int) (Math.random() * -1500 + 1400);
            int y2 = (int) (Math.random() * -3500);
            bonus.add(new Bonus(x2, y2, 2));
            int x3 = (int) (Math.random() * -1500 + 1400);
            int y3 = (int) (Math.random() * -3500);
            bonus.add(new Bonus(x3, y3, 3));

        }

    }

    public void inicializaInimigosAzuis() {
        int quantidade[] = new int[5];
        inimigoAzul = new ArrayList<InimigoAzul>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1400);
            int y = (int) (Math.random() * -3500);
            inimigoAzul.add(new InimigoAzul(x, y));
        }

    }

    public void inicializaInimigosLaranja() {
        int quantidade[] = new int[5];
        inimigoLaranja = new ArrayList<InimigoLaranja>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1400);
            int y = (int) (Math.random() * -3500);
            inimigoLaranja.add(new InimigoLaranja(x, y));
        }
    }

    public void inicializaInimigosVerde() {
        int quantidade[] = new int[5];
        inimigoVerde = new ArrayList<InimigoVerde>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1400);
            int y = (int) (Math.random() * -3500);
            inimigoVerde.add(new InimigoVerde(x, y));
        }
    }

    public void inicializaInimigosRosa() {
        int quantidade[] = new int[5];
        inimigoRosa = new ArrayList<InimigoRosa>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1400);
            int y = (int) (Math.random() * -3500);
            inimigoRosa.add(new InimigoRosa(x, y));
        }
    }

    public void inicializaMeteoros() {
        int quantidade[] = new int[1];
        meteoros = new ArrayList<Meteoro>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * 900 + 1024);
            int y = (int) (Math.random() * -3500);
            meteoros.add(new Meteoro(x, y));
        }
    }

    public void inicializaAsteroides() {
        int quantidade[] = new int[1];
        asteroides = new ArrayList<Asteroide>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1000);
            int y = (int) (Math.random() * -3500);
            asteroides.add(new Asteroide(x, y));
        }
    }

    public void inicializaEstrelas() {
        Estrelas = new ArrayList<Estrelas>();

        for (int i = 0; i < 15; i++) {
            int x = (int) (Math.random() * -8000);
            int y = (int) (Math.random() * -500);
            Estrelas.add(new Estrelas(x, y, 1));
            int x2 = (int) (Math.random() * -8000);
            int y2 = (int) (Math.random() * -500);
            Estrelas.add(new Estrelas(x2, y2, 2));
            int x3 = (int) (Math.random() * -8000);
            int y3 = (int) (Math.random() * -500);
            Estrelas.add(new Estrelas(x3, y3, 3));
            int x4 = (int) (Math.random() * -8000);
            int y4 = (int) (Math.random() * -500);
            Estrelas.add(new Estrelas(x4, y4, 4));
        }
    }

    public int calculaPontuacao() {
        pontuacaoTotal = ((abateInimigoRosa * 200) + (abateInimigoAzul * 100) + (abateInimigoLaranja * 100)
                + (abateInimigoVerde * 300));
        return pontuacaoTotal;
    }

    public void checarColisoes() {
        Rectangle formaNave1 = player1.getLimites();
        Rectangle formaNave2 = player2.getLimites();
        Rectangle formainimigoAzul;
        Rectangle formaTiro;
        Rectangle formaAtaqueInimigoRosa;
        Rectangle formaAtaqueInimigoVerde;
        Rectangle formaAtaqueInimigoLaranja;
        Rectangle formaInimigoRosa;
        Rectangle formaMeteoro;
        Rectangle formaAsteroides;
        Rectangle formaInimigoVerde;
        Rectangle formaInimigoLaranja;
        Rectangle formaEscudo;

        // COLISÕES NAVE x ELEMENTOS
        if (inimigoAzul != null) {
            for (int i = 0; i < inimigoAzul.size(); i++) {
                InimigoAzul tempinimigoAzul = inimigoAzul.get(i);
                formainimigoAzul = tempinimigoAzul.getLimites();
                if (formaNave1.intersects(formainimigoAzul)) {
                    tempinimigoAzul.setVisible(false);
                    if (player1.getEscudo() == false) {
                        vidaPlayer1 -= 1;
                    }

                    player1.setColisao(true);
                }

            }
        }

        if (inimigoAzul != null) {
            for (int i = 0; i < inimigoAzul.size(); i++) {
                InimigoAzul tempinimigoAzul = inimigoAzul.get(i);
                formainimigoAzul = tempinimigoAzul.getLimites();
                if (formaNave2.intersects(formainimigoAzul)) {
                    tempinimigoAzul.setVisible(false);
                    if (player2.getEscudo() == false) {
                        vidaPlayer2 -= 1;
                    }

                    player2.setColisao(true);
                }

            }
        }

        if (inimigoRosa != null) {
            for (int i = 0; i < inimigoRosa.size(); i++) {
                InimigoRosa tempinimigoRosa = inimigoRosa.get(i);
                formaInimigoRosa = tempinimigoRosa.getLimites();
                if (formaNave1.intersects(formaInimigoRosa)) {
                    tempinimigoRosa.setVisible(false);
                    if (player1.getEscudo() == false) {
                        vidaPlayer1 -= 1;
                    }
                    player1.setColisao(true);
                }
            }
        }

        if (inimigoRosa != null) {
            for (int i = 0; i < inimigoRosa.size(); i++) {
                InimigoRosa tempinimigoRosa = inimigoRosa.get(i);
                formaInimigoRosa = tempinimigoRosa.getLimites();
                if (formaNave2.intersects(formaInimigoRosa)) {

                    tempinimigoRosa.setVisible(false);
                    if (player2.getEscudo() == false) {
                        vidaPlayer2 -= 1;
                    }
                    player2.setColisao(true);
                }
            }
        }

        if (inimigoLaranja != null) {
            for (int i = 0; i < inimigoLaranja.size(); i++) {
                InimigoLaranja tempinimigoLaranja = inimigoLaranja.get(i);
                formaInimigoLaranja = tempinimigoLaranja.getLimites();
                if (formaNave1.intersects(formaInimigoLaranja)) {

                    tempinimigoLaranja.setVisible(false);
                    if (player1.getEscudo() == false) {
                        vidaPlayer1 -= 1;
                    }
                    player1.setColisao(true);
                }
            }
        }

        if (inimigoLaranja != null) {
            for (int i = 0; i < inimigoLaranja.size(); i++) {
                InimigoLaranja tempinimigoLaranja = inimigoLaranja.get(i);
                formaInimigoLaranja = tempinimigoLaranja.getLimites();
                if (formaNave2.intersects(formaInimigoLaranja)) {

                    tempinimigoLaranja.setVisible(false);
                    if (player2.getEscudo() == false) {
                        vidaPlayer2 -= 1;
                    }
                    player2.setColisao(true);
                }
            }
        }

        if (inimigoVerde != null) {
            for (int i = 0; i < inimigoVerde.size(); i++) {
                InimigoVerde tempinimigoVerde = inimigoVerde.get(i);
                formaInimigoVerde = tempinimigoVerde.getLimites();
                if (formaNave1.intersects(formaInimigoVerde)) {

                    tempinimigoVerde.setVisible(false);
                    if (player1.getEscudo() == false) {
                        vidaPlayer1 -= 1;
                    }
                    player1.setColisao(true);
                }
            }
        }

        if (inimigoVerde != null) {
            for (int i = 0; i < inimigoVerde.size(); i++) {
                InimigoVerde tempinimigoVerde = inimigoVerde.get(i);
                formaInimigoVerde = tempinimigoVerde.getLimites();
                if (formaNave2.intersects(formaInimigoVerde)) {

                    tempinimigoVerde.setVisible(false);
                    if (player2.getEscudo() == false) {
                        vidaPlayer2 -= 1;
                    }
                    player2.setColisao(true);
                }
            }
        }

        if (meteoros != null) {
            for (int i = 0; i < meteoros.size(); i++) {
                Meteoro tempMeteoro = meteoros.get(i);
                formaMeteoro = tempMeteoro.getLimites();
                if (formaNave1.intersects(formaMeteoro)) {

                    tempMeteoro.setVisible(false);
                    if (player1.getEscudo() == false) {
                        vidaPlayer1 -= 2;
                    }
                    player1.setColisao(true);
                }
            }
        }

        if (meteoros != null) {
            for (int i = 0; i < meteoros.size(); i++) {
                Meteoro tempMeteoro = meteoros.get(i);
                formaMeteoro = tempMeteoro.getLimites();
                if (formaNave2.intersects(formaMeteoro)) {

                    tempMeteoro.setVisible(false);
                    if (player2.getEscudo() == false) {
                        vidaPlayer2 -= 2;
                    }
                    player2.setColisao(true);
                }
            }
        }

        if (asteroides != null) {
            for (int i = 0; i < asteroides.size(); i++) {
                Asteroide tempAsteroide = asteroides.get(i);
                formaAsteroides = tempAsteroide.getLimites();
                if (formaNave1.intersects(formaAsteroides)) {

                    tempAsteroide.setVisible(false);
                    if (player1.getEscudo() == false) {
                        vidaPlayer1 -= 2;
                    }
                    player1.setColisao(true);
                }
            }
        }

        if (asteroides != null) {
            for (int i = 0; i < asteroides.size(); i++) {
                Asteroide tempAsteroide = asteroides.get(i);
                formaAsteroides = tempAsteroide.getLimites();
                if (formaNave2.intersects(formaAsteroides)) {

                    tempAsteroide.setVisible(false);
                    if (player2.getEscudo() == false) {
                        vidaPlayer2 -= 2;
                    }
                    player2.setColisao(true);
                }
            }
        }

        if (bonus != null) {
            for (int i = 0; i < bonus.size(); i++) {
                Bonus tempEscudo = bonus.get(i);
                formaEscudo = tempEscudo.getLimites();
                if (formaNave1.intersects(formaEscudo)) {
                    tempEscudo.setVisible(false);
                    player1.setEscudo(true);
                }
            }
        }

        if (bonus != null) {
            for (int i = 0; i < bonus.size(); i++) {
                Bonus tempEscudo = bonus.get(i);
                formaEscudo = tempEscudo.getLimites();
                if (formaNave2.intersects(formaEscudo)) {
                    tempEscudo.setVisible(false);
                    player2.setEscudo(true);
                }
            }
        }

        // COLISÃO ATAQUE INIMIGO x NAVE
        if (inimigoRosa != null) {
            for (int i = 0; i < inimigoRosa.size(); i++) {
                List<AtaqueInimigo> ataques = inimigoRosa.get(i).getAtaques();

                for (int x = 0; x < ataques.size(); x++) {
                    AtaqueInimigo tempataquerosa = ataques.get(x);
                    formaAtaqueInimigoRosa = tempataquerosa.getLimites();
                    if (formaAtaqueInimigoRosa.intersects(formaNave1)) {
                        tempataquerosa.setVisible(false);
                        if (player1.getEscudo() == false) {
                            vidaPlayer1 -= 1;
                        }
                        player1.setColisao(true);

                    }

                }
            }
        }

        if (inimigoRosa != null) {
            for (int i = 0; i < inimigoRosa.size(); i++) {
                List<AtaqueInimigo> ataques = inimigoRosa.get(i).getAtaques();

                for (int x = 0; x < ataques.size(); x++) {
                    AtaqueInimigo tempataquerosa = ataques.get(x);
                    formaAtaqueInimigoRosa = tempataquerosa.getLimites();
                    if (formaAtaqueInimigoRosa.intersects(formaNave2)) {
                        tempataquerosa.setVisible(false);
                        if (player2.getEscudo() == false) {
                            vidaPlayer2 -= 1;
                        }
                        player2.setColisao(true);

                    }

                }
            }
        }

        if (inimigoLaranja != null) {
            for (int i = 0; i < inimigoLaranja.size(); i++) {
                List<AtaqueInimigo> ataquesD = inimigoLaranja.get(i).getAtaquesD();
                List<AtaqueInimigo> ataquesE = inimigoLaranja.get(i).getAtaquesE();

                for (int x = 0; x < ataquesD.size(); x++) {
                    AtaqueInimigo tempataqueLaranja = ataquesD.get(x);
                    formaAtaqueInimigoLaranja = tempataqueLaranja.getLimites();
                    if (formaAtaqueInimigoLaranja.intersects(formaNave1)) {
                        tempataqueLaranja.setVisible(false);
                        if (player1.getEscudo() == false) {
                            vidaPlayer1 -= 1;
                        }
                        player1.setColisao(true);

                    }

                }

                for (int x = 0; x < ataquesE.size(); x++) {
                    AtaqueInimigo tempataqueLaranja = ataquesE.get(x);
                    formaAtaqueInimigoLaranja = tempataqueLaranja.getLimites();
                    if (formaAtaqueInimigoLaranja.intersects(formaNave1)) {
                        tempataqueLaranja.setVisible(false);
                        if (player1.getEscudo() == false) {
                            vidaPlayer1 -= 1;
                        }
                        player1.setColisao(true);

                    }

                }
            }
        }

        if (inimigoLaranja != null) {
            for (int i = 0; i < inimigoLaranja.size(); i++) {
                List<AtaqueInimigo> ataquesD = inimigoLaranja.get(i).getAtaquesD();
                List<AtaqueInimigo> ataquesE = inimigoLaranja.get(i).getAtaquesE();

                for (int x = 0; x < ataquesD.size(); x++) {
                    AtaqueInimigo tempataqueLaranja = ataquesD.get(x);
                    formaAtaqueInimigoLaranja = tempataqueLaranja.getLimites();
                    if (formaAtaqueInimigoLaranja.intersects(formaNave2)) {
                        tempataqueLaranja.setVisible(false);
                        if (player2.getEscudo() == false) {
                            vidaPlayer2 -= 1;
                        }
                        player2.setColisao(true);

                    }

                }

                for (int x = 0; x < ataquesE.size(); x++) {
                    AtaqueInimigo tempataqueLaranja = ataquesE.get(x);
                    formaAtaqueInimigoLaranja = tempataqueLaranja.getLimites();
                    if (formaAtaqueInimigoLaranja.intersects(formaNave2)) {
                        tempataqueLaranja.setVisible(false);
                        if (player2.getEscudo() == false) {
                            vidaPlayer2 -= 1;
                        }
                        player2.setColisao(true);

                    }

                }
            }
        }

        if (inimigoVerde != null) {
            for (int i = 0; i < inimigoVerde.size(); i++) {
                List<AtaqueInimigo> ataques = inimigoVerde.get(i).getAtaques();

                for (int x = 0; x < ataques.size(); x++) {
                    AtaqueInimigo tempataqueverde = ataques.get(x);
                    formaAtaqueInimigoVerde = tempataqueverde.getLimites();
                    if (formaAtaqueInimigoVerde.intersects(formaNave1)) {
                        tempataqueverde.setVisible(false);
                        if (player1.getEscudo() == false) {
                            vidaPlayer1 -= 1;
                        }
                        player1.setColisao(true);

                    }

                }
            }
        }

        if (inimigoVerde != null) {
            for (int i = 0; i < inimigoVerde.size(); i++) {
                List<AtaqueInimigo> ataques = inimigoVerde.get(i).getAtaques();

                for (int x = 0; x < ataques.size(); x++) {
                    AtaqueInimigo tempataqueverde = ataques.get(x);
                    formaAtaqueInimigoVerde = tempataqueverde.getLimites();
                    if (formaAtaqueInimigoVerde.intersects(formaNave2)) {
                        tempataqueverde.setVisible(false);
                        if (player2.getEscudo() == false) {
                            vidaPlayer2 -= 1;
                        }
                        player2.setColisao(true);

                    }

                }
            }
        }

        // COLISÃO ATAQUE PLAYER x INIMIGOS
        List<AtaquePlayer> ataques1 = player1.getTiros();

        if (ataques1 != null) {
            for (int j = 0; j < ataques1.size(); j++) {
                AtaquePlayer tempTiro = ataques1.get(j);
                formaTiro = tempTiro.getLimites();

                for (int i = 0; i < inimigoAzul.size(); i++) {
                    InimigoAzul tempinimigoAzul = inimigoAzul.get(i);
                    formainimigoAzul = tempinimigoAzul.getLimites();
                    if (formaTiro.intersects(formainimigoAzul)) {
                        tempTiro.setVisible(false);
                        tempinimigoAzul.setColisao(true);
                        abateInimigoAzul += 1;

                    }
                }

                for (int i = 0; i < inimigoRosa.size(); i++) {
                    InimigoRosa tempinimigoRosa = inimigoRosa.get(i);
                    formaInimigoRosa = tempinimigoRosa.getLimites();
                    if (formaTiro.intersects(formaInimigoRosa)) {
                        tempinimigoRosa.setVisible(false);
                        tempTiro.setVisible(false);
                        abateInimigoRosa += 1;
                    }
                }

                for (int i = 0; i < inimigoVerde.size(); i++) {
                    InimigoVerde tempInimigoVerde = inimigoVerde.get(i);
                    formaInimigoVerde = tempInimigoVerde.getLimites();
                    if (formaTiro.intersects(formaInimigoVerde)) {
                        tempTiro.setVisible(false);
                        tempInimigoVerde.setVida(1);
                        tempTiro.setVisible(false);
                        if (tempInimigoVerde.getVida() <= 0) {
                            tempInimigoVerde.setVisible(false);
                            abateInimigoVerde += 1;
                        }
                    }
                }

                for (int i = 0; i < inimigoLaranja.size(); i++) {
                    InimigoLaranja tempInimigoLaranja = inimigoLaranja.get(i);
                    formaInimigoLaranja = tempInimigoLaranja.getLimites();
                    if (formaTiro.intersects(formaInimigoLaranja)) {
                        tempTiro.setVisible(false);
                        tempInimigoLaranja.setVisible(false);
                        abateInimigoLaranja += 1;
                    }
                }

                for (int i = 0; i < meteoros.size(); i++) {
                    Meteoro tempMeteoro = meteoros.get(i);
                    formaMeteoro = tempMeteoro.getLimites();
                    if (formaTiro.intersects(formaMeteoro)) {
                        tempTiro.setVisible(false);
                    }
                }

                for (int i = 0; i < asteroides.size(); i++) {
                    Asteroide tempAsteroide = asteroides.get(i);
                    formaAsteroides = tempAsteroide.getLimites();
                    if (formaTiro.intersects(formaAsteroides)) {
                        tempTiro.setVisible(false);
                    }
                }

            }
        }

        List<AtaquePlayer> ataques2 = player2.getTiros();

        if (ataques2 != null) {
            for (int j = 0; j < ataques2.size(); j++) {
                AtaquePlayer tempTiro = ataques2.get(j);
                formaTiro = tempTiro.getLimites();

                for (int i = 0; i < inimigoAzul.size(); i++) {
                    InimigoAzul tempinimigoAzul = inimigoAzul.get(i);
                    formainimigoAzul = tempinimigoAzul.getLimites();
                    if (formaTiro.intersects(formainimigoAzul)) {
                        tempTiro.setVisible(false);
                        tempinimigoAzul.setColisao(true);
                        abateInimigoAzul += 1;

                    }
                }

                for (int i = 0; i < inimigoRosa.size(); i++) {
                    InimigoRosa tempinimigoRosa = inimigoRosa.get(i);
                    formaInimigoRosa = tempinimigoRosa.getLimites();
                    if (formaTiro.intersects(formaInimigoRosa)) {
                        tempinimigoRosa.setVisible(false);
                        tempTiro.setVisible(false);
                        abateInimigoRosa += 1;
                    }
                }

                for (int i = 0; i < inimigoVerde.size(); i++) {
                    InimigoVerde tempInimigoVerde = inimigoVerde.get(i);
                    formaInimigoVerde = tempInimigoVerde.getLimites();
                    if (formaTiro.intersects(formaInimigoVerde)) {
                        tempTiro.setVisible(false);
                        tempInimigoVerde.setVida(1);
                        tempTiro.setVisible(false);
                        if (tempInimigoVerde.getVida() <= 0) {
                            tempInimigoVerde.setVisible(false);
                            abateInimigoVerde += 1;
                        }
                    }
                }

                for (int i = 0; i < inimigoLaranja.size(); i++) {
                    InimigoLaranja tempInimigoLaranja = inimigoLaranja.get(i);
                    formaInimigoLaranja = tempInimigoLaranja.getLimites();
                    if (formaTiro.intersects(formaInimigoLaranja)) {
                        tempTiro.setVisible(false);
                        tempInimigoLaranja.setVisible(false);
                        abateInimigoLaranja += 1;
                    }
                }

                for (int i = 0; i < meteoros.size(); i++) {
                    Meteoro tempMeteoro = meteoros.get(i);
                    formaMeteoro = tempMeteoro.getLimites();
                    if (formaTiro.intersects(formaMeteoro)) {
                        tempTiro.setVisible(false);
                    }
                }

                for (int i = 0; i < asteroides.size(); i++) {
                    Asteroide tempAsteroide = asteroides.get(i);
                    formaAsteroides = tempAsteroide.getLimites();
                    if (formaTiro.intersects(formaAsteroides)) {
                        tempTiro.setVisible(false);
                    }
                }

            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        calculaPontuacao();
        player1.movimenta();
        player2.movimenta();
        List<AtaquePlayer> tiros1 = player1.getTiros();
        List<AtaquePlayer> tiros2 = player2.getTiros();

        if (tiros1 != null) {
            for (int i = 0; i < tiros1.size(); i++) {
                AtaquePlayer m = tiros1.get(i);
                if (m.isVisible()) {
                    m.movimenta();

                } else {
                    tiros1.remove(i);
                }
            }
        }

        if (tiros2 != null) {
            for (int i = 0; i < tiros2.size(); i++) {
                AtaquePlayer m = tiros2.get(i);
                if (m.isVisible()) {
                    m.movimenta();

                } else {
                    tiros2.remove(i);
                }
            }
        }

        if (inimigoRosa != null) {
            for (int i = 0; i < inimigoRosa.size(); i++) {
                List<AtaqueInimigo> ataques = inimigoRosa.get(i).getAtaques();

                for (int j = 0; j < ataques.size(); j++) {
                    AtaqueInimigo m = ataques.get(j);
                    if (m.isVisible()) {
                        m.movimenta("rosa");

                    } else {
                        ataques.remove(j);
                    }
                }
            }
        }

        if (inimigoVerde != null) {
            for (int i = 0; i < inimigoVerde.size(); i++) {
                List<AtaqueInimigo> ataques = inimigoVerde.get(i).getAtaques();

                for (int j = 0; j < ataques.size(); j++) {
                    AtaqueInimigo m = ataques.get(j);
                    if (m.isVisible()) {
                        m.movimenta("verde");

                    } else {
                        ataques.remove(j);
                    }
                }
            }
        }

        if (inimigoLaranja != null) {
            for (int i = 0; i < inimigoLaranja.size(); i++) {
                List<AtaqueInimigo> ataquesD = inimigoLaranja.get(i).getAtaquesD();
                List<AtaqueInimigo> ataquesE = inimigoLaranja.get(i).getAtaquesE();

                for (int j = 0; j < ataquesD.size(); j++) {
                    AtaqueInimigo m = ataquesD.get(j);
                    if (m.isVisible()) {
                        m.movimenta("laranjaD");

                    } else {
                        ataquesD.remove(j);
                    }
                }
                for (int j = 0; j < ataquesE.size(); j++) {
                    AtaqueInimigo m = ataquesE.get(j);
                    if (m.isVisible()) {
                        m.movimenta("laranjaE");

                    } else {
                        ataquesE.remove(j);
                    }
                }
            }
        }

        if (inimigoAzul != null) {
            for (int i = 0; i < inimigoAzul.size(); i++) {
                InimigoAzul in = inimigoAzul.get(i);
                if (in.isVisible()) {
                    in.movimenta();
                } else {
                    inimigoAzul.remove(i);
                }
            }

        }

        if (inimigoLaranja != null) {
            for (int i = 0; i < inimigoLaranja.size(); i++) {
                InimigoLaranja in = inimigoLaranja.get(i);
                if (in.isVisible()) {
                    in.movimenta();
                } else {
                    inimigoLaranja.remove(i);
                }
            }
        }

        if (inimigoRosa != null) {
            for (int i = 0; i < inimigoRosa.size(); i++) {
                InimigoRosa in = inimigoRosa.get(i);
                if (in.isVisible()) {
                    in.movimenta();
                } else {
                    inimigoRosa.remove(i);
                }
            }
        }

        if (inimigoVerde != null) {
            for (int i = 0; i < inimigoVerde.size(); i++) {
                InimigoVerde in = inimigoVerde.get(i);
                if (in.isVisible()) {
                    in.movimenta();
                } else {
                    inimigoVerde.remove(i);
                }
            }
        }

        if (meteoros != null) {
            for (int i = 0; i < meteoros.size(); i++) {
                Meteoro in = meteoros.get(i);
                if (in.isVisible()) {
                    in.movimenta();
                } else {
                    meteoros.remove(i);
                }
            }
        }

        if (asteroides != null) {
            for (int i = 0; i < asteroides.size(); i++) {
                Asteroide in = asteroides.get(i);
                if (in.isVisible()) {
                    in.movimenta();
                } else {
                    asteroides.remove(i);
                }
            }
        }

        if (Estrelas != null) {
            for (int p = 0; p < Estrelas.size(); p++) {
                Estrelas on = Estrelas.get(p);
                if (on.isVisible()) {
                    on.movimenta();
                } else
                    Estrelas.remove(p);
            }
        }

        if (bonus != null) {
            for (int i = 0; i < bonus.size(); i++) {
                Bonus in = bonus.get(i);
                if (in.isVisible()) {
                    in.movimenta();
                } else {
                    bonus.remove(i);
                }
            }
        }

        if (vidaPlayer1 <= 0) {
            player1.setVisivel(false);
        }

        if (vidaPlayer2 <= 0) {
            player2.setVisivel(false);
        }

        if (vidaPlayer1 <= 0 && vidaPlayer2 <= 0) {
            emJogo = false;
            System.out.println(calculaPontuacao());
        }

        repaint();
        checarColisoes();

    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        if (emJogo) {

            List<AtaquePlayer> tiros1 = player1.getTiros();
            List<AtaquePlayer> tiros2 = player2.getTiros();

            graficos.drawImage(fundoFaseX, 0, 0, null);

            for (int p = 0; p < Estrelas.size(); p++) {
                Estrelas q = Estrelas.get(p);
                q.getImagem();
                graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
            }

            if (player1.isVisivel()) {
                graficos.drawImage(player1.getImagem(), player1.getX(), player1.getY(), this);
                for (int i = 0; i < tiros1.size(); i++) {
                    AtaquePlayer m = tiros1.get(i);
                    m.dadosImagem();

                    graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
                }
            }

            if (player2.isVisivel()) {
                graficos.drawImage(player2.getImagem(), player2.getX(), player2.getY(), this);

                for (int i = 0; i < tiros2.size(); i++) {
                    AtaquePlayer m = tiros2.get(i);
                    m.dadosImagem();

                    graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
                }
            }

            graficos.drawImage(coracao1.getImagem(vidaPlayer1), coracao1.getX(), coracao1.getY(), this);
            graficos.drawImage(coracao2.getImagem(vidaPlayer2), coracao2.getX(), coracao2.getY(), this);

            for (int x = 0; x < inimigoRosa.size(); x++) {
                List<AtaqueInimigo> ataques = inimigoRosa.get(x).getAtaques();

                for (int i = 0; i < ataques.size(); i++) {
                    AtaqueInimigo m = ataques.get(i);
                    m.getImagem();
                    graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

                }
            }

            for (int x = 0; x < inimigoLaranja.size(); x++) {
                List<AtaqueInimigo> ataques = inimigoLaranja.get(x).getAtaquesD();

                for (int i = 0; i < ataques.size(); i++) {
                    AtaqueInimigo m = ataques.get(i);
                    m.getImagem();
                    graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

                }
            }

            for (int x = 0; x < inimigoLaranja.size(); x++) {
                List<AtaqueInimigo> ataques = inimigoLaranja.get(x).getAtaquesE();

                for (int i = 0; i < ataques.size(); i++) {
                    AtaqueInimigo m = ataques.get(i);
                    m.getImagem();
                    graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

                }
            }

            for (int x = 0; x < inimigoVerde.size(); x++) {
                List<AtaqueInimigo> ataques = inimigoVerde.get(x).getAtaques();

                for (int i = 0; i < ataques.size(); i++) {
                    AtaqueInimigo m = ataques.get(i);
                    m.getImagem();
                    graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

                }
            }

            for (int i = 0; i < inimigoAzul.size(); i++) {
                InimigoAzul in = inimigoAzul.get(i);
                in.getImagem();
                ;
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }

            for (int i = 0; i < bonus.size(); i++) {
                Bonus in = bonus.get(i);
                in.getImagem();
                ;
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }

            for (int i = 0; i < inimigoRosa.size(); i++) {
                InimigoRosa b = inimigoRosa.get(i);
                b.getImagem();
                b.movimenta();
                ;
                graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
            }

            for (int i = 0; i < inimigoVerde.size(); i++) {
                InimigoVerde b = inimigoVerde.get(i);
                b.getImagem();
                b.movimenta();
                ;
                graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
            }

            for (int i = 0; i < inimigoLaranja.size(); i++) {
                InimigoLaranja b = inimigoLaranja.get(i);
                b.getImagem();
                b.movimenta();
                ;
                graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
            }

            for (int i = 0; i < meteoros.size(); i++) {
                Meteoro b = meteoros.get(i);
                b.getImagem();
                b.movimenta();
                ;
                graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
            }

            for (int i = 0; i < asteroides.size(); i++) {
                Asteroide b = asteroides.get(i);
                b.dadosImagem();
                b.movimenta();
                ;
                graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
            }

        } else {
            ImageIcon fimJogo = new ImageIcon("imagens//gameover.gif");
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
            player2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
            player2.keyReleased(e);
        }
    }

    public int getPontos() {
        return calculaPontuacao();
    }
}
