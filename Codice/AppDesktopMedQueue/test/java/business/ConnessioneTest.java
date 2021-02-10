package business;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnessioneTest {
    private static Connessione test;

    @BeforeAll
    public static void setUp(){
        test=new Connessione();
    }

    @Test
    void connect() {
        assertNotNull(test.connect());
    }

    @Test
    void disconnect() {
        Connection con= test.connect();
        assertTrue(test.disconnect(con));
    }
}