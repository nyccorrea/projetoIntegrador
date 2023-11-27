import java.time.LocalDate;

public class Emprestimo {
    private boolean situacao; //indica se o empréstimo está em andamento
    private LocalDate dataEmprestimo, dataDevolucao;
    private String ISBN;
    private String codExemplar;
    private String matricula;

    // getters e setters
    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }
    
    public String getCodExemplar() {
        return codExemplar;
    }

    public void setCodExemplar(String codExemplar) {
        this.codExemplar = codExemplar;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public static String realizarEmprestimo(String matricula, String isbn){
        for(Usuario usuario : Repositorio.listaUsuarios){
            if(usuario.getMatricula().equals(matricula)){
                if(usuario.getQtaEmprestimosTomados() > 3){
                    return "Limite de emprésitmos atingido.";
                }
            }
        }
        for(Livro livro : Repositorio.listaLivros){
            if(livro.getIsbn().equals(isbn)){
                for(Exemplar exemplar : livro.listaExemplares){
                    if(exemplar.isSituacao() == true){
                        exemplar.setSituacao(false);
                        Emprestimo newEmprestimo = new Emprestimo();

                        newEmprestimo.setMatricula(matricula);
                        newEmprestimo.setISBN(isbn);
                        newEmprestimo.setCodExemplar(Integer.toString(exemplar.getCodExemplar()));
                        newEmprestimo.setDataEmprestimo(LocalDate.now());
                        newEmprestimo.setDataDevolucao(newEmprestimo.getDataEmprestimo().plusDays(14));
                        newEmprestimo.setSituacao(true);

                        for(Usuario usuario : Repositorio.listaUsuarios){
                            if(usuario.getMatricula().equals(matricula)){
                                usuario.setQtaEmprestimosTomados(usuario.getQtaEmprestimosTomados()+1);
                                usuario.listaEmprestimos.add(newEmprestimo);
                                break;
                            }
                        }
                        Livro.atualizarQtaDisponivel(isbn);
                        break;
                    }
                }
                break;
            }
        }
        return "Empréstimo realizado com sucesso.";
    }

    public static void consultarEmprestimo(String matricula){
        for(Usuario usuario : Repositorio.listaUsuarios){
            if(usuario.getMatricula().equals(matricula)){
                for(Emprestimo emprestimo : usuario.listaEmprestimos){
                    System.out.println("ISBN: " + emprestimo.getISBN());
                    System.out.println("Código Exemplar: " + emprestimo.getCodExemplar());
                    System.out.println("Código matrícula: " + emprestimo.getMatricula());
                    System.out.println("Data Empréstimo: " + emprestimo.getDataEmprestimo());
                    System.out.println("Data Devolução: " + emprestimo.getDataDevolucao());
                }
                break;
            }
        }
    }

    public static void devolver(String matricula, int codExemplar, String isbn){
        for(Usuario usuario : Repositorio.listaUsuarios){
            if(usuario.getMatricula().equals(matricula)){
                usuario.setQtaEmprestimosTomados(usuario.getQtaEmprestimosTomados()-1);
                for(Emprestimo emprestimo : usuario.listaEmprestimos){
                    if(emprestimo.getCodExemplar().equals(Integer.toString(codExemplar))){
                        emprestimo.setSituacao(false);
                        break;                 
                    }
                }
                break;
            }
        }
        for(Livro livro : Repositorio.listaLivros){
            if(livro.getIsbn().equals(isbn)){
                for(Exemplar exemplar : livro.listaExemplares){
                    if(exemplar.getCodExemplar() == codExemplar){
                        exemplar.setSituacao(true);
                        Livro.atualizarQtaDisponivel(isbn);
                        break;
                    }
                }
                break;
            }
        }
        System.out.println("Devolução concluído com sucesso.");
    }

}
