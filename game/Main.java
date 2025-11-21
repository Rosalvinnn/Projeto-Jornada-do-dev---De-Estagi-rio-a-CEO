package game;

import carreira.Carreira;
import desenvolvedor.Desenvolvedor;
import loja.Loja;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Jornada do Dev — De Estagiário a CEO ===\n");
        System.out.println("\nBEM VINDO À SUA JORNADA DE DESENVOLVEDOR PARA INICIAR:");
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine().trim();
        System.out.println("\n\nVOCÊ INICIA SUA JORNADA EM UMA GRANDE EMPRESA DE TECNOLOGIA.\nRECÉM FORMADO NO CURSO DE CIÊNCIA DA COMPUTAÇÃO VOCÊ DEVE ADQUIRIR HABILIDADES PARA CUMPRIR\nSEU OBJETIVO DE SE TORNAR CEO.\n");

        Desenvolvedor dev = new Desenvolvedor(nome);
        Loja loja = new Loja(dev);
        Carreira carreira = new Carreira(dev, loja);

        boolean running = true;
        while (running) {
            System.out.println("\n==== MENU PRINCIPAL ====");
            System.out.println("1 - Trabalhar em um projeto");
            System.out.println("2 - Estudar (comprar curso na loja)");
            System.out.println("3 - Visitar loja");
            System.out.println("4 - Enfrentar evento/desafio");
            System.out.println("5 - Tentar subir de cargo");
            System.out.println("6 - Ativar/Desativar Dev Noturno");
            System.out.println("7 - Ver status");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            String escolha = sc.nextLine().trim();

            switch (escolha) {
                case "1":
                    carreira.menuProjetos(sc);
                    break;
                case "2":
                    loja.mostrarCursos();
                    System.out.print("Digite o ID do curso para comprar (ou ENTER para cancelar): ");
                    String id = sc.nextLine().trim();
                    if (!id.isEmpty()) loja.comprarCurso(id);
                    break;
                case "3":
                    loja.menuLoja(sc);
                    break;
                case "4":
                    carreira.menuEvento(sc);
                    break;
                case "5":
                    carreira.tentarSubir(sc);
                    break;
                case "6":
                    dev.toggleDevNoturno();
                    break;
                case "7":
                    System.out.println(dev.getStatus());
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            if (dev.getNivel().equals("CEO")) {
                System.out.println("\n🎉 Você alcançou CEO! Parabéns — fim do jogo.");
                running = false;
            }
        }
        System.out.println("\nObrigado por jogar. Nível final: " + dev.getNivel() + " | XP: " + dev.getXp() + " | Dinheiro: $" + dev.getDinheiro());
        sc.close();
    }
}
