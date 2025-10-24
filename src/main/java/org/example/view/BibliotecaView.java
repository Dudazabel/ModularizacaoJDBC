package org.example.view;

import org.example.model.Livro;
import org.example.service.Service;

import java.util.Scanner;

public class BibliotecaView {
    static Scanner leia = new Scanner(System.in);

    public static void mostrarMenu(){
        System.out.println("----MENU----");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Consultar Todos od Livros Cadastrados");
        System.out.println("3 - Registrar Empréstimo de Livro");
        System.out.println("4 - Registrar Devolução de Livro");
    }

    public static int capturarOpcao(){
        System.out.println("> ");
        int escolha = leia.nextInt();
        return escolha;
    }

    public static void executarEscolha(){
        boolean sair = false;

        mostrarMenu();
        int opcao = capturarOpcao();

        switch (opcao){
            case 1 ->{
                cadastrarUsuario();
            }
            case 2 ->{

            }
            case 3 ->{

            }
            case 4 ->{

            }
            case 0 ->{
                sair = true;
            }

        }

        while(sair == false){
            executarEscolha();
        }
    }

    public static void cadastrarUsuario(){
        System.out.println("----CADASTRAR LIVRO----");
        System.out.println("Digite o título do livro: ");
        String titulo = leia.nextLine();
        System.out.println("Digite o autor do livro: ");
        String autor = leia.nextLine();
        System.out.println("Digite o ano de lançamento do livro: ");
        int ano = leia.nextInt();
        leia.nextLine();

        var livro = new Livro(titulo, autor, ano);
        Service.cadastrarLivro(livro);
    }

}
