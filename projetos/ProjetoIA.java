package projetos;

public class ProjetoIA extends Projeto {
    public ProjetoIA() {
        super("IA de Recomendação", 3, 90, "IA");
    }

    @Override
    public void concluirProjeto() {
        System.out.println("🤖 Inteligência Artificial treinada com sucesso!");
    }
}
