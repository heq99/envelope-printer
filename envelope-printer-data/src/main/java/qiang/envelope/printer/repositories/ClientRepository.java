package qiang.envelope.printer.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import qiang.envelope.printer.model.Client;

/**
 * Created by Qiang on 29/09/2016.
 */
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    @Override
    Page<Client> findAll(Pageable pageable);

}
