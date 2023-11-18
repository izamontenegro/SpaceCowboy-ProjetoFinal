package modelagem;

//Bibliotecas
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
    private Player player; // Declaração do player que terá na fase
    private Image fundo; // Background da fase
    private Timer timer; // Timer para repetição
    private List<Inimigo1> enemy1; // Declaração dos inimigos
    private List<Stars> stars;
    private boolean emJogo;
    private int abates = 0;

    // Construtor
    public Fase() {
        setFocusable(true); // Permite que o painel receba eventos de teclado.
        setDoubleBuffered(true); // Ativa o double buffering para melhorar a renderização gráfica.
        ImageIcon referencia = new ImageIcon("imagens//fundoJogo.png"); // Imagem do fundo
        fundo = referencia.getImage();

        player = new Player(); // atribui ao player criado na classe fase o construtor que definirá a posição
                               // na tela
        player.load(); // Traz para a classe fase as informações de imagem do player

        addKeyListener(new TecladoAdapter()); // Permitindo que responda á ações do teclado

        timer = new Timer(10, this);
        timer.start(); // Timer para definir o delay nas ações

        inicializaStars();
        inicializaInimigos();

        emJogo = true;

        
    }

    // Método para gerar inimigos aleatórios
    public void inicializaInimigos() {
        int quantidade[] = new int[50];
        // usar isso pro sistema de dificuldade
        enemy1 = new ArrayList<Inimigo1>();

        for (int i = 0; i < quantidade.length; i++) {
            // Gerando inimigos em posições aleatórias dentro do intervado especificado
            int x = (int) (Math.random() * -1500 + 1400);
            int y = (int) (Math.random() * -4500);
            // Adicionando o inimigo com as posições definidas na lista de inimigos da fase
            enemy1.add(new Inimigo1(x, y));
        }

    }

    public void inicializaStars() {
        int quantidade[] = new int[100];
        stars = new ArrayList<Stars>();

        for (int i = 0; i < quantidade.length; i++) {

            int x = (int) (Math.random() * -8000);
            int y = (int) (Math.random() * -4500);
            // modifucar agui

            stars.add(new Stars(x, y));
        }
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);

            for (int p = 0; p < stars.size(); p++) {
                Stars q = stars.get(p);
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

            for (int i = 0; i < enemy1.size(); i++) {
                Inimigo1 in = enemy1.get(i);
                in.load();
                ;
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }

        } else {
            ImageIcon fimJogo = new ImageIcon("imagens//gameover.png");
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();

        for (int p = 0; p < stars.size(); p++) {
            Stars on = stars.get(p);
            if (on.isVisible()) {
                on.update();
            } else
                stars.remove(p);
        }
        List<AtaquePlayer> tiros = player.getTiros();

        for (int i = 0; i < tiros.size(); i++) {
            AtaquePlayer m = tiros.get(i);
            if (m.isVisible()) {
                m.update();
                ;
            } else {
                tiros.remove(i);
            }
        }

        for (int i = 0; i < enemy1.size(); i++) {
            Inimigo1 in = enemy1.get(i);
            if (in.isVisible()) {
                in.update();
            } else {
                enemy1.remove(i);
            }
        }

        checarColisoes();
        repaint();

    }

    public void checarColisoes() {
        Rectangle formaNave = player.getBounds();
        Rectangle formaEnemy1;
        Rectangle formaTiro;

        for (int i = 0; i < enemy1.size(); i++) {
            Inimigo1 tempEnemy1 = enemy1.get(i);
            formaEnemy1 = tempEnemy1.getBounds();
            if (formaNave.intersects(formaEnemy1)) {
                player.setVisivel(false);
                tempEnemy1.setVisible(false);
                emJogo = false;
                System.out.println("num abates: " + abates);
            }
        }

        List<AtaquePlayer> ataques = player.getTiros();
        for (int j = 0; j < ataques.size(); j++) {
            AtaquePlayer tempTiro = ataques.get(j);
            formaTiro = tempTiro.getBounds();

            for (int i = 0; i < enemy1.size(); i++) {
                Inimigo1 tempEnemy1 = enemy1.get(i);
                formaEnemy1 = tempEnemy1.getBounds();
                if (formaTiro.intersects(formaEnemy1)) {
                    tempEnemy1.setVisible(false);
                    tempTiro.setVisible(false);
                    abates += 1;
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
