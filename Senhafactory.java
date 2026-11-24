package model;
public class Senhafactory {
    public static Senha criarSenha(String tipo, int numero, String nomeCliente){
        if (tipo.equalsIgnoreCase("PREFERENCIAL")){
            return new SenhaPreferencial(numero, nomeCliente);
        }
        else if (tipo.equalsIgnoreCase("COMUM")){
            return new SenhaComum(numero, nomeCliente);
        }
        else if (tipo.equalsIgnoreCase("EXAME")){
            return new SenhaExame(numero, nomeCliente);
        }
        else if (tipo.equalsIgnoreCase("EMERGENCIA")){
            return new SenhaEmergencia(numero, nomeCliente);
        }
        else{
            throw new IllegalArgumentException("Tipo de senha invalido: " + tipo);
        }
    }
}
