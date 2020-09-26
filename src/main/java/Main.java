import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.hibernate.Session;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) {

        JDBSConnector connector = null;
        try {
            connector = new JDBSConnector();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connector.createConnect();

        ConsoleReader consoleReader = new ConsoleReader(connector);
        consoleReader.read();

    }
}
