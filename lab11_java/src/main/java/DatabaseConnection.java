import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {

    private static final Properties props = new Properties();

    static {
        try (InputStream in = DatabaseConnection.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {
            props.load(in);

        } catch (Exception e) {
            throw new RuntimeException("Cannot load db.properties", e);
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("user"),
                props.getProperty("password")
        );
    }
}
