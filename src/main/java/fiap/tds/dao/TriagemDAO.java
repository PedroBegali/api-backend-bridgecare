package fiap.tds.dao;

import fiap.tds.tdbentities.Triagem;
import fiap.tds.infra.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TriagemDAO {

    public List<Triagem> listarProximas() throws SQLException {
        List<Triagem> lista = new ArrayList<>();


        String sql = "SELECT t.id_triagem, t.dt_triagem, t.hr_inicial, t.hr_final, t.id_endereco, " +
                "e.nm_local, e.nm_logradouro, e.nr_logradouro, e.nm_bairro, e.nm_cidade " +
                "FROM T_BC_TRIAGEM t " +
                "LEFT JOIN T_BC_ENDERECO e ON t.id_endereco = e.id_endereco " +
                "WHERE t.dt_triagem >= TRUNC(SYSDATE) " +
                "ORDER BY t.dt_triagem ASC";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Triagem t = new Triagem();
                t.setIdTriagem(rs.getInt("id_triagem"));

                if (rs.getDate("dt_triagem") != null) {
                    t.setDtTriagem(rs.getDate("dt_triagem").toLocalDate());
                }

                if (rs.getTimestamp("hr_inicial") != null) {
                    t.setHrInicial(rs.getTimestamp("hr_inicial").toLocalDateTime().toLocalTime());
                }
                if (rs.getTimestamp("hr_final") != null) {
                    t.setHrFinal(rs.getTimestamp("hr_final").toLocalDateTime().toLocalTime());
                }

                t.setIdEndereco(rs.getInt("id_endereco"));
                t.setNmLocal(rs.getString("nm_local"));
                t.setNmLogradouro(rs.getString("nm_logradouro"));
                t.setNrLogradouro(rs.getInt("nr_logradouro"));
                t.setNmBairro(rs.getString("nm_bairro"));
                t.setNmCidade(rs.getString("nm_cidade"));


                lista.add(t);
            }
        }
        return lista;
    }

    public void cadastrar(Triagem triagem) throws SQLException {
        String sql = "INSERT INTO T_BC_TRIAGEM (dt_triagem, hr_inicial, hr_final, id_endereco) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(triagem.getDtTriagem()));
            ps.setTime(2, java.sql.Time.valueOf(triagem.getHrInicial()));
            ps.setTime(3, java.sql.Time.valueOf(triagem.getHrFinal()));
            ps.setInt(4, triagem.getIdEndereco());
            ps.executeUpdate();
        }
    }
}