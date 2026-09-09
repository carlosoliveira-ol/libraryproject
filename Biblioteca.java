package biblioteca;

import java.util.ArrayList;
import java.util.Optional;

public class Biblioteca {

    // Arranjo onde os objetos "Livro" ficam armazenados
    public static ArrayList<Livro> estantes = new ArrayList<>();

    public static void main(String[] args) {

        int decisao = 0; // Variável usada para a decisão do menu (switch)

        while (decisao != 6) { // Mantém o menu rodando até o usuário escolher sair

            System.out.println("======== BIBLIOTECA ========");
            System.out.println("Escolha uma opção para continuar:");
            System.out.println("1 - Cadastrar Livros.");
            System.out.println("2 - Listar Livros.");
            System.out.println("3 - Buscar livros.");
            System.out.println("4 - Remover livros.");
            System.out.println("5 - Editar livro");
            System.out.println("6 - Sair.");

            verificarNumero();
            decisao = LeitorConsole.entrada.nextInt();
            LeitorConsole.entrada.nextLine();

            switch (decisao) { // Direciona o usuário para a opção escolhida
                case 1 -> cadastrarLivro();
                case 2 -> listarLivros();
                case 3 -> buscarLivro();
                case 4 -> removerLivro();
                case 5 -> editarLivro();
                case 6 -> System.out.println("Sistema encerrado!");
                default -> System.out.println("Escolha uma opção válida");
            }
        }
    }

    // Cadastra um novo livro: captura os dados, valida e adiciona ao arranjo
    public static void cadastrarLivro() {

        System.out.println("Nome do livro : ");
        String nome = LeitorConsole.entrada.nextLine();

        while (nome.isBlank()) {
            System.out.println("Erro: Este campo não pode ficar vazio.");
            System.out.println("Nome do livro : ");
            nome = LeitorConsole.entrada.nextLine();
        }

        System.out.println("Nome do autor : ");
        String autor = LeitorConsole.entrada.nextLine();

        while (autor.isBlank()) {
            System.out.println("Erro: Este campo não pode ficar vazio.");
            System.out.println("Nome do autor : ");
            autor = LeitorConsole.entrada.nextLine();
        }

        int ano = lerAnoValido();

        int codigo;
        boolean codigoValido = false;
        int codigoTemp = 0;
        while (!codigoValido) {
            System.out.println("Código do livro : ");
            verificarNumero();
            codigoTemp = LeitorConsole.entrada.nextInt();

            int codigoFinal = codigoTemp;
            Optional<Livro> codigoDuplicado = estantes.stream()
                    .filter(livro -> livro.getCodigo() == codigoFinal)
                    .findFirst();

            if (codigoDuplicado.isPresent()) {
                System.out.println("Esse código já existe! digite outro.");
            } else {
                codigoValido = true;
            }
        }
        codigo = codigoTemp;

        estantes.add(new Livro(nome, autor, ano, codigo));
        System.out.println("Livro cadastrado com sucesso!");
    }

    // Lê o ano pelo teclado e garante que não seja um número negativo.
    // Reaproveitado tanto no cadastro quanto na edição de livros.
    private static int lerAnoValido() {
        int ano;
        while (true) {
            System.out.println("Ano do livro : ");
            verificarNumero();
            ano = LeitorConsole.entrada.nextInt();

            if (ano < 0) {
                System.out.println("Erro: o ano não pode ser negativo.");
            } else {
                break;
            }
        }
        return ano;
    }

    // Percorre o arranjo e imprime os atributos de cada livro
    public static void listarLivros() {
        if (estantes.isEmpty()) {
            System.out.println("Nenhum livro cadastrado ainda.");
            return;
        }

        for (Livro livro : estantes) {
            System.out.println("Titulo : " + livro.getTitulo());
            System.out.println("Autor : " + livro.getAutor());
            System.out.println("Ano : " + livro.getAno());
            System.out.println("Código : " + livro.getCodigo());
            System.out.println("--------------------------");
        }
    }

