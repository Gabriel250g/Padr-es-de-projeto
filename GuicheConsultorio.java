package model;
public class GuicheConsultorio extends Guiche {
    public GuicheConsultorio(int numero) {
        super(numero);
    }

    @Override
    public String getIdentificacaoSetor() {
        return "Guichê " + getNumero() + " - Consultório";
    }
}
