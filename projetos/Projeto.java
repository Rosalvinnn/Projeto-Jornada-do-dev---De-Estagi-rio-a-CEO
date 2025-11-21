package projetos;

public abstract class Projeto {
    protected String nome;
    protected int dificuldade;
    protected int recompensaEmXP;
    protected String tipo;

    public Projeto(String nome, int dificuldade, int recompensaEmXP, String tipo) {
        this.nome = nome;
        this.dificuldade = dificuldade;
        this.recompensaEmXP = recompensaEmXP;
        this.tipo = tipo;
    }

    public abstract void concluirProjeto();

    public int getRecompensa() { return recompensaEmXP; }
    public int getDificuldade() { return dificuldade; }

    @Override
    public String toString() {
        return String.format("[%s] %s (XP: %d, Dif: %d)", tipo, nome, recompensaEmXP, dificuldade);
    }
}
