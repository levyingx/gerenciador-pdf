package br.com.pdfmanager.core;

import br.com.pdfmanager.core.Documento;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Biblioteca {
    private File diretorio;
    private ArrayList<Documento> documentos;
    private final String PATH_TXT = "pdf-manager/src/main/java/br/com/pdfmanager/resources/path.txt";

    /**
     * Além de criar entradas para a biblioteca, o sistema deve conseguir efetuar
     * as seguintes tarefas: deletar entradas na biblioteca, editar entradas da
     * biblioteca, buscar e listar entradas na biblioteca, criar novas bibliotecas,
     * alternar entre bibliotecas, deletar bibliotecas.
     */
    public Biblioteca(String path) {
        documentos = new ArrayList<Documento>();
        diretorio = new File(path);

        if (!diretorio.exists()) {
            if (criarDiretorio()) {
                salvarCaminho();
            }
        } else {
            recuperarCaminho();
        }
    }

    private boolean criarDiretorio() {
        System.out.println("Criando diretório...");
        if (diretorio.mkdirs()) {
            System.out.println("Diretório criado em " + diretorio.getAbsolutePath());
            return true;
        } else {
            System.err.println("Erro ao criar diretório em " + diretorio.getAbsolutePath());
            return false;
        }
    }

    private void salvarCaminho() {
        try (FileWriter writer = new FileWriter(PATH_TXT)) {
            writer.write(diretorio.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Não foi possível salvar o path da biblioteca: " + e.getMessage());
        }
    }

    private void recuperarCaminho() {
        System.out.println("Recuperando caminho...");

        File arquivo = new File(PATH_TXT);

        try (Scanner scanner = new Scanner(arquivo)) {
            String linha = scanner.nextLine();
            diretorio = new File(linha);
            System.out.println("Caminho recuperado da biblioteca: " + linha);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo path.txt: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("Arquivo path.txt está vazio.");
        }
    }

    /**
     * Adiciona um documento na biblioteca e salva o caminho dele no arquivo
     * path.txt
     * 
     * @param documento Um objeto do tipo Documento
     */
    public void adicionarDocumento(Documento documento) {
        documentos.add(documento);
        System.out.println("Documento adicionado");
        // falta salvar o caminho no path.txt
    }

    public void removerDocumento(Documento documento) {
        // TODO Implementar removerDocumento()
    }

    public void editarEntrada() {
        // TODO Implementar editarEntrada()
    }

    public void buscarDocumento() {
        // TODO Implementar buscarDocumento()
    }

    public void listarDocumentos() {
        if (documentos.isEmpty()) {
            System.out.println("Nenhum documento na biblioteca.");
        } else {
            for (int i = 0; i < documentos.size(); i++) {
                System.out.println((i + 1) + ": " + documentos.get(i).titulo + "| " + documentos.get(i).autores);
            }
        }
    }
}