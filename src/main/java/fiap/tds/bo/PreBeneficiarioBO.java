package fiap.tds.bo;

import fiap.tds.dao.PreBeneficiarioDAO;
import fiap.tds.dao.BeneficiarioDAO;
import fiap.tds.tdbentities.PreBeneficiario;
import fiap.tds.tdbentities.Beneficiario;
import fiap.tds.exceptions.RegraNegocioException;

import java.sql.SQLException;
import java.util.List;

public class PreBeneficiarioBO {

    private PreBeneficiarioDAO preBeneficiarioDao;
    private BeneficiarioDAO beneficiarioDao;

    public PreBeneficiarioBO() {
        this.preBeneficiarioDao = new PreBeneficiarioDAO();
        this.beneficiarioDao = new BeneficiarioDAO();
    }

    public void cadastrar(PreBeneficiario pb) throws RegraNegocioException, SQLException {
        validarDados(pb);
        preBeneficiarioDao.cadastrar(pb);
    }

    public List<PreBeneficiario> listar() throws SQLException {
        return preBeneficiarioDao.listar();
    }

    public void atualizarDadosCompletos(PreBeneficiario pb) throws RegraNegocioException, SQLException {
        validarDados(pb);
        preBeneficiarioDao.atualizarDadosCompletos(pb);
    }

    public void atualizarStatus(int id, String novoStatus) throws RegraNegocioException, SQLException {
        if (novoStatus == null || novoStatus.trim().isEmpty()) {
            throw new RegraNegocioException("O novo status não pode ser vazio.");
        }

        preBeneficiarioDao.atualizarStatus(id, novoStatus);

        if ("AP".equals(novoStatus)) {
            Beneficiario novoBeneficiario = new Beneficiario();
            novoBeneficiario.setIdPreBeneficiario(id);
            beneficiarioDao.cadastrar(novoBeneficiario);
        }
    }

    public void excluir(int id) throws SQLException {
        preBeneficiarioDao.excluir(id);
    }

    private void validarDados(PreBeneficiario pb) throws RegraNegocioException {
        if (pb.getNmPreBeneficiario() == null || pb.getNmPreBeneficiario().trim().isEmpty()) {
            throw new RegraNegocioException("O nome do pré-beneficiário é obrigatório.");
        }

        if (pb.getCpfPreBeneficiario() == null) {
            throw new RegraNegocioException("O CPF é obrigatório.");
        }

        String cpfLimpo = pb.getCpfPreBeneficiario().replaceAll("[^0-9]", "");
        if (cpfLimpo.length() != 11) {
            throw new RegraNegocioException("O CPF deve conter exatamente 11 dígitos numéricos.");
        }
    }
}