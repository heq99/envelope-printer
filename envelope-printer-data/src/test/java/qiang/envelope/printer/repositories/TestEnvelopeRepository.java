package qiang.envelope.printer.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import qiang.envelope.printer.model.Envelope;

/**
 * Created by Qiang on 13/10/2016.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TestEnvelopeRepository {

    @Autowired
    private EnvelopeRepository envelopeRepository;

    @Test
    public void testEmsEnvelope() {
        Envelope ems = envelopeRepository.findByName("EMS");
        Assert.assertEquals(Long.valueOf(1), ems.getId());
        Assert.assertEquals("EMS", ems.getName());
    }

    @Test
    public void testShuiFengEnvelopeType() {
        Envelope shunFeng = envelopeRepository.findByName("顺丰");
        Assert.assertEquals(Long.valueOf(2), shunFeng.getId());
        Assert.assertEquals("顺丰", shunFeng.getName());
    }

}
