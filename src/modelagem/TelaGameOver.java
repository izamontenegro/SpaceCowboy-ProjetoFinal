package modelagem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;
public class TelaGameOver extends JPanel implements ActionListener {
    private Image fundo;
    private HighScore highscore;
    private Contagem contagem;
    private Timer timer;
    private int delay = 500;
    private int delay2 = 80;
    private int delay3 = 8;
    private Timer timerFundo;
    private String[] fundos={"img\\fundoOficial"};
    private int j=0;
    private int dy=-800;
    private int dx=0;
    private int i=0;
    public TelaGameOver(){
        this.loadInicial();
       
        // if(j==2){
        //     j=0;
        // }
        // ImageIcon referencia=new ImageIcon(fundos[j]);
        // fundo=referencia.getImage();
        // gameOver=new GameOver();
        // gameOver.load();
        // continuar=new Continue();
        // continuar.load();
        contagem=new Contagem();
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
        j++;
        
    }
     public void deslizamentoTela(){
                            dy = dy+2;  
                            repaint();
              
     }

    public void loadInicial(){
        ImageIcon referencia=new ImageIcon("img\\fundoOficial.png");
        fundo=referencia.getImage();
       
    }
    
    // public void load(){
    //     if(j==2){
    //         j=0;
    //     }
    //     ImageIcon referencia=new ImageIcon(fundos[j]);
    //     fundo=referencia.getImage();
    //    j++;
    //  }
    
    

    public void paint(Graphics g){
        Graphics2D graficos=(Graphics2D) g;
        graficos.drawImage(fundo,this.dx,this.dy,1440,1500, null);
        if(this.dy==0){
            graficos.drawImage(highscore.getImagem(),110,40,400,400, null);
            
        }
        if(this.i==10){
            graficos.drawImage(contagem.getImagem(),150,60,0,0,this);
            
        }else{
            graficos.drawImage(contagem.getImagem(),150,60,800,600,this);

        }
        g.dispose();
    }
    
    public void actionPerformed(ActionEvent e){
        // this.load();
        // gameOver.update();
        //  continuar.trocarPlano();
        //  contagem.load();
        // repaint();
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
