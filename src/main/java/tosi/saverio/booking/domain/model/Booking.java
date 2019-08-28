package tosi.saverio.booking.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import tosi.saverio.booking.domain.exception.SlotNotAvailable;

@Entity
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long courtId;

    @Column(name = "date_from", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date from;

    @Column(name = "date_to", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date to;

    public Booking() { }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCourtId() {
        return courtId;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public boolean assertSlotIsAvailable(Booking booking) throws SlotNotAvailable {
        if (this.from.compareTo(booking.to) >= 0) {
            return true;
        }

        if (this.to.compareTo(booking.from) <= 0) {
            return true;
        }

        throw new SlotNotAvailable();
    }

}