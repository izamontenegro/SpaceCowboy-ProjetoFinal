package modelagem;
public class Bonus extends Elemento {
    private static int VELOCIDADE = 1;

    public Bonus(int x, int y, int tipo) {
        super(x, y);
        if (tipo == 1) {
            dadosImagem("imagens//moedaEscudo.gif");
        } else if (tipo == 2) {
            dadosImagem("imagens//moedaVida.gif");
        } else {
            dadosImagem("imagens//moedaAtaqueEspecial.gif");
        }
    }

    public void movimenta() {
        this.y += VELOCIDADE;
    }
}
