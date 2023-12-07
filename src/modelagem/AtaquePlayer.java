package modelagem;

import javax.swing.ImageIcon;

public class AtaquePlayer extends Elemento {
    private ImageIcon referencia = new ImageIcon("imagens//ataqueBasicoPlayer.png");
    private static int VELOCIDADE = 8;

    public AtaquePlayer(int x, int y) {
        super(x, y);
    }

    public AtaquePlayer(int x, int y, ImageIcon imagem) {
        super(x, y);
        this.x = x;
        this.y = y;
        isVisible = true;
        this.referencia = imagem;
    }

    public void dadosImagem() {
        imagem = referencia.getImage();
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void movimenta() {
        
        this.y -= VELOCIDADE;
        if (this.y <= -100) {
            isVisible = false;
        }
       
    }
}