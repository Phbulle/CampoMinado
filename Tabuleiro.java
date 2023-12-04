import java.util.Random;

public class Tabuleiro {
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

        public boolean marcar(int linha, int coluna){
            return matriz[linha][coluna].marcar();
            
        }

        public boolean getMinado(int linha, int coluna){
            return matriz[linha][coluna].minado;
        }

        public boolean getClicado(int linha,int coluna){
            return matriz[linha][coluna].clicado;
        }

        public boolean getMarcado(int linha,int coluna){
            return matriz[linha][coluna].marcado;
        }

        public int getNumVizinhosMinados(int linha,int coluna){
            return matriz[linha][coluna].minasNosVizinhos();
        }

        public boolean gameOver(){
            for (int i = 0; i < Const.NUM_LINHAS; i++) {
                for (int j = 0; j < Const.NUM_COLUNAS; j++) {
                    if(!matriz[i][j].gameOver()) return false;
                }
            }
            return true;
        }

        public Celula getCelula(int linha, int coluna){
            return matriz[linha][coluna];
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
