package br.com.pdfmanager;

import br.com.pdfmanager.core.GerenciadorPDF;
import br.com.pdfmanager.model.Documento;
import br.com.pdfmanager.model.Exercicio;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GerenciadorPDF gerenciador = new GerenciadorPDF();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Gerenciador de PDF ---");
            System.out.println("1. Criar biblioteca");
            System.out.println("2. Alternar biblioteca");
            System.out.println("3. Listar bibliotecas");
            System.out.println("4. Deletar biblioteca");
            System.out.println("5. Adicionar documento");
            System.out.println("6. Listar documentos da biblioteca atual");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> criarBiblioteca();
                case "2" -> alternarBiblioteca();
                case "3" -> gerenciador.listarBibliotecas();
                case "4" -> deletarBiblioteca();
                case "5" -> adicionarDocumento();
                case "6" -> gerenciador.listarDocumentosBibliotecaAtual();
                case "0" -> {
                    System.out.println("Encerrando.");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void criarBiblioteca() {
        System.out.print("Nome da biblioteca: ");
        String nome = scanner.nextLine();
        System.out.print("Caminho do diretório: ");
        String path = scanner.nextLine();
        gerenciador.criarBiblioteca(nome, path);
    }

    private static void alternarBiblioteca() {
        System.out.print("Nome da biblioteca: ");
        String nome = scanner.nextLine();
        gerenciador.alternarBiblioteca(nome);
    }

    private static void deletarBiblioteca() {
        System.out.print("Nome da biblioteca: ");
        String nome = scanner.nextLine();
        gerenciador.deletarBiblioteca(nome);
    }

    private static void adicionarDocumento() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor(es) (separado por vírgula): ");
        String[] autores = scanner.nextLine().split(",");

        System.out.print("Disciplina: ");
        String disciplina = scanner.nextLine();

        System.out.print("Instituição: ");
        String instituicao = scanner.nextLine();

        System.out.print("Caminho do arquivo PDF: ");
        String caminho = scanner.nextLine();

        List<String> autoresList = new ArrayList<>();
        for (String autor : autores) {
            autoresList.add(autor.trim());
        }

        Documento doc = new Exercicio(autoresList, titulo, disciplina, instituicao, caminho);
        gerenciador.adicionarDocumentoBibliotecaAtual(doc);
    }
}
