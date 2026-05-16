package fiap.tds.bo;

import fiap.tds.dao.BeneficiarioDAO;
import fiap.tds.tdbentities.Beneficiario;
import java.sql.SQLException;
import java.util.List;

public class BeneficiarioBO {
    private BeneficiarioDAO dao;

    public BeneficiarioBO() {
        this.dao = new BeneficiarioDAO();
    }

    public List<Beneficiario> listar() throws SQLException {
        return dao.listar();
    }

    public void atualizar(Beneficiario beneficiario) throws SQLException {
        dao.atualizar(beneficiario);
    }

    public void excluir(int id) throws SQLException {
        dao.excluir(id);
    }
}