package modelagem;


//bibliotecas
import java.awt.Image;
import javax.swing.ImageIcon;
public class Coracao2 {

	    private int x, y;
	    private Image imagem;
	    private boolean isVisivel;
	    private String[]imgs={"imagens\\invisible.png","imagens\\meioCoracao.png","imagens\\umCoracao.png","imagens\\umEMeioCoracao.png","imagens\\doisCoracoes.png","imagens\\doisEMeioCoracoes.png","imagens\\tresCoracoes.png"};
	    private Image returnImg;
        
	    public Coracao2(int x,int y) {
	        this.x = x;
	        this.y = y;
	        isVisivel = true;
	    }

	   
	

	    public void dadosImagem() {
	    	ImageIcon referencia = new ImageIcon("imagens\\trescoracoes.png");
	        imagem = referencia.getImage();
	        imagem.getHeight(null);
	        imagem.getWidth(null);
	    }

	    // Getters and Setters
	    public int getX() {
	        return x;
	    }

	    public int getY() {
	        return y;
	    }

	    public Image getImagem(int x) {
	    	
	    	ImageIcon retornoFoto = new ImageIcon(imgs[x]);
	         returnImg= retornoFoto.getImage();
	        return returnImg;
	    }

	    public boolean isVisivel() {
	        return isVisivel;
	    }

	    public void setVisivel(boolean x) {
	    	this.isVisivel=x;
	}

}