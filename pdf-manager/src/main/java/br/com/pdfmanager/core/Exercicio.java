package br.com.pdfmanager.core;

import java.util.List;

public class Exercicio extends Documento {
    String disciplina;
    String instituicao;

    public Exercicio(List<String> autores, String titulo, String disciplina, String path) {
        super(autores, titulo, path);
        this.disciplina = disciplina;
    }

    public Exercicio(List<String> autores, String titulo, String disciplina, String instituicao, String path) {
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
