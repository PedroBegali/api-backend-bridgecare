package fiap.tds.bo;

import fiap.tds.dao.TriagemDAO;
import fiap.tds.tdbentities.Triagem;

import java.sql.SQLException;
import java.util.List;

public class TriagemBO {
    private TriagemDAO dao;

    public TriagemBO() {
        this.dao = new TriagemDAO();
    }

    public List<Triagem> listarProximas() throws SQLException {
        return dao.listarProximas();
    }

    public void cadastrar(Triagem triagem) throws SQLException {
        dao.cadastrar(triagem);
    }
}