package fiap.tds.dao;

import fiap.tds.tdbentities.ProgramaSocial;
import fiap.tds.infra.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaSocialDAO {

    public void cadastrar(ProgramaSocial programa) throws SQLException {
        String sql = "INSERT INTO T_BC_PROGRAMA_SOCIAL (nm_programa_social) VALUES (?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, programa.getNmProgramaSocial());
            ps.executeUpdate();
        }
    }

    public List<ProgramaSocial> listar() throws SQLException {
        List<ProgramaSocial> programas = new ArrayList<>();
        String sql = "SELECT * FROM T_BC_PROGRAMA_SOCIAL";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProgramaSocial p = new ProgramaSocial(
                        rs.getInt("id_programa_social"),
                        rs.getString("nm_programa_social")
                );
                programas.add(p);
            }
        }
        return programas;
    }

    public void atualizar(ProgramaSocial programa) throws SQLException {
        String sql = "UPDATE T_BC_PROGRAMA_SOCIAL SET nm_programa_social = ? WHERE id_programa_social = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, programa.getNmProgramaSocial());
            ps.setInt(2, programa.getIdProgramaSocial());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM T_BC_PROGRAMA_SOCIAL WHERE id_programa_social = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}