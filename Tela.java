import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tela extends JFrame {
        JPanel panel;
        JButtonCelula[][] matBut;
        Tabuleiro tabuleiro;

        public Tela(Tabuleiro tabuleiro){
            this.tabuleiro = tabuleiro;
            this.panel = new JPanel();
            panel.setLayout(null);
            this.add(panel);
            matBut = new JButtonCelula[Const.NUM_LINHAS][Const.NUM_COLUNAS];

            
            for (int i = 0; i < Const.NUM_LINHAS; i++) {
                for (int j = 0; j < Const.NUM_COLUNAS; j++) {
                    matBut[i][j] = new JButtonCelula(tabuleiro,this);
                    tabuleiro.getCelula(i, j).setButton(matBut[i][j]);
                    matBut[i][j].setPos(i, j);
                    matBut[i][j].setSize(50,50);
                    matBut[i][j].setLocation(50*j,50*i);
                    panel.add(matBut[i][j]);
                }
            }
            configInicial();
        }

        public void configInicial(){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(Const.NUM_COLUNAS*Const.TAM_CELULA+20,Const.NUM_LINHAS*Const.TAM_CELULA+40);
            this.setResizable(false);
            this.setVisible(true);
        }

        public void revelarMinas(){
            for (int i = 0; i < Const.NUM_LINHAS; i++) {
                for (int j = 0; j < Const.NUM_COLUNAS; j++) {
                    if(matBut[i][j].c.minado){
                        matBut[i][j].revela("-1");
                    }
                }
            }
        }

        public void checkGanhou(){
            if(this.tabuleiro.gameOver()){
                System.out.println("Ganhou!!");
            }
            
        }
}
