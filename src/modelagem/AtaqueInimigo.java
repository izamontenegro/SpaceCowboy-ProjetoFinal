package modelagem;

public class AtaqueInimigo extends Elemento{
    private static int VELOCIDADE = 8;

    public AtaqueInimigo(int x, int y) {
        super(x, y);
    }

    public void movimenta(String tipo) {
        if (tipo == "rosa") {
            dadosImagem("imagens//ataqueInimigoRosa.png");
            this.y += VELOCIDADE;
        }
        if (tipo == "verde"){
            dadosImagem("imagens//ataqueInimigoVerde.png");
            this.x += VELOCIDADE - 2;
            this.y += 4;
            
        }
        if(tipo == "laranjaD"){
             dadosImagem("imagens//ataqueInimigoLaranjaD.png");
            this.x += VELOCIDADE - 2;
            this.y -=  2;
        }
        if(tipo == "laranjaE"){
             dadosImagem("imagens//ataqueInimigoLaranjaE.png");
            this.x -= VELOCIDADE - 2;
            this.y -= 2;
        }

    }

    
}
