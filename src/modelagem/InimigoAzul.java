package modelagem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

public class InimigoAzul extends Elemento implements ActionListener {
    private static int VELOCIDADE = 3;
    private Timer timer;
    private boolean colisao = false;

    public InimigoAzul(int x, int y) {
        super(x, y);
        timer = new Timer(300, this);
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (colisao) {
            dadosImagem("imagens//inimigoAzulDanoSofrido.gif");
            isVisible = false;
            
        } else {
            dadosImagem("imagens//InimigoAzul.gif");
        }
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

    public void setColisao(boolean c) {
        this.colisao = c;
    }


}
