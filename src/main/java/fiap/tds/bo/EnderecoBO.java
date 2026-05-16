package fiap.tds.bo;

import fiap.tds.dao.EnderecoDAO;
import fiap.tds.tdbentities.Endereco;
import fiap.tds.infra.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class EnderecoBO {

    private EnderecoDAO dao;

    public EnderecoBO() {
        this.dao = new EnderecoDAO();
    }

    public void cadastrar(Endereco endereco) throws SQLException {
        // Abre a conexão autónoma para o endereço de triagem
        try (Connection conn = DatabaseConfig.getConnection()) {
            dao.cadastrar(endereco, conn);
        }
    }
}
