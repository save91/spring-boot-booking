package tosi.saverio.booking.model.repository;

import org.springframework.data.repository.*;

import tosi.saverio.booking.model.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long> { }