package com.rfb.service.mapper;

import com.rfb.domain.*;
import com.rfb.service.dto.RfbEvAttDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RfbEvAtt and its DTO RfbEvAttDTO.
 */
@Mapper(componentModel = "spring", uses = {RfbEventMapper.class, RfbUserMapper.class})
public interface RfbEvAttMapper extends EntityMapper<RfbEvAttDTO, RfbEvAtt> {

    @Mapping(source = "rfbEvent.id", target = "rfbEventId")
    @Mapping(source = "rfbUser.id", target = "rfbUserId")
    RfbEvAttDTO toDto(RfbEvAtt rfbEvAtt);

    @Mapping(source = "rfbEventId", target = "rfbEvent")
    @Mapping(source = "rfbUserId", target = "rfbUser")
    RfbEvAtt toEntity(RfbEvAttDTO rfbEvAttDTO);

    default RfbEvAtt fromId(Long id) {
        if (id == null) {
            return null;
        }
        RfbEvAtt rfbEvAtt = new RfbEvAtt();
        rfbEvAtt.setId(id);
        return rfbEvAtt;
    }
}
