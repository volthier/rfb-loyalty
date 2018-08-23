package com.rfb.service.mapper;

import com.rfb.domain.RfbUser;
import com.rfb.service.dto.RfbUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity RfbUser and its DTO RfbUserDTO.
 */
@Mapper(componentModel = "spring", uses = {RfbLocationMapper.class})
public interface RfbUserMapper extends EntityMapper<RfbUserDTO, RfbUser> {

    @Mapping(source = "rfbLocation.id", target = "rfbLocationId")
    RfbUserDTO toDto(RfbUser rfbUser);

    @Mapping(source = "rfbLocationId", target = "rfbLocation")
    @Mapping(target = "rfbEvAtts", ignore = true)
    RfbUser toEntity(RfbUserDTO rfbUserDTO);

    default RfbUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        RfbUser rfbUser = new RfbUser();
        rfbUser.setId(id);
        return rfbUser;
    }
}
