package com.rfb.service.mapper;

import com.rfb.domain.RfbEvAtt;
import com.rfb.service.dto.RfbEvAttDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity RfbEvAtt and its DTO RfbEvAttDTO.
 */
@Mapper(componentModel = "spring", uses = {RfbUserMapper.class, RfbEventMapper.class})
public interface RfbEvAttMapper extends EntityMapper<RfbEvAttDTO, RfbEvAtt> {

    @Mapping(source = "rfbUser", target = "rfbUserId")
    @Mapping(source = "rfbEvent", target = "rfbEventId")
    RfbEvAttDTO toDto(RfbEvAtt rfbEvAtt);

    @Mapping(source = "rfbUserId", target = "rfbUser")
    @Mapping(source = "rfbEventId", target = "rfbEvent")
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
