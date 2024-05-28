package br.com.thiago.literalura.catalogo_de_livros;
import br.com.thiago.literalura.catalogo_de_livros.model.Livro;
import br.com.thiago.literalura.catalogo_de_livros.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {

    @Autowired
    private LivroService livroService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Buscar livro por título");
            System.out.println("2. Listar todos os livros");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.print("Digite o título do livro: ");
                String titulo = scanner.nextLine();
                Livro livro = livroService.buscarLivroPorTitulo(titulo);
                System.out.println(livro);
            } else if (opcao == 2) {
                livroService.listarLivros().forEach(System.out::println);
            } else if (opcao == 3) {
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }
}

