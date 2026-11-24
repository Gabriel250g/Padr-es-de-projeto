package controller;
import java.util.List;
import java.util.NoSuchElementException;

import model.Guiche;
import model.PainelAtendimentoService;
import view.PainelView;


public class AtendimentoController{

    private PainelAtendimentoService model;
    private PainelView view;

    public AtendimentoController(PainelAtendimentoService model, PainelView view) {
        this.model = model;
        this.view = view;
    }
    // Método para iniciar o loop principal do sistema, exibindo o menu e processando as opções escolhidas pelo usuário
    public void iniciar(){
        model.registrarGuiche(1, "RECEPCAO");
        model.registrarGuiche(2, "CONSULTORIO");
        model.registrarGuiche(3, "LABORATORIO");
    
        // Loop principal do sistema, exibindo o menu e processando as opções escolhidas pelo usuário
        boolean executando = true;    
        while (executando){
            try{
                int opcao = view.exibirMenu();
                switch (opcao) {
                    case 1:
                        processarEmissaoSenha();
                        break;
                    case 2:
                        processarChamadaGuiche();
                        break;
                    case 3:
                        processarRelatorio();
                        break;
                    case 4:
                        view.exibirMensagem("Encerrando o sistema...");
                        executando = false;
                        break;
                    default:
                        view.exibirMensagem("Opção invalida!");
                }
            } catch (NoSuchElementException e){
                // Entrada encerrada (EOF / Ctrl+D): sai do loop em vez de repetir o erro infinitamente
                view.exibirMensagem("Entrada encerrada. Encerrando o sistema...");
                executando = false;
            } catch (Exception e){
                view.exibirMensagem("Erro na operação: " + e.getMessage());
            }
        }
    }
    // Método para processar a emissão de uma nova senha, solicitando informações ao usuário e exibindo o ticket emitido
    private void processarEmissaoSenha(){
        String nome = view.pedirTexto("Nome do paciente");
        String tipo = view.pedirTexto("Tipo de atendimento (COMUM, PREFERENCIAL, EXAME, EMERGENCIA)");
        // Chama o método do modelo para emitir a senha e exibe o ticket emitido
        String ticket = model.emitirSenha(tipo, nome);
        view.exibirTicketemitido(ticket);
    }
    // Método para processar a chamada do próximo paciente em um guichê, solicitando o número do guichê ao usuário e exibindo a chamada no painel de TV
    private void processarChamadaGuiche(){
        int numeroGuiche = view.pedirNumero("Numero do guiche que esta chamando (1, 2 ou 3)");
        Guiche guiche = model.buscarGuichePorNumero(numeroGuiche);
        if (guiche == null){
            view.exibirMensagem("Guiche " + numeroGuiche + " não cadastrado.");
            return;
        }
        // Chama o método do modelo para chamar a próxima senha e exibe a chamada no painel de TV
        String proximaSenha = model.chamarProximaSenha(numeroGuiche);
        if (proximaSenha == null){
            view.exibirMensagem("Nenhum paciente aguardando nas filas.");
        } else{
            view.exibirChamadaPainelTv(proximaSenha, guiche.getIdentificacaoSetor());
        }

    }
    // Método para processar a exibição do relatório do dia, incluindo informações sobre os guichês e filas
    private void processarRelatorio(){
        List<Guiche> guiches = model.getguichesRegistrados();
        int totalAtendidos = 0;
        for (Guiche g : guiches){
            totalAtendidos += g.getTotalAtendimentos(); 
        } 
        // Chama o método da view para exibir o relatório do dia, passando as informações necessárias
        view.exibirRelatorio(
            guiches, 
            totalAtendidos, 
            model.getQtdeFilaComum(), 
            model.getQtdeFilaPreferencial()
        );
    }
}   