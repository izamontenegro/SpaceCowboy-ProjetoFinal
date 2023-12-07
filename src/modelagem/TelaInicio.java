package modelagem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



public class TelaInicio extends JPanel implements ActionListener{
	private Seta nave;
    private Image background;
   private Timer timer;
   private KeyAdapter tecladoAdapterInicial;
   private FaseGeral fase;
    public TelaInicio(){
	  	setFocusable(true);
  	  	setDoubleBuffered(true);
    	nave=new Seta();
    	nave.load();
        ImageIcon referencia = new ImageIcon("imagens//backgroundHome.png");
        tecladoAdapterInicial= new TecladoAdapter();
        addKeyListener(tecladoAdapterInicial);
        background = referencia.getImage();
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g){
    	Graphics2D graficos = (Graphics2D) g;
    	if(nave.getStart()==0) {
        graficos.drawImage(background, 0, 0,1440,720, null);
        graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(),120,80, this);
    	}else if(nave.getStart()==1) {
    		fase.paint(graficos);
    	}
        g.dispose();
    }
    public void verificarStart(){
 	   if(nave.getStart()==1) {
 		   if(fase==null) {
 			   removeKeyListener(tecladoAdapterInicial);
 			   fase=new FaseGeral();
 			   addKeyListener(fase.getKeyAdapter());
 		   }
 		   fase.actionPerformed(null);
 	   }
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		
		verificarStart();
		repaint();
	}

	private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            nave.keyPressed(e);
        }
    }
}