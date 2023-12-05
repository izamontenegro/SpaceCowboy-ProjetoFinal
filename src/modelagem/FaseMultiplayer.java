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


public class FaseMultiplayer extends JPanel implements ActionListener {
    private List<Estrelas> EstrelaBranca;
    private List<Estrelas> EstrelaRosa;
    private List<Estrelas> EstrelaAmarela;
    private List<Estrelas> EstrelaAzul;
    private Image fundoFaseX;
    private Timer timer;
    private boolean emJogo;
    private int vidaPlayer = 6;
    private Clip clip;

    private Player2 player2;
    private Player1 player1;
    private List<InimigoAzul> inimigoAzul;
    private List<InimigoVerde> inimigoVerde;
    private List<InimigoRosa> inimigoRosa;
    private List<InimigoLaranja> inimigoLaranja;
    private List<Meteoro> meteoros;
    private List<Asteroide> asteroides;
    private List<Escudo> bonus;

    private int abateInimigoAzul = 0;
    private int abateInimigoRosa = 0;
    private int abateInimigoVerde = 0;
    private int abateInimigoLaranja = 0;
    private int pontuacaoTotal = 0;

    public FaseMultiplayer () {
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon referencia = new ImageIcon("imagens//fundoJogo.png");
        fundoFaseX = referencia.getImage();
        emJogo = true;
        addKeyListener(new TecladoAdapter());
        timer = new Timer(10, this);
        timer.start();

        player2 = new Player2();
        player2.dadosImagem();;

        player1 = new Player1();
        player1.dadosImagem();

        inicializaBonus();
        inicializaEstrelas();
        inicializaInimigosVerde();
        inicializaInimigosAzuis();
        inicializaInimigosRosa();
        inicializaInimigosLaranja();
        inicializaMeteoros();
        inicializaAsteroides();

        try {
            File audioFile = new File("sons//trilhaSonora.wav");
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
        int quantidade[] = new int[10];
        bonus = new ArrayList<Escudo>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1400);
            int y = (int) (Math.random() * -3500);
            bonus.add(new Escudo(x, y));
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
        int quantidade[] = new int[5];
        meteoros = new ArrayList<Meteoro>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * 900 + 1024);
            int y = (int) (Math.random() * -3500);
            meteoros.add(new Meteoro(x, y));
        }
    }

    public void inicializaAsteroides() {
        int quantidade[] = new int[8];
        asteroides = new ArrayList<Asteroide>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1000);
            int y = (int) (Math.random() * -3500);
            asteroides.add(new Asteroide(x, y));
        }
    }

    public void inicializaEstrelas() {
        int quantidade[] = new int[20];
        EstrelaBranca = new ArrayList<Estrelas>();
        EstrelaRosa = new ArrayList<Estrelas>();
        EstrelaAmarela = new ArrayList<Estrelas>();
        EstrelaAzul = new ArrayList<Estrelas>();

        for (int i = 0; i < (quantidade.length + 10); i++) {
            int x = (int) (Math.random() * -8000);
            int y = (int) (Math.random() * -500);
            EstrelaBranca.add(new Estrelas(x, y, 1));
        }

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -8000);
            int y = (int) (Math.random() * -4500);
            EstrelaAzul.add(new Estrelas(x, y, 3));
        }

        for (int j = 0; j < (quantidade.length - 10); j++) {
            int x = (int) (Math.random() * -8000);
            int y = (int) (Math.random() * -4500);
            EstrelaRosa.add(new Estrelas(x, y, 2));
        }

        for (int j = 0; j < quantidade.length; j++) {
            int x = (int) (Math.random() * -8000);
            int y = (int) (Math.random() * -4500);
            EstrelaAmarela.add(new Estrelas(x, y, 4));
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
        Rectangle formaInimigoRosa;
        Rectangle formaMeteoro;
        Rectangle formaAsteroides;
        Rectangle formaInimigoVerde;
        Rectangle formaInimigoLaranja;
        Rectangle formaEscudo;

        // COLISÕES NAVE x INIMIGOS

        for (int i = 0; i < inimigoAzul.size(); i++) {
            InimigoAzul tempinimigoAzul = inimigoAzul.get(i);
            formainimigoAzul = tempinimigoAzul.getLimites();
            if (formaNave1.intersects(formainimigoAzul)) {
                player1.setVisivel(false);
                tempinimigoAzul.setVisible(false);
                vidaPlayer -= 1;
                player1.setColisao(true);
                if (vidaPlayer <= 0) {
                    emJogo = false;
                    System.out.println(calculaPontuacao());
                }
            }

        }

        for (int i = 0; i < inimigoAzul.size(); i++) {
            InimigoAzul tempinimigoAzul = inimigoAzul.get(i);
            formainimigoAzul = tempinimigoAzul.getLimites();
            if (formaNave2.intersects(formainimigoAzul)) {
                player2.setVisivel(false);
                tempinimigoAzul.setVisible(false);
                vidaPlayer -= 1;
                player2.setColisao(true);
                if (vidaPlayer <= 0) {
                    emJogo = false;
                    System.out.println(calculaPontuacao());
                }
            }

        }

        for (int i = 0; i < bonus.size(); i++) {
            Escudo tempEscudo = bonus.get(i);
            formaEscudo = tempEscudo.getLimites();
            if (formaNave1.intersects(formaEscudo)) {
                tempEscudo.setVisible(false);
                player1.setEscudo(true);
            }
        }

        for (int i = 0; i < bonus.size(); i++) {
            Escudo tempEscudo = bonus.get(i);
            formaEscudo = tempEscudo.getLimites();
            if (formaNave2.intersects(formaEscudo)) {
                tempEscudo.setVisible(false);
                player2.setEscudo(true);
            }
        }

        for (int i = 0; i < inimigoRosa.size(); i++) {
            InimigoRosa tempinimigoRosa = inimigoRosa.get(i);
            formaInimigoRosa = tempinimigoRosa.getLimites();
            if (formaNave1.intersects(formaInimigoRosa)) {
                player1.setVisivel(false);
                tempinimigoRosa.setVisible(false);
                vidaPlayer -= 1;
                player1.setColisao(true);
                if (vidaPlayer <= 0) {
                    emJogo = false;
                    System.out.println(calculaPontuacao());
                }
            }
        }

        for (int i = 0; i < inimigoRosa.size(); i++) {
            InimigoRosa tempinimigoRosa = inimigoRosa.get(i);
            formaInimigoRosa = tempinimigoRosa.getLimites();
            if (formaNave2.intersects(formaInimigoRosa)) {
                player2.setVisivel(false);
                tempinimigoRosa.setVisible(false);
                vidaPlayer -= 1;
                player2.setColisao(true);
                if (vidaPlayer <= 0) {
                    emJogo = false;
                    System.out.println(calculaPontuacao());
                }
            }
        }

        for (int i = 0; i < inimigoLaranja.size(); i++) {
            InimigoLaranja tempinimigoLaranja = inimigoLaranja.get(i);
            formaInimigoLaranja = tempinimigoLaranja.getLimites();
            if (formaNave1.intersects(formaInimigoLaranja)) {
                player1.setVisivel(false);
                tempinimigoLaranja.setVisible(false);
                vidaPlayer -= 1;
                player1.setColisao(true);
                if (vidaPlayer <= 0) {
                    emJogo = false;
                    System.out.println(calculaPontuacao());
                }
            }
        }

        for (int i = 0; i < inimigoLaranja.size(); i++) {
            InimigoLaranja tempinimigoLaranja = inimigoLaranja.get(i);
            formaInimigoLaranja = tempinimigoLaranja.getLimites();
            if (formaNave2.intersects(formaInimigoLaranja)) {
                player2.setVisivel(false);
                tempinimigoLaranja.setVisible(false);
                vidaPlayer -= 1;
                player2.setColisao(true);
                if (vidaPlayer <= 0) {
                    emJogo = false;
                    System.out.println(calculaPontuacao());
                }
            }
        }

        for (int i = 0; i < inimigoVerde.size(); i++) {
            InimigoVerde tempinimigoVerde = inimigoVerde.get(i);
            formaInimigoVerde = tempinimigoVerde.getLimites();
            if (formaNave1.intersects(formaInimigoVerde)) {
                player1.setVisivel(false);
                tempinimigoVerde.setVisible(false);
                vidaPlayer -= 1;
                player1.setColisao(true);
                if (vidaPlayer <= 0) {
                    emJogo = false;
                    System.out.println(calculaPontuacao());
                }
            }
        }

        for (int i = 0; i < inimigoVerde.size(); i++) {
            InimigoVerde tempinimigoVerde = inimigoVerde.get(i);
            formaInimigoVerde = tempinimigoVerde.getLimites();
            if (formaNave2.intersects(formaInimigoVerde)) {
                player2.setVisivel(false);
                tempinimigoVerde.setVisible(false);
                vidaPlayer -= 1;
                player2.setColisao(true);
                if (vidaPlayer <= 0) {
                    emJogo = false;
                    System.out.println(calculaPontuacao());
                }
            }
        }

        for (int i = 0; i < meteoros.size(); i++) {
            Meteoro tempMeteoro = meteoros.get(i);
            formaMeteoro = tempMeteoro.getLimites();
            if (formaNave1.intersects(formaMeteoro)) {
                player1.setVisivel(false);
                tempMeteoro.setVisible(false);
                vidaPlayer -= 2;
                player1.setColisao(true);
                if (vidaPlayer <= 0) {
                    emJogo = false;
                    System.out.println(calculaPontuacao());
                }
            }
        }

        for (int i = 0; i < meteoros.size(); i++) {
            Meteoro tempMeteoro = meteoros.get(i);
            formaMeteoro = tempMeteoro.getLimites();
            if (formaNave2.intersects(formaMeteoro)) {
                player2.setVisivel(false);
                tempMeteoro.setVisible(false);
                vidaPlayer -= 2;
                player2.setColisao(true);
                if (vidaPlayer <= 0) {
                    emJogo = false;
                    System.out.println(calculaPontuacao());
                }
            }
        }

        for (int i = 0; i < asteroides.size(); i++) {
            Asteroide tempAsteroide = asteroides.get(i);
            formaAsteroides = tempAsteroide.getLimites();
            if (formaNave1.intersects(formaAsteroides)) {
                player1.setVisivel(false);
                tempAsteroide.setVisible(false);
                vidaPlayer -= 2;
                player1.setColisao(true);
                if (vidaPlayer <= 0) {
                    emJogo = false;
                    System.out.println(calculaPontuacao());
                }
            }
        }

        for (int i = 0; i < asteroides.size(); i++) {
            Asteroide tempAsteroide = asteroides.get(i);
            formaAsteroides = tempAsteroide.getLimites();
            if (formaNave2.intersects(formaAsteroides)) {
                player2.setVisivel(false);
                tempAsteroide.setVisible(false);
                vidaPlayer -= 2;
                player2.setColisao(true);
                if (vidaPlayer <= 0) {
                    emJogo = false;
                    System.out.println(calculaPontuacao());
                }
            }
        }

        // colisao atk inimigo x player
        for (int i = 0; i < inimigoRosa.size(); i++) {
            List<AtaqueInimigo> ataques = inimigoRosa.get(i).getAtaques();

            for (int x = 0; x < ataques.size(); x++) {
                AtaqueInimigo tempataquerosa = ataques.get(x);
                formaAtaqueInimigoRosa = tempataquerosa.getLimites();
                if (formaAtaqueInimigoRosa.intersects(formaNave1)) {
                    tempataquerosa.setVisible(false);
                    vidaPlayer -= 1;
                    player1.setColisao(true);

                }

            }
        }

        for (int i = 0; i < inimigoRosa.size(); i++) {
            List<AtaqueInimigo> ataques = inimigoRosa.get(i).getAtaques();

            for (int x = 0; x < ataques.size(); x++) {
                AtaqueInimigo tempataquerosa = ataques.get(x);
                formaAtaqueInimigoRosa = tempataquerosa.getLimites();
                if (formaAtaqueInimigoRosa.intersects(formaNave2)) {
                    tempataquerosa.setVisible(false);
                    vidaPlayer -= 1;
                    player2.setColisao(true);

                }

            }
        }

        // COLISÕES ATAQUES x INIMIGOS
        List<AtaquePlayer> ataques1 = player1.getTiros();
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
                Meteoro tempMeteoro =meteoros.get(i);
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

        List<AtaquePlayer> ataques2 = player2.getTiros();
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
                Meteoro tempMeteoro =meteoros.get(i);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        calculaPontuacao();
        player1.movimenta();
        player2.movimenta();
        List<AtaquePlayer> tiros1 = player1.getTiros();
        List<AtaquePlayer> tiros2 = player2.getTiros();

        for (int i = 0; i < tiros1.size(); i++) {
            AtaquePlayer m = tiros1.get(i);
            if (m.isVisible()) {
                m.movimenta();

            } else {
                tiros1.remove(i);
            }
        }

        for (int i = 0; i < tiros2.size(); i++) {
            AtaquePlayer m = tiros2.get(i);
            if (m.isVisible()) {
                m.movimenta();

            } else {
                tiros2.remove(i);
            }
        }

        for (int i = 0; i < inimigoRosa.size(); i++) {
            List<AtaqueInimigo> ataques = inimigoRosa.get(i).getAtaques();

            for (int j = 0; j < ataques.size(); j++) {
                AtaqueInimigo m = ataques.get(j);
                if (m.isVisible()) {
                    m.movimenta();

                } else {
                    ataques.remove(j);
                }
            }
        }

        for (int p = 0; p < EstrelaBranca.size(); p++) {
            Estrelas on = EstrelaBranca.get(p);
            if (on.isVisible()) {
                on.movimenta();
            } else
                EstrelaBranca.remove(p);
        }

        for (int p = 0; p < EstrelaRosa.size(); p++) {
            Estrelas on = EstrelaRosa.get(p);
            if (on.isVisible()) {
                on.movimenta();
            } else
                EstrelaRosa.remove(p);
        }

        for (int p = 0; p < EstrelaAmarela.size(); p++) {
            Estrelas on = EstrelaAmarela.get(p);
            if (on.isVisible()) {
                on.movimenta();
            } else
                EstrelaAmarela.remove(p);
        }

        for (int p = 0; p < EstrelaAzul.size(); p++) {
            Estrelas on = EstrelaAzul.get(p);
            if (on.isVisible()) {
                on.movimenta();
            } else
                EstrelaAzul.remove(p);
        }

        for (int i = 0; i < inimigoAzul.size(); i++) {
            InimigoAzul in = inimigoAzul.get(i);
            if (in.isVisible()) {
                in.movimenta();
            } else {
                inimigoAzul.remove(i);
            }
        }

        for (int i = 0; i < bonus.size(); i++) {
            Escudo in = bonus.get(i);
            if (in.isVisible()) {
                in.movimenta();
            } else {
                bonus.remove(i);
            }
        }

        for (int i = 0; i < inimigoLaranja.size(); i++) {
            InimigoLaranja in = inimigoLaranja.get(i);
            if (in.isVisible()) {
                in.movimenta();
            } else {
                inimigoLaranja.remove(i);
            }
        }

        for (int i = 0; i < inimigoRosa.size(); i++) {
            InimigoRosa in = inimigoRosa.get(i);
            if (in.isVisible()) {
                in.movimenta();
            } else {
                inimigoRosa.remove(i);
            }
        }

        for (int i = 0; i < inimigoVerde.size(); i++) {
            InimigoVerde in = inimigoVerde.get(i);
            if (in.isVisible()) {
                in.movimenta();
            } else {
                inimigoVerde.remove(i);
            }
        }

        for (int i = 0; i < meteoros.size(); i++) {
            Meteoro in = meteoros.get(i);
            if (in.isVisible()) {
                in.movimenta();
            } else {
                meteoros.remove(i);
            }
        }

        for (int i = 0; i < asteroides.size(); i++) {
            Asteroide in = asteroides.get(i);
            if (in.isVisible()) {
                in.movimenta();
            } else {
                asteroides.remove(i);
            }
        }
        repaint();
        checarColisoes();

    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {

            graficos.drawImage(fundoFaseX, 0, 0, null);

            for (int p = 0; p < EstrelaBranca.size(); p++) {
                Estrelas q = EstrelaBranca.get(p);
                q.dadosImagem();
                graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
            }

            for (int p = 0; p < EstrelaRosa.size(); p++) {
                Estrelas q = EstrelaRosa.get(p);
                q.dadosImagem();
                graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
            }

            for (int p = 0; p < EstrelaAmarela.size(); p++) {
                Estrelas q = EstrelaAmarela.get(p);
                q.dadosImagem();
                graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
            }

            for (int p = 0; p < EstrelaAzul.size(); p++) {
                Estrelas q = EstrelaAzul.get(p);
                q.dadosImagem();
                graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
            }

            graficos.drawImage(player1.getImagem(), player1.getX(), player1.getY(), this);
            graficos.drawImage(player2.getImagem(), player2.getX(), player2.getY(), this);

            List<AtaquePlayer> tiros1 = player1.getTiros();
            List<AtaquePlayer> tiros2 = player2.getTiros();

            for (int i = 0; i < tiros1.size(); i++) {
                AtaquePlayer m = tiros1.get(i);
                m.dadosImagem();

                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < tiros2.size(); i++) {
                AtaquePlayer m = tiros2.get(i);
                m.dadosImagem();

                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            for (int x = 0; x < inimigoRosa.size(); x++) {
                List<AtaqueInimigo> ataques = inimigoRosa.get(x).getAtaques();

                for (int i = 0; i < ataques.size(); i++) {
                    AtaqueInimigo m = ataques.get(i);
                    m.dadosImagem();
                    graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

                }
            }

            for (int i = 0; i < inimigoAzul.size(); i++) {
                InimigoAzul in = inimigoAzul.get(i);
                in.dadosImagem();
                ;
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }

            for (int i = 0; i < bonus.size(); i++) {
                Escudo in = bonus.get(i);
                in.dadosImagem();
                ;
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }

            for (int i = 0; i < inimigoRosa.size(); i++) {
                InimigoRosa b = inimigoRosa.get(i);
                b.dadosImagem();
                b.movimenta();
                ;
                graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
            }

            for (int i = 0; i < inimigoVerde.size(); i++) {
                InimigoVerde b = inimigoVerde.get(i);
                b.dadosImagem();
                b.movimenta();
                ;
                graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
            }

            for (int i = 0; i < inimigoLaranja.size(); i++) {
                InimigoLaranja b = inimigoLaranja.get(i);
                b.dadosImagem();
                b.movimenta();
                ;
                graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
            }

            for (int i = 0; i < meteoros.size(); i++) {
                Meteoro b = meteoros.get(i);
                b.dadosImagem();
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