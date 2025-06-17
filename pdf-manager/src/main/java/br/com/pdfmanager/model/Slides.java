package br.com.pdfmanager.model;

import java.util.List;

/**
 * Slide: Para a criação de item desse tipo de entrada na biblioteca,
 * devem ser informados, de forma obrigatória, os nomes dos autores,
 * título da aula, nome da disciplina, o path do PDF que será salvo na
 * biblioteca. Podem também ser informados, mas não de forma
 * obrigatória, o nome da instituição onde a disciplina é ofertada.
 */
public class Slides extends Documento {
    private static final long serialVersionUID = 1L;
    String disciplina;
    String instituicao;

    public Slides(List<String> autores, String titulo, String disciplina, String path) {
        super(autores, titulo, path);
        this.disciplina = disciplina;
    }

    public Slides(List<String> autores, String titulo, String disciplina, String instituicao, String path) {
        super(autores, titulo, path);
        this.disciplina = disciplina;
        this.instituicao = instituicao;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }
}