package modelagem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

public class InimigoVerde extends Elemento implements ActionListener{
    private static int VELOCIDADE = 1;
    private int vida = 2;
    private List<AtaqueInimigo> ataques;
    private Timer timer;

    public List<AtaqueInimigo> getAtaques() {
        return ataques;
    }

    public InimigoVerde(int x, int y) {
        super(x, y);
        ataques = new ArrayList<AtaqueInimigo>();
        timer = new Timer(700, this);
        timer.start();

        dadosImagem("imagens//InimigoVerde.gif");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.x >= -10 && y <= 800) {
            atacar();
        } else
            ;

    }

    public void atacar() {
        this.ataques.add(new AtaqueInimigo(this.x + 12, this.y + 25));
    }

    public int getVida() {
        return this.vida;
    }

    public void setVida(int x) {
        this.vida = this.vida - x;
    }


    public void movimenta() {
        if (this.y > 900) {
            this.y = -300;
            Random r = new Random();
            int n = r.nextInt(1724);
            this.x = n - 300;

        } else
            this.y += VELOCIDADE;
    }

}
