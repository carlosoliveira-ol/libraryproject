package biblioteca;

public class Livro { // Classe que serve de molde para o objeto 'livro'

    // Atributos utilizados pelo objeto
    private String titulo, autor;
    private int ano, codigo;

    // Construtor do objeto, evita ter que definir cada atributo em uma linha separada
    public Livro(String titulo, String autor, int ano, int codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.codigo = codigo;
    }

    // Método get para pegar o valor do título
    public String getTitulo() {
        return titulo;
    }

    // Método get para pegar o valor do autor
    public String getAutor() {
        return autor;
    }

    // Método get para pegar o valor do ano
    public int getAno() {
        return ano;
    }

    // Método get para pegar o valor do código
    public int getCodigo() {
        return codigo;
    }

    // Método set para definir o título
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Método set para definir o autor
    public void setAutor(String autor) {
        this.autor = autor;
    }

    // Método set para definir o ano (bloqueia valores negativos como segurança extra,
    // mesmo já havendo validação no momento da entrada do usuário)
    public void setAno(int ano) {
        if (ano >= 0) {
            this.ano = ano;
        }
    }

    // Método set para definir o código
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}