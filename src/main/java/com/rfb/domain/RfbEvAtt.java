package com.rfb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A RfbEvAtt.
 */
@Entity
@Table(name = "rfb_ev_att")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RfbEvAtt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "attendance_day")
    private LocalDate attendanceDay;

    @ManyToOne
    @JsonIgnoreProperties("rfbEvAtts")
    private RfbUser rfbUser;

    @ManyToOne
    @JsonIgnoreProperties("rfbEvAtts")
    private RfbEvent rfbEvent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAttendanceDay() {
        return attendanceDay;
    }

    public RfbEvAtt attendanceDay(LocalDate attendanceDay) {
        this.attendanceDay = attendanceDay;
        return this;
    }

    public void setAttendanceDay(LocalDate attendanceDay) {
        this.attendanceDay = attendanceDay;
    }

    public RfbUser getRfbUser() {
        return rfbUser;
    }

    public RfbEvAtt rfbUser(RfbUser rfbUser) {
        this.rfbUser = rfbUser;
        return this;
    }

    public void setRfbUser(RfbUser rfbUser) {
        this.rfbUser = rfbUser;
    }

    public RfbEvent getRfbEvent() {
        return rfbEvent;
    }

    public RfbEvAtt rfbEvent(RfbEvent rfbEvent) {
        this.rfbEvent = rfbEvent;
        return this;
    }

    public void setRfbEvent(RfbEvent rfbEvent) {
        this.rfbEvent = rfbEvent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RfbEvAtt rfbEvAtt = (RfbEvAtt) o;
        if (rfbEvAtt.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rfbEvAtt.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RfbEvAtt{" +
            "id=" + getId() +
            ", attendanceDay='" + getAttendanceDay() + "'" +
            "}";
    }
}
