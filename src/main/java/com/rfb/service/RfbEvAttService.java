package com.rfb.service;

import com.rfb.service.dto.RfbEvAttDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RfbEvAtt.
 */
public interface RfbEvAttService {

    /**
     * Save a rfbEvAtt.
     *
     * @param rfbEvAttDTO the entity to save
     * @return the persisted entity
     */
    RfbEvAttDTO save(RfbEvAttDTO rfbEvAttDTO);

    /**
     * Get all the rfbEvAtts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RfbEvAttDTO> findAll(Pageable pageable);


    /**
     * Get the "id" rfbEvAtt.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RfbEvAttDTO> findOne(Long id);

    /**
     * Delete the "id" rfbEvAtt.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
