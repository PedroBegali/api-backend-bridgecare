package fiap.tds.dao;

import fiap.tds.tdbentities.PreBeneficiario;
import fiap.tds.infra.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreBeneficiarioDAO {

    public void atualizarDadosCompletos(PreBeneficiario pb) throws SQLException {
        String sql = "UPDATE T_BC_PRE_BENEFICIARIO SET nm_pre_beneficiario = ?, cpf_pre_beneficiario = ?, ds_problema_dentario = ? WHERE id_pre_beneficiario = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pb.getNmPreBeneficiario());
            ps.setString(2, pb.getCpfPreBeneficiario());
            ps.setString(3, pb.getDsProblemaDentario());
            ps.setInt(4, pb.getIdPreBeneficiario());
            ps.executeUpdate();
        }
    }

    public void atualizarStatus(int id, String novoStatus) throws SQLException {
        String sql = "UPDATE T_BC_PRE_BENEFICIARIO SET st_situacao = ? WHERE id_pre_beneficiario = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, novoStatus);
            ps.setInt(2, id);

            ps.executeUpdate();
        }
    }

    public List<PreBeneficiario> listarPorSituacao(String situacao) throws SQLException {
        List<PreBeneficiario> fila = new ArrayList<>();

        String sql = "SELECT * FROM T_BC_PRE_BENEFICIARIO WHERE st_situacao = ? AND (st_direcionamento IS NULL OR st_direcionamento != 'N')";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, situacao);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PreBeneficiario pb = new PreBeneficiario(
                            rs.getInt("id_pre_beneficiario"),
                            rs.getString("nm_pre_beneficiario"),
                            rs.getString("cpf_pre_beneficiario"),
                            rs.getString("sx_pre_beneficiario"),
                            rs.getString("ds_problema_dentario"),
                            rs.getString("st_situacao"),
                            rs.getInt("id_programa_social"),
                            rs.getInt("id_solicitante"),
                            rs.getInt("id_endereco")
                    );
                    fila.add(pb);
                }
            }
        }

        return fila;
    }
    public PreBeneficiario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM T_BC_PRE_BENEFICIARIO WHERE id_pre_beneficiario = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PreBeneficiario(
                            rs.getInt("id_pre_beneficiario"),
                            rs.getString("nm_pre_beneficiario"),
                            rs.getString("cpf_pre_beneficiario"),
                            rs.getString("sx_pre_beneficiario"),
                            rs.getString("ds_problema_dentario"),
                            rs.getString("st_situacao"),
                            rs.getInt("id_programa_social"),
                            rs.getInt("id_solicitante"),
                            rs.getInt("id_endereco")
                    );
                }
            }
        }
        return null;
    }

    public void cadastrar(PreBeneficiario pb) throws SQLException {
        String sqlPre = "INSERT INTO T_BC_PRE_BENEFICIARIO (nm_pre_beneficiario, dt_nascimento, cpf_pre_beneficiario, sx_pre_beneficiario, ds_problema_dentario, st_situacao, id_programa_social, id_endereco, id_solicitante, vl_renda_familiar, st_programa_gov, ds_escolaridade_resp, ds_termo_autorizacao, ds_boletim_ocorrencia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";        String sqlUpdateSolicitante = "UPDATE T_BC_SOLICITANTE SET st_solicitante = 'I' WHERE id_solicitante = ?";

        // NOVA QUERY: Para inserir na tabela associativa N:M
        String sqlRelacaoTriagem = "INSERT INTO T_BC_REL_TRIAGEM_PRE_BENEF (id_triagem, id_pre_beneficiario) VALUES (?, ?)";

        Connection conn = null;
        try {
            conn = DatabaseConfig.getConnection();
            conn.setAutoCommit(false); // Transação iniciada

            // 1. Cadastra o endereço residencial do Pré-Beneficiário
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            int idEnderecoGerado = enderecoDAO.cadastrar(pb.getEndereco(), conn);

            int idPreBeneficiarioGerado = 0;

            // 2. Cadastra o Pré-Beneficiário pedindo o ID autogerado de volta (ID_PRE_BENEFICIARIO)
            try (PreparedStatement ps = conn.prepareStatement(sqlPre, new String[]{"ID_PRE_BENEFICIARIO"})) {
                ps.setString(1, pb.getNmPreBeneficiario());
                ps.setDate(2, java.sql.Date.valueOf(pb.getDtNascimento()));
                ps.setString(3, pb.getCpfPreBeneficiario());
                ps.setString(4, pb.getSxPreBeneficiario());
                ps.setString(5, pb.getDsProblemaDentario());
                ps.setString(6, pb.getStSituacao());
                ps.setInt(7, pb.getIdProgramaSocial());
                ps.setInt(8, idEnderecoGerado);
                ps.setInt(9, pb.getIdSolicitante());

                // NOVOS CAMPOS
                if (pb.getVlRendaFamiliar() != null) {
                    ps.setDouble(10, pb.getVlRendaFamiliar());
                } else {
                    ps.setNull(10, Types.NUMERIC);
                }
                ps.setString(11, pb.getStProgramaGov());
                ps.setString(12, pb.getDsEscolaridadeResp());
                ps.setString(13, pb.getDsTermoAutorizacao());
                ps.setString(14, pb.getDsBoletimOcorrencia());

                ps.executeUpdate();

                // Recupera o ID gerado pelo banco para o Pré-Beneficiário
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idPreBeneficiarioGerado = rs.getInt(1);
                    }
                }
            }

            if (idPreBeneficiarioGerado == 0) {
                throw new SQLException("Falha ao cadastrar Pré-Beneficiário, ID não obtido.");
            }

            // 3. PASSO DA TRIAGEM: Vincula o ID da triagem selecionado no React com o Pré-Beneficiário criado
            try (PreparedStatement psRel = conn.prepareStatement(sqlRelacaoTriagem)) {
                psRel.setInt(1, pb.getIdTriagem()); // Captura o valor idTriagem enviado pelo React
                psRel.setInt(2, idPreBeneficiarioGerado); // Usa o ID gerado no passo anterior
                psRel.executeUpdate();
            }

            // 4. Inativa o solicitante inicial
            try (PreparedStatement psUpd = conn.prepareStatement(sqlUpdateSolicitante)) {
                psUpd.setInt(1, pb.getIdSolicitante());
                psUpd.executeUpdate();
            }

            // Confirma todas as alterações com segurança no banco Oracle
            conn.commit();

        } catch (SQLException e) {
            if (conn != null) conn.rollback(); // Cancela tudo se houver falhas
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    public List<PreBeneficiario> listar() throws SQLException {
        List<PreBeneficiario> preBeneficiarios = new ArrayList<>();

        // NOVA QUERY: Traz apenas os que têm direcionamento NULL ou diferente de 'N'
        String sql = "SELECT * FROM T_BC_PRE_BENEFICIARIO WHERE st_direcionamento IS NULL OR st_direcionamento != 'N'";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PreBeneficiario pb = new PreBeneficiario(
                        rs.getInt("id_pre_beneficiario"),
                        rs.getString("nm_pre_beneficiario"),
                        rs.getString("cpf_pre_beneficiario"),
                        rs.getString("sx_pre_beneficiario"),
                        rs.getString("ds_problema_dentario"),
                        rs.getString("st_situacao"),
                        rs.getInt("id_programa_social"),
                        rs.getInt("id_solicitante"),
                        rs.getInt("id_endereco")
                );
                preBeneficiarios.add(pb);
            }
        }
        return preBeneficiarios;
    }

    public void atualizar(PreBeneficiario pb) throws SQLException {
        String sql = "UPDATE T_BC_PRE_BENEFICIARIO SET nm_pre_beneficiario = ?, cpf_pre_beneficiario = ?, sx_pre_beneficiario = ?, ds_problema_dentario = ?, st_situacao = ?, id_programa_social = ?, id_solicitante = ?, id_endereco = ? WHERE id_pre_beneficiario = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pb.getNmPreBeneficiario());
            ps.setString(2, pb.getCpfPreBeneficiario());
            ps.setString(3, pb.getSxPreBeneficiario());
            ps.setString(4, pb.getDsProblemaDentario());
            ps.setString(5, pb.getStSituacao());
            ps.setInt(6, pb.getIdProgramaSocial());
            ps.setInt(7, pb.getIdSolicitante());
            ps.setInt(8, pb.getIdEndereco());
            ps.setInt(9, pb.getIdPreBeneficiario());

            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "UPDATE T_BC_PRE_BENEFICIARIO SET st_direcionamento = 'N' WHERE id_pre_beneficiario = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

