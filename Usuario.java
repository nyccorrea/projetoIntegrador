import java.util.ArrayList;

public class Usuario {

    // atributos
    private String matricula, nome, cpf, email, senha;
    private int tipoUsuario;
    private int qtaEmprestimosTomados = 0;

    public ArrayList<Emprestimo> listaEmprestimos = new ArrayList<>();
    
    // getters e setters
    public int getQtaEmprestimosTomados() {
        return qtaEmprestimosTomados;
    }

    public void setQtaEmprestimosTomados(int qtaEmprestimosTomados) {
        this.qtaEmprestimosTomados = qtaEmprestimosTomados;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    // metodo cadastrar usuario
    public static void cadastrarUsuario(String nome, String matricula, String cpf, String email,
            String senha, int tipoUsuario) {

        // construtor
        Usuario newUsuario = new Usuario();

        newUsuario.setNome(nome);
        newUsuario.setMatricula(matricula);
        newUsuario.setCpf(cpf);
        newUsuario.setEmail(email);
        newUsuario.setSenha(senha);
        newUsuario.setTipoUsuario(tipoUsuario);

        if (tipoUsuario == 0) {
            System.out.println("\nCadastro como usuario Discente realizado com sucesso.\n");
        } else if (tipoUsuario == 1) {
            System.out.println("\nCadastro como usuario Funcionario realizado com sucesso.\n");
        } else {
            System.err.println(
                    "\nDetectamos um erro. Forneça apenas 0(se for Discente) ou 1(se for Funcionario) no seu tipo de Usuario.\n");
        }

        Repositorio.listaUsuarios.add(newUsuario);

    }

    // metodo que valida acesso
    public static String validarAcesso(String email, String senha) {

        for (Usuario usuario : Repositorio.listaUsuarios) {
            if (email.equals(usuario.getEmail()) && senha.equals(usuario.getSenha())) {
                if(usuario.getTipoUsuario() == 1)
                    return "Você está logado como funcionário"; // Acesso válido como funcionario
                else if(usuario.getTipoUsuario() == 0)
                    return "Você está logado como discente"; // Acesso válido como discente
            } 
        }
        return "Login inválido"; // Acesso inválido
    }

}