    // Busca um livro pelo nome dentro do arranjo
    public static void buscarLivro() {

        System.out.println("Digite o nome do livro: ");
        String busca = LeitorConsole.entrada.nextLine();

        while (busca.isBlank()) {
            System.out.println("Erro: Este campo não pode ficar vazio.");
            System.out.println("Digite o nome do livro: ");
            busca = LeitorConsole.entrada.nextLine();
        }

        final String buscaFinal = busca;

        Optional<Livro> resultado = estantes.stream()
                .filter(livro -> livro.getTitulo().equalsIgnoreCase(buscaFinal))
                .findFirst();

        if (resultado.isPresent()) {
            Livro livroEncontrado = resultado.get();
            System.out.println("Livro encontrado!");
            System.out.println("Titulo : " + livroEncontrado.getTitulo());
            System.out.println("Autor : " + livroEncontrado.getAutor());
            System.out.println("Ano : " + livroEncontrado.getAno());
            System.out.println("Código : " + livroEncontrado.getCodigo());
            System.out.println("--------------------------");
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    // Remove um livro do arranjo a partir do nome
    public static void removerLivro() {

        System.out.println("Digite o nome do livro que deseja remover :");
        String remover = LeitorConsole.entrada.nextLine();

        while (remover.isBlank()) {
            System.out.println("Erro: Este campo não pode ficar vazio.");
            System.out.println("Digite o nome do livro que deseja remover :");
            remover = LeitorConsole.entrada.nextLine();
        }

        final String nomeRemover = remover;
        Optional<Livro> remove = estantes.stream()
                .filter(livro -> livro.getTitulo().equalsIgnoreCase(nomeRemover))
                .findFirst();

        if (remove.isPresent()) {
            estantes.remove(remove.get());
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    // Edita os dados de um livro já cadastrado
    public static void editarLivro() {
        boolean continuar = false;

        while (!continuar) {
            System.out.println("Digite o nome do livro que deseja editar : ");
            String livroEditar = LeitorConsole.entrada.nextLine();

            while (livroEditar.isBlank()) {
                System.out.println("Erro: Este campo não pode ficar vazio.");
                System.out.println("Digite o nome do livro que deseja editar : ");
                livroEditar = LeitorConsole.entrada.nextLine();
            }

            final String tituloEditar = livroEditar;
            Optional<Livro> editar = estantes.stream()
                    .filter(livro -> livro.getTitulo().equalsIgnoreCase(tituloEditar))
                    .findFirst();

            if (editar.isPresent()) {
                Livro livroEncontrado = editar.get();
                System.out.println("Livro encontrado, o que deseja editar?");

                int editEscolha = 0;
                while (editEscolha != 7) {
                    System.out.println("1- Tudo");
                    System.out.println("2- Nome");
                    System.out.println("3- Autor");
                    System.out.println("4- Ano");
                    System.out.println("5- Código");
                    System.out.println("6- Editar outro livro");
                    System.out.println("7- Parar de Editar");

                    verificarNumero();
                    editEscolha = LeitorConsole.entrada.nextInt();
                    LeitorConsole.entrada.nextLine();

                    switch (editEscolha) {
                        case 1 -> {
                            estantes.remove(livroEncontrado);
                            cadastrarLivro();
                        }
                        case 2 -> {
                            System.out.println("Nome do livro : ");
                            String nome = LeitorConsole.entrada.nextLine();

                            while (nome.isBlank()) {
                                System.out.println("Erro: Este campo não pode ficar vazio.");
                                System.out.println("Nome do livro : ");
                                nome = LeitorConsole.entrada.nextLine();
                            }
                            livroEncontrado.setTitulo(nome);
                        }
                        case 3 -> {
                            System.out.println("Nome do Autor : ");
                            String autor = LeitorConsole.entrada.nextLine();

                            while (autor.isBlank()) {
                                System.out.println("Erro: Este campo não pode ficar vazio.");
                                System.out.println("Nome do Autor : ");
                                autor = LeitorConsole.entrada.nextLine();
                            }
                            livroEncontrado.setAutor(autor);
                        }
                        case 4 -> {
                            int novoAno = lerAnoValido();
                            livroEncontrado.setAno(novoAno);
                        }
                        case 5 -> {
                            boolean codigoValido = false;
                            while (!codigoValido) {
                                System.out.println("Código do livro : ");
                                verificarNumero();
                                int codigo = LeitorConsole.entrada.nextInt();

                                Optional<Livro> codigoDuplicado = estantes.stream()
                                        .filter(livro -> livro.getCodigo() == codigo)
                                        .findFirst();

                                if (codigoDuplicado.isPresent()) {
                                    System.out.println("Esse código já existe! digite outro.");
                                } else {
                                    livroEncontrado.setCodigo(codigo);
                                    codigoValido = true;
                                }
                            }
                        }
                        case 6 -> {
                            buscarLivro();
                            cadastrarLivro();
                        }
                        case 7 -> {
                            System.out.println("Você saiu da área de edição");
                            continuar = true;
                        }
                        default -> System.out.println("Escolha uma opção válida");
                    }
                }

            } else {
                System.out.println("Livro não encontrado, deseja tentar novamente:");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");

                verificarNumero();
                int umOuDois = LeitorConsole.entrada.nextInt();
                LeitorConsole.entrada.nextLine();

                switch (umOuDois) {
                    case 1 -> continuar = false;
                    case 2 -> {
                        System.out.println("Você saiu da área de edição");
                        continuar = true;
                    }
                    default -> System.out.println("Escolha uma opção válida");
                }
            }
        }
    }

    // Garante que o próximo valor digitado seja um número inteiro válido
    public static void verificarNumero() {
        while (!LeitorConsole.entrada.hasNextInt()) {
            System.out.println("Erro: Este campo aceita apenas números");
            System.out.print("Digite novamente: ");
            LeitorConsole.entrada.next();
        }
    }

}