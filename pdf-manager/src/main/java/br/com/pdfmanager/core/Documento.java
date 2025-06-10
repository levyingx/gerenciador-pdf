package br.com.pdfmanager.core;

import java.util.List;

/**
 * Classe abstrata que representa um documento PDF genérico.
 * Pode ser estendida para representar livros, slides, notas de aula, etc.
 */
public abstract class Documento {
    protected List<String> autores;
    protected String titulo;
    protected String path;

    public Documento(List<String> autores, String titulo, String path) {
        this.autores = autores;
        this.titulo = titulo;
        this.path = path;
    }

    public List<String> getAutores() {
        return autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCaminho() {
        return path;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}