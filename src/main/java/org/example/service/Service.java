package org.example.service;

import org.example.model.Emprestimo;
import org.example.model.Livro;
import org.example.model.Usuario;
import org.example.repository.EmprestimoDAO;
import org.example.repository.LivroDAO;
import org.example.repository.UsuarioDAO;
import org.example.view.BibliotecaView;

import java.sql.SQLException;
import java.util.List;

public class Service {

    public static void cadastrarLivro(Livro livro){
        var dao = new LivroDAO();

        try{
            if(dao.livroExiste(livro)){
                throw new RuntimeException("Livro já está cadastrado!");
            }

            dao.inserirLivro(livro);

        } catch (SQLException erro) {
            erro.printStackTrace();
        }

    }

    public static void cadastrarUsuario(Usuario usuario){
        var dao = new UsuarioDAO();

        try{
            if(dao.usuarioExiste(usuario)){
                throw new RuntimeException("Usuario já existe!");
            }

            dao.inserirUsuario(usuario);

        }catch(SQLException erro){
            erro.printStackTrace();
        }
    }

    public static void cadastrarEmprestimo(Emprestimo emprestimo){
        var dao = new EmprestimoDAO();
        var daoL = new LivroDAO();

        try{
            dao.cadastrarEmpestimo(emprestimo);
            daoL.indisponibilizarLivro(emprestimo);

        }catch (SQLException erro){
            erro.printStackTrace();
        }
    }


}
