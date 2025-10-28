package org.example.service;

import org.example.model.Livro;
import org.example.repository.LivroDAO;

import java.sql.SQLException;

public class Service {

    public static void cadastrarLivro(Livro livro)throws SQLException {
        var dao = new LivroDAO();

        if(dao.livroExiste(livro.getTitulo())){
            throw new RuntimeException("Livro já está cadastrado!");
        }

        dao.inserirLivro(livro);
    }
}
