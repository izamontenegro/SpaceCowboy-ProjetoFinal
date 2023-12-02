package modelagem;

//Bibliotecas
import java.util.Random;

public class Estrelas extends Elemento{
    private static int VELOCIDADE = 3;

    public Estrelas(int x, int y, int n) {
        super(x, y);
        if (n == 1) {
            dadosImagem("imagens//EstrelaBranca.png");
        } else if (n == 2) {
            dadosImagem("imagens//EstrelaRosa.png");
        } else if (n == 3) {
            dadosImagem("imagens//EstrelaAzul.png");
        } else {
            dadosImagem("imagens//EstrelaAmarela.png");
        }
    }

    public void movimenta() {
        if (this.y > 850) {
            this.y = -300;
            Random a = new Random();
            int m = a.nextInt(768);
            this.y = m;
            Random r = new Random();
            int n = r.nextInt(1724);
            this.x = n - 300;

        } else
            this.y += VELOCIDADE;
    }
}
