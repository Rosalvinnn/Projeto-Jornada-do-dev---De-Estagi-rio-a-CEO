package loja;

import desenvolvedor.Desenvolvedor;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Loja {
    private Desenvolvedor dev;
    private Map<String, Curso> cursos = new HashMap<>();

    public Loja(Desenvolvedor dev) {
        this.dev = dev;
        cursos.put("C1", new Curso("Algoritmos e Estruturas", 80, "Algoritmos"));
        cursos.put("C2", new Curso("Desenvolvimento Mobile", 120, "Mobile" ));
        cursos.put("C3", new Curso("Machine Learning Básico", 180, "IA" ));
        cursos.put("C4", new Curso("Testes e Qualidade", 60, "Testes" ));
    }

    public void mostrarCursos() {
        System.out.println("\n--- Cursos Disponíveis ---");
        for (String id : cursos.keySet()) {
            Curso c = cursos.get(id);
            System.out.println(id + " - " + c.nome + " | Preço: $" + c.preco + " | Habilidade: " + c.habilidade);
        }
    }

    public void comprarCurso(String id) {
        if (!cursos.containsKey(id)) {
            System.out.println("Curso inexistente.");
            return;
        }
        Curso c = cursos.get(id);
        dev.estudar(c.habilidade, c.preco);
    }

    public void menuLoja(Scanner sc) {
        mostrarCursos();
        System.out.print("Digite o ID do curso para comprar (ou ENTER para voltar): ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) return;
        comprarCurso(id);
    }

    private static class Curso {
        String nome;
        int preco;
        String habilidade;
        Curso(String nome, int preco, String habilidade) {
            this.nome = nome; this.preco = preco; this.habilidade = habilidade;
        }
    }
}
