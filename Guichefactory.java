package model;
public class Guichefactory {
    public static Guiche criarGuiche(int numero, String tipoSetor){
        if (tipoSetor == null){
            throw new IllegalArgumentException("O setor do guiche não pode ser nulo");
        }

        if (tipoSetor.equalsIgnoreCase("RECEPCAO")){
            return new GuicheRecepcao(numero);
        }
        else if (tipoSetor.equalsIgnoreCase("CONSULTORIO")|| tipoSetor.equalsIgnoreCase("EMERGENCIA")){
            return new GuicheConsultorio(numero);
        }
        else if (tipoSetor.equalsIgnoreCase("LABORATORIO")|| tipoSetor.equalsIgnoreCase("EXAME")){
            return new GuicheLaboratorio(numero);
        }
        else{
            throw new IllegalArgumentException("Tipo de setor invalido: " + tipoSetor);
        }
    }
}
