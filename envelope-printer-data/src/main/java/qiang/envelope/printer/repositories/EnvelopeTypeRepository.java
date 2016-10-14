package qiang.envelope.printer.repositories;

import org.springframework.data.repository.CrudRepository;
import qiang.envelope.printer.model.EnvelopeType;

/**
 * Created by Qiang on 13/10/2016.
 */
public interface EnvelopeTypeRepository extends CrudRepository<EnvelopeType, Long> {

    EnvelopeType findByType(String type);

}
