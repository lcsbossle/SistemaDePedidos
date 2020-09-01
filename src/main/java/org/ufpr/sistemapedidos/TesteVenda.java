package org.ufpr.sistemapedidos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TesteVenda {

    public static void main(String[] args) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        System.out.println("Deu boa");
    }
}
