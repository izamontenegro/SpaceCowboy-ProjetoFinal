package modelagem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Horda1 extends JPanel implements ActionListener {
    private List<Estrelas> EstrelaBranca;
    private List<Estrelas> EstrelaRosa;
    private List<Estrelas> EstrelaAmarela;
    private List<Estrelas> EstrelaAzul;
    private List<InimigoAzul> inimigoAzul;
    private List<Asteroide> asteroides;

    public Horda1() {
        setFocusable(true);
        setDoubleBuffered(true);

        inicializaEstrelas();
        inicializaInimigosAzuis();
        inicializaAsteroides();

    }

    public List<InimigoAzul> getInimigosAzul() {
        return this.inimigoAzul;
    }

    public List<Asteroide> getAsteroides() {
        return this.asteroides;
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

        for (int i = 0; i < asteroides.size(); i++) {
            Asteroide in = asteroides.get(i);
            if (in.isVisible()) {
                in.movimenta();
            } else {
                asteroides.remove(i);
            }
        }
        repaint();

    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

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

        for (int i = 0; i < inimigoAzul.size(); i++) {
            InimigoAzul in = inimigoAzul.get(i);
            in.dadosImagem();
            ;
            graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
        }

        for (int i = 0; i < asteroides.size(); i++) {
            Asteroide b = asteroides.get(i);
            b.dadosImagem();
            b.movimenta();
            ;
            graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
        }

        g.dispose();
    }

}
