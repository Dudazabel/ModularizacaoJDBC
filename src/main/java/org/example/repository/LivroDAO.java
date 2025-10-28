package org.example.repository;

import org.example.infraestrutura.database.Conexao;
import org.example.model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroDAO {

    public static void inserirLivro(Livro livro) throws SQLException{
        String query = "INSERT INTO livros(titulo, autor, ano, disponibilidade) VALUES (?,?,?, true)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.executeUpdate();
        }
    }

    public static boolean livroExiste(String titulo) throws SQLException{
        String query = "SELECT titulo FROM livros where titulo = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            stmt.setString(1, titulo);

            while(rs.next()){
                return true;
            }
        }

        return false;
    }
}
