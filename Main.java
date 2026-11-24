import controller.AtendimentoController;
import model.PainelAtendimentoService;
import view.PainelView;

public class Main {
    public static void main(String[] args) {
        // 1. Obtém o Model (Singleton)
        PainelAtendimentoService model = PainelAtendimentoService.getInstancia();

        // 2. Instancia a View
        PainelView view = new PainelView();

        // 3. Conecta ambos no Controller e inicia
        AtendimentoController controller = new AtendimentoController(model, view);
        controller.iniciar();
        
    }
}