package br.com.pdfmanager.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import br.com.pdfmanager.model.Documento;

public class Biblioteca {
    private String nome;
    private File diretorio;
    private ArrayList<Documento> documentos;
    private File arquivoCaminho;

    /**
     * Construtor da classe Biblioteca.
     * Cria um diretório para a biblioteca se ele não existir e carrega os
     * documentos salvos.
     * 
     * @param nome nome da biblioteca
     * @param path caminho onde a biblioteca será criada.
     */
    public Biblioteca(String nome, String path) {
        this.nome = nome;
        this.documentos = new ArrayList<Documento>();
        this.diretorio = new File(path);

        if (!diretorio.exists()) {
            if (criarDiretorio()) {
                salvarCaminho();
            }
        } else {
            recuperarCaminho();
            carregarDocumentos();
        }
    }

    /**
     * Cria o diretório da biblioteca se ele não existir.
     * 
     * @return true se o diretório foi criado, false caso contrário.
     */
    private boolean criarDiretorio() {
        if (diretorio.mkdirs()) {
            System.out.println("Diretório criado com sucesso: " + diretorio.getAbsolutePath());
            return true;
        } else {
            System.out.println("Falha ao criar o diretório: " + diretorio.getAbsolutePath());
        }
        return false;
    }

    /**
     * Salva o caminho da biblioteca no folder resources em um arquivo com o nome da
     * biblioteca.
     */
    private void salvarCaminho() {
        if (diretorio == null || !diretorio.exists()) {
            System.err.println("Diretório não existe. Caminho não pode ser salvo.");
            return;
        }

        arquivoCaminho = new File("pdf-manager/src/main/java/br/com/pdfmanager/resources/" + nome + "_path.txt");

        try (FileWriter writer = new FileWriter(arquivoCaminho)) {
            writer.write(diretorio.getAbsolutePath());
            System.out.println("Caminho salvo com sucesso: " + diretorio.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao salvar caminho: " + e.getMessage());
        }
    }

    /**
     * Recupera o caminho da biblioteca do arquivo salvo no folder resources.
     */
    private void recuperarCaminho() {
        try (Scanner scanner = new Scanner(
                new File("pdf-manager/src/main/java/br/com/pdfmanager/resources/" + nome + "_path.txt"))) {
            if (scanner.hasNextLine()) {
                String caminho = scanner.nextLine();
                diretorio = new File(caminho);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Não foi possível recuperar caminho: " + e.getMessage());

            // Se arquivo com caminho não existir, cria o arquivo em resources com o caminho
            // da biblioteca
            if (diretorio != null && diretorio.exists()) {
                salvarCaminho();
            } else {
                System.err.println("Diretório não existe. Caminho não pode ser recuperado.");
            }
        }
    }

    /**
     * Lê o documento .bin com informações da ArrayList documentos e carrega os
     * dados.
     */
    @SuppressWarnings("unchecked")
    private void carregarDocumentos() {
        File arquivo = new File("pdf-manager/src/main/java/br/com/pdfmanager/resources", this.nome + ".bin");
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                documentos = (ArrayList<Documento>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar documentos: " + e.getMessage());
            }
        } else {
            System.out.println("Nenhum documento encontrado para carregar.");
        }
    }

    // O sistema deve conseguir copiar o arquivo apontado pelo path do arquivo
    // PDF informado durante a criação de uma entrada na biblioteca para dentro
    // de subdiretório, nomeado como o nome do primeiro autor do livro,
    // nota de aula ou slide.

    /**
     * Adiciona um documento à biblioteca e salva os documentos.
     * 
     * @implNote Documento é registrado no .bin e é literalmente copiado para o
     *           diretório da biblioteca.
     * @param documento documento a ser adicionado.
     */
    public void adicionarDocumento(Documento documento) {
        carregarDocumentos();

        if (documento == null || documentos.contains(documento)) {
            System.out.println("Documento já existe ou é nulo.");
        } else {
            // Copia o arquivo para o diretório da biblioteca
            File pdf = new File(documento.getCaminho());
            File subdiretorio = new File(diretorio, documento.getAutores().get(0));

            if (!subdiretorio.exists()) {
                subdiretorio.mkdirs(); // cria se não existir
            }

            File destino = new File(subdiretorio, documento.getTitulo() + ".pdf");

            try {
                if (pdf.exists()) {
                    Files.copy(pdf.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    System.err.println("Arquivo PDF não encontrado: " + pdf.getAbsolutePath());
                }
            } catch (IOException e) {
                System.err.println("Erro ao copiar arquivo: " + e.getMessage());
            }

            documentos.add(documento);
            salvarDocumentos();
        }
    }

    /**
     * Salva o estado do ArrayList de documentos em um arquivo .bin no diretório da
     * biblioteca.
     */
    private void salvarDocumentos() {
        File arquivo = new File("pdf-manager/src/main/java/br/com/pdfmanager/resources/", this.nome + ".bin");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(documentos);
            System.out.println("Documentos salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar documentos: " + e.getMessage());
        }
    }

    /**
     * Remove um documento da biblioteca.
     * 
     * @implNote O documento é removido do ArrayList,
     *           o .bin é sobrescrito sem o
     *           documento e o PDF é deletado do diretório.
     * @param documento documento a ser removido.
     */
    public void removerDocumento(Documento documento) {
        carregarDocumentos();

        if (documento == null || !documentos.contains(documento)) {
            System.out.println("Documento não encontrado ou é nulo.");
            return;
        }

        documentos.remove(documento);
        salvarDocumentos();

        File subdiretorio = new File(diretorio, documento.getAutores().get(0));
        File pdf = new File(subdiretorio, documento.getTitulo() + ".pdf");

        if (pdf.exists()) {
            if (pdf.delete()) {
                System.out.println("PDF deletado: " + pdf.getAbsolutePath());

                if (subdiretorio.isDirectory() && subdiretorio.list().length == 0) {
                    subdiretorio.delete();
                    System.out.println("Subdiretório vazio deletado: " + subdiretorio.getAbsolutePath());
                }
            } else {
                System.err.println("Falha ao deletar PDF: " + pdf.getAbsolutePath());
            }
        } else {
            System.out.println("Arquivo PDF não encontrado para deletar.");
        }
    }

    /**
     * Edita um documento na biblioteca.
     * 
     * @implNote O documento é editado no ArrayList,
     *           o .bin é sobrescrito com o novo documento.
     */
    public void editarDocumento(int indice, Documento novoDocumento) {
        // TODO editarDocumento()
        if (indice < 0 || indice >= documentos.size()) {
            System.out.println("Índice inválido.");
        } else {
            documentos.set(indice, novoDocumento);
            salvarDocumentos();
        }
    }

    /**
     * Busca documentos na biblioteca pelo título.
     * 
     * @param nome título do documento a ser buscado.
     */
    public void buscarDocumento(String nome) {
        carregarDocumentos();

        for (Documento doc : documentos) {
            if (doc.getTitulo().equalsIgnoreCase(nome)) {
                System.out.println("Documento encontrado:");
                System.out.println("Título: " + doc.getTitulo());
                System.out.println("Autores: " + String.join(", ", doc.getAutores()));
                System.out.println("Caminho: " + doc.getCaminho());
            }
        }

        System.out.println("Documento não encontrado: \"" + nome + "\"");
    }

    /**
     * Lê a ArrayList de documentos e exibe seus detalhes.
     */
    public void listarDocumentos() {
        Integer i = 1;
        for (Documento doc : documentos) {
            System.out.println(i + ".");
            System.out.println("Título: " + doc.getTitulo());
            System.out.println("Autores: " + String.join(", ", doc.getAutores()));
            System.out.println("Caminho: " + doc.getCaminho());
            System.out.println("-----------------------------------");
            i++;
        }
    }
}