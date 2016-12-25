package qiang.envelope.printer.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qiang.envelope.printer.services.PrintEnvelopeService;

/**
 * Created by Qiang on 26/11/2016.
 */
@RestController
public class PrintEnvelopeController {

    public static final Logger logger = LoggerFactory.getLogger(PrintEnvelopeController.class);

    @Autowired
    private PrintEnvelopeService printEnvelopeService;

    @RequestMapping(path = "/print", method = RequestMethod.POST)
    public void printEnvelopes(@RequestBody Long[] clientIds) {
        for (Long l: clientIds) {
            System.out.println(l);
        }
    }
}
