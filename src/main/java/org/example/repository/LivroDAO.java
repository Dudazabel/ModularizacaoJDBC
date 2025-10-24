package org.example.repository;

import org.example.model.Livro;

import java.sql.SQLException;

public class LivroDAO {

    public static void inserirLivro(Livro livro) throws SQLException{
        String query = "INSERT INTO livros(titulo, autor, ano, disponibilidade) VALUES (?,?,?, true)";
    }
}
