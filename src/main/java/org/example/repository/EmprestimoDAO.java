package org.example.repository;

import org.example.infraestrutura.database.Conexao;
import org.example.model.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmprestimoDAO {

    public static void cadastrarEmpestimo(Emprestimo emprestimo) throws SQLException {
        String query = "INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo, data_devolucao) VALUES (?,?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, emprestimo.getId_livro());
            stmt.setInt(2, emprestimo.getId_usuario());
            stmt.setObject(3, emprestimo.getDataEmprestimo());
            stmt.setObject(4, emprestimo.getDataDevolucao());
            stmt.executeUpdate();
        }
    }

}
