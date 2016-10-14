package qiang.envelope.printer.repositories;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import qiang.envelope.printer.model.Client;
import qiang.envelope.printer.model.EnvelopeType;

/**
 * Created by Qiang on 13/10/2016.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TestClientRepository {

    @Autowired
    private EnvelopeTypeRepository envelopeTypeRepository;

    @Autowired
    private ClientRepository clientRepository;

    private EnvelopeType ems;
    private EnvelopeType shunFeng;

    @Before
    public void beforeTest() {
        ems = envelopeTypeRepository.findByType("EMS");
        shunFeng = envelopeTypeRepository.findByType("顺丰");
    }

    @Test
    public void testInitialDatabase() {
        long numberOfClients = clientRepository.count();
        Assert.assertEquals(319, numberOfClients);
    }

    @Test
    public void testClientCRUD() {

        Client client = new Client();
        client.setCompany("Test Company");
        client.setContact1("Contact 1");
        client.setAddress("Test Company Address");
        client.setEnvelopeType(ems);

        client = clientRepository.save(client);
        Assert.assertEquals(320, clientRepository.count());

        client.setCompany("Test Company Updated");
        client = clientRepository.findOne(client.getId());
        Assert.assertEquals("Test Company Updated", client.getCompany());

        clientRepository.delete(client);
        Assert.assertEquals(319, clientRepository.count());

    }

}
