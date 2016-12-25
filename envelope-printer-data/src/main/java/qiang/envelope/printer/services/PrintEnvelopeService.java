package qiang.envelope.printer.services;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qiang.envelope.printer.model.Client;
import qiang.envelope.printer.model.EnvelopeType;
import qiang.envelope.printer.repositories.ClientRepository;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qiang on 26/11/2016.
 */
@Service
public class PrintEnvelopeService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EnvelopeParser envelopeParser;

    public OutputStream printEnvelope(Long[] clientIds, EnvelopeType envelopeType) throws DocumentException {
        List<Client> clients = new ArrayList<>();
        for (Long id : clientIds) {
            Client client = clientRepository.findOne(id);
            clients.add(client);
        }
        return envelopeParser.generatePDF(clients, envelopeType);
    }
}
