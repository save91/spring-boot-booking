package tosi.saverio.booking.domain.repository;

import org.springframework.data.repository.CrudRepository;
import tosi.saverio.booking.domain.model.Court;


public interface CourtRepository extends CrudRepository<Court, Long> { }