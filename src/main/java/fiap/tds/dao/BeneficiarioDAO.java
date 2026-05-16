package fiap.tds.dao;

import fiap.tds.tdbentities.Beneficiario;
import fiap.tds.infra.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeneficiarioDAO {

    public void cadastrar(Beneficiario beneficiario) throws SQLException {
        String sql = "INSERT INTO T_BC_BENEFICIARIO (id_pre_beneficiario) VALUES (?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, beneficiario.getIdPreBeneficiario());
            ps.executeUpdate();
        }
    }

    public List<Beneficiario> listar() throws SQLException {
        List<Beneficiario> lista = new ArrayList<>();

        String sql = "SELECT b.id_beneficiario, b.id_pre_beneficiario, p.nm_pre_beneficiario, p.cpf_pre_beneficiario " +
                "FROM T_BC_BENEFICIARIO b " +
                "JOIN T_BC_PRE_BENEFICIARIO p ON b.id_pre_beneficiario = p.id_pre_beneficiario";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Beneficiario b = new Beneficiario();
                b.setIdBeneficiario(rs.getInt("id_beneficiario"));
                b.setIdPreBeneficiario(rs.getInt("id_pre_beneficiario"));
                b.setNmPreBeneficiario(rs.getString("nm_pre_beneficiario"));
                b.setCpfPreBeneficiario(rs.getString("cpf_pre_beneficiario"));

                lista.add(b);
            }
        }
        return lista;
    }

    public void atualizar(Beneficiario beneficiario) throws SQLException {
        String sql = "UPDATE T_BC_BENEFICIARIO SET id_pre_beneficiario = ? WHERE id_beneficiario = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, beneficiario.getIdPreBeneficiario());
            ps.setInt(2, beneficiario.getIdBeneficiario());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM T_BC_BENEFICIARIO WHERE id_beneficiario = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}