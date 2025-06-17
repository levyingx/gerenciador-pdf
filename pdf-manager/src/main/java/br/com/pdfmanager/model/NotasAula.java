package br.com.pdfmanager.model;

import java.util.List;

/**
 * Notas de aula: Para a criação de item desse tipo de entrada na
 * biblioteca, devem ser informados, de forma obrigatória, os nomes
 * dos autores, título e subtítulo das notas de aula, nome da disciplina,
 * o path do PDF que será salvo na biblioteca. Podem também ser
 * informados, mas não de forma obrigatória, o nome da instituição
 * onde a disciplina é ofertada, o número de páginas.
 */
public class NotasAula extends Documento {
    private static final long serialVersionUID = 1L;

    private String subtitulo;
    private String disciplina;
    private String instituicao;
    private Integer paginas;

    public NotasAula(List<String> autores, String titulo, String subtitulo, String disciplina, String path) {
        super(autores, titulo, path);
        this.subtitulo = subtitulo;
        this.disciplina = disciplina;
    }

    public NotasAula(List<String> autores, String titulo, String subtitulo, String disciplina, String instituicao,
            Integer paginas, String path) {
        super(autores, titulo, path);
        this.subtitulo = subtitulo;
        this.disciplina = disciplina;
        this.instituicao = instituicao;
        this.paginas = paginas;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }
}