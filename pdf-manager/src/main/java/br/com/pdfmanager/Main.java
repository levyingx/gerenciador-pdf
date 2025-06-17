package br.com.pdfmanager;

import br.com.pdfmanager.core.Biblioteca;
import br.com.pdfmanager.model.Exercicio;
import br.com.pdfmanager.model.Documento;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String path = "pdf-manager/src/main/java/br/com/pdfmanager/library";
        String nome = "biblioteca1";
        Biblioteca biblioteca = new Biblioteca(nome, path);
        ArrayList<String> autores = new ArrayList<>();
        autores.add("Valdigleis");
        Documento exercicio = new Exercicio(
                autores,
                "Desc trabalho unidade 1 funciona pfv",
                "LP2",
                "DIMAP",
                "Trabalho01.pdf");
        biblioteca.adicionarDocumento(exercicio);
        biblioteca.listarDocumentos();
    }
}