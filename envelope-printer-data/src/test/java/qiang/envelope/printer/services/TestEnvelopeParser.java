package qiang.envelope.printer.services;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import qiang.envelope.printer.Application;
import qiang.envelope.printer.configuration.ApplicationConfiguration;
import qiang.envelope.printer.model.Client;
import qiang.envelope.printer.model.Envelope;
import qiang.envelope.printer.repositories.ClientRepository;
import qiang.envelope.printer.repositories.EnvelopeRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Qiang on 21/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@DataJpaTest
//@ContextConfiguration(classes=ApplicationConfiguration.class)
public class TestEnvelopeParser {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EnvelopeParser envelopeParser;

    @Autowired
    private EnvelopeRepository envelopeRepository;

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

        Optional<Client> client = clientRepository.findById(Long.valueOf(1));
        Assert.assertEquals("建德南方水泥有限公司", client.get().getCompany());

        Envelope envelope = envelopeRepository.findByName("EMS");

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            ByteArrayOutputStream os = envelopeParser.generatePDF(client.get(), envelope);
            os.writeTo(fos);
            os.flush();
            os.close();
        }

        try (FileInputStream fis = new FileInputStream(tempFile)) {
            PdfReader reader = new PdfReader(fis);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            TextExtractionStrategy strategy = parser.processContent(1, new SimpleTextExtractionStrategy());
            String result = strategy.getResultantText();

            Assert.assertTrue(result.contains(client.get().getAddress()));
            Assert.assertTrue(result.contains(client.get().getCompany()));

            reader.close();
        }
    }

    @Test
    public void testMultiClientsProduceEnvelopePDF() throws DocumentException, IOException {
        List<Client> clients = new ArrayList<>();
        clients.add(clientRepository.findById(Long.valueOf(1)).get());
        clients.add(clientRepository.findById(Long.valueOf(2)).get());

        Envelope envelope = envelopeRepository.findByName("EMS");

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            ByteArrayOutputStream os = envelopeParser.generatePDF(clients, envelope);
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
