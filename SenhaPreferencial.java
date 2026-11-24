package model;
public class SenhaPreferencial extends Senha {
    public SenhaPreferencial(int numero, String nomeCliente){
        super(numero, nomeCliente);
    }

    @Override 
    public String formatarSenha(){
        return "P-" + getNumero() + " | " + getNomeCliente().toUpperCase();
    }
    @Override
    public boolean isPrioritaria() {
        return true;
    }
}
