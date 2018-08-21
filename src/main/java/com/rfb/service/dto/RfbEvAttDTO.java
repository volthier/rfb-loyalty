package com.rfb.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the RfbEvAtt entity.
 */
public class RfbEvAttDTO implements Serializable {

    private Long id;

    private LocalDate attendanceDay;

    private Long rfbUserId;

    private Long rfbEventId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAttendanceDay() {
        return attendanceDay;
    }

    public void setAttendanceDay(LocalDate attendanceDay) {
        this.attendanceDay = attendanceDay;
    }

    public Long getRfbUserId() {
        return rfbUserId;
    }

    public void setRfbUserId(Long rfbUserId) {
        this.rfbUserId = rfbUserId;
    }

    public Long getRfbEventId() {
        return rfbEventId;
    }

    public void setRfbEventId(Long rfbEventId) {
        this.rfbEventId = rfbEventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RfbEvAttDTO rfbEvAttDTO = (RfbEvAttDTO) o;
        if (rfbEvAttDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rfbEvAttDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RfbEvAttDTO{" +
            "id=" + getId() +
            ", attendanceDay='" + getAttendanceDay() + "'" +
            ", rfbUser=" + getRfbUserId() +
            ", rfbEvent=" + getRfbEventId() +
            "}";
    }
}
