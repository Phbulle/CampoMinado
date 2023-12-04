
public class campominado { 
    public static void main(String[] args) {        
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.adicionarMinas();
        Tela tela = new Tela(tabuleiro);  
    }
}
