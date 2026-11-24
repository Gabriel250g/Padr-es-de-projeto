package model;
import java.util.ArrayList;
import java.util.List;

public class PainelAtendimentoService {

    // Instância única para o padrão Singleton
    private static PainelAtendimentoService instancia;

    // Filas de atendimento
    private List<String> filaComum;
    private List<String> filaPreferencial;

    // lista simples de guiches
    private List<Guiche> guichesRegistrados;


    // Contadores de controle
    private int contadorGeral;
    private int contagemPreferenciaisSeguidos;


    
    // Construtor privado: impede a criação direta com "new" fora da classe
    private PainelAtendimentoService() {
        this.filaComum = new ArrayList<>();
        this.filaPreferencial = new ArrayList<>();
        //inicializa os guichesRegistrados dentro do array
        this.guichesRegistrados = new ArrayList<>();

        this.contadorGeral = 0;
        this.contagemPreferenciaisSeguidos = 0;
    }

    // Ponto global de acesso à instância única (Singleton)
    public static PainelAtendimentoService getInstancia() {
        if (instancia == null) {
            instancia = new PainelAtendimentoService();
        }
        return instancia;
    }

    // Método para registrar um novo guichê usando a fábrica
    public void registrarGuiche(int numero, String tipoSetor){
        // Cria o guichê usando a fábrica e adiciona à lista de guichês registrados
        Guiche novoGuiche = Guichefactory.criarGuiche(numero, tipoSetor);
        this.guichesRegistrados.add(novoGuiche);
    }

    // Método privado para buscar um guichê pelo número
    public Guiche buscarGuichePorNumero(int numero){
        // Percorre a lista de guichês registrados para encontrar o correspondente
        for (Guiche guiche : this.guichesRegistrados) {
            // Compara o número do guichê com o número fornecido
            if (guiche.getNumero() == numero) {
                return guiche;
            }
        }
        return null; // Retorna null se não encontrar o guichê
    }

    // Emissão de senha usando o Factory Method e Polimorfismo
    public String emitirSenha(String tipoAtendimento, String nomeCliente) {
        // 1. A fábrica instancia a subclasse correspondente
        // O contador só avança se a senha for criada com sucesso (tipo inválido não consome número)
        Senha novaSenha = Senhafactory.criarSenha(tipoAtendimento, this.contadorGeral + 1, nomeCliente);
        this.contadorGeral++;

        // 2. O próprio objeto formata o texto do bilhete
        String ticketFormatado = novaSenha.formatarSenha();

        // 3. O próprio objeto responde se é prioritário (Polimorfismo)
        if (novaSenha.isPrioritaria()) {
            this.filaPreferencial.add(ticketFormatado);
        } else {
            this.filaComum.add(ticketFormatado);
        }
        
         //O System.out.println("Ticket emitido: " + ticketFormatado); não interagirá com o usuário
        return ticketFormatado;

    }

    // Chamada de senha para atendimento
    public String chamarProximaSenha(int numeroGuiche) {
        // Busca o guichê correspondente pelo número
        Guiche guiche = buscarGuichePorNumero(numeroGuiche);

        if (guiche == null) {
            return null; // A view/controller informam o usuário
        }

        String proxima = null;
        // Regra: até 2 prioritárias seguidas antes de chamar uma comum
        boolean podeChamarPreferencial = !this.filaPreferencial.isEmpty() && this.contagemPreferenciaisSeguidos < 2;
        
        if (podeChamarPreferencial) {
            proxima = this.filaPreferencial.remove(0);
            this.contagemPreferenciaisSeguidos++;
        } else if (!this.filaComum.isEmpty()) {
            proxima = this.filaComum.remove(0);
            this.contagemPreferenciaisSeguidos = 0; // Reinicia o ciclo
        } else if (!this.filaPreferencial.isEmpty()) {
            proxima = this.filaPreferencial.remove(0);
        }
        //Não haverá mais interações com o usuario. Apenas a view a fará.
        // Se não há senhas aguardando em nenhuma fila
        /*  if (proxima == null) {
            System.out.println("Guichê " + numeroGuiche + ": Nenhuma senha aguardando.");
            return;
        }
        guiche.registrarAtendimento();
        System.out.println("\n-------------------------------------------");
        // Exibe a senha chamada e o guichê correspondente
        System.out.println(">>> PAINEL TV: " + proxima + " COMPARECER AO GUICHÊ " + guiche.getIdentificacaoSetor().toUpperCase());
        System.out.println("-------------------------------------------\n");
        */

        if (proxima != null){
            guiche.registrarAtendimento();
        }
        return proxima;
    }

    /*  Relatório final com o balanço dos atendimentos
    public void relatorioSenhas() {
        int totalAtendidos = 0;
        System.out.println("\n===========================================");
        System.out.println("           RELATÓRIO DO DIA                ");
        System.out.println("===========================================");
        // Percorre a lista de guichês registrados e exibe o total de atendimentos de cada um
        for (Guiche guiche : this.guichesRegistrados) {
            System.out.println(" • " + guiche.getIdentificacaoSetor() + " atendeu: " + guiche.getTotalAtendimentos() + " clientes");
            // Acumula o total de atendimentos realizados por todos os guichês
            totalAtendidos += guiche.getTotalAtendimentos();
        }
        System.out.println("-------------------------------------------");
        System.out.println("Total de atendimentos realizados:        " + totalAtendidos);
        System.out.println("Senhas restantes na fila Comum:          " + this.filaComum.size());
        System.out.println("Senhas restantes na fila Preferencial:   " + this.filaPreferencial.size());
        System.out.println("===========================================\n");
    }
    */

    public List<Guiche> getguichesRegistrados(){
        return this.guichesRegistrados;
    }

    public int getQtdeFilaComum(){
        return this.filaComum.size();
    }

    public int getQtdeFilaPreferencial(){
        return this.filaPreferencial.size();
    }
}