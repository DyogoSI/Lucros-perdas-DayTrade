package dao;

import model.Lucro;
import dao.Conexao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LucroDao {

    public void salvar(Lucro lucro) throws SQLException {
        Connection connection = null;
        try {
            connection = Conexao.getConnection();
            String sql = "INSERT INTO lucro (data, valor) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            // Formata a data para o formato correto no banco de dados
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dataString = sdf.format(lucro.getData());
            
            stmt.setString(1, dataString);
            stmt.setDouble(2, lucro.getValor());
            stmt.executeUpdate();
            stmt.close();
        } finally {
            Conexao.closeConnection(connection);
        }
    }

    public List<Lucro> listarTodos() throws SQLException {
        Connection connection = null;
        List<Lucro> lucros = new ArrayList<>();
        try {
            connection = Conexao.getConnection();
            String sql = "SELECT * FROM lucro";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Lucro lucro = new Lucro();
                lucro.setId(rs.getInt("id"));

                // Converte a data do banco para o formato Date
                String dataString = rs.getString("data");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date data = sdf.parse(dataString);

                lucro.setData(data);
                lucro.setValor(rs.getDouble("valor"));
                lucros.add(lucro);
            }
            rs.close();
            stmt.close();
        } catch (ParseException e) {
            throw new SQLException("Erro ao converter a data", e);
        } finally {
            Conexao.closeConnection(connection);
        }
        return lucros;
    }
}
