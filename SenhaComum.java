package model;
public class SenhaComum extends Senha {
    public SenhaComum(int numero, String nomeCliente){
        super(numero, nomeCliente);
    }

    @Override 
    public String formatarSenha(){
        return "C-" + getNumero() + " | " + getNomeCliente().toUpperCase();
    }
    @Override
    public boolean isPrioritaria() {
        return false;
    }
}
