package org.example.repository;

import org.example.infraestrutura.database.Conexao;
import org.example.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public static void inserirUsuario(Usuario usuario)throws SQLException{
        String query = "INSERT INTO usuarios(nome, email, telefone) VALUES (?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.executeUpdate();
        }
    }

    public static boolean usuarioExiste(Usuario usuario)throws SQLException{
        String query = "SELECT nome FROM usuarios WHERE nome = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, usuario.getNome());

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                return true;
            }

            return false;
        }
    }
}
