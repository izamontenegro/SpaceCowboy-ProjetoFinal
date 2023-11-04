package modelagem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
    private List<EstrelaBranca> EstrelaBranca;
    private List<EstrelaRosa> EstrelaRosa;
    private List<EstrelaAmarela> EstrelaAmarela;
    private List<EstrelaAzul> EstrelaAzul;
    private Image fundoFase;
    private Timer timer;
    private boolean emJogo;

    private Player player;
    private List<InimigoAzul> inimigoAzul;
    private List<InimigoRosa> inimigoRosa;

    private int abateInimigos = 0;
    private int pontuacaoTotal = 0;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon referencia = new ImageIcon("imagens//fundoJogo.png");
        fundoFase = referencia.getImage();
        emJogo = true;
        addKeyListener(new TecladoAdapter());
        timer = new Timer(5, this);
        timer.start();

        player = new Player();
        player.dadosImagem();

        inicializaEstrela();
        inicializaInimigosAzuis();
        inicializaInimigosRosa();

    }

    public void inicializaInimigosAzuis() {
        int quantidade[] = new int[25];
        inimigoAzul = new ArrayList<InimigoAzul>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1400);
            int y = (int) (Math.random() * -4500);
            inimigoAzul.add(new InimigoAzul(x, y));
        }
    }

    public void inicializaInimigosRosa() {
        int quantidade[] = new int[20];
        inimigoRosa = new ArrayList<InimigoRosa>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1400);
            int y = (int) (Math.random() * -4500);
            inimigoRosa.add(new InimigoRosa(x, y));
        }
    }


    public void inicializaEstrela() {
        int quantidade[] = new int[20];
        EstrelaBranca = new ArrayList<EstrelaBranca>();
        EstrelaRosa = new ArrayList<EstrelaRosa>();
        EstrelaAmarela = new ArrayList<EstrelaAmarela>();
        EstrelaAzul = new ArrayList<EstrelaAzul>();

        for (int i = 0; i < (quantidade.length + 10); i++) {
            int x = (int) (Math.random() * -8000);
            int y = (int) (Math.random() * -4500);
            EstrelaBranca.add(new EstrelaBranca(x, y));
        }

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -8000);
            int y = (int) (Math.random() * -4500);
            EstrelaAzul.add(new EstrelaAzul(x, y));
        }

        for (int j = 0; j < (quantidade.length-10); j++) {
            int x = (int) (Math.random() * -8000);
            int y = (int) (Math.random() * -4500);
            EstrelaRosa.add(new EstrelaRosa(x, y));
        }

        for (int j = 0; j < quantidade.length; j++) {
            int x = (int) (Math.random() * -8000);
            int y = (int) (Math.random() * -4500);
            EstrelaAmarela.add(new EstrelaAmarela(x, y));
        }
    }

    public int calculaPontuacao() {
        pontuacaoTotal += (abateInimigos * 100);
        return pontuacaoTotal;
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {

            graficos.drawImage(fundoFase, 0, 0, null);

            for (int p = 0; p < EstrelaBranca.size(); p++) {
                EstrelaBranca q = EstrelaBranca.get(p);
                q.load();
                graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
            }

            for (int p = 0; p < EstrelaRosa.size(); p++) {
                EstrelaRosa q = EstrelaRosa.get(p);
                q.load();
                graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
            }

            for (int p = 0; p < EstrelaAmarela.size(); p++) {
                EstrelaAmarela q = EstrelaAmarela.get(p);
                q.load();
                graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
            }

            for (int p = 0; p < EstrelaAzul.size(); p++) {
                EstrelaAzul q = EstrelaAzul.get(p);
                q.load();
                graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
            }

            graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

            List<AtaquePlayer> tiros = player.getTiros();

            for (int i = 0; i < tiros.size(); i++) {
                AtaquePlayer m = tiros.get(i);
                m.load();
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }

            for (int i = 0; i < inimigoAzul.size(); i++) {
                InimigoAzul in = inimigoAzul.get(i);
                in.load();
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

        } else {
            ImageIcon fimJogo = new ImageIcon("imagens//gameover.png");
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    // atualizar o estado dos objetos do jogo, verificar colisões e solicitar a
    // repintura da tela. É chamado periodicamente pelo Timer para criar uma
    // atualização contínua do jogo.
    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();

        for (int p = 0; p < EstrelaBranca.size(); p++) {
            EstrelaBranca on = EstrelaBranca.get(p);
            if (on.isVisible()) {
                on.update();
            } else
                EstrelaBranca.remove(p);
        }

        for (int p = 0; p < EstrelaRosa.size(); p++) {
            EstrelaRosa on = EstrelaRosa.get(p);
            if (on.isVisible()) {
                on.update();
            } else
                EstrelaRosa.remove(p);
        }

        for (int p = 0; p < EstrelaAmarela.size(); p++) {
            EstrelaAmarela on = EstrelaAmarela.get(p);
            if (on.isVisible()) {
                on.update();
            } else
                EstrelaAmarela.remove(p);
        }

        for (int p = 0; p < EstrelaAzul.size(); p++) {
            EstrelaAzul on = EstrelaAzul.get(p);
            if (on.isVisible()) {
                on.update();
            } else
                EstrelaAzul.remove(p);
        }

        List<AtaquePlayer> tiros = player.getTiros();

        for (int i = 0; i < tiros.size(); i++) {
            AtaquePlayer m = tiros.get(i);
            if (m.isVisible()) {
                m.update();
            } else {
                tiros.remove(i);
            }
        }

        for (int i = 0; i < inimigoAzul.size(); i++) {
            InimigoAzul in = inimigoAzul.get(i);
            if (in.isVisible()) {
                in.update();
            } else {
                inimigoAzul.remove(i);
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

        checarColisoes();
        repaint();

    }

    public void checarColisoes() {
        Rectangle formaNave = player.getBounds();
        Rectangle formainimigoAzul;
        Rectangle formaTiro;
        Rectangle formaInimigoRosa;

        for (int i = 0; i < inimigoAzul.size(); i++) {
            InimigoAzul tempinimigoAzul = inimigoAzul.get(i);
            formainimigoAzul = tempinimigoAzul.getBounds();
            if (formaNave.intersects(formainimigoAzul)) {
                player.setVisivel(false);
                tempinimigoAzul.setVisible(false);
                emJogo = false;
                System.out.println("num abates: " + abateInimigos);
                System.out.println("Pontos: " + calculaPontuacao());
            }
        }

        for (int i = 0; i < inimigoRosa.size(); i++) {
            InimigoRosa tempinimigoRosa = inimigoRosa.get(i);
            formaInimigoRosa = tempinimigoRosa.getBounds();
            if (formaNave.intersects(formaInimigoRosa)) {
                player.setVisivel(false);
                tempinimigoRosa.setVisible(false);
                emJogo = false;
                System.out.println("num abates: " + abateInimigos);
                System.out.println("Pontos: " + calculaPontuacao());
            }
        }

        List<AtaquePlayer> ataques = player.getTiros();
        for (int j = 0; j < ataques.size(); j++) {
            AtaquePlayer tempTiro = ataques.get(j);
            formaTiro = tempTiro.getBounds();

            for (int i = 0; i < inimigoAzul.size(); i++) {
                InimigoAzul tempinimigoAzul = inimigoAzul.get(i);
                formainimigoAzul = tempinimigoAzul.getBounds();
                if (formaTiro.intersects(formainimigoAzul)) {
                    tempinimigoAzul.setVisible(false);
                    tempTiro.setVisible(false);
                    abateInimigos += 1;
                    calculaPontuacao();
                }
            }

            for (int i = 0; i < inimigoRosa.size(); i++) {
                InimigoRosa tempinimigoRosa = inimigoRosa.get(i);
                formaInimigoRosa = tempinimigoRosa.getBounds();
                if (formaTiro.intersects(formaInimigoRosa)) {
                    tempinimigoRosa.setVisible(false);
                    tempTiro.setVisible(false);
                    abateInimigos += 1;
                    calculaPontuacao();
                }
            }
        }
    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }

}
