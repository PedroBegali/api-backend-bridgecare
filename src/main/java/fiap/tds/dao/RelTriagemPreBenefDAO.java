package fiap.tds.dao;

import fiap.tds.tdbentities.RelTriagemPreBenef;
import fiap.tds.infra.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelTriagemPreBenefDAO {

    public void atualizarTriagem(int idPreBeneficiario, int idNovaTriagem) throws SQLException {
        String sql = "UPDATE T_BC_REL_TRIAGEM_PRE_BENEF SET id_triagem = ? WHERE id_pre_beneficiario = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNovaTriagem);
            ps.setInt(2, idPreBeneficiario);
            ps.executeUpdate();
        }
    }

    public void cadastrar(RelTriagemPreBenef rel) throws SQLException {
        String sql = "INSERT INTO T_BC_REL_TRIAGEM_PRE_BENEF (id_triagem, id_pre_beneficiario) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rel.getIdTriagem());
            ps.setInt(2, rel.getIdPreBeneficiario());
            ps.executeUpdate();
        }
    }

    public List<RelTriagemPreBenef> listar() throws SQLException {
        List<RelTriagemPreBenef> relacoes = new ArrayList<>();
        String sql = "SELECT * FROM T_BC_REL_TRIAGEM_PRE_BENEF";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RelTriagemPreBenef r = new RelTriagemPreBenef(
                        rs.getInt("id_triagem"),
                        rs.getInt("id_pre_beneficiario")
                );
                relacoes.add(r);
            }
        }
        return relacoes;
    }

    public void excluir(int idTriagem, int idPreBeneficiario) throws SQLException {
        String sql = "DELETE FROM T_BC_REL_TRIAGEM_PRE_BENEF WHERE id_triagem = ? AND id_pre_beneficiario = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTriagem);
            ps.setInt(2, idPreBeneficiario);
            ps.executeUpdate();
        }
    }
}