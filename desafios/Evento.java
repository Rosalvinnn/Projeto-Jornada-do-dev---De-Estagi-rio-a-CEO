package desafios;

import desenvolvedor.Desenvolvedor;
import java.util.Random;
import java.util.Scanner;

public class Evento {
    private String descricao;
    private int impactoXp;
    private int impactoDinheiro;
    private boolean opcional;

    public Evento(String descricao, int impactoXp, int impactoDinheiro, boolean opcional) {
        this.descricao = descricao;
        this.impactoXp = impactoXp;
        this.impactoDinheiro = impactoDinheiro;
        this.opcional = opcional;
    }

    public String getDescricao() { return descricao; }
    public boolean isOpcional() { return opcional; }

    public void aplicarEnfrentar(Desenvolvedor d, Scanner sc) {
        System.out.println("\n Evento: " + descricao);
        System.out.println("Como deseja reagir?");
        System.out.println("1 - Enfrentar diretamente (resultado padrão)");
        System.out.println("2 - Tentar solução alternativa (50% bom, 50% ruim)");
        System.out.println("3 - Usar uma habilidade (+20 XP extra, se tiver)");
        System.out.println("4 - Pedir ajuda (custa 30$, evita impacto)");
        System.out.print("Escolha: ");

        String escolha = sc.nextLine().trim();
        Random rand = new Random();

        switch (escolha) {
            case "1":
                aplicarImpactoPadrao(d);
                break;

            case "2":
                if (rand.nextInt(100) < 50) {
                    System.out.println("A solução alternativa funcionou! +30 XP");
                    d.perderXp(+30);
                } else {
                    System.out.println("A alternativa deu errado! -30 XP e -50$");
                    d.perderXp(-30);
                    d.adicionarDinheiro(-50);
                }
                break;

            case "3":
                if (!d.getHabilidades().isEmpty()) {
                    System.out.println("Você usou suas habilidades! +20 XP extra.");
                    d.perderXp(+20);
                }
                aplicarImpactoPadrao(d);
                break;

            case "4":
                if (d.getDinheiro() >= 30) {
                    System.out.println("Você contratou ajuda e evitou o impacto.");
                    d.adicionarDinheiro(-30);
                } else {
                    System.out.println("Sem dinheiro, impacto padrão aplicado.");
                    aplicarImpactoPadrao(d);
                }
                break;

            default:
                System.out.println("Opção inválida. Resultado padrão.");
                aplicarImpactoPadrao(d);
        }
    }

    private void aplicarImpactoPadrao(Desenvolvedor d) {
        d.perderXp(impactoXp);
        d.adicionarDinheiro(impactoDinheiro);
    }

    public void aplicarIgnorar(Desenvolvedor d) {
        System.out.println("Você ignorou o evento. Nada muda… por enquanto.");
    }
}
