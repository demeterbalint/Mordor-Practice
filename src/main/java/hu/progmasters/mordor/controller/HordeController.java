package hu.progmasters.mordor.controller;

import hu.progmasters.mordor.dto.HordeForm;
import hu.progmasters.mordor.dto.HordeListItem;
import hu.progmasters.mordor.service.HordeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}")
    public ResponseEntity updateHorde(@RequestBody HordeForm hordeForm, @PathVariable Integer id) {
        hordeService.updateHorde(id, hordeForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHorde(@PathVariable Integer id) {
        hordeService.deleteHorde(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HordeListItem>> getAllHordes() {
        return new ResponseEntity<>(hordeService.findAll(), HttpStatus.OK);
    }
}
