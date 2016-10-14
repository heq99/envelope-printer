package qiang.envelope.printer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import qiang.envelope.printer.configuration.ApplicationConfiguration;
import qiang.envelope.printer.model.Client;
import qiang.envelope.printer.model.EnvelopeType;
import qiang.envelope.printer.repositories.ClientRepository;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Qiang on 05/10/2016.
 */
public class ImportData {

    public static void main(String... args) {

        try {
            new ImportData().preparedata();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void preparedata() throws ClassNotFoundException, SQLException {

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://192.168.0.136:9092/~/envelope/envelope", "envelope", "password");


        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO PUBLIC.ENVELOPE_TYPE (ID, TYPE, TEMPLATE) VALUES (1, 'EMS', null)");
        statement.closeOnCompletion();
        statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO PUBLIC.ENVELOPE_TYPE (ID, TYPE, TEMPLATE) VALUES (2, '顺丰', null)");
        statement.closeOnCompletion();

        String sql = "INSERT INTO PUBLIC.CLIENT (ID, COMPANY, CONTACT_1, CONTACT_2, CONTACT_3, ADDRESS, TELEPHONE_1, TELEPHONE_2, ENVELOPE_TYPE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        conn.setAutoCommit(false);

        int id = 1;

        try (
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Qiang\\Documents\\Client.txt"))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(",", -1);

                String company = trimQuotes(elements[1]);
                String contact1 = trimQuotes(elements[2]);
                String address = trimQuotes(elements[3]);
                String contact2 = trimQuotes(elements[4]);
                String contact3 = trimQuotes(elements[5]);
                String telephone1 = trimQuotes(elements[6]);
                String telephone2 = trimQuotes(elements[7]);
                String envelopeTypeName = trimQuotes(elements[8]);

                stmt.setInt(1, id++);
                stmt.setString(2, company);
                stmt.setString(3, contact1);
                stmt.setString(4, contact2);
                stmt.setString(5, contact3);
                stmt.setString(6, address);
                stmt.setString(7, telephone1);
                stmt.setString(8, telephone2);
                if (envelopeTypeName == null) {
                    stmt.setObject(9, null);
                } else if (envelopeTypeName.equalsIgnoreCase("EMS")) {
                    stmt.setInt(9, 1);
                } else if (envelopeTypeName.equalsIgnoreCase("顺丰")) {
                    stmt.setInt(9, 2);
                }

                stmt.addBatch();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stmt.executeBatch();

        conn.commit();

        stmt.closeOnCompletion();

        conn.close();
    }

    private String trimQuotes(String str) {
        if (str != null && !str.equalsIgnoreCase("")) {
            return str.substring(1, str.length()-1).trim();
        } else {
            return null;
        }
    }
}
