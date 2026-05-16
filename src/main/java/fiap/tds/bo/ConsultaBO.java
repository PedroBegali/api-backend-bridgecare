package fiap.tds.bo;

import fiap.tds.dao.ConsultaDAO;
import fiap.tds.tdbentities.Consulta;
import fiap.tds.exceptions.RegraNegocioException;

import java.sql.SQLException;
import java.util.List;

public class ConsultaBO {

    private ConsultaDAO dao;

    public ConsultaBO() {
        this.dao = new ConsultaDAO();
    }

    public List<Consulta> listarTodas() throws SQLException {
        return dao.listar();
    }

    public List<Consulta> listarProximasDentista(int idDentista) throws SQLException {
        return dao.listarProximasDentista(idDentista);
    }

    public List<Consulta> listarPendentesDentista(int idDentista) throws SQLException {
        return dao.listarPendentesDentista(idDentista);
    }

    public List<Consulta> listarProximasBeneficiario(int idBeneficiario) throws SQLException {
        return dao.listarProximasBeneficiario(idBeneficiario);
    }

    public List<Consulta> listarHistoricoBeneficiario(int idBeneficiario) throws SQLException {
        return dao.listarHistoricoBeneficiario(idBeneficiario);
    }

    public void preencherProntuario(int idConsulta, String texto) throws RegraNegocioException, SQLException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new RegraNegocioException("O texto do prontuário não pode estar vazio.");
        }
        if (texto.length() < 10) {
            throw new RegraNegocioException("O prontuário deve conter detalhes suficientes (mínimo de 10 caracteres).");
        }
        dao.atualizarProntuario(idConsulta, texto);
    }
}