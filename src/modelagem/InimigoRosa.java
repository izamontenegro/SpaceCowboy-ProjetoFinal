package modelagem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class InimigoRosa extends Elemento implements ActionListener {
    private static int VELOCIDADE = 2;
    private List<AtaqueInimigo> ataques;
    private Timer timer;

    public List<AtaqueInimigo> getAtaques() {
        return ataques;
    }

    public InimigoRosa(int x, int y) {
        super(x, y);

        ataques = new ArrayList<AtaqueInimigo>();

        timer = new Timer(2000, this);
        timer.start();
        dadosImagem("imagens//inimigoRosa.gif");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((this.x >= 0 && this.y >= -120) && y <= 800) {
            atacar();
        } else
            ;

    }

    public void atacar() {
        this.ataques.add(new AtaqueInimigo(this.x + 12, this.y + 10));
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
