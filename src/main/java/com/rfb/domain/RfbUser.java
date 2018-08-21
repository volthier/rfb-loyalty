package com.rfb.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A RfbUser.
 */
@Entity
@Table(name = "rfb_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RfbUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @OneToOne
    @JoinColumn(unique = true)
    private RfbLocation rfbLocation;

    @OneToMany(mappedBy = "rfbUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RfbEvAtt> rfbEvAtts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public RfbUser userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RfbLocation getRfbLocation() {
        return rfbLocation;
    }

    public RfbUser rfbLocation(RfbLocation rfbLocation) {
        this.rfbLocation = rfbLocation;
        return this;
    }

    public void setRfbLocation(RfbLocation rfbLocation) {
        this.rfbLocation = rfbLocation;
    }

    public Set<RfbEvAtt> getRfbEvAtts() {
        return rfbEvAtts;
    }

    public RfbUser rfbEvAtts(Set<RfbEvAtt> rfbEvAtts) {
        this.rfbEvAtts = rfbEvAtts;
        return this;
    }

    public RfbUser addRfbEvAtt(RfbEvAtt rfbEvAtt) {
        this.rfbEvAtts.add(rfbEvAtt);
        rfbEvAtt.setRfbUser(this);
        return this;
    }

    public RfbUser removeRfbEvAtt(RfbEvAtt rfbEvAtt) {
        this.rfbEvAtts.remove(rfbEvAtt);
        rfbEvAtt.setRfbUser(null);
        return this;
    }

    public void setRfbEvAtts(Set<RfbEvAtt> rfbEvAtts) {
        this.rfbEvAtts = rfbEvAtts;
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
        RfbUser rfbUser = (RfbUser) o;
        if (rfbUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rfbUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RfbUser{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            "}";
    }
}
