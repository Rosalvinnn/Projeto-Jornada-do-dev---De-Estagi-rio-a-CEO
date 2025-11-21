package projetos;

public class ProjetoWeb extends Projeto {
    public ProjetoWeb() {
        super("Site Institucional", 1, 40, "Web");
    }

    @Override
    public void concluirProjeto() {
        System.out.println("Você desenvolveu um site moderno e responsivo!");
    }
}
