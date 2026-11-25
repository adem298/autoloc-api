package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.web.dto.ClientRequestDto;
import tn.esprit.autoloc.web.dto.ClientResponseDto;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "idClient", ignore = true)
    @Mapping(target = "numPermis", ignore = true)
    @Mapping(target = "dateInscription", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Client toEntity(ClientRequestDto dto);

    ClientResponseDto toResponseDto(Client client);
}