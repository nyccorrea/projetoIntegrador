public class Exemplar{

    private int codExemplar;
    private boolean situacao;

    public int getCodExemplar() {
        return codExemplar;
    }
    public void setCodExemplar(int codExemplar) {
        this.codExemplar = codExemplar;
    }
    public boolean isSituacao() {
        return situacao;
    }
    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public static void cadastrarExemplar(Livro livro, int codExemplar, boolean situacao){
        Exemplar newExemplar = new Exemplar();

        newExemplar.setCodExemplar(codExemplar);
        newExemplar.setSituacao(situacao);

        livro.listaExemplares.add(newExemplar);
        Livro.atualizarQtaDisponivel(livro.getIsbn());
    }

}
