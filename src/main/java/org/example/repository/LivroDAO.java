package org.example.repository;

import com.sun.net.httpserver.Authenticator;
import org.example.infraestrutura.database.Conexao;
import org.example.model.Emprestimo;
import org.example.model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public static void inserirLivro(Livro livro) throws SQLException{
        String query = "INSERT INTO livros(titulo, autor, ano, disponivel) VALUES (?,?,?, true)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.executeUpdate();
        }
    }

    public static boolean livroExiste(Livro livro) throws SQLException{
        String query = "SELECT titulo FROM livros where titulo = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, livro.getTitulo());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                return true;
            }
        }

        return false;
    }

    public static List<Livro> listarLivros()throws SQLException{
        String query = "SELECT id, titulo, autor, ano, disponivel FROM livros";

        List<Livro> livros = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int ano = rs.getInt("ano");
                boolean disponivel = rs.getBoolean("disponivel");

                var livro = new Livro(id, titulo, autor, ano, disponivel);
                livros.add(livro);
            }
        }

        return livros;
    }

    public static void indisponibilizarLivro(Emprestimo emprestimo) throws SQLException{
        String query = "UPDATE livros SET disponivel = false WHERE id = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, emprestimo.getId_livro());
            stmt.executeUpdate();
        }
    }
}
