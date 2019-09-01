package tosi.saverio.booking.domain.model;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import tosi.saverio.booking.domain.exception.SlotLengthInvalid;
import tosi.saverio.booking.domain.exception.SlotNotAvailable;
import tosi.saverio.booking.domain.exception.SlotTimeInvalid;

@Entity
public class Booking {
    public static final Long ONE_HOUR_TIMESTAMP = 1L * 60 * 60 * 1000;
    public static final Long THREE_HOURS_TIMESTAMP = 3L * 60 * 60 * 1000;
    public static final int  FIRST_HOUR_BOOKABLE = 9;
    public static final int  LAST_HOUR_BOOKABLE = 23;

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

    @Column(columnDefinition = "tinyint(1) default 0", nullable = false)
    private boolean free;

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
        if (this.from.compareTo(booking.getTo()) >= 0) {
            return true;
        }

        if (this.to.compareTo(booking.getFrom()) <= 0) {
            return true;
        }

        throw new SlotNotAvailable();
    }

    public boolean assertSlotLengthIsValid() throws SlotLengthInvalid {
        Long difference = to.getTime() - from.getTime();
        if (difference >= ONE_HOUR_TIMESTAMP && difference <= THREE_HOURS_TIMESTAMP) {
            return true;
        }

        throw new SlotLengthInvalid();
    }

    public boolean assertTimeIsValid() throws SlotTimeInvalid {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        int fromHour = calendar.get(Calendar.HOUR_OF_DAY);
        int fromMinute = calendar.get(Calendar.MINUTE);

        calendar.setTime(to);
        int toHour = calendar.get(Calendar.HOUR_OF_DAY);
        int toMinute = calendar.get(Calendar.MINUTE);

        if (isHourValid(fromHour, fromMinute) & isHourValid(toHour, toMinute)) {
            return true;
        }

        throw new SlotTimeInvalid();
    }

    private boolean isHourValid(int hour, int minute) {
        if (hour < FIRST_HOUR_BOOKABLE) {
            return false;
        }

        if ((hour > LAST_HOUR_BOOKABLE) | ((hour == LAST_HOUR_BOOKABLE) & minute > 0)) {
            return false;
        }

        return true;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}