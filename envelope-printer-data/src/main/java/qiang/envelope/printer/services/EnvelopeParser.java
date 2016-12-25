package qiang.envelope.printer.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import qiang.envelope.printer.model.Client;
import qiang.envelope.printer.model.EnvelopeField;
import qiang.envelope.printer.model.EnvelopeType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qiang on 13/11/2016.
 */
@Component
public class EnvelopeParser {

    public static final Logger logger = LoggerFactory.getLogger(EnvelopeParser.class);

    private final BaseFont baseFont;
    private final Font font;

    public EnvelopeParser() throws IOException, DocumentException {
        baseFont = BaseFont.createFont("./fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font = new Font(baseFont, 10f);
    }

    public ByteArrayOutputStream generatePDF(Client client) throws IOException, DocumentException {
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        return generatePDF(clients);
    }

    public ByteArrayOutputStream generatePDF(List<Client> clients) throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EnvelopeType envelopeType = clients.get(0).getEnvelopeType();

        Rectangle pageSize = new Rectangle(millimeterToUserUnit(envelopeType.getWidth()), millimeterToUserUnit(envelopeType.getHeight()));
        Document doc = new Document(pageSize, 0, 0, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(doc, outputStream);
        doc.open();

        PdfContentByte cb = writer.getDirectContent();

        for (Client client: clients) {

            logger.debug("(" + client.getId() + ") " + client.getCompany() + "   details:");

            for (EnvelopeField envelopeField : envelopeType.getEnvelopeFields()) {

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
