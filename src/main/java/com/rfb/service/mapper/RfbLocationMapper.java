package com.rfb.service.mapper;

import com.rfb.domain.*;
import com.rfb.service.dto.RfbLocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RfbLocation and its DTO RfbLocationDTO.
 */
@Mapper(componentModel = "spring", uses = {RfbEventMapper.class})
public interface RfbLocationMapper extends EntityMapper<RfbLocationDTO, RfbLocation> {

    @Mapping(source = "rvbEvent.id", target = "rvbEventId")
    RfbLocationDTO toDto(RfbLocation rfbLocation);

    @Mapping(source = "rvbEventId", target = "rvbEvent")
    RfbLocation toEntity(RfbLocationDTO rfbLocationDTO);

    default RfbLocation fromId(Long id) {
        if (id == null) {
            return null;
        }
        RfbLocation rfbLocation = new RfbLocation();
        rfbLocation.setId(id);
        return rfbLocation;
    }
}
