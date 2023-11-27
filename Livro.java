import java.util.ArrayList;

public class Livro {

    private String isbn;
    private String titulo;
    private String anoPublicacao;
    private String qtaPaginas;
    private String autor;
    private String Categoria;
    private String editora;
    private int qtaExemplaresDisponiveis;

    public ArrayList<Exemplar> listaExemplares = new ArrayList<>();

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getQtaPaginas() {
        return qtaPaginas;
    }
    public void setQtaPaginas(String qtaPaginas) {
        this.qtaPaginas = qtaPaginas;
    }
    public String getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getCategoria() {
        return Categoria;
    }
    public void setCategoria(String categoria) {
        Categoria = categoria;
    }
    public String getEditora() {
        return editora;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }
    public int getQtaExemplaresDisponiveis() {
        return qtaExemplaresDisponiveis;
    }
    public void setQtaExemplaresDisponiveis(int qtaExemplaresDisponiveis) {
        this.qtaExemplaresDisponiveis = qtaExemplaresDisponiveis;
    }

    public static Livro cadastrarLivro(String isbn, String titulo, String anoPublicacao, String qtaPaginas, String autor, String categoria, String editora){
        Livro newLivro = new Livro();

        newLivro.setIsbn(isbn);
        newLivro.setTitulo(titulo);
        newLivro.setAnoPublicacao(anoPublicacao);
        newLivro.setQtaPaginas(qtaPaginas);
        newLivro.setAutor(autor);
        newLivro.setCategoria(categoria);
        newLivro.setEditora(editora);

        Repositorio.listaLivros.add(newLivro);
        return newLivro;
    }
    public static void consultarLivro(String isbn){
        for(Livro livro : Repositorio.listaLivros){
            if(livro.getIsbn().equals(isbn)){
                System.out.println("===============================================");
                System.out.println("ISBN: " + livro.getIsbn() +
                                "\nTítulo: " + livro.getTitulo() + 
                                "\nAno de Publicação: " + livro.getAnoPublicacao() + 
                                "\nQuantidade de Páginas: " + livro.getQtaPaginas() +
                                "\nAutor: " + livro.getAutor() +
                                "\nCategoria: " + livro.getCategoria() +
                                "\nEditora: " + livro.getEditora());
                System.out.println("===============================================");
            }
        }
    }

    public static void consultarCatalogo(){
        System.out.println("===============================================");
        for(Livro livro : Repositorio.listaLivros){
            System.out.println("ISBN: " + livro.getIsbn() +
                                "\nTítulo: " + livro.getTitulo() + 
                                "\nAno de Publicação: " + livro.getAnoPublicacao() + 
                                "\nQuantidade de Páginas: " + livro.getQtaPaginas() +
                                "\nAutor: " + livro.getAutor() +
                                "\nCategoria: " + livro.getCategoria() +
                                "\nEditora: " + livro.getEditora() +
                                "\nQuantidade disponível: " + livro.getQtaExemplaresDisponiveis());
            System.out.println("===============================================");
        }
    }

    public static void atualizarQtaDisponivel(String isbn){
        for(Livro livro : Repositorio.listaLivros){
            if(livro.getIsbn().equals(isbn)){
                int cont = 0;
                for(Exemplar exemplar : livro.listaExemplares){
                    if(exemplar.isSituacao() == true){
                        cont++;
                    }
                }
                livro.setQtaExemplaresDisponiveis(cont);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("################ CATÁLOGO ################");
        Livro.consultarCatalogo();
        System.out.println("##########################################");
    }

}
