package qiang.envelope.printer.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import qiang.envelope.printer.model.ClientGroup;

/**
 * Created by Qiang on 28/11/2016.
 */
public interface ClientGroupRepository extends PagingAndSortingRepository<ClientGroup, Long> {

    @Override
    Page<ClientGroup> findAll(Pageable pageable);

}
