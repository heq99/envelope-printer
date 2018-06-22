package qiang.envelope.printer.restful;

import com.itextpdf.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qiang.envelope.printer.model.Envelope;
import qiang.envelope.printer.repositories.EnvelopeRepository;
import qiang.envelope.printer.services.PrintEnvelopeService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Qiang on 26/11/2016.
 */
@RestController
public class PrintEnvelopeController {

    public static final Logger logger = LoggerFactory.getLogger(PrintEnvelopeController.class);

    @Autowired
    private EnvelopeRepository envelopeRepository;

    @Autowired
    private PrintEnvelopeService printEnvelopeService;

    @RequestMapping(path = "/print/{envelope}", method = RequestMethod.POST)
    public ResponseEntity<byte[]> printEnvelopes(
            @PathVariable(value="envelope") String envelopeTypeStr,
            @RequestBody Long[] clientIds) {

        Envelope envelope = envelopeRepository.findByName(envelopeTypeStr);
        try {
            ByteArrayOutputStream os = printEnvelopeService.printEnvelope(clientIds, envelope);
            byte[] bytes = os.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            String fileName = envelopeTypeStr + ".pdf";
            headers.setContentDispositionFormData(fileName, fileName);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            logger.error("Error when generating the printing file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (IOException e) {
            logger.error("Error when generating the printing file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
