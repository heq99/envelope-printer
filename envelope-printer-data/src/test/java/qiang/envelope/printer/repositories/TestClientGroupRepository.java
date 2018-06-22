package qiang.envelope.printer.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qiang.envelope.printer.model.ClientGroup;
import qiang.envelope.printer.model.Envelope;

import java.util.Optional;

/**
 * Created by Qiang on 25/12/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class TestClientGroupRepository {

    @Autowired
    private ClientGroupRepository clientGroupRepository;

    @Autowired
    private EnvelopeRepository envelopeRepository;

    private Envelope ems;
    private Envelope shunfeng;

    @Before
    public void beforeTest() {
        ems = envelopeRepository.findByName("EMS");
        shunfeng = envelopeRepository.findByName("顺丰");
    }

    @Test
    public void testClientGroupRead() {

        Optional<ClientGroup> clientGroupEms = clientGroupRepository.findById(Long.valueOf(1));
        Assert.assertEquals(34, clientGroupEms.get().getClients().size());

        Optional<ClientGroup> clientGroupShunfeng = clientGroupRepository.findById(Long.valueOf(2));
        Assert.assertEquals(278, clientGroupShunfeng.get().getClients().size());
    }
    
}
