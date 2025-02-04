package Config;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDB {
    Connection getConnection() throws SQLException;
    void closeConnection() throws SQLException;
}
