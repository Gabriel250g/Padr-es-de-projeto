package model;
public class SenhaEmergencia extends Senha {
    public SenhaEmergencia(int numero, String nomeCliente){
        super(numero, nomeCliente);
    }

    @Override 
    public String formatarSenha(){
        return "EM-" + getNumero() + " | " + getNomeCliente().toUpperCase();
    }
    @Override
    public boolean isPrioritaria() {
        return true;
    }
}
