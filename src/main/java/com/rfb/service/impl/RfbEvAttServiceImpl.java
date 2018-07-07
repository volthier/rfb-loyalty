package com.rfb.service.impl;

import com.rfb.service.RfbEvAttService;
import com.rfb.domain.RfbEvAtt;
import com.rfb.repository.RfbEvAttRepository;
import com.rfb.service.dto.RfbEvAttDTO;
import com.rfb.service.mapper.RfbEvAttMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing RfbEvAtt.
 */
@Service
@Transactional
public class RfbEvAttServiceImpl implements RfbEvAttService {

    private final Logger log = LoggerFactory.getLogger(RfbEvAttServiceImpl.class);

    private final RfbEvAttRepository rfbEvAttRepository;

    private final RfbEvAttMapper rfbEvAttMapper;

    public RfbEvAttServiceImpl(RfbEvAttRepository rfbEvAttRepository, RfbEvAttMapper rfbEvAttMapper) {
        this.rfbEvAttRepository = rfbEvAttRepository;
        this.rfbEvAttMapper = rfbEvAttMapper;
    }

    /**
     * Save a rfbEvAtt.
     *
     * @param rfbEvAttDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RfbEvAttDTO save(RfbEvAttDTO rfbEvAttDTO) {
        log.debug("Request to save RfbEvAtt : {}", rfbEvAttDTO);
        RfbEvAtt rfbEvAtt = rfbEvAttMapper.toEntity(rfbEvAttDTO);
        rfbEvAtt = rfbEvAttRepository.save(rfbEvAtt);
        return rfbEvAttMapper.toDto(rfbEvAtt);
    }

    /**
     * Get all the rfbEvAtts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RfbEvAttDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RfbEvAtts");
        return rfbEvAttRepository.findAll(pageable)
            .map(rfbEvAttMapper::toDto);
    }


    /**
     * Get one rfbEvAtt by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RfbEvAttDTO> findOne(Long id) {
        log.debug("Request to get RfbEvAtt : {}", id);
        return rfbEvAttRepository.findById(id)
            .map(rfbEvAttMapper::toDto);
    }

    /**
     * Delete the rfbEvAtt by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RfbEvAtt : {}", id);
        rfbEvAttRepository.deleteById(id);
    }
}
