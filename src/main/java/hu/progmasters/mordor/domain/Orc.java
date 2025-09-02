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

package hu.progmasters.mordor.domain;

import hu.progmasters.mordor.dto.OrcForm;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Orc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private OrcRaceType orcRaceType;

    private Integer killCount;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = WeaponType.class, fetch = FetchType.EAGER)
    private List<WeaponType> weapons = new ArrayList<>();

    private String hordeName;

    public Orc(OrcForm orcForm) {
        this.name = orcForm.getName();
        this.killCount = orcForm.getKillCount();
        this.orcRaceType = OrcRaceType.valueOf(orcForm.getOrcRaceType());
        for (String weapon : orcForm.getWeapons()) {
            weapons.add(WeaponType.valueOf(weapon));
        }
        this.hordeName = orcForm.getHordeName();
    }

    public Orc() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrcRaceType getOrcRaceType() {
        return orcRaceType;
    }

    public void setOrcRaceType(OrcRaceType orcRaceType) {
        this.orcRaceType = orcRaceType;
    }

    public Integer getKillCount() {
        return killCount;
    }

    public void setKillCount(Integer killCount) {
        this.killCount = killCount;
    }

    public List<WeaponType> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<WeaponType> weapons) {
        this.weapons = weapons;
    }

    public String getHordeName() {
        return hordeName;
    }

    public void setHordeName(String hordeName) {
        this.hordeName = hordeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orc orc = (Orc) o;

        return id != null ? id.equals(orc.id) : orc.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
