package br.com.pdfmanager.core;

import java.io.File;

import java.util.ArrayList;

import br.com.pdfmanager.model.Documento;
import br.com.pdfmanager.model.Exercicio;

public class Biblioteca {
    private String nome;
    private File diretorio;
    private ArrayList<Documento> documentos;
    private final String STORAGE_PATH = "pdf-manager/src/main/java/br/com/pdfmanager/resources/data.json";

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
        // TODO salvarCaminho()
    }

    private void recuperarCaminho() {
        // TODO recuperarCaminho()
    }

    private void carregarDocumentos() {
        // TODO carregarDocumentos()
    }

    public void adicionarDocumento(Documento documento) {
        if (documento != null) {
            documentos.add(documento);
        }

        // TODO adicionarDocumento()
    }

    public void removerDocumento(Documento documento) {
        if (documentos.remove(documento)) {
            System.out.println("Documento removido com sucesso.");
            adicionarDocumento(null); 
        } else {
            System.out.println("Documento não encontrado na biblioteca.");
        }
    }

    public void editarEntrada(int indice, Documento novoDocumento) {
        if (indice >= 0 && indice < documentos.size()) {
            documentos.set(indice, novoDocumento);
            System.out.println("Documento atualizado com sucesso.");
        } else {
            System.out.println("Índice inválido."); // // TODO throw error Índice inválido. Tente novamente and allow user to try another index
        }
    }

    public void buscarDocumento(String nome) {
        // TODO buscarDocumento()
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