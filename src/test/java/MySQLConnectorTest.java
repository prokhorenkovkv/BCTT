import com.library.services.MySQLConnector;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class MySQLConnectorTest {
    public MySQLConnectorTest() throws Exception {
    }

    private MySQLConnector connector = MySQLConnector.getInstance();
    private Connection connection = connector.getConnection();

    @Test
    public void getConnectionTest() throws Exception {
        Assert.assertNotNull(connection);
    }

    @Test
    public void closeConnectionTest() throws Exception {
        Assert.assertNotNull(connector.closeConnection());
    }
}