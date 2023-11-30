package modelagem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Horda2 extends JPanel implements ActionListener {
    private List<Estrelas> EstrelaBranca;
    private List<Estrelas> EstrelaRosa;
    private List<Estrelas> EstrelaAmarela;
    private List<Estrelas> EstrelaAzul;

    private List<InimigoAzul> inimigoAzul;
    private List<InimigoRosa> inimigoRosa;
    private List<Asteroide> asteroides;
    private List<Escudo> bonus;

    public Horda2() {
        setFocusable(true);
        setDoubleBuffered(true);

        inicializaBonus();
        inicializaEstrelas();
        inicializaInimigosAzuis();
        inicializaInimigosRosa();
        inicializaAsteroides();

    }

    public List<InimigoAzul> getInimigosAzul() {
        return this.inimigoAzul;
    }

    public List<Asteroide> getAsteroides() {
        return this.asteroides;
    }

    public List<InimigoRosa> getInimigosRosa() {
        return this.inimigoRosa;
    }

    public List<Escudo> getBonus() {
        return this.bonus;
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

    public void inicializaInimigosRosa() {
        int quantidade[] = new int[5];
        inimigoRosa = new ArrayList<InimigoRosa>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1400);
            int y = (int) (Math.random() * -3500);
            inimigoRosa.add(new InimigoRosa(x, y));
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

        for (int i = 0; i < inimigoRosa.size(); i++) {
            InimigoRosa in = inimigoRosa.get(i);
            if (in.isVisible()) {
                in.movimenta();
            } else {
                inimigoRosa.remove(i);
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
