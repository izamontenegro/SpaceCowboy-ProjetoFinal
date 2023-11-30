package modelagem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TelaInicio extends JPanel implements ActionListener {
    private List<Estrelas> EstrelaBranca;
    private List<Estrelas> EstrelaRosa;
    private List<Estrelas> EstrelaAmarela;
    private List<Estrelas> EstrelaAzul;
    private Image fundoFaseX;
    private Timer timer;
    private int contador = 0;

    private Player player;

    public TelaInicio() {
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon referencia = new ImageIcon("imagens//fundodTeste.png");
        fundoFaseX = referencia.getImage();
        addKeyListener(new TecladoAdapter());
        timer = new Timer(10, this);
        timer.start();

        player = new Player();
        player.dadosImagem();
        player.setX(1100);
        player.setY(650);

        inicializaEstrelas();

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

    @Override
    public void actionPerformed(ActionEvent e) {
        List<AtaquePlayer> tiros = player.getTiros();
        contador++;

        player.movimentaInicio();

        if (contador % 60 == 0) {
            player.tiroSimples();
        }

        for (int i = 0; i < tiros.size(); i++) {
            AtaquePlayer m = tiros.get(i);
            if (m.isVisible()) {
                m.movimenta();

            } else {
                tiros.remove(i);
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
        repaint();

    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

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

        graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

        List<AtaquePlayer> tiros = player.getTiros();

        for (int i = 0; i < tiros.size(); i++) {
            AtaquePlayer m = tiros.get(i);
            m.dadosImagem();

            graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
        }
        g.dispose();
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
