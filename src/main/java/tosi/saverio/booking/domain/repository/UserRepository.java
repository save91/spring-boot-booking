package tosi.saverio.booking.domain.repository;

import org.springframework.data.repository.CrudRepository;
import tosi.saverio.booking.domain.model.User;


public interface UserRepository extends CrudRepository<User, Long> { }