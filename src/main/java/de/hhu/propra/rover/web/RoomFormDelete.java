package de.hhu.propra.rover.web;

import javax.validation.constraints.NotBlank;

public record RoomFormDelete(
        @NotBlank(message = "Mindestangabe zum Löschen") String roomName,
        Integer anzahlPlaetze,
        String ausstattung){
}
