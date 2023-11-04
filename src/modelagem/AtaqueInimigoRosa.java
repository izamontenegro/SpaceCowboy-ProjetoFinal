package modelagem;

//Bibliotecas aaaa
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class AtaqueInimigoRosa {
    private Image imagem; // Imagem do ataque
    private int x, y; // Posição do ataque
    private int largura, altura; // Tamanho da imagem do ataque
    private boolean isVisible; // Visibilidade do ataque
    private static int VELOCIDADE = 8; // Velocidade com que ele se move depois de disparado

    // Construtor que recebe as posições x,y da nave, para que possa sair
    // diretamente dela
    public AtaqueInimigoRosa(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    // Método que pega as informações da imagem do ataque
    public void load() {
        ImageIcon referencia = new ImageIcon("imagens//ataqueInimigoRosa.png");
        imagem = referencia.getImage();
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    // Método responsavel por realizar o movimento tiro, saindo da sua posição
    // inicial na nave e se movimentando de acordo com a velocidade estipulada
    public void update() {
        this.y -= VELOCIDADE;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    // Getters and Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

}
