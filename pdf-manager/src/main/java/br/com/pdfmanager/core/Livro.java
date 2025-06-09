package br.com.pdfmanager.core;

import java.util.List;

/**
 * Classe que representa um documento PDF do tipo Livro.
 */
public class Livro extends Documento {

    public Livro(List<String> autores, String titulo, String caminhoOriginal) {
        super(autores, titulo, caminhoOriginal);
    } 
}