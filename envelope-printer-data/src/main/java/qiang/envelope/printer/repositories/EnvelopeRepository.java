package qiang.envelope.printer.repositories;

import org.springframework.data.repository.CrudRepository;
import qiang.envelope.printer.model.Envelope;

/**
 * Created by Qiang on 13/10/2016.
 */
public interface EnvelopeRepository extends CrudRepository<Envelope, Long> {

    @Override
    Iterable<Envelope> findAll();

    Envelope findByName(String name);

}
