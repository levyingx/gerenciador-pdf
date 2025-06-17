package br.com.pdfmanager.model;

import java.io.Serializable;
import java.util.List;

/**
 * Classe abstrata que representa um documento PDF genérico.
 * Pode ser estendida para representar livros, slides, notas de aula, etc.
 */
public abstract class Documento implements Serializable {
    private static final long serialVersionUID = 1L;
    public List<String> autores;
    public String titulo;
    public String path;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Documento documento = (Documento) o;

        return titulo.equals(documento.titulo) &&
                autores.equals(documento.autores) &&
                path.equals(documento.path);
    }

    @Override
    public int hashCode() {
        int result = titulo.hashCode();
        result = 31 * result + autores.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }
}
