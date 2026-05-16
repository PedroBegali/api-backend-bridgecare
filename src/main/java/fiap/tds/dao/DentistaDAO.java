package fiap.tds.dao;

import fiap.tds.tdbentities.Dentista;
import fiap.tds.infra.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DentistaDAO {

    public void atualizarStatus(int idDentista, String novoStatus) throws SQLException {
        String sql = "UPDATE T_BC_DENTISTA SET st_dentista = ? WHERE id_dentista = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novoStatus);
            ps.setInt(2, idDentista);
            ps.executeUpdate();
        }
    }

    public void cadastrar(Dentista dentista) throws SQLException {
        String sql = "INSERT INTO T_BC_DENTISTA (nm_dentista, dt_nascimento, sx_dentista, cpf_dentista, cro_dentista, ds_especialidade, st_dentista) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dentista.getNmDentista());
            ps.setDate(2, Date.valueOf(dentista.getDtNascimento()));
            ps.setString(3, dentista.getSxDentista());
            ps.setString(4, dentista.getCpfDentista());
            ps.setString(5, dentista.getCroDentista());
            ps.setString(6, dentista.getDsEspecialidade());
            ps.setString(7, dentista.getStDentista());

            ps.executeUpdate();
        }
    }

    public List<Dentista> listar() throws SQLException {
        List<Dentista> dentistas = new ArrayList<>();
        String sql = "SELECT * FROM T_BC_DENTISTA";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Dentista d = new Dentista(
                        rs.getInt("id_dentista"),
                        rs.getString("nm_dentista"),
                        rs.getDate("dt_nascimento").toLocalDate(),
                        rs.getString("sx_dentista"),
                        rs.getString("cpf_dentista"),
                        rs.getString("cro_dentista"),
                        rs.getString("ds_especialidade"),
                        rs.getString("st_dentista")
                );
                dentistas.add(d);
            }
        }
        return dentistas;
    }

    public Dentista buscarPorId(int id) throws SQLException {
        Dentista dentista = null;
        String sql = "SELECT * FROM T_BC_DENTISTA WHERE id_dentista = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dentista = new Dentista(
                            rs.getInt("id_dentista"),
                            rs.getString("nm_dentista"),
                            rs.getDate("dt_nascimento").toLocalDate(),
                            rs.getString("sx_dentista"),
                            rs.getString("cpf_dentista"),
                            rs.getString("cro_dentista"),
                            rs.getString("ds_especialidade"),
                            rs.getString("st_dentista")
                    );
                }
            }
        }
        return dentista;
    }

    public void atualizar(Dentista dentista) throws SQLException {
        String sql = "UPDATE T_BC_DENTISTA SET nm_dentista = ?, dt_nascimento = ?, sx_dentista = ?, cpf_dentista = ?, cro_dentista = ?, ds_especialidade = ?, st_dentista = ? WHERE id_dentista = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dentista.getNmDentista());
            ps.setDate(2, Date.valueOf(dentista.getDtNascimento()));
            ps.setString(3, dentista.getSxDentista());
            ps.setString(4, dentista.getCpfDentista());
            ps.setString(5, dentista.getCroDentista());
            ps.setString(6, dentista.getDsEspecialidade());
            ps.setString(7, dentista.getStDentista());
            ps.setInt(8, dentista.getIdDentista());

            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM T_BC_DENTISTA WHERE id_dentista = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}