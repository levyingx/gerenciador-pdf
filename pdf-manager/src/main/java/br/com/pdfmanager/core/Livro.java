package br.com.pdfmanager.core;

import java.util.List;

/**
 * Livro: Para a criação de item desse tipo de entrada na biblioteca,
 * devem ser informados, de forma obrigatória, os nomes dos autores,
 * título e subtítulo do livro, área de conhecimento do livro, ano de
 * publicação, o path do PDF que será salvo na biblioteca. Podem
 * também ser informados, mas não de forma obrigatória, a editora
 * do livro, o número de páginas.
 */
public class Livro extends Documento {

    private String subtitulo;
    private String areaConhecimento;
    private Integer anoPublicacao;
    private String editora;
    private Integer numPaginas;

    public Livro(List<String> autores, String titulo, String subtitulo, String areaConhecimento, Integer anoPublicacao,
            String path) {
        super(autores, titulo, path);
        this.subtitulo = subtitulo;
        this.areaConhecimento = areaConhecimento;
        this.anoPublicacao = anoPublicacao;
    }

    public Livro(List<String> autores, String titulo, String subtitulo, String areaConhecimento, Integer anoPublicacao,
            String path, String editora, Integer numPaginas) {
        this(autores, titulo, subtitulo, areaConhecimento, anoPublicacao, path);
        this.editora = editora;
        this.numPaginas = numPaginas;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public String getAreaConhecimento() {
        return areaConhecimento;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getEditora() {
        return editora;
    }

    public Integer getNumPaginas() {
        return numPaginas;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public void setAreaConhecimento(String areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setNumPaginas(Integer numPaginas) {
        this.numPaginas = numPaginas;
    }
}