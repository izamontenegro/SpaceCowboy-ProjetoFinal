package modelagem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Horda3 extends JPanel implements ActionListener {
    private List<Estrelas> Estrelas;

    private List<InimigoAzul> inimigoAzul;
    private List<InimigoVerde> inimigoVerde;
    private List<InimigoRosa> inimigoRosa;
    private List<InimigoLaranja> inimigoLaranja;
    private List<Meteoro> meteoros;
    private List<Asteroide> asteroides;
    private List<Bonus> bonus;

    public Horda3() {
        setFocusable(true);
        setDoubleBuffered(true);
        inicializaElementos();
    }

    public void inicializaElementos() {
        inicializaBonus();
        inicializaEstrelas();
        inicializaInimigosVerde();
        inicializaInimigosAzuis();
        inicializaInimigosRosa();
        inicializaInimigosLaranja();
        inicializaMeteoros();
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

    public List<Bonus> getBonus() {
        return this.bonus;
    }

    public List<InimigoVerde> getInimigosVerde() {
        return this.inimigoVerde;
    }

    public List<Meteoro> getMeteoros() {
        return this.meteoros;
    }

    public List<InimigoLaranja> getInimigosLaranja() {
        return this.inimigoLaranja;
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

        for (int p = 0; p < Estrelas.size(); p++) {
            Estrelas on = Estrelas.get(p);
            if (on.isVisible()) {
                on.movimenta();
            } else
                Estrelas.remove(p);
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
            Bonus in = bonus.get(i);
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
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        for (int p = 0; p < Estrelas.size(); p++) {
            Estrelas q = Estrelas.get(p);
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

        for (int x = 0; x < inimigoLaranja.size(); x++) {
            List<AtaqueInimigo> ataques = inimigoLaranja.get(x).getAtaquesD();

            for (int i = 0; i < ataques.size(); i++) {
                AtaqueInimigo m = ataques.get(i);
                m.dadosImagem();
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

            }
        }

        for (int x = 0; x < inimigoLaranja.size(); x++) {
            List<AtaqueInimigo> ataques = inimigoLaranja.get(x).getAtaquesE();

            for (int i = 0; i < ataques.size(); i++) {
                AtaqueInimigo m = ataques.get(i);
                m.dadosImagem();
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

            }
        }

        for (int x = 0; x < inimigoVerde.size(); x++) {
            List<AtaqueInimigo> ataques = inimigoVerde.get(x).getAtaques();

            for (int i = 0; i < ataques.size(); i++) {
                AtaqueInimigo m = ataques.get(i);
                m.dadosImagem();
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
            b.dadosImagem();
            graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
        }

        for (int i = 0; i < inimigoVerde.size(); i++) {
            InimigoVerde b = inimigoVerde.get(i);
            b.dadosImagem();
            graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
        }

        for (int i = 0; i < inimigoLaranja.size(); i++) {
            InimigoLaranja b = inimigoLaranja.get(i);
            b.dadosImagem();
            graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
        }

        for (int i = 0; i < meteoros.size(); i++) {
            Meteoro b = meteoros.get(i);
           b.dadosImagem();
            graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
        }

        for (int i = 0; i < asteroides.size(); i++) {
            Asteroide b = asteroides.get(i);
            b.dadosImagem();
          
            graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
        }

        g.dispose();
    }

}
