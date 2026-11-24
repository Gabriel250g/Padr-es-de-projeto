package model;

public abstract class Guiche{
        private int numero;
        private int totalAtendimentos;
        
        public Guiche(int numero){
            this.numero = numero;
            this.totalAtendimentos = 0;
        }

        public int getNumero(){
            return numero;
        }
        public int getTotalAtendimentos(){
            return totalAtendimentos;
        }
        
        public void registrarAtendimento() {
            this.totalAtendimentos++; // Incrementa o total de atendimentos
        }
        public abstract String getIdentificacaoSetor();
    }