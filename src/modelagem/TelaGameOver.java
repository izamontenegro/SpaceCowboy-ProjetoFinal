package modelagem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class TelaGameOver extends JPanel implements ActionListener {
    private Image fundo;
    private HighScore highscore;
    private Contagem contagem;
    private Timer timer;
    private int dy=-800;
    private int dx=0;
    private int i=0;

    
    public TelaGameOver(){
        this.loadInicial();
        contagem = new Contagem();
        contagem.load();
        timer = new Timer(400,new ActionListener(){
            
        public void actionPerformed(ActionEvent e){
            if(i==10){
                timer.setDelay(10);
                if(dy==0){
                    timer.stop();
                }else{
                    deslizamentoTela();
                }
            }else{
                contagem.load();
                i++;
                repaint();
            }
    }
        });

        timer.start();
        highscore=new HighScore();
        highscore.load();
    }
     public void deslizamentoTela(){
        dy = dy+2;  
        repaint();
     }

    public void loadInicial(){
        ImageIcon referencia=new ImageIcon("imagens\\fundoOficial.png");
        fundo=referencia.getImage();
    }

    public void paint(Graphics g){
        Graphics2D graficos=(Graphics2D) g;
        graficos.drawImage(fundo,this.dx,this.dy,1920,2000, null);
        if(this.dy==0){
            graficos.drawImage(highscore.getImagem(),110,40,1920,1920, null);
        }
        if(this.i==10){
            graficos.drawImage(contagem.getImagem(),150,60,0,0,this);           
        }else{
            graficos.drawImage(contagem.getImagem(),150,60,800,600,this);
        }
        g.dispose();
    } 

    public void actionPerformed(ActionEvent e){
    }

	public Image getFundo() {
		return fundo;
	}

	public int getDy() {
		return dy;
	}

	public int getDx() {
		return dx;
	}
}