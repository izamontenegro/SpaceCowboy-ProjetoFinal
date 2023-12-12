package modelagem;

import java.awt.Image;



import javax.swing.ImageIcon;
public class NaveCaindo {
    private int x,y;
        private Image imagem;
        private int altura,largura;
        private int i=0;
        
        public NaveCaindo(){
            this.y=0;
            this.x=200;
        }
        public void queda() {
        	this.y=this.y+10;
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
          
                ImageIcon referencia= new ImageIcon("imgs\\NaveVermelha.png");
                imagem=referencia.getImage();
                altura=imagem.getHeight(null);
                largura=imagem.getWidth(null);
              
                
        }
    
    
}