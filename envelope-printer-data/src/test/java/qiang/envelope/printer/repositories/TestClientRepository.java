package qiang.envelope.printer.repositories;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qiang.envelope.printer.model.Client;
import qiang.envelope.printer.model.ClientStatus;

/**
 * Created by Qiang on 13/10/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class TestClientRepository {

    @Autowired
    private ClientRepository clientRepository;

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
        client.setStatus(ClientStatus.ACTIVE);

        client = clientRepository.save(client);
        Assert.assertEquals(320, clientRepository.count());

        client.setCompany("Test Company Updated");
        client = clientRepository.findById(client.getId()).get();
        Assert.assertEquals("Test Company Updated", client.getCompany());

        clientRepository.delete(client);
        Assert.assertEquals(319, clientRepository.count());
    }
}
