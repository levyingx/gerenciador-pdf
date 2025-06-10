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
     * Construtor da classe Biblioteca.
     * Cria a estrutura de diretórios caso não exista e salva o path em um
     * arquivo .txt
     *
     * @param path Local onde a biblioteca será criada ou acessada.
     */
    public Biblioteca(String path) {
        documentos = new ArrayList<>();
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

    public void adicionarDocumento(Documento documento) {
        documentos.add(documento);
    }
}