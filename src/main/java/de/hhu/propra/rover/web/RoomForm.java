package de.hhu.propra.rover.web;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record RoomForm (
    @NotBlank(message = "Darf nicht leer sein") String roomName,
    @Min(value = 1, message = "muss mindestens 1 sein") Integer anzahlPlaetze,
    @NotBlank(message = "Darf nicht leer sein") String ausstattung){
}
