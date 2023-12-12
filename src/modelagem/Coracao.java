package modelagem;


//bibliotecas
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.Timer;
public class Coracao {


		
	    private int x, y;
	    private Image imagem;
	    private int altura, largura;	
	    private boolean isVisivel;
	    private String[]imgs={"imagens\\invisible.png","imagens\\meioCoracao.png","imagens\\umCoracao.png","imagens\\umEMeioCoracao.png","imagens\\doisCoracoes.png","imagens\\doisEMeioCoracoes.png","imagens\\tresCoracoes.png"};
	    private Image returnImg;
	    
	    
	    
	    public Coracao(int x,int y) {
	        this.x = x;
	        this.y = y;
	        isVisivel = true;
	    }

	   
	

	    public void dadosImagem() {
	    	ImageIcon referencia = new ImageIcon("imagens\\trescoracoes.png");
	        imagem = referencia.getImage();
	        altura = imagem.getHeight(null);
	        largura = imagem.getWidth(null);
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
  public Image getImagem() {
	    	
	    	ImageIcon retornoFoto = new ImageIcon("imagens\\coracaoVazio.png");
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
