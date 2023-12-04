import java.util.ArrayList;

public class Celula {
        boolean minado; //possui uma mina
        boolean revelado; // está revelado
        boolean marcado; // está marcado com a bandeira
        boolean clicado; // foi clicado

        ArrayList<Celula> vizinhos;

        JButtonCelula button;

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
                gameOver();
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

        public void setButton(JButtonCelula button){
            this.button = button;
        }


        public void reset(){
            this.minado = false;
            this.revelado = false;
            this.marcado = false;
            this.clicado = false;
        }

        public boolean gameOver(){
            if(this.minado && this.marcado){
                return true;
            }
            if(!this.minado && !this.marcado && this.clicado){
                return true;
            }
            return false;
        }

        public String toString(){
            if(this.minado){
                return "-1";
            }else
                return "+" + this.minasNosVizinhos();
        }
}
