package modelagem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class InimigoLaranja extends Elemento implements ActionListener {
    private static int VELOCIDADE = 2;
    private List<AtaqueInimigo> ataquesD;
    private List<AtaqueInimigo> ataquesE;
    private Timer timer;

    public InimigoLaranja(int x, int y) {
        super(x, y);

        ataquesD = new ArrayList<AtaqueInimigo>();
        ataquesE = new ArrayList<AtaqueInimigo>();

        timer = new Timer(2500, this);
        timer.start();
        dadosImagem("imagens//inimigoLaranja.gif");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.x >= 0 && y <= 800) {
            atacar();
        } else
            ;

    }

    public void atacar() {
        this.ataquesD.add(new AtaqueInimigo(this.x + 12, this.y + 20));
        this.ataquesE.add(new AtaqueInimigo(this.x + 12, this.y + 20));
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

    public List<AtaqueInimigo> getAtaquesD() {
        return ataquesD;
    }

    public List<AtaqueInimigo> getAtaquesE() {
        return ataquesE;
    }
}
