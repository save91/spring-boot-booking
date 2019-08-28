package tosi.saverio.booking.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;

import org.springframework.data.repository.query.Param;
import tosi.saverio.booking.model.entity.Booking;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.courtId=:courtId")
    List<Booking> fetchOverlappingBooking(@Param("courtId") Long courtId);
}