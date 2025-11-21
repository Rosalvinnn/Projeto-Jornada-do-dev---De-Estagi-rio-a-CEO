package projetos;

public class ProjetoMobile extends Projeto {
    public ProjetoMobile() {
        super("App Mobile", 2, 60, "Mobile");
    }

    @Override
    public void concluirProjeto() {
        System.out.println("📱 App Mobile criado com sucesso!");
    }
}
