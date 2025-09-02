/*
 * Copyright © Progmasters (QTC Kft.), 2018.
 * All rights reserved. No part or the whole of this Teaching Material (TM) may be reproduced, copied, distributed,
 * publicly performed, disseminated to the public, adapted or transmitted in any form or by any means, including
 * photocopying, recording, or other electronic or mechanical methods, without the prior written permission of QTC Kft.
 * This TM may only be used for the purposes of teaching exclusively by QTC Kft. and studying exclusively by QTC Kft.’s
 * students and for no other purposes by any parties other than QTC Kft.
 * This TM shall be kept confidential and shall not be made public or made available or disclosed to any unauthorized person.
 * Any dispute or claim arising out of the breach of these provisions shall be governed by and construed in accordance with the laws of Hungary.
 */

package hu.progmasters.mordor.controller;

import hu.progmasters.mordor.domain.Orc;
import hu.progmasters.mordor.domain.OrcRaceType;
import hu.progmasters.mordor.domain.WeaponType;
import hu.progmasters.mordor.dto.OrcDetails;
import hu.progmasters.mordor.dto.OrcForm;
import hu.progmasters.mordor.dto.OrcListItem;
import hu.progmasters.mordor.service.OrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/orcs")
@CrossOrigin
public class OrcController {

    private OrcService orcService;

    @Autowired
    public OrcController(OrcService orcService) {
        this.orcService = orcService;
    }

    @ResponseBody
    @GetMapping
    public List<OrcListItem> listAll() {
        return orcService.findAll();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<OrcDetails> findById(@PathVariable Integer id) {
        OrcDetails orcDetails = orcService.findById(id);
        return new ResponseEntity<>(orcDetails, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/races")
    public Map<OrcRaceType, String> listRaceTypes() {
        Map<OrcRaceType, String> raceTypes = new HashMap<>();
        for (OrcRaceType orcRaceType : OrcRaceType.values()) {
            raceTypes.put(orcRaceType, orcRaceType.getDisplayName());
        }
        return raceTypes;
    }

    @ResponseBody
    @GetMapping("/weapons")
    public Map<WeaponType, String> listWeapons() {
        Map<WeaponType, String> weaponList = new HashMap<>();
        for (WeaponType weaponType : WeaponType.values()) {
            weaponList.put(weaponType, weaponType.getDisplayName());
        }
        return weaponList;
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity saveOrc(@RequestBody OrcForm orcForm) {
        orcService.save(orcForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity removeOrc(@PathVariable Integer id) {
        orcService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<Orc> modifyOrc(@RequestBody OrcForm orcForm, @PathVariable Integer id) {
        orcService.update(id, orcForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
