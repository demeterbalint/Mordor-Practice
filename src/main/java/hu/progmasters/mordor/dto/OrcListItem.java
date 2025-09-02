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

package hu.progmasters.mordor.dto;

import hu.progmasters.mordor.domain.Orc;
import hu.progmasters.mordor.domain.WeaponType;

import java.util.ArrayList;
import java.util.List;

public class OrcListItem {

    private Integer id;
    private String name;
    private String orcRaceType;
    private Integer killCount;
    private List<String> weapons;
    private String hordeName;

    public OrcListItem(Orc orc) {
        this.id = orc.getId();
        this.name = orc.getName();
        this.orcRaceType = orc.getOrcRaceType().getDisplayName();
        this.killCount = orc.getKillCount();
        this.weapons = new ArrayList<>();
        for (WeaponType weaponType : orc.getWeapons()) {
            this.weapons.add(weaponType.getDisplayName());
        }
        this.hordeName = orc.getHordeName();
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

    public String getOrcRaceType() {
        return orcRaceType;
    }

    public void setOrcRaceType(String orcRaceType) {
        this.orcRaceType = orcRaceType;
    }

    public Integer getKillCount() {
        return killCount;
    }

    public void setKillCount(Integer killCount) {
        this.killCount = killCount;
    }

    public List<String> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<String> weapons) {
        this.weapons = weapons;
    }

    public String getHordeName() {
        return hordeName;
    }

    public void setHordeName(String hordeName) {
        this.hordeName = hordeName;
    }
}
