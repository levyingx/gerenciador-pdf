package br.com.pdfmanager.core;

import br.com.pdfmanager.model.Documento;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por gerenciar múltiplas bibliotecas.
 */
public class GerenciadorPDF {
    private Map<String, Biblioteca> bibliotecas;
    private Biblioteca bibliotecaAtual;
    private static final String ARQUIVO_BIBLIOTECAS = "pdf-manager/src/main/java/br/com/pdfmanager/resources/gerenciador.bin";

    public GerenciadorPDF() {
        this.bibliotecas = new HashMap<>();
        carregarBibliotecas();
    }

    /**
     * Cria uma nova biblioteca e adiciona ao gerenciador.
     * @param nome Nome da biblioteca.
     * @param path Caminho onde a biblioteca será armazenada.
     */
    public void criarBiblioteca(String nome, String path) {
        if (bibliotecas.containsKey(nome)) {
            System.out.println("Biblioteca já existe.");
            return;
        }

        Biblioteca nova = new Biblioteca(nome, path);
        bibliotecas.put(nome, nova);
        bibliotecaAtual = nova;
        salvarBibliotecas();
        System.out.println("Biblioteca criada e selecionada: " + nome);
    }

    /**
     * Alterna para uma biblioteca já existente.
     * @param nome Nome da biblioteca a ser selecionada.
     */
    public void alternarBiblioteca(String nome) {
        Biblioteca b = bibliotecas.get(nome);
        if (b == null) {
            System.out.println("Biblioteca não encontrada.");
        } else {
            bibliotecaAtual = b;
            System.out.println("Biblioteca atual: " + nome);
        }
    }

    /**
     * Deleta uma biblioteca existente (do mapa e do sistema de arquivos).
     * @param nome Nome da biblioteca a ser deletada.
     */
    public void deletarBiblioteca(String nome) {
        Biblioteca b = bibliotecas.get(nome);
        if (b == null) {
            System.out.println("Biblioteca não encontrada.");
            return;
        }

        File dir = new File("pdf-manager/src/main/java/br/com/pdfmanager/resources/");
        File pathFile = new File(dir, nome + "_path.txt");
        File binFile = new File(dir, nome + ".bin");

        if (pathFile.exists()) pathFile.delete();
        if (binFile.exists()) binFile.delete();

        bibliotecas.remove(nome);
        if (bibliotecaAtual != null && bibliotecaAtual.equals(b)) {
            bibliotecaAtual = null;
        }

        salvarBibliotecas();
        System.out.println("Biblioteca deletada: " + nome);
    }

    /**
     * Lista todas as bibliotecas registradas.
     */
    public void listarBibliotecas() {
        if (bibliotecas.isEmpty()) {
            System.out.println("Nenhuma biblioteca registrada.");
        } else {
            System.out.println("Bibliotecas disponíveis:");
            for (String nome : bibliotecas.keySet()) {
                System.out.println("- " + nome);
            }
        }
    }

    /**
     * Adiciona um documento à biblioteca atual.
     * @param documento Documento a ser adicionado.
     */
    public void adicionarDocumentoBibliotecaAtual(Documento documento) {
        if (bibliotecaAtual == null) {
            System.out.println("Nenhuma biblioteca selecionada.");
        } else {
            bibliotecaAtual.adicionarDocumento(documento);
        }
    }

    public void listarDocumentosBibliotecaAtual() {
        if (bibliotecaAtual == null) {
            System.out.println("Nenhuma biblioteca selecionada.");
        } else {
            bibliotecaAtual.listarDocumentos();
        }
    }

    /**
     * Carrega as bibliotecas do arquivo persistido.
     */
    @SuppressWarnings("unchecked")
    private void carregarBibliotecas() {
        File arquivo = new File(ARQUIVO_BIBLIOTECAS);
        if (!arquivo.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Map<String, String> caminhos = (Map<String, String>) ois.readObject();
            for (Map.Entry<String, String> entry : caminhos.entrySet()) {
                bibliotecas.put(entry.getKey(), new Biblioteca(entry.getKey(), entry.getValue()));
            }
            System.out.println("Bibliotecas carregadas com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar bibliotecas: " + e.getMessage());
        }
    }

    /**
     * Salva as bibliotecas no arquivo persistido.
     */
    private void salvarBibliotecas() {
        Map<String, String> caminhos = new HashMap<>();
        for (Map.Entry<String, Biblioteca> entry : bibliotecas.entrySet()) {
            caminhos.put(entry.getKey(), entry.getValue().getDiretorio().getAbsolutePath());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_BIBLIOTECAS))) {
            oos.writeObject(caminhos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar bibliotecas: " + e.getMessage());
        }
    }
}
