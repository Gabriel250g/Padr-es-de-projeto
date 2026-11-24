package model;
public class SenhaExame extends Senha {
    public SenhaExame(int numero, String nomeCliente){
        super(numero, nomeCliente);
    }

    @Override 
    public String formatarSenha(){
        return "EX-" + getNumero() + " | " + getNomeCliente().toUpperCase();
    }
    @Override
    public boolean isPrioritaria() {
        return false;
    }
}
