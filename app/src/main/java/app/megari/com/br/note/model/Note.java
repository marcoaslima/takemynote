package app.megari.com.br.note.model;

/**
 * Created by marck on 03/09/2016.
 */
public class Note {
    private Integer Codigo;
    private String Titulo;
    private String Descricao;
    private Boolean Lixeira;
    private Boolean Ativo;
    private String Cor;

    public Note(Integer codigo, String titulo, String descricao, Boolean lixeira, Boolean ativo, String cor) {
        Codigo = codigo;
        Titulo = titulo;
        Descricao = descricao;
        Lixeira = lixeira;
        Ativo = ativo;
        Cor = cor;
    }

    public Integer getCodigo() {
        return Codigo;
    }

    public void setCodigo(Integer codigo) {
        Codigo = codigo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Boolean getLixeira() {
        return Lixeira;
    }

    public void setLixeira(Boolean lixeira) {
        Lixeira = lixeira;
    }

    public Boolean getAtivo() {
        return Ativo;
    }

    public void setAtivo(Boolean ativo) {
        Ativo = ativo;
    }

    public String getCor() {
        return Cor;
    }

    public void setCor(String cor) {
        Cor = cor;
    }
}
