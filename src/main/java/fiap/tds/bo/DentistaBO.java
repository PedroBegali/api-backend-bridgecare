package fiap.tds.bo;

import fiap.tds.dao.DentistaDAO;
import fiap.tds.tdbentities.Dentista;
import fiap.tds.exceptions.RegraNegocioException;

import java.sql.SQLException;

public class DentistaBO {

    private DentistaDAO dao;

    public DentistaBO() {
        this.dao = new DentistaDAO();
    }

    public Dentista buscarPorId(int id) throws RegraNegocioException, SQLException {
        Dentista dentista = dao.buscarPorId(id);
        if (dentista == null) {
            throw new RegraNegocioException("Dentista não encontrado com o ID fornecido.");
        }
        return dentista;
    }

    public void mudarStatus(int id, String status) throws RegraNegocioException, SQLException {
        if (status == null || (!status.equalsIgnoreCase("A") && !status.equalsIgnoreCase("I"))) {
            throw new RegraNegocioException("Status inválido. Use 'A' para Ativo ou 'I' para Inativo.");
        }

        Dentista dentista = dao.buscarPorId(id);
        if (dentista == null) {
            throw new RegraNegocioException("Dentista não encontrado.");
        }

        dao.atualizarStatus(id, status.toUpperCase());
    }
}