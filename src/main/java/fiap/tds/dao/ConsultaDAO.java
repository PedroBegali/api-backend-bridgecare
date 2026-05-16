package fiap.tds.dao;

import fiap.tds.tdbentities.Consulta;
import fiap.tds.infra.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public List<Consulta> listarProximasBeneficiario(int idBeneficiario) throws SQLException {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_BC_CONSULTA WHERE id_beneficiario = ? AND dt_consulta >= TRUNC(SYSDATE) ORDER BY dt_consulta ASC, hr_consulta ASC";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idBeneficiario);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Consulta c = new Consulta(
                            rs.getInt("id_consulta"),
                            rs.getString("ds_prontuario"),
                            rs.getDate("dt_consulta").toLocalDate(),
                            rs.getTime("hr_consulta").toLocalTime(),
                            rs.getInt("id_beneficiario"),
                            rs.getInt("id_dentista")
                    );
                    lista.add(c);
                }
            }
        }
        return lista;
    }

    public List<Consulta> listarHistoricoBeneficiario(int idBeneficiario) throws SQLException {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_BC_CONSULTA WHERE id_beneficiario = ? AND dt_consulta < TRUNC(SYSDATE) ORDER BY dt_consulta DESC, hr_consulta DESC";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idBeneficiario);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Consulta c = new Consulta(
                            rs.getInt("id_consulta"),
                            rs.getString("ds_prontuario"),
                            rs.getDate("dt_consulta").toLocalDate(),
                            rs.getTime("hr_consulta").toLocalTime(),
                            rs.getInt("id_beneficiario"),
                            rs.getInt("id_dentista")
                    );
                    lista.add(c);
                }
            }
        }
        return lista;
    }

    public List<Consulta> listarProximasDentista(int idDentista) throws SQLException {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_BC_CONSULTA WHERE id_dentista = ? AND dt_consulta >= TRUNC(SYSDATE) ORDER BY dt_consulta ASC, hr_consulta ASC";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDentista);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Consulta c = new Consulta(
                            rs.getInt("id_consulta"),
                            rs.getString("ds_prontuario"),
                            rs.getDate("dt_consulta").toLocalDate(),
                            rs.getTime("hr_consulta").toLocalTime(),
                            rs.getInt("id_beneficiario"),
                            rs.getInt("id_dentista")
                    );
                    lista.add(c);
                }
            }
        }
        return lista;
    }

    public List<Consulta> listarPendentesDentista(int idDentista) throws SQLException {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_BC_CONSULTA WHERE id_dentista = ? AND dt_consulta < TRUNC(SYSDATE) AND ds_prontuario IS NULL";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDentista);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Consulta c = new Consulta(
                            rs.getInt("id_consulta"),
                            rs.getString("ds_prontuario"),
                            rs.getDate("dt_consulta").toLocalDate(),
                            rs.getTime("hr_consulta").toLocalTime(),
                            rs.getInt("id_beneficiario"),
                            rs.getInt("id_dentista")
                    );
                    lista.add(c);
                }
            }
        }
        return lista;
    }

    public void atualizarProntuario(int idConsulta, String dsProntuario) throws SQLException {
        String sql = "UPDATE T_BC_CONSULTA SET ds_prontuario = ? WHERE id_consulta = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dsProntuario);
            ps.setInt(2, idConsulta);
            ps.executeUpdate();
        }
    }

    public void cadastrar(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO T_BC_CONSULTA (ds_prontuario, dt_consulta, hr_consulta, id_beneficiario, id_dentista) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, consulta.getDsProntuario());
            ps.setDate(2, Date.valueOf(consulta.getDtConsulta()));
            ps.setTime(3, Time.valueOf(consulta.getHrConsulta()));
            ps.setInt(4, consulta.getIdBeneficiario());
            ps.setInt(5, consulta.getIdDentista());

            ps.executeUpdate();
        }
    }

    public List<Consulta> listar() throws SQLException {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM T_BC_CONSULTA";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Consulta c = new Consulta(
                        rs.getInt("id_consulta"),
                        rs.getString("ds_prontuario"),
                        rs.getDate("dt_consulta").toLocalDate(),
                        rs.getTime("hr_consulta").toLocalTime(),
                        rs.getInt("id_beneficiario"),
                        rs.getInt("id_dentista")
                );
                consultas.add(c);
            }
        }
        return consultas;
    }

    public void atualizar(Consulta consulta) throws SQLException {
        String sql = "UPDATE T_BC_CONSULTA SET ds_prontuario = ?, dt_consulta = ?, hr_consulta = ?, id_beneficiario = ?, id_dentista = ? WHERE id_consulta = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, consulta.getDsProntuario());
            ps.setDate(2, Date.valueOf(consulta.getDtConsulta()));
            ps.setTime(3, Time.valueOf(consulta.getHrConsulta()));
            ps.setInt(4, consulta.getIdBeneficiario());
            ps.setInt(5, consulta.getIdDentista());
            ps.setInt(6, consulta.getIdConsulta());

            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM T_BC_CONSULTA WHERE id_consulta = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}