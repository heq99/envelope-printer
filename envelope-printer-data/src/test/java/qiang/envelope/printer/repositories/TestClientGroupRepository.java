package qiang.envelope.printer.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import qiang.envelope.printer.model.ClientGroup;
import qiang.envelope.printer.model.EnvelopeType;

/**
 * Created by Qiang on 25/12/2016.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TestClientGroupRepository {

    @Autowired
    private ClientGroupRepository clientGroupRepository;

    @Autowired
    private EnvelopeTypeRepository envelopeTypeRepository;

    private EnvelopeType ems;
    private EnvelopeType shunfeng;

    @Before
    public void beforeTest() {
        ems = envelopeTypeRepository.findByType("EMS");
        shunfeng = envelopeTypeRepository.findByType("顺丰");
    }

    @Test
    public void testClientGroupRead() {

        ClientGroup clientGroupEms = clientGroupRepository.findOne(Long.valueOf(1));
        Assert.assertEquals(34, clientGroupEms.getClients().size());

        ClientGroup clientGroupShunfeng = clientGroupRepository.findOne(Long.valueOf(2));
        Assert.assertEquals(278, clientGroupShunfeng.getClients().size());
    }
    
}