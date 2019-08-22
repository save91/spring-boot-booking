package tosi.saverio.booking.dto;

import java.util.Date;

public class ReservationDTO {
    private Long userId;
    private Long courtId;
    private Date from;
    private Date to;

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

}