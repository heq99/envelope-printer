package qiang.envelope.printer.services;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import qiang.envelope.printer.model.Client;
import qiang.envelope.printer.model.EnvelopeType;
import qiang.envelope.printer.repositories.ClientRepository;
import qiang.envelope.printer.repositories.EnvelopeTypeRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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

    @Autowired
    private EnvelopeTypeRepository envelopeTypeRepository;

    private File tempFile;

    @Before
    public void setUp() {
        tempFile = new File(System.getProperty("java.io.tmpdir")+File.separator+"printEnvelopeTemp.pdf");
    }

    @After
    public void tearDown() throws IOException {
        Path path = tempFile.toPath();
        Files.deleteIfExists(path);
    }

    @Test
    public void testSingleClientProduceEnvelopePDF() throws IOException, DocumentException {

        Client client = clientRepository.findOne(Long.valueOf(1));
        Assert.assertEquals("建德南方水泥有限公司", client.getCompany());

        EnvelopeType envelopeType = envelopeTypeRepository.findByType("EMS");

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            ByteArrayOutputStream os = envelopeParser.generatePDF(client, envelopeType);
            os.writeTo(fos);
            os.flush();
            os.close();
        }

        try (FileInputStream fis = new FileInputStream(tempFile)) {
            PdfReader reader = new PdfReader(fis);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            TextExtractionStrategy strategy = parser.processContent(1, new SimpleTextExtractionStrategy());
            String result = strategy.getResultantText();

            Assert.assertTrue(result.contains(client.getAddress()));
            Assert.assertTrue(result.contains(client.getCompany()));

            reader.close();
        }
    }

    @Test
    public void testMultiClientsProduceEnvelopePDF() throws DocumentException, IOException {
        List<Client> clients = new ArrayList<>();
        clients.add(clientRepository.findOne(Long.valueOf(1)));
        clients.add(clientRepository.findOne(Long.valueOf(2)));

        EnvelopeType envelopeType = envelopeTypeRepository.findByType("EMS");

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            ByteArrayOutputStream os = envelopeParser.generatePDF(clients, envelopeType);
            os.writeTo(fos);
            os.flush();
            os.close();
        }

        try (FileInputStream fis = new FileInputStream(tempFile)) {
            PdfReader reader = new PdfReader(fis);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            TextExtractionStrategy strategy;
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
                String result = strategy.getResultantText();

                Assert.assertTrue(result.contains(clients.get(i - 1).getAddress()));
                Assert.assertTrue(result.contains(clients.get(i - 1).getCompany()));
            }
            reader.close();
        }
    }
}