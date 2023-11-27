package modelagem;

import java.awt.Image;

import javax.swing.ImageIcon;
public class HighScore {
    private int x,y;
        private Image imagem;
        private int altura,largura;
        private int i=0;
        public HighScore(){
            this.y=220;
            this.x=620;
        }
    
        public Image getImagem() {
            return imagem;
        }
        public int getX() {
            return x;
        }
    
        public int getY() {
            return y;
        }
        public void load(){
          
                ImageIcon referencia= new ImageIcon("img\\nave.png");
                imagem=referencia.getImage();
                altura=imagem.getHeight(null);
                largura=imagem.getWidth(null);
              
                
        }
    
    
}