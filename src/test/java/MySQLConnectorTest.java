import com.library.MySQLConnector;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

import static com.library.MySQLConnector.closeConnection;


public class MySQLConnectorTest {
    public MySQLConnectorTest() throws Exception {
    }
private MySQLConnector connector = new MySQLConnector();
    Connection connection = connector.getConnection();

    @Test
    public void getConnectionTest() throws Exception {
        Assert.assertNotNull(connection);
    }
    @Test
    public void closeConnectionTest() throws Exception {
        Assert.assertNotNull(closeConnection(connection));
    }
}
