package tosi.saverio.booking.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;

import org.springframework.data.repository.query.Param;
import tosi.saverio.booking.domain.model.Booking;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.courtId=:courtId and DATE(b.from)=DATE(:from)")
    List<Booking> fetchBookingByCourtAndDay(@Param("courtId") Long courtId, @Param("from") Date from);

    List<Booking> findByUserId(Long userId);
}