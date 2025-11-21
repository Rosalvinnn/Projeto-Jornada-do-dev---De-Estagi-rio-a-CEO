package desenvolvedor;

import projetos.Projeto;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Desenvolvedor {
    private String nome;
    private String nivel; // Junior, Pleno, Senior, CEO
    private int xp;
    private int dinheiro;
    private ArrayList<String> habilidades = new ArrayList<>();
    private boolean devNoturnoAtivo = false;

    public Desenvolvedor(String nome) {
        this.nome = nome;
        this.nivel = "Junior";
        this.xp = 0;
        this.dinheiro = 200; // dinheiro inicial

        if (nome.equalsIgnoreCase("Lucas")) {
            this.dinheiro = 5000;
            System.out.println("Esse nome é muito interessante...");
        }
    }

    public void toggleDevNoturno() {
        devNoturnoAtivo = !devNoturnoAtivo;
        System.out.println(devNoturnoAtivo ? "🔥 Modo Dev Noturno ativado! XP extra por trabalhar de madrugada!" : "🌞 Modo Dev Noturno desativado.");
    }

public void trabalharEmProjeto(Projeto p, Scanner sc) {
    p.concluirProjeto();

    System.out.println("\nComo deseja resolver este projeto?");
    System.out.println("1 - Fazer rápido (chance de erro: 40%) | +10 XP se acertar");
    System.out.println("2 - Fazer com calma (garantido) | sem bônus");
    System.out.println("3 - Pesquisar antes (custa 20$) | +20 XP extra garantido");
    System.out.print("Escolha: ");
    String escolha = sc.nextLine().trim();

    int bonusXP = 0;

    switch (escolha) {
        case "1":
            if (new Random().nextInt(100) < 60) {
                System.out.println("Feito rápido e funcionou!");
                bonusXP += 10;
            } else {
                System.out.println("Erro no caminho rápido! -10 XP");
                bonusXP -= 10;
            }
            break;

        case "2":
            System.out.println("🙂 Feito com calma, sem bônus.");
            break;

        case "3":
            if (dinheiro >= 20) {
                System.out.println("📚 Você pesquisou bastante! +20 XP extra.");
                dinheiro -= 20;
                bonusXP += 20;
            } else {
                System.out.println("💸 Dinheiro insuficiente para pesquisar.");
            }
            break;

        default:
            System.out.println("Opção inválida. Nenhum bônus aplicado.");
    }

    // ganhos reais
    int ganhoXP = p.getRecompensa() + bonusXP;
    int ganhoDinheiro = p.getRecompensa() / 2 + p.getDificuldade()*10;

    if (devNoturnoAtivo) {
        ganhoXP += 20;
        ganhoDinheiro += 10;
        System.out.println("🌙 Bônus Dev Noturno aplicado!");
    }

    xp += ganhoXP;
    dinheiro += ganhoDinheiro;

    System.out.println("💼 Projeto concluído! XP final ganho: " + ganhoXP + ", Dinheiro: $" + ganhoDinheiro);
}

    public void estudar(String habilidade, int custo) {
        if (dinheiro < custo) {
            System.out.println("Saldo insuficiente para comprar o curso. Custo: $" + custo);
            return;
        }
        dinheiro -= custo;
        habilidades.add(habilidade);
        xp += 15;
        System.out.println("📘 Curso adquirido: " + habilidade + " | XP +15");
    }

    public void adicionarHabilidade(String h) {
        if (!habilidades.contains(h)) habilidades.add(h);
    }

    public void perderXp(int quantidade) {
        xp += quantidade; // quantidade pode ser negativa
        System.out.println("XP perdido/ganho: " + quantidade);
        if (xp < 0) xp = 0;
    }

    public void adicionarDinheiro(int valor) {
        dinheiro += valor;
    }

    public void subirDeCargo() throws Exception {
        switch (nivel) {
            case "Junior":
                if (xp < 100) throw new Exception("XP insuficiente para virar Pleno!");
                nivel = "Pleno";
                System.out.println("🏆 Promoção! Agora você é Pleno.");
                break;
            case "Pleno":
                if (xp < 250) throw new Exception("XP insuficiente para virar Sênior!");
                nivel = "Senior";
                System.out.println("🏆 Promoção! Agora você é Sênior.");
                break;
            case "Senior":
                if (xp < 500) throw new Exception("XP insuficiente para virar CEO!");
                nivel = "CEO";
                System.out.println("🏆 Promoção máxima! Agora você é CEO.");
                break;
            case "CEO":
                System.out.println("Você já é CEO!");
                break;
        }
    }

public int getXpNecessarioParaProximoCargo() {
    switch (nivel) {
        case "Junior":
            return Math.max(0, 100 - xp);   
        case "Pleno":
            return Math.max(0, 250 - xp);   
        case "Senior":
            return Math.max(0, 500 - xp);   
        case "CEO":
            return 0;                       
    }
    return 0;
}

    public String getNivel() { return nivel; }
    public int getXp() { return xp; }
    public int getDinheiro() { return dinheiro; }
    public String getNome() { return nome; }
    public ArrayList<String> getHabilidades() { return habilidades; }

    public String getStatus() {
    int falta = getXpNecessarioParaProximoCargo();
    String msgFalta = nivel.equals("CEO") ? "Você já atingiu o nível máximo!" :
            "XP restante para o próximo cargo: " + falta;

    return String.format(
        "Nome: %s | Nível: %s | XP: %d | Dinheiro: $%d | Habilidades: %s\n%s",
        nome, nivel, xp, dinheiro, habilidades.toString(), msgFalta);
    }
}

