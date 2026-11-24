package model;
public class GuicheRecepcao extends Guiche {
    public GuicheRecepcao(int numero) {
        super(numero);
    }

    @Override
    public String getIdentificacaoSetor() {
        return "Guichê " + getNumero() + " - Recepção";
    }
}
