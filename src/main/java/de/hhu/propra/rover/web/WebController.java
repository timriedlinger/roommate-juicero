package de.hhu.propra.rover.web;

import de.hhu.propra.rover.data.Platz;
import de.hhu.propra.rover.data.Room;
import de.hhu.propra.rover.service.RoomService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionScope
public class WebController {

    private final RoomService roomService;

    public WebController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/")
    public String main(Model model, @AuthenticationPrincipal OAuth2User principal) {
        model.addAttribute("user",
                principal != null ? principal.getAttribute("login") : null
        );
        List<Room> rooms = roomService.getRooms();
        model.addAttribute("rooms", rooms);
        return "mainpage";
    }

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public String admin() {
        return "admin";
    }

    @GetMapping("/addroom")
    public String addRoom(RoomForm roomForm){
        return "addroom";
    }

    @PostMapping("/addroom")
    public String addRoom(Model model, @Valid RoomForm roomForm, RedirectAttributes redirectAttributes, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "addroom";
        }
        List<Platz> platzList = new ArrayList<>();
        for (int i = 1; i <= roomForm.anzahlPlaetze(); i++){
            Platz platz = new Platz(roomForm.ausstattung(), i, true, roomForm.roomName());
            platzList.add(platz);
        }
        Room room = new Room(roomForm.anzahlPlaetze(), platzList, roomForm.roomName());
        roomService.addRoom(room);
        redirectAttributes.addAttribute("sucess", "Raum erfolgreich hinzugefügt");
        return "redirect:/";
    }
    @GetMapping("/deleteroom")
    public String deleteRoom(RoomFormDelete roomFormDelete) {return "deleteroom";}

    @PostMapping("/deleteroom")
    public String deleteRoom(Model model,@Valid RoomFormDelete roomFormDelete, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "deleteroom";
        }
        if(roomFormDelete.roomName() != null && roomFormDelete.anzahlPlaetze() == null && roomFormDelete.ausstattung().isEmpty()) {
            roomService.deleteRoom(roomFormDelete.roomName());
        } else if(roomFormDelete.roomName() != null && roomFormDelete.anzahlPlaetze() != null && roomFormDelete.ausstattung().isEmpty()) {
            roomService.deletePlatz(roomFormDelete.roomName(), roomFormDelete.anzahlPlaetze());
        } else if (roomFormDelete.roomName() != null && roomFormDelete.anzahlPlaetze() != null && roomFormDelete.ausstattung() != null) {
            roomService.addPlatz(roomFormDelete.roomName(), roomFormDelete.anzahlPlaetze(), roomFormDelete.ausstattung());
        }
        return "redirect:/";
    }

    @GetMapping ("/room/{raumName}")
    public String roomDetails(Model model, @PathVariable String raumName) {
        model.addAttribute("room", roomService.getByNumber(raumName));
        return "room";
    }
    @PostMapping("/buchen")
    public String buchen(Model model, @RequestParam("roomNumber") String raumName, @RequestParam("platznummer") Integer platznummer){
        model.addAttribute("room", roomService.getByNumber(raumName));
        roomService.buchen(raumName, platznummer);
        return "room";
    }
}


