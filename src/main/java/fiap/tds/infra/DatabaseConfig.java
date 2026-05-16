package fiap.tds.infra;
import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = DatabaseConfig.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (is == null) {
                throw new RuntimeException("Arquivo database.properties não encontrado!");
            }
            props.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASS");

            if (url == null) url = props.getProperty("db.url");
            if (user == null) user = props.getProperty("db.user");
            if (pass == null) pass = props.getProperty("db.pass");

            if (url == null) {
                throw new SQLException("A URL de conexão com o banco de dados não foi encontrada (Variável de ambiente ou Properties).");
            }

            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver Oracle não encontrado.", e);
        }
    }
}
