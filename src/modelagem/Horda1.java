package modelagem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Horda1 extends JPanel implements ActionListener {
    private List<Estrelas> Estrelas;
    private List<InimigoAzul> inimigoAzul;
    private List<Asteroide> asteroides;
    private List<Bonus> bonus;

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

    public List<Bonus> getBonus() {
        return this.bonus;
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

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int p = 0; p < Estrelas.size(); p++) {
            Estrelas on = Estrelas.get(p);
            if (on.isVisible()) {
                on.movimenta();
            } else
                Estrelas.remove(p);
        }

        for (int i = 0; i < bonus.size(); i++) {
            Bonus in = bonus.get(i);
            if (in.isVisible()) {
                in.movimenta();
            } else {
                bonus.remove(i);
            }
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

        for (int p = 0; p < Estrelas.size(); p++) {
            Estrelas q = Estrelas.get(p);
            q.dadosImagem();
            graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
        }

        for (int i = 0; i < inimigoAzul.size(); i++) {
            InimigoAzul in = inimigoAzul.get(i);
            in.dadosImagem();
            ;
            graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
        }

        for (int i = 0; i < bonus.size(); i++) {
            Bonus in = bonus.get(i);
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
