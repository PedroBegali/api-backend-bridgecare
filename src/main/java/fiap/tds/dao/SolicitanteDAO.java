package fiap.tds.dao;

import fiap.tds.tdbentities.Solicitante;
import fiap.tds.infra.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitanteDAO {

    public void cadastrar(Solicitante s) throws SQLException {
        // Veja que no VALUES, o 4º parâmetro está fixado como 'A'
        String sql = "INSERT INTO T_BC_SOLICITANTE (nm_solicitante, nm_responsavel, st_libras, st_solicitante, nr_telefone, email) VALUES (?, ?, ?, 'A', ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getNmSolicitante());
            ps.setString(2, s.getNmResponsavel());
            ps.setString(3, s.getStLibras());
            ps.setString(4, s.getNrTelefone());
            ps.setString(5, s.getEmail());

            ps.executeUpdate();
        }
    }

    public List<Solicitante> listar() throws SQLException {
        List<Solicitante> lista = new ArrayList<>();

        String sql = "SELECT * FROM T_BC_SOLICITANTE WHERE st_solicitante = 'A'";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Solicitante s = new Solicitante();
                s.setIdSolicitante(rs.getInt("id_solicitante"));
                s.setNmSolicitante(rs.getString("nm_solicitante"));
                s.setNmResponsavel(rs.getString("nm_responsavel"));
                s.setStLibras(rs.getString("st_libras"));
                s.setStSolicitante(rs.getString("st_solicitante"));
                s.setNrTelefone(rs.getString("nr_telefone"));
                s.setEmail(rs.getString("email"));

                lista.add(s);
            }
        }
        return lista;
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM T_BC_SOLICITANTE WHERE id_solicitante = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}