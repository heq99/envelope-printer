package qiang.envelope.printer.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import qiang.envelope.printer.model.Client;
import qiang.envelope.printer.model.Envelope;
import qiang.envelope.printer.model.EnvelopeField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qiang on 13/11/2016.
 */
@Service
public class EnvelopeParser {

    public static final Logger logger = LoggerFactory.getLogger(EnvelopeParser.class);

    private final BaseFont baseFont;
    private final Font font;

    public EnvelopeParser() throws IOException, DocumentException {
        String fontFile = this.getClass().getResource("/fonts/simhei.ttf").getFile();
        baseFont = BaseFont.createFont(fontFile, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font = new Font(baseFont, 10f);
    }

    public ByteArrayOutputStream generatePDF(Client client, Envelope envelope) throws IOException, DocumentException {
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        return generatePDF(clients, envelope);
    }

//    public ByteArrayOutputStream generatePDF(List<Client> clients, Envelope envelope) throws IOException {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//        PDRectangle pageSize = new PDRectangle(millimeterToUserUnit(envelope.getWidth()),
//                millimeterToUserUnit(envelope.getHeight()));
//
//        PDDocument doc = new PDDocument();
//
//        PDFont font = PDType1Font.HELVETICA_BOLD;
//
//        for (Client client: clients) {
//
//            PDPage page = new PDPage(pageSize);
//            doc.addPage(page);
//
//            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
//
//            logger.debug("(" + client.getId() + ") " + client.getCompany() + "   details:");
//
//            for (EnvelopeField envelopeField : envelope.getEnvelopeFields()) {
//
//                String fieldName = envelopeField.getFieldName();
//                String clientData = client.getClientData(fieldName);
//                if (clientData == null) {
//                    clientData = envelopeField.getDefaultValue();
//                }
//                float x = millimeterToUserUnit(envelopeField.getFieldPositionX());
//                float y = millimeterToUserUnit(envelopeField.getFieldPositionY());
//                float w = millimeterToUserUnit(envelopeField.getFieldWidth());
//                float h = millimeterToUserUnit(envelopeField.getFieldHeight());
//
//                logger.debug(fieldName + ":  " + clientData + "  x=" + x + "  y=" + y + "  w=" + w + "  h=" + h);
//
//                contentStream.beginText();
//                contentStream.setFont(font, 12);
//                contentStream.moveTextPositionByAmount(x, y);
//                contentStream.drawString(clientData);
//                contentStream.endText();
//            }
//
//            contentStream.close();
//
//        }
//
//        doc.save(outputStream);
//        doc.close();
//
//        return outputStream;
//
//    }

    public ByteArrayOutputStream generatePDF(List<Client> clients, Envelope envelope) throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Rectangle pageSize = new Rectangle(millimeterToUserUnit(envelope.getWidth()), millimeterToUserUnit(envelope.getHeight()));
        Document doc = new Document(pageSize, 0, 0, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(doc, outputStream);
        doc.open();

        PdfContentByte cb = writer.getDirectContent();

        for (Client client: clients) {

            logger.debug("(" + client.getId() + ") " + client.getCompany() + "   details:");

            for (EnvelopeField envelopeField : envelope.getEnvelopeFields()) {

                String fieldName = envelopeField.getFieldName();
                String clientData = client.getClientData(fieldName);
                if (clientData == null) {
                    clientData = envelopeField.getDefaultValue();
                }
                float x = millimeterToUserUnit(envelopeField.getFieldPositionX());
                float y = millimeterToUserUnit(envelopeField.getFieldPositionY());
                float w = millimeterToUserUnit(envelopeField.getFieldWidth());
                float h = millimeterToUserUnit(envelopeField.getFieldHeight());

                logger.debug(fieldName + ":  " + clientData + "  x=" + x + "  y=" + y + "  w=" + w + "  h=" + h);

                ColumnText ct = new ColumnText(cb);
                Phrase p = new Phrase(0, clientData, font);
                ct.setSimpleColumn(p, x, y, x+w, y+h,0, Element.ALIGN_LEFT);
                ct.go();
            }

            doc.newPage();

        }

        doc.close();


        return outputStream;
    }

    public float millimeterToUserUnit(float mm) {
        return mm / 10f * 0.3937007874f * 72f;
    }
}
