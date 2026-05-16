package fiap.tds.dao;

import fiap.tds.tdbentities.Endereco;
import fiap.tds.infra.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    public int cadastrar(Endereco endereco, Connection conn) throws SQLException {

        String sql = "INSERT INTO T_BC_ENDERECO (nm_local, nm_logradouro, nr_logradouro, nm_bairro, nm_cidade, nr_cep) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID_ENDERECO"})) {

            ps.setString(1, endereco.getNmLocal());
            ps.setString(2, endereco.getNmLogradouro());
            ps.setInt(3, endereco.getNrLogradouro());
            ps.setString(4, endereco.getNmBairro());
            ps.setString(5, endereco.getNmCidade());
            ps.setString(6, endereco.getNrCep());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Retorna o ID gerado para o PreBeneficiarioDAO usar
                }
            }
        }
        throw new SQLException("Falha ao criar endereço, ID não obtido.");
    }

    public List<Endereco> listar() throws SQLException {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM T_BC_ENDERECO";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Endereco e = new Endereco(
                        rs.getInt("id_endereco"),
                        rs.getString("nm_local"),
                        rs.getString("nm_logradouro"),
                        rs.getInt("nr_logradouro"),
                        rs.getString("nm_bairro"),
                        rs.getString("nm_cidade"),
                        rs.getString("nr_cep") // Alterado para getString
                );
                enderecos.add(e);
            }
        }
        return enderecos;
    }

    public void atualizar(Endereco endereco) throws SQLException {
        String sql = "UPDATE T_BC_ENDERECO SET nm_local = ?, nm_logradouro = ?, nr_logradouro = ?, nm_bairro = ?, nm_cidade = ?, nr_cep = ? WHERE id_endereco = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, endereco.getNmLocal());
            ps.setString(2, endereco.getNmLogradouro());
            ps.setInt(3, endereco.getNrLogradouro());
            ps.setString(4, endereco.getNmBairro());
            ps.setString(5, endereco.getNmCidade());
            ps.setString(6, endereco.getNrCep()); // Alterado para setString
            ps.setInt(7, endereco.getIdEndereco());

            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sqlEndereco = "DELETE FROM T_BC_ENDERECO WHERE id_endereco = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlEndereco)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}