package modelagem;

public class Bonus extends Elemento {
    private int tipo;
    private static int VELOCIDADE = 2;

    public Bonus(int x, int y, int tipo) {
        super(x, y);
        this.tipo = tipo;
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

    public int getTipo() {
        return this.tipo;
    }
}
