package com.rfb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A RfbEvent.
 */
@Entity
@Table(name = "rfb_event")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RfbEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "event_code")
    private String eventCode;

    @OneToMany(mappedBy = "rfbEvent")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RfbEvAtt> rfbEvAtts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("rfbEvents")
    private RfbLocation rfbLocation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public RfbEvent eventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventCode() {
        return eventCode;
    }

    public RfbEvent eventCode(String eventCode) {
        this.eventCode = eventCode;
        return this;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public Set<RfbEvAtt> getRfbEvAtts() {
        return rfbEvAtts;
    }

    public RfbEvent rfbEvAtts(Set<RfbEvAtt> rfbEvAtts) {
        this.rfbEvAtts = rfbEvAtts;
        return this;
    }

    public RfbEvent addRfbEvAtt(RfbEvAtt rfbEvAtt) {
        this.rfbEvAtts.add(rfbEvAtt);
        rfbEvAtt.setRfbEvent(this);
        return this;
    }

    public RfbEvent removeRfbEvAtt(RfbEvAtt rfbEvAtt) {
        this.rfbEvAtts.remove(rfbEvAtt);
        rfbEvAtt.setRfbEvent(null);
        return this;
    }

    public void setRfbEvAtts(Set<RfbEvAtt> rfbEvAtts) {
        this.rfbEvAtts = rfbEvAtts;
    }

    public RfbLocation getRfbLocation() {
        return rfbLocation;
    }

    public RfbEvent rfbLocation(RfbLocation rfbLocation) {
        this.rfbLocation = rfbLocation;
        return this;
    }

    public void setRfbLocation(RfbLocation rfbLocation) {
        this.rfbLocation = rfbLocation;
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
        RfbEvent rfbEvent = (RfbEvent) o;
        if (rfbEvent.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rfbEvent.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RfbEvent{" +
            "id=" + getId() +
            ", eventDate='" + getEventDate() + "'" +
            ", eventCode='" + getEventCode() + "'" +
            "}";
    }
}
