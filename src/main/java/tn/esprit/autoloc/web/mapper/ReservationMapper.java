package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.web.dto.ReservationResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "client.nom", target = "nomClient")
    @Mapping(source = "client.prenom", target = "prenomClient")
    @Mapping(source = "vehicule.marque", target = "marqueVehicule")
    @Mapping(source = "vehicule.modele", target = "modeleVehicule")
    @Mapping(source = "vehicule.immatriculation", target = "immatriculationVehicule")
    ReservationResponseDto toDto(Reservation reservation);

    List<ReservationResponseDto> toDtoList(List<Reservation> reservations);
}