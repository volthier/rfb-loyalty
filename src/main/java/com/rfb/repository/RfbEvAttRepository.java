package com.rfb.repository;

import com.rfb.domain.RfbEvAtt;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RfbEvAtt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RfbEvAttRepository extends JpaRepository<RfbEvAtt, Long> {

}
