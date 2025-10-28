package org.example.view;

import org.example.model.Emprestimo;
import org.example.model.Livro;
import org.example.model.Usuario;
import org.example.repository.EmprestimoDAO;
import org.example.repository.LivroDAO;
import org.example.service.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaView {
    static Scanner leia = new Scanner(System.in);

    public static void mostrarMenu(){
        System.out.println("----MENU----");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Cadastrar Usuário");
        System.out.println("3 - Consultar Todos os Livros Cadastrados");
        System.out.println("4 - Registrar Empréstimo de Livro");
        System.out.println("5 - Registrar Devolução de Livro");
        System.out.println("0 - Sair");
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
        leia.nextLine();

        switch (opcao){
            case 1 ->{
                informacoesLivro();
            }
            case 2 ->{
                informacoesUsuario();
            }
            case 3 ->{
                listarLivros();
            }
            case 4 ->{
                informacoesEmprestimo();
            }
            case 5 ->{

            }
            case 0 ->{
                sair = true;
            }

        }

        while(sair == false){
            executarEscolha();
        }
    }

    public static void informacoesLivro(){
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

    public static void informacoesUsuario(){
        System.out.println("----CADASTRAR USUÁRIO----");
        System.out.println("Digite o nome do usuário: ");
        String nome = leia.nextLine();
        System.out.println("Digite o email do usuário: ");
        String email = leia.nextLine();
        System.out.println("Digite o telefone do usuário: ");
        String telefone = leia.nextLine();

        var usuario = new Usuario(nome, email, telefone);
        Service.cadastrarUsuario(usuario);
    }

    public static List<Livro> exibirLivrosCadastrados(List <Livro> lista){

        List<Livro> listaLivros = new ArrayList<>();

        for( Livro livro : lista){
            System.out.println("------------------");
            System.out.println("ID do livro: " + livro.getId());
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano de publicação: " + livro.getAno());
            System.out.println("Disponibilidade: " + livro.getDisponivel());
            System.out.println("------------------");

            listaLivros.add(livro);
        }

        return listaLivros;
    }

    public static void listarLivros(){
        System.out.println("---LISTAR LIVROS---");

        var dao = new LivroDAO();

        try{
            List<Livro> livros = dao.listarLivros();
            exibirLivrosCadastrados(livros);
        }catch(SQLException erro){
            erro.printStackTrace();
        }
    }


    public static void informacoesEmprestimo(){

        LocalDate dataEmprestimo = null;
        LocalDate dataDevolucao = null;

        System.out.println("----CADASTRAR EMPRÉSTIMO----");
        System.out.println("Digite o ID do livro do empréstimo: ");
        int id_livro = leia.nextInt();
        leia.nextLine();
        System.out.println("Digite o ID do usuário que fez o empréstimo: ");
        int id_usuario = leia.nextInt();
        leia.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            System.out.println("Digite a data do empréstimo: ");
            String dataE = leia.nextLine();
            dataEmprestimo = LocalDate.parse(dataE);
        }catch(DateTimeParseException erro){
            System.out.println("Formato de data inválido! Por favor, use o formato dd/MM/yyyy.");
        }
        try {
            System.out.println("Digite a data de devolução: ");
            String dataD = leia.nextLine();
            dataDevolucao = LocalDate.parse(dataD);
        }catch(DateTimeParseException erro){
            System.out.println("Formato de data inválido! Por favor, use o formato dd/MM/yyyy.");
        }

        var emprestimo = new Emprestimo(id_livro, id_usuario, dataEmprestimo, dataDevolucao);
        Service.cadastrarEmprestimo(emprestimo);
    }
    }

