package model;
public abstract class Senha{
        private int numero;
        private String nomeCliente;
        
        public Senha(int numero, String nomeCliente){
            this.numero = numero;
            this.nomeCliente = nomeCliente;
        }

        public int getNumero(){return numero;}
        public String getNomeCliente(){return nomeCliente;}
        
        public abstract String formatarSenha();
        public abstract boolean isPrioritaria();
    }