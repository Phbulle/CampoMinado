import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class campominado {

    static class Celula{
        boolean minado; //possui uma mina
        boolean revelado; // está revelado
        boolean marcado; // está marcado com a bandeira
        boolean clicado; // foi clicado

        ArrayList<Celula> vizinhos;

        public Celula(){
            this.minado = false;
            this.revelado = false;
            this.marcado = false;
            this.clicado = false;
            this.vizinhos = new ArrayList<>();
        }

        public void AdicionarVizinhos(Celula c){
            this.vizinhos.add(c);
        }

        //Metodo minar: indica que o espaço tem uma mina
        //Se a célula já tem uma mina, retorna false
        //Se a célula não tinha mina, ela é colocada

        public boolean minar(){
            if(!this.minado){
                this.minado = true;
                return true;
            }else
                return false;
        }

        //Metodo marcar: marca a célula com uma bandeira
        //Após marcado, se a célula não tiver sido marcado antes ela recebe uma bandeira
        //Caso já tiver sido marcada, ela é desmarcada e tirada a bandeira

        public boolean marcar(){
            this.marcado = !this.marcado;
            return this.marcado;
        }


        // retorna -1 se clicar em uma mina
        // retorna o numero de minas nos vizinhos

        public int clicar(){
            this.clicado = true;
            if(this.minado){
                return -1;
            }else{
                return minasNosVizinhos();
            }
        }

        public int minasNosVizinhos(){
            int n = 0;
            for(Celula c: this.vizinhos){
                if(c.minado) n++;
            }
            return n;
        }

        public void reset(){
            this.minado = false;
            this.revelado = false;
            this.marcado = false;
            this.clicado = false;
        }

        public boolean gameOver(){
            if(this.minado && this.marcado) return true;
            if(!this.minado && !this.marcado) return true;
            return false;
        }

        public String toString(){
            if(this.minado){
                return "-1";
            }else
                return "+" + this.minasNosVizinhos();
        }
    }

    static class Tabuleiro{
        Celula[][] matriz;

        public Tabuleiro(){
            matriz = new Celula[Const.NUM_LINHAS][Const.NUM_COLUNAS];
            for(int i=0; i<Const.NUM_LINHAS;i++){
                for(int j=0; j<Const.NUM_COLUNAS;j++){
                    matriz[i][j] = new Celula();
                }
            }
            for(int i=0; i<Const.NUM_LINHAS;i++){
                for(int j=0; j<Const.NUM_COLUNAS;j++){
                    if(i>0){
                        if(j>0) matriz[i][j].AdicionarVizinhos(matriz[i-1][j-1]);
                        matriz[i][j].AdicionarVizinhos(matriz[i-1][j]);
                        if(j<Const.NUM_COLUNAS-1) matriz[i][j].AdicionarVizinhos(matriz[i-1][j+1]);    
                    }

                    if(j>0) matriz[i][j].AdicionarVizinhos(matriz[i][j-1]);
                    if(j<Const.NUM_COLUNAS-1)matriz[i][j].AdicionarVizinhos(matriz[i][j+1]);

                    if(i<Const.NUM_LINHAS-1){
                        if(j>0)matriz[i][j].AdicionarVizinhos(matriz[i+1][j-1]);
                        matriz[i][j].AdicionarVizinhos(matriz[i+1][j]);
                        if(j<Const.NUM_COLUNAS-1)matriz[i][j].AdicionarVizinhos(matriz[i+1][j+1]);
                    }
                }
            }
        }

        public void adicionarMinas(){
            int n = Const.NUM_MINAS;
            Random rand = new Random();
            while(n>0){
                int l = rand.nextInt(Const.NUM_LINHAS);
                int c = rand.nextInt(Const.NUM_COLUNAS);
                if(matriz[l][c].minar()){
                    n--;
                }
            }
        }

        public int clicar(int linha, int coluna){
            return matriz[linha][coluna].clicar();
        }

        public boolean gameOver(){
            for (int i = 0; i < Const.NUM_LINHAS; i++) {
                for (int j = 0; j < Const.NUM_COLUNAS; j++) {
                    if(!matriz[i][j].gameOver()) return false;
                }
            }
            return true;
        }

        public String toString(){
            String str = "";

            for (int i = 0; i < Const.NUM_LINHAS; i++) {
                for (int j = 0; j < Const.NUM_COLUNAS; j++) {
                    str = str + matriz[i][j] + " ";
                }
                str += "\n";
            }
            return str;
        }




    }

    public class Const{
        static int NUM_LINHAS = 6;
        static int NUM_COLUNAS = 6;
        static int NUM_MINAS = 5;
        static int TAM_CELULA = 60;
    }

    static public class Tela extends JFrame{
        JPanel panel;
        JButtonCelula[][] matBut;
        Tabuleiro tabuleiro;

        public Tela(Tabuleiro tabuleiro){
            this.tabuleiro = tabuleiro;
            this.panel = new JPanel();
            panel.setLayout(null);
            this.add(panel);
            matBut = new JButtonCelula[Const.NUM_LINHAS][Const.NUM_COLUNAS];

            int n = 0;
            for (int i = 0; i < Const.NUM_LINHAS; i++) {
                for (int j = 0; j < Const.NUM_COLUNAS; j++) {
                    matBut[i][j] = new JButtonCelula(tabuleiro);
                    matBut[i][j].linha = i;
                    matBut[i][j].coluna = j;
                    matBut[i][j].setSize(50,50);
                    matBut[i][j].setLocation(50*j,50*i);
                    panel.add(matBut[i][j]);
                }
            }
            configInicial();
        }

        public void configInicial(){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(Const.NUM_COLUNAS*Const.TAM_CELULA,Const.NUM_LINHAS*Const.TAM_CELULA);
            this.setResizable(false);
            this.setVisible(true);
        }
    }

    static public class JButtonCelula extends JButton{
        int linha;
        int coluna;
        Tabuleiro tabuleiro;
        String texto;

        public JButtonCelula(Tabuleiro tabuleiro){
            texto = "";
            this.setText(texto);
            this.tabuleiro=tabuleiro;
            this.addActionListener((java.awt.event.ActionEvent evt) -> {
                botaoPressionado(evt);
            });
        }
        
        public void botaoPressionado(java.awt.event.ActionEvent evt){
        System.out.println("Linha: "+ linha + " Coluna: "+ coluna);
        int ret = tabuleiro.clicar(linha, coluna);
        this.texto = Integer.toString(ret);
        this.setText(texto);
        this.setEnabled(false);
    }
    }





    public static void main(String[] args) {        
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.adicionarMinas();
        Tela tela = new Tela(tabuleiro);
        
    }
}
