package fiap.tds.bo;

import fiap.tds.dao.SolicitanteDAO;
import fiap.tds.tdbentities.Solicitante;
import java.sql.SQLException;
import java.util.List;

public class SolicitanteBO {
    private SolicitanteDAO dao;

    public SolicitanteBO() {
        this.dao = new SolicitanteDAO();
    }

    public List<Solicitante> listar() throws SQLException {
        return dao.listar();
    }

    public void cadastrar(Solicitante solicitante) throws SQLException {
        dao.cadastrar(solicitante);
    }

    public void excluir(int id) throws SQLException {
        dao.excluir(id);
    }
}