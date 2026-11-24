package model;
public class GuicheLaboratorio extends Guiche {
    public GuicheLaboratorio(int numero) {
        super(numero);
    }

    @Override
    public String getIdentificacaoSetor() {
        return "Guichê " + getNumero() + " - Laboratório";
    }
}
