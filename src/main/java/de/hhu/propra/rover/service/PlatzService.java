package de.hhu.propra.rover.service;

import de.hhu.propra.rover.repositories.PlatzRepository;
import org.springframework.stereotype.Service;

@Service
public class PlatzService {

    PlatzRepository platzRepository;

    PlatzService(PlatzRepository platzRepository){
        this.platzRepository = platzRepository;
    }
}
