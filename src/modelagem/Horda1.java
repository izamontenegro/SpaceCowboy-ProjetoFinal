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

public class Horda1 extends JPanel implements ActionListener {
    private List<Estrelas> EstrelaBranca;
    private List<Estrelas> EstrelaRosa;
    private List<Estrelas> EstrelaAmarela;
    private List<Estrelas> EstrelaAzul;
    private List<Asteroide> asteroides;

  
    private Player player;
    private List<InimigoAzul> inimigoAzul;

    private int abateInimigoAzul = 0;
    private int pontuacaoTotal = 0;

    public Horda1() {
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon referencia = new ImageIcon("imagens//fundoJogo.png");
        
        inicializaEstrelas();
        inicializaInimigosAzuis();
      
        inicializaAsteroides();

    }
    public List<InimigoAzul> getInimigosAzul(){
    	return this.inimigoAzul;
    }
    public List<Asteroide> getAsteroides(){
    	return this.asteroides;
    }
    public void inicializaInimigosAzuis() {
        int quantidade[] = new int[15];
        inimigoAzul = new ArrayList<InimigoAzul>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1400);
            int y = (int) (Math.random() * -3500);
            inimigoAzul.add(new InimigoAzul(x, y));
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
    public void inicializaAsteroides() {
        int quantidade[] = new int[8];
        asteroides = new ArrayList<Asteroide>();

        for (int i = 0; i < quantidade.length; i++) {
            int x = (int) (Math.random() * -1500 + 1000);
            int y = (int) (Math.random() * -3500);
            asteroides.add(new Asteroide(x, y));
        }
    }

//    public int calculaPontuacao() {
//        pontuacaoTotal = abateInimigoAzul * 100;
//        return pontuacaoTotal;
//    }
//
//    public void checarColisoes() {
//        Rectangle formaNave = player.getLimites();
//        Rectangle formainimigoAzul;
//        Rectangle formaTiro;
//
//        // COLISÕES NAVE x INIMIGOS
//
//        for (int i = 0; i < inimigoAzul.size(); i++) {
//            InimigoAzul tempinimigoAzul = inimigoAzul.get(i);
//            formainimigoAzul = tempinimigoAzul.getLimites();
//            if (formaNave.intersects(formainimigoAzul)) {
//                player.setVisivel(false);
//                tempinimigoAzul.setVisible(false);
//                vidaPlayer -= 1;
//                player.setColisao(true);
//                if (vidaPlayer <= 0) {
//                    emJogo = false;
//                    System.out.println(calculaPontuacao());
//                }
//            }
//
//        }

        // COLISÕES ATAQUES x INIMIGOS
//        List<AtaquePlayer> ataques = player.getTiros();
//        for (int j = 0; j < ataques.size(); j++) {
//            AtaquePlayer tempTiro = ataques.get(j);
//            formaTiro = tempTiro.getLimites();
//
//            for (int i = 0; i < inimigoAzul.size(); i++) {
//                InimigoAzul tempinimigoAzul = inimigoAzul.get(i);
//                formainimigoAzul = tempinimigoAzul.getLimites();
//                if (formaTiro.intersects(formainimigoAzul)) {
//                    tempTiro.setVisible(false);
//                    tempinimigoAzul.setColisao(true);
//                    abateInimigoAzul += 1;
//
//                }
//            }
//        }
//    }

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
            for (int j = 0; j < asteroides.size(); j++) {
          		Asteroide b = asteroides.get(j);
          		b.dadosImagem();
          		b.movimenta();
          		;
          		graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
          	}

            g.dispose();
        }

    

//    private class TecladoAdapter extends KeyAdapter {
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            player.keyPressed(e);
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//            player.keyReleased(e);
//        }
//    }

}


