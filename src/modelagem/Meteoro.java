package modelagem;

public class Meteoro  extends Elemento {
    private static int VELOCIDADE = 2;


    public Meteoro(int x, int y) {
        super(x, y);
        dadosImagem("imagens//meteoro.png");
    }
   

    public void movimenta() {
        this.y += VELOCIDADE;
        this.x -= 1;
    }


}
