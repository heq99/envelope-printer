package qiang.envelope.printer.parser;

import com.itextpdf.text.DocumentException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import qiang.envelope.printer.model.Client;
import qiang.envelope.printer.repositories.ClientRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qiang on 21/11/2016.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class TestEnvelopeParser {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EnvelopeParser envelopeParser;

    @Test
    public void testSingleClientProduceEnvelopePDF() throws IOException, DocumentException {
        Client client = clientRepository.findOne(Long.valueOf(1));
        Assert.assertEquals("建德南方水泥有限公司", client.getCompany());

        File out = new File(System.getProperty("java.io.tmpdir")+File.separator+"singlePage.pdf");

        ByteArrayOutputStream os = envelopeParser.generatePDF(client);
        os.writeTo(new FileOutputStream(out));

        os.flush();
        os.close();

    }

    @Test
    public void testMultiClientsProduceEnvelopePDF() throws DocumentException, IOException {
        List<Client> clients = new ArrayList<>();
        clients.add(clientRepository.findOne(Long.valueOf(1)));
        clients.add(clientRepository.findOne(Long.valueOf(2)));

        File out = new File(System.getProperty("java.io.tmpdir")+File.separator+"multiplePage.pdf");

        ByteArrayOutputStream os = envelopeParser.generatePDF(clients);
        os.writeTo(new FileOutputStream(out));

        os.flush();
        os.close();

    }
}
