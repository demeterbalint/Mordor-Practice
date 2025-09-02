package hu.progmasters.mordor.controller;

import hu.progmasters.mordor.dto.HordeForm;
import hu.progmasters.mordor.service.HordeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/hordes")
@CrossOrigin
public class HordeController {

    private HordeService hordeService;

    @Autowired
    public HordeController(HordeService hordeService) {
        this.hordeService = hordeService;
    }

    @PostMapping
    public ResponseEntity saveHorde(@RequestBody HordeForm hordeForm) {
        hordeService.saveHorde(hordeForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
