package tosi.saverio.booking.repository;

import org.springframework.data.repository.*;

import tosi.saverio.booking.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> { }