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


		ImageIcon referencia = new ImageIcon("imagens//CoracaoCheio.png");
	    private int x, y;
	    private Image imagem;
	    private int altura, largura;	
	    private boolean isVisivel;
	

	    public Coracao(int x,int y) {
	        this.x = x;
	        this.y = y;
	        isVisivel = true;
	    }

	   
	

	    public void dadosImagem() {
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

	    public Image getImagem() {
	        return imagem;
	    }

	    public boolean isVisivel() {
	        return isVisivel;
	    }

	    public void setVisivel(boolean x) {
	    	this.isVisivel=x;
	}

}
