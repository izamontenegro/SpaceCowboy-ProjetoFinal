package modelagem;

import java.awt.Graphics;
import modelagem.TelaGameOver;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import modelagem.NaveCaindo;
public class TelaQueda extends JPanel implements ActionListener {
    private Image fundo;
    private int dy=0;
    private int dx=0;
    private int i=0;
    private Timer timer;
    private NaveCaindo nave;
    private TelaGameOver gameover;
    public TelaQueda(){
        this.load();
        nave=new NaveCaindo();
        nave.load();
        
        timer = new Timer(10, this); 
        timer.start();
       
        
    }
     public void deslizamentoTela(){
    	 if(dy>=-1000) {
    		 dy = dy-10;  
    		 
    	 }
                            
              
     }


    public void load(){
        ImageIcon referencia=new ImageIcon("imgs\\fundos.png");
        fundo=referencia.getImage();
       
    }
  

    public void paint(Graphics g){
    	Graphics2D graficos=(Graphics2D) g;
    	if(nave.getY()<=800) {
    	graficos.drawImage(fundo,this.dx,this.dy,1440,2000, null);
    	graficos.drawImage(nave.getImagem(),nave.getX(),nave.getY(),300,300, this);
    	}else if (this.i==1){
    		gameover.paint(graficos);
    	}
    	g.dispose();
     
    }
   public void verificarQueda() {
   	if(nave.getY()>800) {
   		if(gameover==null) {
   			
   			gameover=new TelaGameOver(); 
   			System.out.println("construiu tela game over");
   			this.i++;
   			
   		}
        }
   }
    public void actionPerformed(ActionEvent e){
       deslizamentoTela();
      
       if(dy<=-1000 && nave.getY()<=800) {
    	   nave.queda();   
    	  
       }
       
      verificarQueda();
       repaint();
    
    }
}
