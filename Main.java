import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int codExemplar = 0;

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("                          Bem-vindo ao BOOKLINE!                              ");
        System.out.println("-----------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);

        // inicia a execução do programa
        while (true) {

            System.out.println("O que deseja fazer? \n [1] --> cadastrar \n [2] --> Login \n [3] --> sair ");
            int resposta = scanner.nextInt();

            // para a execução do programa
            if (resposta == 3) {
                break;
            }

            // cadastro no sistema
            else if (resposta == 1) {
                System.out.println("------------------------------- Cadastre-se ---------------------------------");
                System.out.println("Por favor, informe os dados abaixo:");
                System.out.print("Nome: ");
                String nome = scanner.next();
                System.out.print("Matrícula: ");
                String matricula = scanner.next();
                System.out.print("CPF: ");
                String cpf = scanner.next();
                System.out.print("Email: ");
                String email = scanner.next();
                System.out.print("Crie uma senha: ");
                String senha = scanner.next();
                System.out.print("Informe seu tipo de Usuario [0 -> se for Discente | 1 -> se for Funcionario]: ");
                int tipoUsuario = scanner.nextInt();

                // Realiza o cadastro do usuário conforme os dados informados
                Usuario.cadastrarUsuario(nome, matricula, cpf, email, senha, tipoUsuario);

                continue;
            }
            // login
            else if (resposta == 2) {
                System.out.println("---------------- LOGIN --------------");
                scanner.nextLine(); // consumir quebra de linha residual

                System.out.print("Email: ");
                String email_inserido = scanner.nextLine();

                System.out.print("Senha: ");
                String senha_inserida = scanner.nextLine();

                Usuario.validarAcesso(email_inserido, senha_inserida);
                
                System.out.println("--------------------------------------");
                System.out.println(Usuario.validarAcesso(email_inserido, senha_inserida));

                // valida acesso como funcionario
                if (Usuario.validarAcesso(email_inserido, senha_inserida) == "Você está logado como funcionário") {
                    //entra no sistema como funcionario, se o acesso for validado
                    while (true) {
                        System.out.println("O que deseja fazer?");
                        System.out.println(" [1] --> Consultar Pedido de Empréstimo \n [2] --> Gerenciar Livros \n [3] --> Logout ");
                        
                        int decisao = scanner.nextInt();
                        
                        //sai do sistema
                        if (decisao == 3) {
                            break;
                        }

                        switch (decisao) {
        
                            case 1:
                                System.out.println("##################################################");
                                System.out.println("       Consultando Pedido de Empréstimo           ");
                                System.out.println("##################################################");
                                scanner.nextLine(); // consumir quebra de linha residual
                                System.out.println("Informe a matrícula do discente: ");
                                String matricula = scanner.nextLine();
        
                                // metodo de consultar emprestimo
                                Emprestimo.consultarEmprestimo(matricula);
                                continue;
                            case 2:
                                System.out.println("##################################################");
                                System.out.println("                 Gerenciando Livros               ");
                                System.out.println("##################################################");
                                scanner.nextLine(); // consumir quebra de linha residual
                                System.out.println("Digite a opção conforme o que desejar: ");
                                System.out.println(" [1] --> cadastrar livro \n [2] --> consultar livro \n [3] --> Repor exemplar do livro \n [4] --> sair do Gerenciador");
                                int opcao = scanner.nextInt();
                                
                                switch (opcao) {
                                    //sai do gerenciador
                                    case 4:
                                        break;
                                    //cadastra livros
                                    case 1:
                                        System.out.println("----> Quantos livros deseja cadastrar?");
                                        int quantidade = scanner.nextInt();

                                        
                                        while (quantidade > 0) {
                                            System.out.println("Por favor, informe os dados abaixo:");
                                            scanner.nextLine(); // consumir quebra de linha residual
                                            System.out.println("----------------- Dados Livro ---------------");
                                            System.out.print("ISBN: ");
                                            String isbn = scanner.nextLine();
                                            System.out.print("Título: ");
                                            String titulo = scanner.nextLine();
                                            System.out.print("Ano de Publicação: ");
                                            String anoPublicacao = scanner.nextLine();
                                            System.out.print("Quantidade de Páginas: ");
                                            String qtdPaginas = scanner.nextLine();
                                            System.out.print("Autor: ");
                                            String autor = scanner.nextLine();                                                
                                            System.out.print("Categoria: ");
                                            String categoria = scanner.nextLine();
                                            System.out.print("Editora: ");
                                            String editora = scanner.nextLine();
                                            
                                            
                                            // Realiza o cadastro do Livro conforme os dados informados
                                            Livro livro = Livro.cadastrarLivro(isbn, titulo, anoPublicacao, qtdPaginas, autor, categoria, editora);
                                            
                                            System.out.print("Quantidade de exemplares para cadastro: ");
                                            int qtaExemplares = scanner.nextInt();
                                            System.out.print("\n");
                                            for(int i = 0; i < qtaExemplares; i++){
                                                codExemplar = codExemplar + 1;
                                                Exemplar.cadastrarExemplar(livro, codExemplar, true);
                                                System.out.printf("Exemplar: %d cadastrado com sucesso.\n", codExemplar);
                                            }
                                            quantidade--;
                                        }
                                            continue;
                                    //consulta livros
                                    case 2:
                                        System.out.println("---------------- Consultando Livro Cadastrado --------------");
                                        scanner.nextLine(); // consumir quebra de linha residual
                                        System.out.print("Por favor Funcionario, informe o ISBN do livro: ");
                                        String isbn_informado = scanner.nextLine();

                                        Livro.consultarLivro(isbn_informado);
                                        continue;

                                    //repor exemplar
                                    case 3:
                                        System.out.println("---------------- Repor Exemplar ---------------");
                                        scanner.nextLine(); // consumir quebra de linha residual
                                        System.out.println("Informe a matrícula do discente:");
                                        String matricula_disc = scanner.nextLine();
                                        System.out.println("Informe o código do exemplar: ");
                                        int codExemplar_emprestado = scanner.nextInt();
                                        System.out.println("Informe o isbn do livro: ");
                                        String isbn_livro = scanner.nextLine();
                                        Emprestimo.devolver(matricula_disc, codExemplar_emprestado, isbn_livro);
        
                                    // se não for opcao válida
                                    default:
                                        System.out.println("\nValor de entrada inválida\n");
                                        continue;
                                }
                            default:
                                System.out.println("\nValor de entrada inválida\n");
        
                        } 
                    }
                }

                else if (Usuario.validarAcesso(email_inserido, senha_inserida) == "Você está logado como discente") {
                    //entra no sistema se o acesso for validado
                    while (true) {
                        System.out.println("O que deseja fazer?");
                        System.out.println(" [0] --> Visualizar Catálogo \n [1] --> Visualizar meu pedido \n [3] --> Logout");

                        int decisao = scanner.nextInt();

                        //sai do sistema
                        if (decisao == 3) {
                            break;
                        }

                        switch(decisao) {
                            case 0:
                                Livro.main(args);
                                System.out.println("Deseja realizar pedido de empréstimo? [S/N]");
                                char respPedido = scanner.next().toUpperCase().charAt(0);
                                if (respPedido == 'S') {
                                    System.out.println("Informe sua matrícula: ");
                                    scanner.nextLine(); // consumir quebra de linha residual
                                    String matricula = scanner.nextLine();
                                    System.out.println("Informe o isbn do livro: ");
                                    String isbn = scanner.nextLine();

                                    Emprestimo.realizarEmprestimo(matricula, isbn);
                                    continue;
                                }else{
                                    continue;
                                }
                            case 1:
                                System.out.println("##################################################");
                                System.out.println("       Consultando Pedido de Empréstimo           ");
                                System.out.println("##################################################");
                                scanner.nextLine(); // consumir quebra de linha residual
                                System.out.println("Informe sua matrícula: ");
                                String matricula = scanner.nextLine();
        
                                // metodo de consultar emprestimo
                                Emprestimo.consultarEmprestimo(matricula);
                                continue;
                        }
                    }
                }

            }else{
                System.out.println("\nPor favor, insira uma opção existente. \n");
            }
        }
        scanner.close();
    }
}
