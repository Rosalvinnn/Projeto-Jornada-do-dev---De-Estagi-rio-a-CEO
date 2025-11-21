package carreira;

import desenvolvedor.Desenvolvedor;
import projetos.*;
import desafios.Evento;
import loja.Loja;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Carreira {
    private Desenvolvedor dev;
    private ArrayList<Projeto> projetos = new ArrayList<>();
    private ArrayList<Evento> eventos = new ArrayList<>();
    private Random random = new Random();
    private Loja loja;

    public Carreira(Desenvolvedor dev, Loja loja) {
        this.dev = dev;
        this.loja = loja;
        projetos.add(new ProjetoWeb());
        projetos.add(new ProjetoMobile());
        projetos.add(new ProjetoIA());

        eventos.add(new Evento("Hackathon com prêmio", +80, +300, true));
        eventos.add(new Evento("Bug crítico em produção", -30, -200, false));
        eventos.add(new Evento("Entrevista de emprego", +50, 0, true));
        eventos.add(new Evento("Cliente insatisfeito", -15, -50, false));
    }

    public void menuProjetos(Scanner sc) {
        System.out.println("\n--- Projetos Disponíveis ---");
        for (int i = 0; i < projetos.size(); i++) {
            System.out.println(i + " - " + projetos.get(i));
        }
        System.out.print("Escolha o ID do projeto (ou ENTER para cancelar): ");
        String line = sc.nextLine().trim();
        if (line.isEmpty()) return;
        try {
            int id = Integer.parseInt(line);
            if (id < 0 || id >= projetos.size()) {
                System.out.println("Projeto inexistente.");
                return;
            }
            dev.trabalharEmProjeto(projetos.get(id), sc);
            // chance de evento após projeto
            if (random.nextInt(100) < 40) {
                Evento e = eventos.get(random.nextInt(eventos.size()));
                System.out.println("Evento apareceu: " + e.getDescricao());
                if (e.isOpcional()) {
                    System.out.print("Deseja enfrentar? (s/n): ");
                    String r = sc.nextLine().trim();
                    if (r.equalsIgnoreCase("s")) e.aplicarEnfrentar(dev, sc);
                    else e.aplicarIgnorar(dev);
                } else {
                    System.out.println("Evento obrigatório. Aplicando consequência...");
                    e.aplicarEnfrentar(dev, sc);
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("Entrada inválida.");
        }
    }

    public void menuEvento(Scanner sc) {
        Evento e = eventos.get(random.nextInt(eventos.size()));
        System.out.println("Evento aleatório: " + e.getDescricao());
        if (e.isOpcional()) {
            System.out.print("Enfrentar? (s/n): ");
            String r = sc.nextLine().trim();
            if (r.equalsIgnoreCase("s")) e.aplicarEnfrentar(dev, sc);
            else e.aplicarIgnorar(dev);
        } else {
            System.out.println("Evento obrigatório. Aplicando...");
            e.aplicarEnfrentar(dev, sc);
        }
    }

    public void tentarSubir(Scanner sc) {
        try {
            dev.subirDeCargo();
        } catch (Exception ex) {
            System.out.println("➡ Evolução pausada: " + ex.getMessage());
            System.out.print("Deseja tentar cumprir requisitos agora estudando/aceitando eventos? (s/n): ");
            String r = sc.nextLine().trim();
            if (r.equalsIgnoreCase("s")) {
                System.out.println("Tente ganhar mais XP com projetos ou visite a loja para cursos.");
            }
        }
    }
}
