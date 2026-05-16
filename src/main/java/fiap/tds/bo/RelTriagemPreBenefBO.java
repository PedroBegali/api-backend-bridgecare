package fiap.tds.bo;

import fiap.tds.dao.RelTriagemPreBenefDAO;
import fiap.tds.tdbentities.RelTriagemPreBenef;
import java.sql.SQLException;
import java.util.List;

public class RelTriagemPreBenefBO {
    private RelTriagemPreBenefDAO dao;

    public RelTriagemPreBenefBO() {
        this.dao = new RelTriagemPreBenefDAO();
    }

    public void atualizarTriagem(int idPreBeneficiario, int idNovaTriagem) throws SQLException {
        dao.atualizarTriagem(idPreBeneficiario, idNovaTriagem);
    }

    public void cadastrar(RelTriagemPreBenef rel) throws SQLException {
        dao.cadastrar(rel);
    }

    public List<RelTriagemPreBenef> listar() throws SQLException {
        return dao.listar();
    }

    public void excluir(int idTriagem, int idPreBeneficiario) throws SQLException {
        dao.excluir(idTriagem, idPreBeneficiario);
    }
}