package fiap.tds.bo;

import fiap.tds.dao.DentistaDAO;
import fiap.tds.tdbentities.Dentista;
import fiap.tds.exceptions.RegraNegocioException;
import java.sql.SQLException;
import java.util.List;

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
    public List<Dentista> listar() throws SQLException {
        return dao.listar();
    }
    public void cadastrar(Dentista dentista) throws RegraNegocioException, SQLException {
        if (dentista.getNmDentista() == null || dentista.getNmDentista().trim().isEmpty()) {
            throw new RegraNegocioException("O nome do profissional é obrigatório.");
        }
        if (dentista.getCpfDentista() == null || dentista.getCpfDentista().length() != 11) {
            throw new RegraNegocioException("O CPF fornecido deve conter exatamente 11 dígitos numéricos.");
        }
        if (dentista.getCroDentista() == null || dentista.getCroDentista().trim().isEmpty()) {
            throw new RegraNegocioException("O número do registro CRO é obrigatório.");
        }

        // Garante que o status inicial comece ativo 'A' conforme mapeamento do banco
        dentista.setStDentista("A");
        dao.cadastrar(dentista);
    }
}