import javax.swing.JButton;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;

public class JButtonCelula extends JButton {
        int linha;
        int coluna;
        Tabuleiro tabuleiro;
        Celula c;
        Tela tabuleiroGrafico;
        String texto;

        public JButtonCelula(Tabuleiro tabuleiro, Tela tg){
            
            this.tabuleiroGrafico = tg;
            this.texto = "";
            this.setText(texto);
            this.tabuleiro=tabuleiro;
            this.addActionListener((java.awt.event.ActionEvent evt) -> {
                botaoPressionado(evt);
            });
            
            this.addMouseListener(new java.awt.event.MouseAdapter(){
                public void mousePressed(MouseEvent e){
                 if(SwingUtilities.isRightMouseButton(e)){
                    botaoDireiroPressionado(e);
                 }
                }
            });
        }
        
        public void botaoPressionado(java.awt.event.ActionEvent evt){
            this.clicar();
        
        }

        public void clicar(){
            System.out.println("Linha: "+linha+"  Coluna: "+coluna);

            if(tabuleiro.getMarcado(linha, coluna)==false){
            if(tabuleiro.getMinado(linha, coluna) == true){
                this.tabuleiroGrafico.revelarMinas();

            
        }else{
            int ret = tabuleiro.clicar(linha, coluna);
            this.tabuleiroGrafico.checkGanhou();

            if(ret == 0){
                for(Celula vizinho : c.vizinhos){
                    if(!vizinho.clicado)
                    vizinho.button.clicar();  
                }
            }

            this.texto = Integer.toString(ret);
            this.revela(this.texto);
        }
        }
        

        }

        public void botaoDireiroPressionado(java.awt.event.MouseEvent evt){
            if(tabuleiro.getClicado(linha, coluna)==false){
                if(evt.getButton() == MouseEvent.BUTTON3){
                boolean ret = tabuleiro.marcar(linha, coluna);
                this.tabuleiroGrafico.checkGanhou();
                if(ret == true){
                    this.setText("|>");
                    this.setEnabled(false);
                }else this.setText(" ");
                      this.setEnabled(true);
            }
            }
            
        }

        public void setPos(int linha, int coluna){
            this.linha = linha;
            this.coluna = coluna;
            this.c = tabuleiro.getCelula(linha, coluna);
        }

        public void revela(String cod){
            this.setText(cod);
            this.setEnabled(false);
        }

    
}
    